/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jus.aor.mobilagent.kernel;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author romane
 */
/* implement thread ou extend runnable ?*/
public class AgentServer extends Thread {
    
    private int port;
    private String name;
    private Map<String,_Service<?>> services;
    
    
    AgentServer(int p, String s){
    	System.out.println("port = "+p);
        port=p;
        name =s;
        services = new HashMap<String, _Service<?>>();
    }
    
    
    public _Agent getAgent(Socket s){
        try {
            BAMAgentClassLoader classLoader = new BAMAgentClassLoader(this.getClass().getClassLoader());
            InputStream is= s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            AgentInputStream ais= new AgentInputStream(is, classLoader);

            Jar jarjar = (Jar) ois.readObject();
            classLoader.integrateCode(jarjar);

        
            _Agent agent = (_Agent)ais.readObject();
            ois.close();
            ais.close();
            is.close();
            return agent;
        } catch (IOException ex) {
            Logger.getLogger(AgentServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AgentServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return null;
        
    }   
    
    @Override
    public void run(){
        boolean b= true;
        ServerSocket socketServer;
        try {
            socketServer = new ServerSocket(port);
            Logger.getLogger(AgentServer.class.getName()).log(Level.SEVERE, "Lancement de l'agent Server");
            
            while (b){
                Logger.getLogger(AgentServer.class.getName()).log(Level.SEVERE,  "chargement d'un agent ");
                Socket socketclient = socketServer.accept();
                Logger.getLogger(AgentServer.class.getName()).log(Level.SEVERE, "reception de l'agent");
                _Agent agent= this.getAgent(socketclient);
                agent.reInit(this, name);
                new Thread (agent).start();
                socketclient.close();
                }
            socketServer.close();
        } catch (IOException ex) {
            Logger.getLogger(AgentServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    public void AddService (String s, _Service<?> service){
        this.services.put(s, service);
    }
    
    
    public String toString(){
        return null;
        //TODO
    }
    
    public _Service<?> getService(String s){
        return this.services.get(s);
    }
    
    public URI site(){
        
        try {
            return new URI("//localhost:"+this.port);
        } catch (URISyntaxException ex) {
            Logger.getLogger(AgentServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
