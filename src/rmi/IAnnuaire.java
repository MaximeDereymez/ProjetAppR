package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAnnuaire extends Remote {
	public String getNum(String hotel) throws RemoteException;
}
