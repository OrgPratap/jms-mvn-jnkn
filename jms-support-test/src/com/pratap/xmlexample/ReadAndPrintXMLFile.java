package com.pratap.xmlexample;

import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.*;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ReadAndPrintXMLFile{

	public static void main (String argv []){
		try {

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse (new File("C:\\Users\\singsate\\workspace-mars\\jms-support-test\\datafile1.xml"));

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

					//------


				}//end of if clause


			}//end of for loop with s var


		}catch (SAXParseException err) {
			System.out.println ("** Parsing error" + ", line " + err.getLineNumber () + ", uri " + err.getSystemId ());
			System.out.println(" " + err.getMessage ());

		}catch (SAXException e) {
			Exception x = e.getException ();
			((x == null) ? e : x).printStackTrace ();

		}catch (Throwable t) {
			t.printStackTrace ();
		}
		//System.exit (0);

	}//end of main
	
	
	public static void main0 (String argv []){
		try {

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse (new File("C:\\Users\\singsate\\workspace-mars\\jms-support-test\\datafile.xml"));

			// normalize text representation
			doc.getDocumentElement ().normalize ();
			System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());


			NodeList listOfPersons = doc.getElementsByTagName("person");
			int totalPersons = listOfPersons.getLength();
			System.out.println("Total no of people : " + totalPersons);

			for(int s=0; s<listOfPersons.getLength() ; s++){


				Node firstPersonNode = listOfPersons.item(s);
				if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){


					Element firstPersonElement = (Element)firstPersonNode; 

					//-------
					NodeList firstNameList = firstPersonElement.getElementsByTagName("first");
					Element firstNameElement = (Element)firstNameList.item(0);

					NodeList textFNList = firstNameElement.getChildNodes();
					System.out.println("First Name : " + ((Node)textFNList.item(0)).getNodeValue().trim());

					//------- 
					NodeList lastNameList = firstPersonElement.getElementsByTagName("last");
					Element lastNameElement = (Element)lastNameList.item(0);

					NodeList textLNList = lastNameElement.getChildNodes();
					System.out.println("Last Name : " + ((Node)textLNList.item(0)).getNodeValue().trim());

					//----
					NodeList ageList = firstPersonElement.getElementsByTagName("age");
					Element ageElement = (Element)ageList.item(0);

					NodeList textAgeList = ageElement.getChildNodes();
					System.out.println("Age : " + ((Node)textAgeList.item(0)).getNodeValue().trim());

					//------


				}//end of if clause


			}//end of for loop with s var


		}catch (SAXParseException err) {
			System.out.println ("** Parsing error" + ", line " + err.getLineNumber () + ", uri " + err.getSystemId ());
			System.out.println(" " + err.getMessage ());

		}catch (SAXException e) {
			Exception x = e.getException ();
			((x == null) ? e : x).printStackTrace ();

		}catch (Throwable t) {
			t.printStackTrace ();
		}
		//System.exit (0);

	}//end of main


}