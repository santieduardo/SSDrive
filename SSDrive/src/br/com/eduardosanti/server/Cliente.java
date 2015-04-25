package br.com.eduardosanti.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import br.com.eduardosanti.controller.DashboardController;
import br.com.eduardosanti.interfaces.DocumentoInterface;

public class Cliente {
	
	public void chamaCli(String titulo, String conteudo) {
        //String titulo = JOptionPane.showInputDialog("Titulo: ");
        //String conteudo = JOptionPane.showInputDialog("Conteudo: ");
        try {
            //LocateRegistry.getRegistry("201-89-184-180.paemt700.dsl.brasiltelecom.net.br", 1099);
            DocumentoInterface doc = (DocumentoInterface) Naming.lookup("rmi://localhost:1099/Doc");
            doc.setTituloDocumento(titulo);
            doc.setConteudoDocumento(conteudo);
            //doc.setConteudoDocumento(conteudo);
            //System.out.println(doc.getTituloDocumento()+" "+doc.getConteudoDocumento());
            System.out.println("T: "+doc.getTituloDocumento());
            System.out.println("C: "+doc.getConteudoDocumento());
            
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (NotBoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
