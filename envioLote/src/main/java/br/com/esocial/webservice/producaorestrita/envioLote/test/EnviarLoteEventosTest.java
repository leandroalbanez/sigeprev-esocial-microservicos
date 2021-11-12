package br.com.esocial.webservice.producaorestrita.envioLote.test;

import java.util.Map;

import javax.xml.bind.JAXBException;

import br.com.atlantic.comum.dao.daogenerico.DAOGenerico;
import br.com.esocial.webservice.producaorestrita.envioLote.EnviarLoteEventos.LoteEventos;
import br.com.esocial.webservice.producaorestrita.envioLote.EnviarLoteEventosResponse.EnviarLoteEventosResult;
import br.com.esocial.webservice.producaorestrita.envioLote.ServicoEnviarLoteEventos;
import br.com.esocial.webservice.producaorestrita.envioLote.ServicoEnviarLoteEventos_Service;
import br.com.esocial.webservice.util.EsocialUtil;

public class EnviarLoteEventosTest {
	public static void main(String[] args) throws Exception {
		int i=0;
		for (String arg : args) {
			System.out.println("args["+i+"] : " + arg);	
		}
		
		Map<String, String> map = DAOGenerico.executeQueryForUniqueValue("SELECT NUM_CPF AS RESULTADO FROM TB_PESSOA_FISICA PF WHERE ROWNUM = 1 ");
		System.out.println("Resultado: " + map.get("RESULTADO"));

		ServicoEnviarLoteEventos_Service service = new ServicoEnviarLoteEventos_Service();
		ServicoEnviarLoteEventos enviarLote = service.getWsEnviarLoteEventos();
		LoteEventos loteEventos = new LoteEventos();
		loteEventos.setAny(EsocialUtil.toDocument(XML_123).getDocumentElement());
		EnviarLoteEventosResult result = enviarLote.enviarLoteEventos(loteEventos);
		
		System.out.println(EsocialUtil.toObject(result));
	}	
	
	public static void test() throws JAXBException {
		ServicoEnviarLoteEventos_Service service = new ServicoEnviarLoteEventos_Service();
		ServicoEnviarLoteEventos enviarLote = service.getWsEnviarLoteEventos();
		LoteEventos loteEventos = new LoteEventos();
		loteEventos.setAny(EsocialUtil.toDocument(XML_123).getDocumentElement());
		EnviarLoteEventosResult result = enviarLote.enviarLoteEventos(loteEventos);
		
		System.out.println(EsocialUtil.toObject(result));
	}
	
	public static final String XML_123 =
			"<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + 
			"<eSocial xmlns=\"http://www.esocial.gov.br/schema/lote/eventos/envio/v1_1_1\">\r\n" + 
			"<envioLoteEventos grupo=\"1\">\r\n" + 
			"<ideEmpregador>\r\n" + 
			"<tpInsc>1</tpInsc>\r\n" + 
			"<nrInsc>09041213</nrInsc>\r\n" + 
			"</ideEmpregador>\r\n" + 
			"<ideTransmissor>\r\n" + 
			"<tpInsc>1</tpInsc>\r\n" + 
			"<nrInsc>09041213000136</nrInsc>\r\n" + 
			"</ideTransmissor>\r\n" + 
			"<eventos>\r\n" + 
			"<evento Id=\"ID1090412130000002021102110334000001\">" +

			//INÍCIO BLOCO DO EVENTO ASSSINADO
			
			"<eSocial xmlns=\"http://www.esocial.gov.br/schema/evt/evtInfoEmpregador/v_S_01_00_00\">  \r\n" + 
			"	<evtInfoEmpregador Id=\"ID1090412130000002021102110334000001\">  \r\n" + 
			"		<ideEvento>  \r\n" + 
			"			<tpAmb>2</tpAmb>  \r\n" + 
			"			<procEmi>1</procEmi>  \r\n" + 
			"			<verProc>1</verProc>  \r\n" + 
			"		</ideEvento>  \r\n" + 
			"		<ideEmpregador>  \r\n" + 
			"			<tpInsc>1</tpInsc>  \r\n" + 
			"			<nrInsc>09041213</nrInsc>  \r\n" + 
			"		</ideEmpregador>  \r\n" + 
			"		<infoEmpregador>  \r\n" + 
			"			<exclusao>\r\n" + 
			"               <idePeriodo>\r\n" + 
			"                 <iniValid>2021-07</iniValid>\r\n" + 
			"               </idePeriodo>\r\n" + 
			"			</exclusao>\r\n" + 
			"		</infoEmpregador>  \r\n" + 
			"	</evtInfoEmpregador>  \r\n" + 
			"<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/><SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#rsa-sha256\"/><Reference URI=\"\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/><Transform Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/><DigestValue>s5zmoAsGQbko2d4F1cX5AkOgW5oilMWxPTeIcvK8zjo=</DigestValue></Reference></SignedInfo><SignatureValue>mIVS8Z1qSks6/sCW6jX4I+5KPHj6+2I1XVJrcTLPivPST/5SLKglGHi/tTjY0ggqn00r+tp5Vs5Q\r\n" + 
			"F+bCbaExAOxiBMQsza+2yHD4oKcir2UK4nBIGRFjmoSokaditwXPZoAZekVDbOZL6rFiYRgXX9PU\r\n" + 
			"WU+tdtPtA81+uZkSS1abluKqnL8Y2pTHqxTfWl+7BT9IEbiuJVLj2LxHkgeQGHK6cyhxf/0Dx5ml\r\n" + 
			"sA1NzmZEN1eR7tYtBpzb7WoU2mfpwRBedibMyElztHxgC0dS/JFzWOsIEZ1yv61yVaDpBcfGH/qh\r\n" + 
			"bWYtgz7XhMR6dMOTyNq2OlY7FHS1k/ZDoUux0w==</SignatureValue><KeyInfo><X509Data><X509Certificate>MIIH4TCCBcmgAwIBAgIJASDOsUP0JYQRMA0GCSqGSIb3DQEBCwUAMIGCMQswCQYDVQQGEwJCUjET\r\n" + 
			"MBEGA1UEChMKSUNQLUJyYXNpbDE2MDQGA1UECxMtU2VjcmV0YXJpYSBkYSBSZWNlaXRhIEZlZGVy\r\n" + 
			"YWwgZG8gQnJhc2lsIC0gUkZCMSYwJAYDVQQDEx1BQyBJbXByZW5zYSBPZmljaWFsIFNQIFJGQiBH\r\n" + 
			"NTAeFw0yMTAyMTAxNDA2MjRaFw0yMjAyMTAxNDA2MjRaMIH3MQswCQYDVQQGEwJCUjETMBEGA1UE\r\n" + 
			"ChMKSUNQLUJyYXNpbDELMAkGA1UECBMCU1AxEjAQBgNVBAcTCVNhbyBQYXVsbzE2MDQGA1UECxMt\r\n" + 
			"U2VjcmV0YXJpYSBkYSBSZWNlaXRhIEZlZGVyYWwgZG8gQnJhc2lsIC0gUkZCMRYwFAYDVQQLEw1S\r\n" + 
			"RkIgZS1DTlBKIEExMRMwEQYDVQQLEwpwcmVzZW5jaWFsMRcwFQYDVQQLEw4xMTczNTIzNjAwMDE5\r\n" + 
			"MjE0MDIGA1UEAxMrU0FPIFBBVUxPIFBSRVZJREVOQ0lBIFNQUFJFVjowOTA0MTIxMzAwMDEzNjCC\r\n" + 
			"ASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBANaPFMui6FAwuUrQ/x+4pKG+TcV+mgv0O8Dk\r\n" + 
			"Wves5ITJj7LmkyibDZUHOlR27d0+O2Ra0Mw63OyqUbgwRu6bGYQAhhLVVz315NdGqq3/lpwZCuLD\r\n" + 
			"zzHWsDUwF/k50DaD8ROU53m8iL7BEznQqaWWxMJZUHUhIjTXEN+YLS7hWKRpH3Dxbf06v4fLcHBO\r\n" + 
			"EeC/r4AHu6Mqz2+EZMDVXupGk7+9cTPEW/byOT87VI1p3PErwzKvdGgHzClfm7WUebKtHJ9+pnbd\r\n" + 
			"RJMGgYryxmc/MhiRmUwT0ScfF/BIAQ+ltOnKpiNBI1ZZK1dOtfqWVLk2jCp46L0RU7vJ9Qorm+0D\r\n" + 
			"9DUCAwEAAaOCAuEwggLdMA4GA1UdDwEB/wQEAwIF4DCBpwYIKwYBBQUHAQEEgZowgZcwNwYIKwYB\r\n" + 
			"BQUHMAGGK2h0dHA6Ly9pby1vY3NwLWljcGJyLmltcHJlbnNhb2ZpY2lhbC5jb20uYnIwXAYIKwYB\r\n" + 
			"BQUHMAKGUGh0dHA6Ly9pby1jb20taWNwYnIuaW1wcmVuc2FvZmljaWFsLmNvbS5ici9yZXBvc2l0\r\n" + 
			"b3Jpby9JTUVTUFJGQi9BQ0lNRVNQUkZCRzUucDdiMB8GA1UdIwQYMBaAFEr/viv/WJqH/i3Nc/Qm\r\n" + 
			"RjLtsq5iMGIGA1UdIARbMFkwVwYGYEwBAgEUME0wSwYIKwYBBQUHAgEWP2h0dHA6Ly9pby1jb20t\r\n" + 
			"aWNwYnIuaW1wcmVuc2FvZmljaWFsLmNvbS5ici9yZXBvc2l0b3Jpby9JTUVTUFJGQjAJBgNVHRME\r\n" + 
			"AjAAMIGyBgNVHR8EgaowgacwVqBUoFKGUGh0dHA6Ly9pby1jb20taWNwYnIuaW1wcmVuc2FvZmlj\r\n" + 
			"aWFsLmNvbS5ici9yZXBvc2l0b3Jpby9JTUVTUFJGQi9BQ0lNRVNQUkZCRzUuY3JsME2gS6BJhkdo\r\n" + 
			"dHRwOi8vbGNyLmltcHJlbnNhb2ZpY2lhbC5jb20uYnIvcmVwb3NpdG9yaW8vSU1FU1BSRkIvQUNJ\r\n" + 
			"TUVTUFJGQkc1LmNybDCBsAYDVR0RBIGoMIGlgRJqcm1vcmFlc0BzcC5nb3YuYnKgOAYFYEwBAwSg\r\n" + 
			"LwQtMjkwMzE5NTE1MTkwNzQ4ODgwNDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwoCEGBWBMAQMC\r\n" + 
			"oBgEFkpPU0UgUk9CRVJUTyBERSBNT1JBRVOgGQYFYEwBAwOgEAQOMDkwNDEyMTMwMDAxMzagFwYF\r\n" + 
			"YEwBAwegDgQMMDAwMDAwMDAwMDAwMCkGA1UdJQQiMCAGCCsGAQUFBwMCBggrBgEFBQcDBAYKKwYB\r\n" + 
			"BAGCNxQCAjANBgkqhkiG9w0BAQsFAAOCAgEAI4hr9CBJPty3D/r4I1fv5IpUoGrZgIgHjjPwl+3A\r\n" + 
			"lgIA3ABosplF7SXpwmp2jo0/9muzOjo6YJLPVGdCSGv/1ypgaqoJ4pxXIHGTHXdFfzxen2K09WT3\r\n" + 
			"lypnL8r22ZFJoiULtlwn1jAQyinuSk8ah/C0yjg1yWsDMo3bv3JOLQX96f9e/+eRRAVsDey6zqBV\r\n" + 
			"c++s2j3sGMNoKeYmYJ+GLExGPPshyQlGBquaf1ow0kVegtyntS5tbXbDtA3lNTiU4R8RqMZEjw+j\r\n" + 
			"pO44AHMp4zBdFDxNOd/LvLHr2SHputFrZTYoZCsAQCCuH5VRb0z9bp5Kcds/SD8IXdrr+Xts2OUe\r\n" + 
			"1ohOgwuz3WSkwxOWt9VmeNFNXmCSwRuW2yHlRAYuRb/VNO0achjGu9lwiCnm/aJTygMqgqknoJhB\r\n" + 
			"zBDjlEcK6yi+AvzKKwZxtQ8LMIxjFhyFClT0HocgEkuWg6/Efadrg4Ya1laNaDqkENljCJepw6Ie\r\n" + 
			"Eda1A790hF8eAGQYfr8xfmEuGYu4Y7n+mttdKLL3RTwx7Qyl049eX9xLUlstPuK9PMiMz+Rj6NP3\r\n" + 
			"kOv5kgjLqYHNu58tddvZMZ4cW26lhWilD/KVSolQXr/2mleoWrZAwt8jF8MIp0/VPXZsUeEQ2WAs\r\n" + 
			"OEtBzeryWsVaJ/OfRYfp/oF8b1vZ4sKvTms=</X509Certificate></X509Data></KeyInfo></Signature></eSocial>" +
			
			
			
			
			
			//FIM BLOCO DO EVENTO ASSSINADO
			"</evento>" +
			"</eventos>\r\n" + 
			"</envioLoteEventos>\r\n" + 
			"</eSocial>";
}
