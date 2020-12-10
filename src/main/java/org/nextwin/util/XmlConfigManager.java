package org.nextwin.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class XmlConfigManager {
	
	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private Document document;
	
	public XmlConfigManager(String path) {
		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			document = builder.parse(new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String findValueAsUniqueTag(String tag) {
		return document.getElementsByTagName(tag).item(0).getFirstChild().getNodeValue();
	}

}
