package org.brots.scidata.nfe.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlDoc {

	public static Document getXmlDoc(File xml) throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		DocumentBuilder docBuilder = dbf.newDocumentBuilder();

		Document doc = docBuilder.parse(xml);

		doc.getDocumentElement().normalize();

		return doc;
	}
}