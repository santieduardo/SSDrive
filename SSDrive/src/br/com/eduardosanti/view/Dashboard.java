package br.com.eduardosanti.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

import br.com.eduardosanti.controller.DashboardController;
import br.com.eduardosanti.interfaces.DocumentoInterface;
import br.com.eduardosanti.server.Cliente;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JRadioButton;

public class Dashboard extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldTitulo;
	private JTextArea textAreaConteudo;
	private DashboardController dashboard = new DashboardController();
	DocumentoInterface doc;
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Dashboard frame = new Dashboard();
					
					frame.setVisible(true);
					frame.setTitle("Dashboard");
					
					TimerTask receberDados = new TimerTask() {						
						@Override
						public void run() {
							try {
								frame.receiveFromServer();
								frame.repaint();
								
								System.out.println("RECEBI");
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					};
					
					Timer timerReceber = new Timer();
					timerReceber.scheduleAtFixedRate(receberDados, 5000, 5000);
					
				
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void sendToServer() throws RemoteException{
		String titulo = textFieldTitulo.getText();
		String conteudo = textAreaConteudo.getText();
		
		//dashboard.setTituloDocumento(titulo);
		//dashboard.setConteudoDocumento(conteudo);
		doc.setConteudoDocumento(conteudo);
		doc.setTituloDocumento(titulo);
				
		System.err.println("send titulo " + titulo);
		System.err.println("send conteudo " + conteudo);
	}
	
	public void receiveFromServer() throws RemoteException {
		
		textFieldTitulo.setText(doc.getTituloDocumento());
		textFieldTitulo.setCaretPosition(textFieldTitulo.getDocument().getLength());
		
		textAreaConteudo.setText(doc.getConteudoDocumento());
		textAreaConteudo.setCaretPosition(textAreaConteudo.getDocument().getLength());
		System.out.println("1 " + doc.getTituloDocumento());
		System.out.println("11 " + doc.getConteudoDocumento());
	}
	
	/**
	 * Create the frame.
	 */
	public Dashboard() {
		
		iniciaConexaoServidor();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 556);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTituloDoDocumento = new JLabel("Titulo do Documento:");
		lblTituloDoDocumento.setBounds(10, 11, 132, 14);
		contentPane.add(lblTituloDoDocumento);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(152, 8, 305, 20);
		contentPane.add(textFieldTitulo);
		textFieldTitulo.setColumns(10);
		textFieldTitulo.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					doc.setTituloDocumento(textFieldTitulo.getText());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JLabel lblContedo = new JLabel("Conte\u00FAdo:");
		lblContedo.setBounds(10, 36, 102, 14);
		contentPane.add(lblContedo);
		
		textAreaConteudo = new JTextArea();
		textAreaConteudo.setBounds(10, 61, 594, 456);
		textAreaConteudo.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
						
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					doc.setConteudoDocumento(textAreaConteudo.getText());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		contentPane.add(textAreaConteudo);
		
		JButton btnLimpar = new JButton("Limpar Tudo");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					doc.setTituloDocumento("");
					doc.setConteudoDocumento("");
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				textFieldTitulo.requestFocus();
			}
		});
		btnLimpar.setBounds(494, 7, 110, 23);
		contentPane.add(btnLimpar);
	}

	private void iniciaConexaoServidor() {
		try {
			doc = (DocumentoInterface) Naming.lookup("rmi://localhost:1099/Doc");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
