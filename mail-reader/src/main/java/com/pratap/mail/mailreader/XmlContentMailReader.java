package com.pratap.mail.mailreader;

import java.util.*;

import javax.mail.*;

import com.pratap.mail.xmlsupport.MailToXmlCreator;

public class XmlContentMailReader {
	public static void main(String[] args) {

		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "imaps");
		String l_m_id = null; //listed member id
		String l_m_info = null;//listed member information

		try {
			Session session = Session.getInstance(props, null);
			Store store = session.getStore();
			store.connect("imap.gmail.com", "jmsmvntestmail@gmail.com", "satendra78990");
			Folder inbox = store.getFolder("INBOX");
			//inbox.open(Folder.READ_WRITE);
			Message messages[];
			while(true){
				inbox.open(Folder.READ_WRITE);
				try{
					messages = inbox.getMessages();
					if(messages.length == 0){
						System.err.println("No Mail!!!!!retry after 2 min....!!!");
						inbox.close(true);
						Thread.currentThread().sleep(120000);
						continue;
					}
					for(Message msg : messages){
						Address[] in = msg.getFrom();
						for (Address address : in) {
							System.out.println("FROM:" + address.toString());
						}
						System.out.println("SENT DATE:" + msg.getSentDate());
						System.out.println("SUBJECT:" + msg.getSubject());
						l_m_id = msg.getSubject();
						Multipart mp = (Multipart) msg.getContent();
						BodyPart bp = mp.getBodyPart(0);
						System.out.println("CONTENT:" + bp.getContent());
						l_m_info = (String) bp.getContent();
						new Thread(new MailToXmlCreator(l_m_id,l_m_info)).start();
						msg.setFlag(Flags.Flag.DELETED, true);
						System.err.println("Marked DELETE for message: " + msg.getSubject());
						System.out.println("++++++++++++{ next mail read after 10 second...]++++++++++++++++");
						Thread.currentThread().sleep(10000);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("recheck after 15 sec.....");
					Thread.currentThread().sleep(15000);
				}
				System.err.println("!!!!end of the mail...retry [30sec]");
				inbox.close(true);
				Thread.currentThread().sleep(30000);
			}	

		} catch (Exception mex) {
			mex.printStackTrace();
		}

	}
}