/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jus.aor.mobilagent.kernel;

import java.net.Socket;
import java.net.URI;

/**
 *
 * @author romane
 */
/* implement thread ou extend runnable ?*/
public class AgentServer extends Thread {
    
    int id;
    String jesaispas;//peut etre URI
    
    
    AgentServer(int i, String s){
        id=i;
        jesaispas=s;
    }
    
    
    public _Agent getAgent(Socket s){
        return null;
        //TODO
    }
    @Override
    public void run(){
        
    }
    
    
    
    public void AddService (String s, _Service<?> service){
        //TODO
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
