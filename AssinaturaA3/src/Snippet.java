

public class Snippet {
	**
	     *
	     * @param args
	     * Teste de execu??o com xml exemplo.
	     */
	    public static void main(String[] args) {
	
	        String xmlNaoAssinado = "<?xml version=\"1.0\" encoding=\"utf-8\"?><NFe xmlns=\"http://www.portalfiscal.inf.br/nfe\"><infNFe Id=\"NFe43120103987283000133550010000006241607910981\" versao=\"3.00\"><ide><cUF>43</cUF><cNF>60791098</cNF><natOp>5.101 - VENDA</natOp><indPag>1</indPag><mod>55</mod><serie>1</serie><nNF>624</nNF><dEmi>2012-01-25</dEmi><tpNF>1</tpNF><cMunFG>4313409</cMunFG><tpImp>1</tpImp><tpEmis>1</tpEmis><cDV>1</cDV><tpAmb>2</tpAmb><finNFe>1</finNFe><procEmi>0</procEmi><verProc>2.02</verProc></ide><emit><CNPJ>03987283000133</CNPJ><xNome>MINHA EMPRESA TESTE LTDA.</xNome><xFant>MINHA EMPRESA FANTASIA TESTE</xFant><enderEmit><xLgr>RUA TESTE</xLgr><nro>123</nro><xBairro>CENTRO</xBairro><cMun>4312345</cMun><xMun>PORTO ALEGRE</xMun><UF>RS</UF><CEP>93000000</CEP> <cPais>1058</cPais><xPais>BRASIL</xPais><fone>5111223344</fone></enderEmit><IE>0868689965</IE><CRT>1</CRT></emit><dest><CNPJ>99999999000191</CNPJ><xNome>NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL</xNome><enderDest><xLgr>AV. DOS TESTES</xLgr><nro>202</nro><xBairro>CENTRO</xBairro><cMun>4369854</cMun><xMun>PORTO ALEGRE</xMun><UF>RS</UF><CEP>93000000</CEP><cPais>1058</cPais><xPais>BRASIL</xPais></enderDest><IE>ISENTO</IE></dest><det nItem=\"1\"><prod><cProd>15036</cProd><cEAN/><xProd>PRODUTO TESTE REF 123123</xProd><NCM>84123123</NCM><CFOP>5101</CFOP><uCom>UN</uCom><qCom>1.0000</qCom><vUnCom>590.0000</vUnCom><vProd>590.00</vProd><cEANTrib/><uTrib>UN</uTrib><qTrib>1.0000</qTrib><vUnTrib>590.0000</vUnTrib><indTot>1</indTot></prod><imposto><ICMS><ICMSSN101><orig>0</orig><CSOSN>101</CSOSN><pCredSN>2.82</pCredSN><vCredICMSSN>16.64</vCredICMSSN></ICMSSN101></ICMS><PIS><PISOutr><CST>99</CST><vBC>0.00</vBC><pPIS>0.00</pPIS><vPIS>0.00</vPIS></PISOutr></PIS><COFINS><COFINSOutr><CST>99</CST><vBC>0.00</vBC><pCOFINS>0.00</pCOFINS><vCOFINS>0.00</vCOFINS></COFINSOutr></COFINS></imposto></det><total><ICMSTot><vBC>0.00</vBC><vICMS>0.00</vICMS><vBCST>0.00</vBCST><vST>0.00</vST><vProd>590.00</vProd><vFrete>0.00</vFrete><vSeg>0.00</vSeg><vDesc>0.00</vDesc><vII>0.00</vII><vIPI>0.00</vIPI><vPIS>0.00</vPIS><vCOFINS>0.00</vCOFINS><vOutro>0.00</vOutro><vNF>590.00</vNF></ICMSTot></total><transp><modFrete>1</modFrete></transp><cobr><dup><nDup>624</nDup><dVenc>2013-02-08</dVenc><vDup>590.00</vDup></dup></cobr><infAdic><infCpl>TESTE DE INFORMACOES ADICIONAIS</infCpl></infAdic></infNFe></NFe>";
	        /**
	         * Teste com senha que deve ser passada por par?metro
	         */
	        String password = "21902190";
	        /**
	         * Tipo do certificado que deve ser passada por par?metro.
	         */
	        String tipoCertificado = "A3";
	
	        /**
	         * Modelo do certificado que deve ser passado por par?metro ... a aplica??o tem modelos padr?es que grava na raiz do usu?rio caso n?o exista o arquivo.
	         * Mas o desenvolvedor por tanto criar outros nomes, quando personalizar os arquivos de configura??o. e colocalos na raiz do usu?rio do sistema operacional.
	         * no caso abaixo, se n?o existir o arquivo Smartcard.cfg na pasta raiz, o sistema possui um modelo que aponta para a pasta system32 no caso do windows, e um modelo que aponta para /opt/foo/lib/libpkcs11.so no caso do linux.
	         * em outros sistemas operacionais o responsavel dever? personalizar o arquivo para apontar para a biblioteca pksc11 correspondente.
	         */
	        String modeloCertificado = "Smartcard";
	
	
	        /**
	         * Esse c?digo cria automaticamente o arquivo cacerts na raiz do usu?rio caso o arquivo n?o exista, ? importante sempre procurar adicionar no c?digo os UFs para as quais deseja comunicar-se.
	         * no caso abaixo
	         * ? gerado os cacerts para todos os endere?os a seguir:
	         * get("homnfe.sefaz.am.gov.br", 443, ks);
	            get("hnfe.sefaz.ba.gov.br", 443, ks);
	            get("nfeh.sefaz.ce.gov.br", 443, ks);
	            get("homolog.sefaz.go.gov.br", 443, ks);
	            get("hnfe.fazenda.mg.gov.br", 443, ks);
	            get("homologacao.nfe.ms.gov.br", 443, ks);
	            get("homologacao.sefaz.mt.gov.br", 443, ks);
	            get("nfehomolog.sefaz.pe.gov.br", 443, ks);
	            get("homologacao.nfe2.fazenda.pr.gov.br", 443, ks);
	            get("homologacao.nfe.sefaz.rs.gov.br", 443, ks);
	            get("homologacao.nfe.fazenda.sp.gov.br", 443, ks);
	            get("hom.nfe.fazenda.gov.br", 443, ks);
	            get("hom.sefazvirtual.fazenda.gov.br", 443, ks);
	            get("homologacao.nfe.sefazvirtual.rs.gov.br", 443, ks);
	
	         */
	
	        String codigoEstado = "24";
	        String ambiente = "2";
	
	        File cacerts = GerarCacertsNfe.gerarCacertsNfe("changeit", Servico.DemaisServicos, NFAmbiente.valueOfCodigo(ambiente),  NFUnidadeFederativa.valueOfCodigo(codigoEstado));
	
	        /**
	         * Abaixo definimos as propriedades necess?rias para a assinatura e em sequ?ncia assinamos o conte?do utilizando o c?digo da biblioteca Fincatto.
	         * Caso n?o aja arquivo de configura??o ele ser? criado.
	         */
	        try {
	            SystemPropertyManager.setProperties(TipoCertificado.valueOfCodigo(tipoCertificado), password, null, null, modeloCertificado);
	            System.out.println("3");
	            System.out.println(new AssinaturaDigitalApplet(password).assinarDocumento(xmlNaoAssinado));
	        } catch (Exception e) {
	            // TODO: handle exception
	            //JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	            JOptionPane.showMessageDialog(new Frame(), e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	        }
	
	
	    }
	
}