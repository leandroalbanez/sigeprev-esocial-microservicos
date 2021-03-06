package br.com.esocial.webservice.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import br.com.esocial.webservice.producaorestrita.envioLote.EnviarLoteEventosResponse.EnviarLoteEventosResult;

public class EsocialUtil {

	public static Document toDocument(String xml) {
		DocumentBuilder db;
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			return db.parse(is);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static String toObject(EnviarLoteEventosResult result) throws JAXBException {
		StringWriter sw = new StringWriter();
		JAXB.marshal(result, sw);
		return sw.toString();
	}

//	public MessageElement[] convertXMLStringtoMessageElement(String xmlString)
//			throws SAXException, IOException, ParserConfigurationException {
//		MessageElement[] m = new MessageElement[1];
//		Document XMLDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
//				.parse(new InputSource(new StringReader(xmlString)));
//		Element element = XMLDoc.getDocumentElement();
//		m[0] = new MessageElement(element);
//		return m;
//	}

	public static void tofile(String xml) throws FileNotFoundException {
		try {
			Files.write(Paths.get("my-file.txt"), xml.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
