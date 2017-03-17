package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * J<i>ava</i> U<i>tilities</i> for S<i>tudents</i>
 */

/**
 * Un hotel qui est caractérisé par son nom et sa localisation.
 * @author Morat 
 */
public interface IHotel extends Remote{
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String name() throws RemoteException;
}
