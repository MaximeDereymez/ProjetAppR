package rmi;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Server {
	
	public static void bindHotelFile(File f) throws SAXException, IOException, ParserConfigurationException, AlreadyBoundException{
		Hotel hotel;
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);
		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("Hotel");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				hotel = new Hotel(eElement.getAttribute("name"), eElement.getAttribute("location"));
				Naming.bind(hotel.name, hotel);
				System.out.println("Bound "+hotel.name);
			}
		}
	}
	
	public static void main(final String args[]) {
		String nom="Hotel";
		int nombre=1; int port = 1099;
		Registry registry=null;
		// installation d'un securityManager
		// A COMPLETER : INSTALLATIOND'UN SECURITYMANAGER
		// A COMPLETER : MISE EN PLACE DU REGISTRY
		try {
			registry = LocateRegistry.createRegistry(port);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			bindHotelFile(new File("DataStore/Hotels1.xml"));
			bindHotelFile(new File("DataStore/Hotels2.xml"));
			bindHotelFile(new File("DataStore/Hotels3.xml"));
			bindHotelFile(new File("DataStore/Hotels4.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
