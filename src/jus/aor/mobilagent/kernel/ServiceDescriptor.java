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
public class ServiceDescriptor {
	
	/*
	 probablement faux ou incomplet
	 */
	
	
	private String name;
	
	public ServiceDescriptor(String name) {
		this.name = name;
	}
	
	public String name() {
		return name;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public boolean equals(Object serviceDescriptor) {
		return ((ServiceDescriptor) serviceDescriptor).name().equals(this.name);
	}
}
