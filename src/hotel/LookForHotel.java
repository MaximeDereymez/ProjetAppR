package hotel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jus.aor.mobilagent.kernel.Agent;
import jus.aor.mobilagent.kernel._Action;
import jus.aor.mobilagent.kernel._Service;

/**
 * J<i>ava</i> U<i>tilities</i> for S<i>tudents</i>
 */

/**
 * Représente un client effectuant une requête lui permettant d'obtenir les numéros de téléphone des hôtels répondant à son critère de choix.
 * @author  Morat
 */
public class LookForHotel extends Agent {
    private static final long serialVersionUID = -7856759896920597986L;
	/** le critère de localisaton choisi */
	private String localisation;
	private List<Hotel> hotels;
	private Map<Hotel,Numero> numeros;
	// ...
	/**
	 * Définition de l'objet représentant l'interrogation.
	 * @param args les arguments n'en comportant qu'un seul qui indique le critère
	 *          de localisation
	 */
	public LookForHotel(String... args){
		localisation = args[0];
		hotels = new LinkedList<>();
		numeros = new HashMap<Hotel,Numero>();
	}

	protected _Action findHotel = new _Action(){

	    private static final long serialVersionUID = -5462249915229967567L;

	    @Override
	    public void execute() {
		List<Hotel> chaineHotels = (List<Hotel>) LookForHotel.this.getService("Hotels").call(new Object[]{localisation});
		hotels.addAll(chaineHotels);
	    }
	    
	};
	
	protected _Action findTelephone = new _Action(){

	    private static final long serialVersionUID = -9211118182369995431L;

	    @Override
	    public void execute() {
		_Service<Numero> annuaire = (_Service<Numero>) LookForHotel.this.getService("Telephones");
		for(Hotel h : hotels){
		    Numero n = annuaire.call(new Object[]{h.name});
		    numeros.put(h, n);
		}
	    }
	    
	};
}
