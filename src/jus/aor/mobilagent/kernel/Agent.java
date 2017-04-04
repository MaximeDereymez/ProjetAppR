/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jus.aor.mobilagent.kernel;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;

/**
 *
 * @author romane
 */
public class Agent implements _Agent{
    Route route;
	private AgentServer agentServer;
	private String serverName;

    public Agent(){
    	
    }
    
    public void init(AgentServer agentServer, String serverName) {
        this.agentServer = agentServer;
        this.serverName = serverName;
        this.route = new Route(new Etape(agentServer.site(), _Action.NIHIL));
        this.route.add(new Etape(agentServer.site(), _Action.NIHIL));
    }

    public void reInit(AgentServer server, String serverName) {
    	this.agentServer = server;
    	this.serverName = serverName;
    }

    public void addEtape(Etape etape) {
       route.add(etape);
    }
    
    @Override
    public void run() {
        //TODO
        //Faire toute les etapes de la feuille de route
        while(route.hasNext){
            Etape etape=route.next();
            etape.action.execute();

            //On ne fait pas de move sur la dernière étape
            if(this.route.hasNext)
                move(etape.server);
            
        }
       
    }

    protected _Action retour(){
        return null;
        //TODO
    }
    
    protected _Service<?> getService(String s){
        return null;
        //TODO
    }
    
    private void move(){
    	this.move(this.route.get().server);
    }
    
    protected void move(URI uri){
    	try {
			Socket socket = new Socket(uri.getHost(),uri.getPort());
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			//TODO ATTENTION EXPLOSION (il faut peut etre utiliser deux oos differents)
			BAMAgentClassLoader bam = (BAMAgentClassLoader) this.getClass().getClassLoader();
			Jar jarJar = bam.extractCode();
			oos.writeObject(jarJar);
			oos.writeObject(this);
			
			oos.close();
			os.close();
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	// aller a l'adresse URI
    }
    
    protected String route (){
        return route.toString();
        //je suis pas sure
    }
}
