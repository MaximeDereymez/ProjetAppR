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

	public BAMServerClassLoader(URL[] urls, ClassLoader c) {
		super(urls);
	}

	@Override
	protected void addURL(URL url) {
		super.addURL(url);
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
