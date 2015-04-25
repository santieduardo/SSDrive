package br.com.eduardosanti.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import br.com.eduardosanti.interfaces.DocumentoInterface;
import br.com.eduardosanti.model.DocumentoModel;

public class Servidor {
	
public static void main(String[] args) {
        
        try {
            DocumentoInterface docInterface = new DocumentoModel();
            LocateRegistry.createRegistry(1099);
            try {
                Naming.rebind("Hello", docInterface);
                System.out.println("Servidor iniciado!");
              
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }

        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

}
