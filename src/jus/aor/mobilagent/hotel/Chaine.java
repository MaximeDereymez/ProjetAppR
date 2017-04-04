package jus.aor.mobilagent.hotel;

import java.io.File;
import java.io.IOException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import jus.aor.mobilagent.kernel._Service;

/**
 * J<i>ava</i> U<i>tilities</i> for S<i>tudents</i>
 */

/**
 * Un hotel qui est caractérisé par son nom et sa localisation.
 * @author Morat 
 */
public class Chaine extends UnicastRemoteObject implements _Chaine, _Service<List<Hotel>> {
	/**
     * 
     */
    private static final long serialVersionUID = -8101147890358423596L;
	protected ArrayList<Hotel> hotels;
	/**
	 * Définition d'un hôtel par son nom et sa localisation.
	 * @param name le nom de l'hôtel
	 * @param localisation la localisation de l'hôtel
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public Chaine(Object[] args) throws ParserConfigurationException, SAXException, IOException { 
		super();
		DocumentBuilder docBuilder = null;
		Document doc=null;
		docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		doc = docBuilder.parse(new File(args[0].toString()));

		String name, localisation;
		hotels = new ArrayList<Hotel>();
		NodeList list = doc.getElementsByTagName("Hotel");
		NamedNodeMap attrs;
		/* acquisition de toutes les entrées de la base d'hôtels */
		for(int i =0; i<list.getLength();i++) {
			attrs = list.item(i).getAttributes();
			name=attrs.getNamedItem("name").getNodeValue();
			localisation=attrs.getNamedItem("localisation").getNodeValue();
			hotels.add(new Hotel(name,localisation));
		}
	}
	@Override
	public List<Hotel> get(String location){
	    ArrayList<Hotel> result = new ArrayList<Hotel>();
	    for(Hotel hotel : hotels)
		if(hotel.localisation.equals(location))
		    result.add(hotel);
	    return result;
	}
	
	@Override
	public List<Hotel> call(Object... params) throws IllegalArgumentException {
	    return get(params[0].toString());
	}
}
