/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jus.aor.mobilagent.kernel;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

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
        port=p;
        name =s;
        services = new HashMap<String, _Service<?>>();
    }
    
    
    public _Agent getAgent(Socket s) throws IOException, ClassNotFoundException{
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
        
        return _Agent;
    }   
    
    @Override
    public void run(){
        
    }
    
    
    
    public void AddService (String s, _Service<?> service){
        this.services.put(s, service);
    }
    
    
    public String toString(){
        return null;
        //TODO
    }
    
    public _Service<?> getService(String s){
        return null;
        //TODO
    }
    
    public URI site(){
        //TODO
        return null;
    }
}
