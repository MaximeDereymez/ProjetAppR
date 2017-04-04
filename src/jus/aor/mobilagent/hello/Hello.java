package jus.aor.mobilagent.hello;

import java.util.LinkedList;
import java.util.logging.Level;

import jus.aor.mobilagent.kernel.Agent;
import jus.aor.mobilagent.kernel.Starter;
import jus.aor.mobilagent.kernel._Action;

/**
 * Classe de test élémentaire pour le bus à agents mobiles
 * @author  Morat
 */
public class Hello extends Agent{

    	/**
     * 
     */
    private static final long serialVersionUID = -6811554479498309191L;
	LinkedList<String> serverList;
	 /**
	  * construction d'un agent de type hello.
	  * @param args aucun argument n'est requis
	  */
	 public Hello(Object... args) {
		serverList = new LinkedList<String>();
	 }
	 /**
	 * l'action à entreprendre sur les serveurs visités  
	 */
	protected _Action doIt = new _Action(){


	    /**
	     * 
	     */
	    private static final long serialVersionUID = -9129644307555501553L;

	    @Override
	    public void execute() {
		Starter.logger().log(Level.INFO,"Hello "+serverName+".");
		System.out.println("Hello "+serverName+".");
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
		    	Starter.logger().log(Level.INFO,"Visited the following servers");
		    	System.out.println("Visited the following servers");

		    	for(String server : serverList) {
		    		Starter.logger().log(Level.INFO, server);
        			System.out.println(server);
		    	}
        	}
                    
                };
	}
	// ...
}
