/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jus.aor.mobilagent.kernel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;


/**
 *
 * @author romane
 */
public class BAMAgentClassLoader extends ClassLoader {
    private Jar jar;

    private Map<String, byte[]> classes;
    
    public BAMAgentClassLoader(String s, ClassLoader classLoader) {
        super(classLoader);
        Jar jarJar;
		try {
			jarJar = new Jar(s);
	        integrateCode(jarJar);
	        this.jar = jarJar;
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public BAMAgentClassLoader(ClassLoader classLoader) {
        super(classLoader);
        this.classes = new HashMap<String, byte[]>();
    }
    
    private void integrateCode (Jar jar){
        for(Entry<String,byte[]> e : jar)
        {
        	String s = this.className(e.getKey());
        	this.classes.put(s, e.getValue());
        	Class<?> c = this.defineClass(s, e.getValue(), 0, e.getValue().length);
        	super.resolveClass(c);
        }       
    }
    
    public String className(String s){
        return s.replace(".class", "").replace("/", ".");        
    }
    
    public Jar extractCode(){
		try {

			File f = File.createTempFile("tempJar", ".jar");
    	
			JarOutputStream jos = new JarOutputStream(new FileOutputStream(f));
			for(Entry<String,byte[]> e : classes.entrySet()){
				jos.putNextEntry(new JarEntry(e.getKey()));
				jos.write(e.getValue());
    		
			}
			jos.close();
			this.jar = new Jar(f.getAbsolutePath());
		
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return jar;
	}
    
    @Override
    public String toString(){
        return classes.toString();
    }
}
