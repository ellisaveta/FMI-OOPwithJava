package app;

import com.CipherInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CipherTest {
    public static void main(String[] args) {
        String remoteObjectName = "Cipher";
        CipherInterface icipher;
        try {
            icipher = new CipherImp();
            Registry registry = LocateRegistry.createRegistry(1099);

            registry.rebind(remoteObjectName, icipher);
            System.out.printf("Remote object with name %s is registered. " +
                    "Registry is running.\n", remoteObjectName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
