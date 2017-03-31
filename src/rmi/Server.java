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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Server {
	
	public static void bindHotelFile(String name, String filename) throws SAXException, IOException, ParserConfigurationException, AlreadyBoundException{
		Naming.bind(name,new Chaine(new File(filename)));
		System.out.println("Bound "+name);
	}
	
	public static void main(final String args[]) {
		String nom="Hotel";
		int nombre=1; int port = 1099;
		Registry registry=null;
		try {
			registry = LocateRegistry.createRegistry(port);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			bindHotelFile("Chaine0","DataStore/Hotels1.xml");
			bindHotelFile("Chaine1","DataStore/Hotels2.xml");
			bindHotelFile("Chaine2","DataStore/Hotels3.xml");
			bindHotelFile("Chaine3","DataStore/Hotels4.xml");
			Naming.bind("Annuaire", new Annuaire());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
