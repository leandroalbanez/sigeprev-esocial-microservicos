import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import DefaultNamespace.AssinaturaProxy;

public class AssinaturaClientIprev {

	public static void main(String[] args) throws SQLException, IOException {
		AssinaturaProxy proxy = new AssinaturaProxy();
			
		final String XML_ESOCIAL = "<eSocial xmlns=\"http://www.esocial.gov.br/schema/evt/evtTabLotacao/v_S_01_00_00\">\r\n" + 
				"	<evtTabLotacao Id=\"ID1087172990000002021111918334000001\">\r\n" + 
				"		<ideEvento>\r\n" + 
				"			<tpAmb>1</tpAmb>\r\n" + 
				"			<procEmi>1</procEmi>\r\n" + 
				"			<verProc>1</verProc>\r\n" + 
				"		</ideEvento>\r\n" + 
				"		<ideEmpregador>\r\n" + 
				"			<tpInsc>1</tpInsc>\r\n" + 
				"			<nrInsc>08717299</nrInsc>\r\n" + 
				"		</ideEmpregador>\r\n" + 
				"		<infoLotacao>\r\n" + 
				"			<inclusao>\r\n" + 
				"				<ideLotacao>\r\n" + 
				"					<codLotacao>IPREVGERAL</codLotacao>\r\n" + 
				"					<iniValid>2021-07</iniValid>\r\n" + 
				"				</ideLotacao>\r\n" + 
				"				<dadosLotacao>\r\n" + 
				"					<tpLotacao>01</tpLotacao>\r\n" + 
				"					<fpasLotacao>\r\n" + 
				"						<fpas>582</fpas>\r\n" + 
				"						<codTercs>0000</codTercs>\r\n" + 
				"					</fpasLotacao>\r\n" + 
				"				</dadosLotacao>\r\n" + 
				"			</inclusao>\r\n" + 
				"		</infoLotacao>\r\n" + 
				"	</evtTabLotacao>\r\n" + 
				"</eSocial>" ; 
//		Assinatura assinatura = new Assinatura();
//		String retorno = assinatura.getAssinatura(XML_ESOCIAL);
//		System.out.println(retorno);
		long tmpInicio = System.currentTimeMillis();
		proxy.setEndpoint("https://sigeprev.spprev.sp.gov.br/assinaturahmg/services/Assinatura");
//		proxy.setEndpoint("http://localhost:8080/AssinaturaWS/services/Assinatura");
		System.out.println(proxy.getAssinatura(XML_ESOCIAL));
		long tmpFim = System.currentTimeMillis();
		printInFile("TESTES");
		System.out.println(tmpFim-tmpInicio);
	}

	public static void printInFile(String string) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true));
		writer.append(string + "\n");
		writer.close();
	}
}
