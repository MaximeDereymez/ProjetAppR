/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jus.aor.mobilagent.kernel;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author romane
 */
public abstract class Agent implements _Agent{
    /**
     * 
     */
    private static final long serialVersionUID = -899873575940529123L;
    Route route;
    protected transient AgentServer agentServer;
    //protected pour pouvoir suivre l'avancement avec l'agent Hello
    protected String serverName;

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
        
        Logger.getLogger(AgentServer.class.getName()).log(Level.FINE, "Lancement de l'agent" + this.toString());
        if(route.hasNext){
            Etape etape=route.next();
            etape.action.execute();
            Logger.getLogger(AgentServer.class.getName()).log(Level.FINE,"L'agent "+this+ "fait l'action"+ etape);
            //On ne fait pas de move sur la dernière étape
            if(this.route.hasNext)
                move();
            else
        	retour().execute();
                //Logger.getLogger(AgentServer.class.getName()).log(Level.FINE," L'agent "+ this+"a fini");
            
        }
       
    }

    protected abstract _Action retour();
    
    protected _Service<?> getService(String s){
        return agentServer.getService(s);
    }
    
    private void move(){
    	this.move(this.route.get().server);
    }
    
    protected void move(URI uri){
    	try {
			Socket socket = new Socket(uri.getHost(),uri.getPort());
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			ObjectOutputStream oos2 = new ObjectOutputStream(os);
			//TODO ATTENTION EXPLOSION (il faut peut etre utiliser deux oos differents)
			BAMAgentClassLoader bam = (BAMAgentClassLoader) this.getClass().getClassLoader();
			Jar jarJar = bam.extractCode();
			oos.writeObject(jarJar);
			oos2.writeObject(this);
			
			oos.close();
			oos2.close();
			os.close();
			socket.close();
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
    @Override
    public String toString (){
        return "route : "+this.route()+ "position"+this.serverName;
    }
}
