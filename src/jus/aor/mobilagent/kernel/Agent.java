/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jus.aor.mobilagent.kernel;

import java.net.URI;

/**
 *
 * @author romane
 */
public class Agent implements _Agent{
    public Agent(){}
    Route route;
    public void init(AgentServer agentServer, String serverName) {
        //TODO
        
    }

    public void reInit(AgentServer server, String serverName) {
        //TODO
        
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
             //aller a l'adresse etape.URI
             //move(etape);
            //faire l'action etape.Action
            
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
        //TODO
    }
    
    protected void move(URI uri){
        // aller a l'adresse URI
    }
    
    protected String route (){
        return route.toString();
        //je suis pas sure
    }
}
