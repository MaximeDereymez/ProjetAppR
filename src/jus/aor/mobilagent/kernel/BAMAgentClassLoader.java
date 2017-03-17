/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jus.aor.mobilagent.kernel;

/**
 *
 * @author romane
 */
public class BAMAgentClassLoader extends ClassLoader {
    String s;
    Jar jar;
    
    public BAMAgentClassLoader(String s, ClassLoader classLoader) {
        super(classLoader);
        this.s=s;
    }
    
    public BAMAgentClassLoader(ClassLoader classLoader) {
        super(classLoader);
    }
    
    public void integrateCode (Jar jar){
        this.jar=jar;
        //TODO defineClass
       
    }
    
    public String className(String s){
        return null;
        //TODO
    }
    
    public Jar extractCode(){
        return jar;
    }
    @Override
    public String toString(){
        return null;
        //TODO
    }
}
