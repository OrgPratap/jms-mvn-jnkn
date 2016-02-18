package com.pratap.mail.xmlsupport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MailToXmlCreator implements Runnable
{
	String l_m_id = null; //listed member id
	String l_m_info = null;//listed member information

	public MailToXmlCreator(String l_m_id, String l_m_info) {
		this.l_m_id = l_m_id;
		this.l_m_info = l_m_info;
	}

	public void run() 
	{
		File receivedFile = new File("C:\\xml_provider\\"+l_m_id+".xml");
		try {
			if (!receivedFile.exists()) {
				receivedFile.createNewFile();
			}
			FileWriter fw = new FileWriter(receivedFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(l_m_info);
			bw.close();
			System.out.println("data received from : {"+l_m_id+"} successfully...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to receive data from : {"+l_m_id+"} successfully...");
			e.printStackTrace();
		}
	}
}
