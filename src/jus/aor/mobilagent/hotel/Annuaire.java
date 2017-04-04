package jus.aor.mobilagent.hotel;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import jus.aor.mobilagent.kernel._Service;

public class Annuaire extends UnicastRemoteObject implements _Annuaire, _Service<Numero> {
	protected HashMap<String, String> annuaire;
	
	public Annuaire(Object[] args) throws ParserConfigurationException, SAXException, IOException {
		super();
		/* Récupération de l'annuaire dans le fichier xml */
		DocumentBuilder docBuilder = null;
		Document doc=null;
		docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		doc = docBuilder.parse(new File(args[0].toString()));

		String name, numero;
		NodeList list = doc.getElementsByTagName("Telephone");
		NamedNodeMap attrs;
		annuaire = new HashMap<>();
		/* acquisition de toutes les entrées de l'annuaire */
		for(int i =0; i<list.getLength();i++) {
			attrs = list.item(i).getAttributes();
			name=attrs.getNamedItem("name").getNodeValue();
			numero=attrs.getNamedItem("numero").getNodeValue();
			annuaire.put(name, numero);
		}
	}

	@Override
	public Numero get(String hotel) {
		return new Numero(annuaire.get(hotel));
	}

	@Override
	public Numero call(Object... params) throws IllegalArgumentException {
	    return get(params[0].toString());
	}

}
