package com.pratap.xmlprovider;

import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.*;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SimpleXmlProvider{

	public static void main (String argv []){
		while(true){
			try {

				File directory = new File("C:\\xml_provider");
				String files[] = directory.list();
				if(!(files.length == 0 || files == null)){

					for(String file : files)
					{
						DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
						File dataFile = new File(directory.getAbsolutePath()+"\\"+file);
						if(!file.endsWith(".xml")){
							System.err.println("---error : "+file+" is not an xml file");
							if(dataFile.delete()){
								System.err.println("data file : "+file+" deleted successfully....");
							}else{
								System.err.println("error!! data file : "+file+" not deleted !!!!!!!!!");
							}
							continue;
						}
						Document doc = docBuilder.parse (dataFile);

						// normalize text representation
						doc.getDocumentElement ().normalize ();
						System.out.println ("QUEUE NAME : " + doc.getDocumentElement().getNodeName());


						NodeList listOfMSGs = doc.getElementsByTagName("MSG");

						System.out.println("Total no of MESSAGES : " + listOfMSGs.getLength());

						for(int i=0; i<listOfMSGs.getLength() ; i++)
						{

							Node msg = listOfMSGs.item(i);

							if(msg.getNodeType() == Node.ELEMENT_NODE)
							{

								Element msgContents = (Element)msg; 

								//-------
								NodeList userMsgId = msgContents.getElementsByTagName("userMsgId");
								Element userMsgIdElement = (Element)userMsgId.item(0);

								NodeList userMsgIdValue = userMsgIdElement.getChildNodes();
								System.out.println("USER MESSAGE ID  : " + ((Node)userMsgIdValue.item(0)).getNodeValue().trim());

								//------- 
								NodeList userMsgData = msgContents.getElementsByTagName("userMsgData");
								Element userMsgDataElement = (Element)userMsgData.item(0);

								NodeList userMsgDataValue = userMsgDataElement.getChildNodes();
								System.out.println("USER MESSAGE DATA: " + ((Node)userMsgDataValue.item(0)).getNodeValue().trim());

								//----
								NodeList priority = msgContents.getElementsByTagName("priority");
								Element priorityElement = (Element)priority.item(0);

								NodeList priorityValue = priorityElement.getChildNodes();
								System.out.println("MESSAGE PRIORITY : " + ((Node)priorityValue.item(0)).getNodeValue().trim());

							}

						}
						if(dataFile.delete()){
							System.err.println("data file : "+file+" deleted successfully....");
						}else{
							System.err.println("error!! data file : "+file+" not deleted !!!!!!!!!");
						}
						Thread.currentThread().sleep(10000);
					}
				}else{
					System.err.println("empty xml provider....retry after 10sec.....");
					Thread.currentThread().sleep(10000);
				}
			}catch (SAXParseException err) {
				System.out.println ("** Parsing error" + ", line " + err.getLineNumber () + ", uri " + err.getSystemId ());
				System.out.println(" " + err.getMessage ());

			}catch (SAXException e) {
				Exception x = e.getException ();
				((x == null) ? e : x).printStackTrace ();

			}catch (Throwable t) {
				t.printStackTrace ();
			}

		}
	}
}