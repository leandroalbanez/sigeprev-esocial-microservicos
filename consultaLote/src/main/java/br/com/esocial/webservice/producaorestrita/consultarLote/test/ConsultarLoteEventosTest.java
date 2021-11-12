package br.com.esocial.webservice.producaorestrita.consultarLote.test;

import java.util.Map;

import br.com.atlantic.comum.dao.daogenerico.DAOGenerico;
import br.com.esocial.webservice.producaorestrita.consultarLote.ConsultarLoteEventos.Consulta;
import br.com.esocial.webservice.producaorestrita.consultarLote.ConsultarLoteEventosResponse.ConsultarLoteEventosResult;
import br.com.esocial.webservice.producaorestrita.consultarLote.ServicoConsultarLoteEventos;
import br.com.esocial.webservice.producaorestrita.consultarLote.ServicoConsultarLoteEventos_Service;
import br.com.esocial.webservice.util.EsocialUtil;

public class ConsultarLoteEventosTest {
	
	public static void main(String[] args) throws Exception {		
		int i=0;
		for (String arg : args) {
			System.out.println("args["+ i++ +"] : " + arg);	
		}
		
		Map<String, String> map = DAOGenerico.executeQueryForUniqueValue("SELECT NUM_CPF AS RESULTADO FROM TB_PESSOA_FISICA PF WHERE ROWNUM = 1 ");
		System.out.println("Resultado: " + map.get("RESULTADO"));
		
		ServicoConsultarLoteEventos_Service service = new ServicoConsultarLoteEventos_Service();
		ServicoConsultarLoteEventos consultarLote = service.getServicosEmpregadorServicoConsultarLoteEventos();
		Consulta consulta = new Consulta();
		consulta.setAny(EsocialUtil.toDocument(XML_CONSULTA).getDocumentElement());
		ConsultarLoteEventosResult result = consultarLote.consultarLoteEventos(consulta);
		
		System.out.println(EsocialUtil.toObject(result));		
	}
	
	public static final String XML_CONSULTA = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + 
			"<eSocial xmlns=\"http://www.esocial.gov.br/schema/lote/eventos/envio/consulta/retornoProcessamento/v1_0_0\" " 
			+ "xsi:schemaLocation=\"http://www.esocial.gov.br/schema/lote/eventos/envio/consulta/retornoProcessamento/v1_0_0 schema.xsd\" "
			+ "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" + 
			"	<consultaLoteEventos>\r\n" + 
			"		<protocoloEnvio>1.2.202110.0000000000090927698</protocoloEnvio>\r\n" + 
			"	</consultaLoteEventos>\r\n" + 
			"</eSocial>";

}
