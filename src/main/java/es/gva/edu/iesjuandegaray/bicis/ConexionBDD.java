package es.gva.edu.iesjuandegaray.bicis;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConexionBDD extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JTextField textField = new JTextField();

	// Declaramos la conexion a mysql
	private static Connection con;
	private static Statement s;

	private static DatosJSon dJSon;

	private static int numEst = 3;

	private static final String driver="com.mysql.cj.jdbc.Driver";
	private static final String user="admin";
	private static final String pass="Alba12345!";
	private static final String url="jdbc:mysql://valenbicibd.csbtgwmj7odf.us-east-1.rds.amazonaws.com:3306/valenbicibd";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConexionBDD frame = new ConexionBDD();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConexionBDD() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 592, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Introduce el número de estaciones a consultar");
		lblNewLabel.setBounds(103, 12, 295, 38);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Datos");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dJSon = new DatosJSon(3);

				dJSon.mostrarDatos(3);

				System.out.println("Objeto DatosJSon creado");
				System.out.println("Consultando estaciones...");

			}
		});

		btnNewButton.setBounds(42, 60, 121, 21);
		contentPane.add(btnNewButton);

		textField.setBounds(471, 21, 91, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(201, 80, 361, 159);
		contentPane.add(textArea);
		
		JButton btnNewButton_1 = new JButton("Conectar");

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				conectarBD();

			}
		});

		btnNewButton_1.setBounds(42, 251, 121, 21);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Estado Conexión:");
		lblNewLabel_1.setBounds(201, 251, 121, 17);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Obtener Datos de Estaciones:");
		lblNewLabel_2.setBounds(201, 62, 197, 17);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("Añadir a BDD");
		btnNewButton_2.setBounds(42, 289, 121, 21);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("Primero Obtener Datos de Estaciones y Conectar con BDD");
		lblNewLabel_3.setBounds(201, 289, 356, 17);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton_3 = new JButton("Cerrar Conexión");
		btnNewButton_3.setBounds(201, 354, 132, 27);
		contentPane.add(btnNewButton_3);
	}
	public static void conectarBD() {

	    try {

	        Class.forName(driver);

	        con = DriverManager.getConnection(url, user, pass);
	        
	        System.out.println("URL = " + url);
	        System.out.println("USER = " + user);
	        System.out.println("PASS = " + pass);

	        System.out.println("Conexión realizada correctamente");

	    } catch (ClassNotFoundException ex) {

	        Logger.getLogger(ConexionBDD.class.getName()).log(Level.SEVERE, null, ex);

	    } catch (SQLException ex) {

	        Logger.getLogger(ConexionBDD.class.getName()).log(Level.SEVERE, null, ex);

	    }

	}

	}

