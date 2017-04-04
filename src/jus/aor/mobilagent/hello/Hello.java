package jus.aor.mobilagent.hello;

import java.net.URI;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import jus.aor.mobilagent.kernel._Action;
import jus.aor.mobilagent.kernel.Agent;

/**
 * Classe de test élémentaire pour le bus à agents mobiles
 * @author  Morat
 */
public class Hello extends Agent{

    	/**
     * 
     */
    private static final long serialVersionUID = -6811554479498309191L;
	ArrayList<String> serverList;
	 /**
	  * construction d'un agent de type hello.
	  * @param args aucun argument n'est requis
	  */
	 public Hello(Object... args) {
		serverList = new ArrayList<String>();
	 }
	 /**
	 * l'action à entreprendre sur les serveurs visités  
	 */
	protected _Action doIt = new _Action(){

	    /**
	     * 
	     */
	    private static final long serialVersionUID = 1L;

	    @Override
	    public void execute() {
		System.out.println("Hello");
		serverList.add(serverName);
	    }
            
	};
	/* (non-Javadoc)
	 * @see jus.aor.mobilagent.kernel.Agent#retour()
	 */
	//@Override
	protected _Action retour(){
                return new _Action(){

		    /**
		     * 
		     */
		    private static final long serialVersionUID = 8112403583439231794L;

		    @Override
        	    public void execute() {
        		System.out.println("Visited the following servers");
        		for(String server : serverList)
        		    System.out.println(server);
        	    }
                    
        	};
	}
	// ...
}
