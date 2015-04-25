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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JRadioButton;

public class Dashboard extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldTitulo;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
					frame.setVisible(true);
					frame.setTitle("Dashboard");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		
		JTextArea textAreaConteudo = new JTextArea();
		textAreaConteudo.setBounds(10, 61, 447, 191);
		contentPane.add(textAreaConteudo);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textFieldTitulo.getText().length() == 0 || textAreaConteudo.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "Você deve preencher o título e o conteúdo!");
				}else{
					String titulo = textFieldTitulo.getText();
					String conteudo = textAreaConteudo.getText();
					DashboardController dashboard = new DashboardController(titulo, conteudo);
				}
			}
		});
		btnSalvar.setBounds(466, 62, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(466, 130, 89, 23);
		contentPane.add(btnExcluir);
		
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
