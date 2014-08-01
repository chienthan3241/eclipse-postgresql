package testui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Window.Type;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JSeparator;
import  javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;


public class mygui extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mygui frame = new mygui();
					//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
	public mygui() {
		setForeground(Color.GRAY);
		setTitle("hessen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 949, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("max LKW(kmh):");
		lblNewLabel.setBounds(146, 15, 104, 14);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(260, 12, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblMaxPkwkmh = new JLabel("Max PKW(kmh):");
		lblMaxPkwkmh.setBounds(146, 46, 104, 14);
		lblMaxPkwkmh.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblMaxPkwkmh);
		
		textField_1 = new JTextField();
		textField_1.setBounds(260, 43, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Show Data");
		btnNewButton_1.setBounds(10, 11, 109, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
			        int num1 = Integer.parseInt(textField.getText());
			        int num2 = Integer.parseInt(textField_1.getText());
			        
			        Connection c = null;
				    Statement stmt = null;
				    try {
						c = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/detektordaten_hessen", "postgres", "Password2013");
						stmt = c.createStatement();
						String sql = "SELECT * FROM mdp WHERE (flow_lkw > 0 AND speed_lkw < " + Integer.toString(num1) + " ) AND (flow_pkw > 0 AND speed_pkw < " + Integer.toString(num2) + " ) ORDER BY site asc, tsp asc ";
						ResultSet rs = stmt.executeQuery(sql);
						DefaultTableModel dtm = new DefaultTableModel(0, 0);
						//add header of the table
						String header[] = new String[] { "site", "tsp", "flow_lkw", "speed_lkw", "flow_pkw", "speed_pkw", "flow_all", "speed_all", "concentration" };
						//add header in table model
						 dtm.setColumnIdentifiers(header);
						 table.setModel(dtm);						 
						while ( rs.next() ) {
							dtm.addRow(new Object[] { rs.getString("site"), rs.getString("tsp"), rs.getString("flow_lkw"),rs.getString("speed_lkw"),
													  rs.getString("flow_pkw"),rs.getString("speed_pkw"), rs.getString("flow_all"),rs.getString("speed_all"),
													  rs.getString("concentration") });
				         }
						
						 rs.close();			         
				         stmt.close();
				         c.close();
					} catch (SQLException e) { 					
						return; 
					}
			        			        
			    } catch (NumberFormatException e) {
			        JOptionPane.showConfirmDialog(null, "Please enter numbers only", "validate", JOptionPane.CANCEL_OPTION);
			    }
				
				
			}
		});
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("clear");
		btnNewButton_2.setBounds(10, 42, 109, 23);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
			}
		});
		contentPane.add(btnNewButton_2);	
		
		JSeparator separator = new JSeparator();
		separator.setBounds(268, 333, 1, 2);
		contentPane.add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 87, 913, 402);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);	
		scrollPane.setViewportView(table);
	}	
}
