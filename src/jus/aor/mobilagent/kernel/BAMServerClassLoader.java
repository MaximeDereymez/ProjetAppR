/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jus.aor.mobilagent.kernel;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

/**
 *
 * @author romane
 */
public class BAMServerClassLoader extends URLClassLoader {
    
	
	
    private Jar jar;
    private HashMap<String,byte[]> classes;
    
    
    
    BAMServerClassLoader(URL[] tab, ClassLoader classLoader){
        super(tab,classLoader);
    }
    
    @Override
    public void addURL(URL url){
        super.addURL(url);
    }
    
    @Override
    public Class<?> findClass(String s) throws ClassNotFoundException {
    	byte [] myClass = classes.get(s);
    	if(myClass == null) {
    		throw new ClassNotFoundException("s introuvable\n");
    	}
    	
    	return defineClass(s,myClass,0,myClass.length);
    }
    
    public Jar jar() {
    	return jar;
    }
    
    @Override
    public String toString(){
        return super.toString();
    }
    
}
