
import java.io.IOException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;

public class Assinatura {
	PrivateKey privateKey;
	
	private void loadCertificates(String senha, XMLSignatureFactory signatureFactory) throws Exception {

		/**
		 * Para Certificados A3 Cartao usar: SmartCard.cfg; 
		 * Para Certificados A3 Token  usar: Token.cfg;
		 */
		Provider provider = new sun.security.pkcs11.SunPKCS11("SmartCard.cfg");
		Security.addProvider(provider);

		KeyStore ks = KeyStore.getInstance("pkcs11", provider);
		try {
			ks.load(null, senha.toCharArray());
		} catch (IOException e) {
			throw new Exception("Senha do Certificado Digital incorreta ou Certificado inválido.");
		}

		KeyStore.PrivateKeyEntry pkEntry = null;
		Enumeration<String> aliasesEnum = ks.aliases();
		
		while (aliasesEnum.hasMoreElements()) {
			String alias = (String) aliasesEnum.nextElement();
			if (ks.isKeyEntry(alias)) {
				pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias,
						new KeyStore.PasswordProtection(senha.toCharArray()));
				privateKey = pkEntry.getPrivateKey();
				break;
			}
		}

		X509Certificate cert = (X509Certificate) pkEntry.getCertificate();
		System.out.println("SubjectDN: " + cert.getSubjectDN().toString());

		KeyInfoFactory keyInfoFactory = signatureFactory.getKeyInfoFactory();
		List<X509Certificate> x509Content = new ArrayList<X509Certificate>();

		x509Content.add(cert);
		X509Data x509Data = keyInfoFactory.newX509Data(x509Content);
		KeyInfo keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(x509Data));
	}
	
	 public String assinaEnviNFe(String xml, String senha) throws Exception {  
	        Document document = documentFactory(xml);    

	        XMLSignatureFactory signatureFactory = XMLSignatureFactory.getInstance("DOM");    

	        ArrayList<Transform> transformList = signatureFactory(signatureFactory);    

	        loadCertificates(senha, signatureFactory);    

	        for (int i = 0; i < document.getDocumentElement().getElementsByTagName(NFE).getLength(); i++) {    
	            try {  
	                assinarNFe(signatureFactory, transformList, privateKey, keyInfo, document, i);    
	            } catch(Exception e)  {  
	                continue;  
	            }  
	        }    

	        return outputXML(document);  
	    } 
	 
	 private void assinarNFe(XMLSignatureFactory fac, ArrayList<Transform> transformList, PrivateKey privateKey, KeyInfo ki, Document document, int indexNFe) throws Exception {  

	        NodeList elements = document.getElementsByTagName("infNFe");  
	        org.w3c.dom.Element el = (org.w3c.dom.Element) elements.item(indexNFe);  
	        String id = el.getAttribute("Id");  

	        Reference ref = fac.newReference("#" + id,  
	                fac.newDigestMethod(DigestMethod.SHA1, null), transformList,  
	                null, null);  

	        SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(  
	                CanonicalizationMethod.INCLUSIVE,  
	                (C14NMethodParameterSpec) null), fac  
	                .newSignatureMethod(SignatureMethod.RSA_SHA1, null),  
	                Collections.singletonList(ref));  

	        XMLSignature signature = fac.newXMLSignature(si, ki);  

	        DOMSignContext dsc = new DOMSignContext(privateKey, document.getDocumentElement().getElementsByTagName(NFE).item(indexNFe));  
	        signature.sign(dsc);  
	    } 
}
