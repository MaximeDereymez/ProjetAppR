package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * J<i>ava</i> U<i>tilities</i> for S<i>tudents</i>
 */

/**
 * Représente un client effectuant une requête lui permettant d'obtenir les numéros de téléphone des hôtels répondant à son critère de choix.
 * @author  Morat
 */
public class LookForHotelRMI{
    /** le critère de localisaton choisi */
    private String localisation;
    private static final int N=4;
    // ...
    /**
     * Définition de l'objet représentant l'interrogation.
     * @param args les arguments n'en comportant qu'un seul qui indique le critère
     *          de localisation
     */
    public LookForHotelRMI(String... args){
	localisation = args[0];
    }
    /**
     * réalise une intérrogation
     * @return la durée de l'interrogation
     * @throws RemoteException
     */
    public long call() {
	List<Numero> numeros = new ArrayList<Numero>();
	long time = System.currentTimeMillis();
	try {
	    _Chaine chaine;
	    _Annuaire annuaire = (_Annuaire) Naming.lookup("Annuaire");
	    for(int i=0; i<N; i++){
	        chaine = (_Chaine) Naming.lookup("Chaine"+i);
	        for(Hotel hotel : chaine.get(localisation))
	            numeros.add(annuaire.get(hotel.name));
	            //System.out.println("L'h�tel "+hotel.name+" correspond, son num�ro est "+annuaire.get(hotel.name));
	    }
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	time = System.currentTimeMillis() - time;
	for(Numero num : numeros)
	    System.out.println(num);
	return time;
    }
    
    public static void main(String[] args) {
	System.out.println("Time = "+new LookForHotelRMI("Paris").call());
    }

    // ...
}
