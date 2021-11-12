import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.security.cert.X509Certificate;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.SignatureMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.sun.org.apache.xml.internal.security.encryption.Reference;

public class AlternativaA3 {
	public static String getAssinaturaString(String xml) {

	    try {
	        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

	        List<Transform> transforms = new ArrayList<>();
	        transforms.add(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null));
	        transforms.add(
	                fac.newTransform("http://www.w3.org/TR/2001/REC-xml-c14n-20010315", (TransformParameterSpec) null));

	        Reference ref = fac.newReference("", fac.newDigestMethod(DigestMethod.SHA256, null), transforms, null,
	                null);

	        // Create the SignedInfo.
	        SignedInfo si = fac.newSignedInfo(
	                fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null),
	                fac.newSignatureMethod("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256",
	                        (SignatureMethodParameterSpec) null),
	                Collections.singletonList(ref));

	        KeyStore ks = KeyStore.getInstance("Windows-MY");
	        ks.load(null, null);
	        KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(CERT_ALIAS,
	                new KeyStore.PasswordProtection(CERT_PASSWORD.toCharArray()));

	        X509Certificate cert = (X509Certificate) keyEntry.getCertificate();

	        KeyInfoFactory kif = fac.getKeyInfoFactory();
	        List<Serializable> x509Content = new ArrayList<Serializable>();

	        x509Content.add(cert);
	        X509Data xd = kif.newX509Data(x509Content);
	        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));

	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        dbf.setNamespaceAware(true);
	        Document doc = dbf.newDocumentBuilder().parse(new InputSource(new StringReader(xml)));

	        DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), doc.getDocumentElement());

	        XMLSignature signature = fac.newXMLSignature(si, ki);

	        signature.sign(dsc); //here show the dialog

	        StringWriter xmlAssinado = new StringWriter();
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer transformer = tf.newTransformer();
	        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

	        transformer.transform(new DOMSource(doc), new StreamResult(xmlAssinado));
	        return xmlAssinado.toString();

	    } catch (Exception e) {
	        e.getStackTrace();
	        return null;
	    }

	}
}
