/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jus.aor.mobilagent.kernel;

import java.net.URL;
import java.net.URLClassLoader;

/**
 *
 * @author romane
 */
public class BAMServerClassLoader extends URLClassLoader {
    
    
    BAMServerClassLoader(URL[] tab, ClassLoader classLoader){
        super(tab,classLoader);
    }
    @Override
    public void addURL(URL url){
        super.addURL(url);
    }
    @Override
    public String toString(){
        return super.toString();
    }
    
}
