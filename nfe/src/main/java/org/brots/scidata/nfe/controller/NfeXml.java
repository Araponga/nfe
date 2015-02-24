package org.brots.scidata.nfe.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.lang.model.element.Element;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;

import org.brots.scidata.nfe.utils.EscolherArquivo;
import org.brots.scidata.nfe.utils.XmlDoc;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class NfeXml {

	public static void main(String argv[]) throws IOException, SAXException, ParserConfigurationException {

			// Define caminho de origem dos .xml
			EscolherArquivo escolherArquivo = new EscolherArquivo();
			
			File dir = escolherArquivo.getDiretorio();
			
			System.out.println("Caminho selecionado" + dir);
			
			// Cria lista de xml do diretório
			File[] listXml = dir.listFiles(new FilenameFilter() {
			    public boolean accept(File dir, String name) {
			        return name.toLowerCase().endsWith("-nfe.xml");
			    }
			}); 
			
			// Se mão possui xml	
			if (listXml.length == 0) {
				
				JOptionPane.showMessageDialog(null, "Não existe arquivos XML de Nfe!");
				System.exit(1);
			}
			// Varre a lista de xml
	
			for (File xml : listXml) {
				System.out.println(xml);
			}
			// Define arquivo destino 
			String file = dir+"\\Transacoes.csv";
			
			System.out.println(file);
			
			// Cria csv destino
			FileWriter csvTransacoes = new FileWriter(file, false);

			int numeroNfe = 0;
			
			// Varre a lista de xml
			for (File xml : listXml) {
				
				Document doc = XmlDoc.getXmlDoc(xml); 


				// Busca pela tag <prod>
				NodeList nList = doc.getElementsByTagName("prod");
				
				// Adiciona nova NFE
				System.out.println("Nova Nfe.........................." + "/n");
				
				// String de trabalho
				StringBuilder produto = new StringBuilder();
				
				numeroNfe++; 
				
				// Varre os elementos com a tag <prod>
				
				for (int i = 0; i < nList.getLength(); i++) {
					
					Node nNode = (Node) nList.item(i);

					Element eElement = (Element) nNode;

					produto.append(numeroNfe + ";" +  
							((Document) eElement).getElementsByTagName("cProd").item(0).getTextContent() + " " +
							((Document) eElement).getElementsByTagName("xProd").item(0).getTextContent() + ";");

					System.out.print(produto);

					csvTransacoes.append(produto);
				}
				csvTransacoes.append("\n");
				System.out.print("\n");
				
			}
			
			csvTransacoes.flush();
			csvTransacoes.close();

			JOptionPane.showMessageDialog(null, "Arquivo de transacoes criado: " + file);

	}
}