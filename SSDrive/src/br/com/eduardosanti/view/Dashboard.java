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

	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
					
					frame.setVisible(true);
					frame.setTitle("Dashboard");
					
					
					TimerTask enviarDados = new TimerTask() {
						
						@Override
						public void run() {
							frame.sendToServer();
							frame.repaint();
						
							System.out.println("ENVIEI");
						}
					};
					
					
					
					TimerTask receberDados = new TimerTask() {
						
						@Override
						public void run() {
							try {
								frame.receiveFromServer();
								frame.repaint();
								
								System.out.println("RECEBI");
							} catch (MalformedURLException | RemoteException
									| NotBoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					};
					Timer timerEnviar = new Timer();
					timerEnviar.scheduleAtFixedRate(enviarDados, 10000, 10000);
					//Timer timerReceber = new Timer();
					//timerReceber.scheduleAtFixedRate(receberDados, 3000, 3000);
					
				
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void sendToServer(){
		String titulo = textFieldTitulo.getText();
		String conteudo = textAreaConteudo.getText();
		
		dashboard.setTituloDocumento(titulo);
		dashboard.setConteudoDocumento(conteudo);
		System.err.println("send titulo " + titulo);
		System.err.println("send conteudo " + conteudo);
	}
	
	public void receiveFromServer() throws MalformedURLException, RemoteException, NotBoundException{
		DocumentoInterface doc = (DocumentoInterface) Naming.lookup("rmi://localhost:1099/Doc");
		textFieldTitulo.setText(doc.getTituloDocumento());
		textAreaConteudo.setText(doc.getConteudoDocumento());
		System.out.println("1 " + doc.getTituloDocumento());
		System.out.println("11 " + doc.getConteudoDocumento());
	}
	
	/**
	 * Create the frame.
	 */
	public Dashboard() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTituloDoDocumento = new JLabel("Titulo do Documento:");
		lblTituloDoDocumento.setBounds(10, 11, 132, 14);
		contentPane.add(lblTituloDoDocumento);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(152, 8, 335, 20);
		contentPane.add(textFieldTitulo);
		textFieldTitulo.setColumns(10);
		
		JLabel lblContedo = new JLabel("Conte\u00FAdo:");
		lblContedo.setBounds(10, 36, 102, 14);
		contentPane.add(lblContedo);
		
		textAreaConteudo = new JTextArea();
		textAreaConteudo.setBounds(10, 61, 447, 191);
		contentPane.add(textAreaConteudo);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				while (true){
					String titulo = textFieldTitulo.getText();
					String conteudo = textAreaConteudo.getText();
					
					dashboard.setTituloDocumento(titulo);
					dashboard.setConteudoDocumento(conteudo);
				}
			}
		});
		btnSalvar.setBounds(466, 62, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DocumentoInterface doc = (DocumentoInterface) Naming.lookup("rmi://localhost:1099/Doc");
					textFieldTitulo.setText(doc.getTituloDocumento());
					textAreaConteudo.setText(doc.getConteudoDocumento());
				} catch (MalformedURLException | RemoteException
						| NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBuscar.setBounds(466, 130, 89, 23);
		contentPane.add(btnBuscar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textAreaConteudo.setText("");
				textFieldTitulo.setText("");
				textFieldTitulo.requestFocus();
			}
		});
		btnLimpar.setBounds(467, 96, 89, 23);
		contentPane.add(btnLimpar);
	}
}
