package density_pkg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import javax.swing.JProgressBar;
import javax.swing.JList;
import javax.swing.JEditorPane;

import jsyntaxpane.DefaultSyntaxKit;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import com.toedter.calendar.JDateChooser;



@SuppressWarnings("serial")
public class Density_Calc extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel_1;
	private JTextField filename;
	private JTextField textField_2;
	private JTable table;
	private JFileChooser fileChooser1 = new JFileChooser();
	private JFileChooser fileChooser2 = new JFileChooser();
	private FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv", "csv");
	private JLabel lblLkw;
	private JLabel lblPkw;
	private JTextField textField_1;
	private JButton btnNewButton_2;
	private JButton btnExport;
	private JTextField textField_3;
	@SuppressWarnings("rawtypes")
	private JList list1;
	@SuppressWarnings("rawtypes")
	private JList list2;
	private JEditorPane editorPane;
	@SuppressWarnings("rawtypes")
	private DefaultListModel listModel_source = new DefaultListModel();
	@SuppressWarnings("rawtypes")
	private DefaultListModel listModel_des = new DefaultListModel();
	@SuppressWarnings("rawtypes")
	private DefaultListModel listModel_reset = new DefaultListModel();
	private JProgressBar progressBar;
	private String sql;
	private JScrollPane scrollPane_1;
	private Boolean error;
	private JButton btnNewButton_4;
	private import_from_mst_Task task1;
	private JButton btnLoadFromMdp;
	private import_from_mdp_Task task2;
	private export_induktiv_Task task3;
	private JButton button;
	private JButton button_1;
	private JButton btnNewButton_3;
	private import_from_file_Task task4;
	private export_density_Task task5;
	private JButton btnNewButton;
	private JProgressBar progressBar_1;
	private JLabel lblSelectFahrstreifen;
	private JLabel lblNewLabel_2;
	private JLabel lblDateTo;
	private JLabel lblHhmmss;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	private JComboBox comboBox_3;
	private JLabel lblHhmmss_1;
	private JComboBox comboBox_4;
	private JComboBox comboBox_5;
	private JComboBox comboBox_6;
	private JButton btnClearAll;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	private JButton btnExecute;
	private show_density_on_table_without_set_sprung task6;
	private int line_nr;
	private JButton btnNewButton_5;
	private show_density_on_chart_with_set_sprung task7;
	private show_density_on_chart_without_set_sprung task8;
	private JButton btnNewButton_6;
	private show_stosswellen_on_chart task9;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Density_Calc frame = new Density_Calc();					
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
	@SuppressWarnings("rawtypes")
	public Density_Calc() {
		setTitle("Density Calc");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 1574, 524);
		contentPane.add(tabbedPane);
		
		//tab panel1 to load density from DB
		JPanel panel = new JPanel();
		tabbedPane.addTab("Load density from DB", null, panel, null);
		panel.setPreferredSize(new Dimension(100, 480));
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(BorderFactory.createTitledBorder("Source Induktiv-Schleife"));
		panel_2.setBounds(10, 11, 272, 374);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 23, 252, 276);
		panel_2.add(scrollPane_1);		
				
		list1 = new JList();		
		scrollPane_1.setViewportView(list1);
		
		btnNewButton_3 = new JButton("Load from file");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_3.setEnabled(false);
				//open csv file 
				fileChooser2.setFileFilter(filter);
				 int rueckgabeWert = fileChooser2.showOpenDialog(null);
				 if(rueckgabeWert == JFileChooser.APPROVE_OPTION)
			        {
			         task4 = new import_from_file_Task();
			         task4.execute();
			        }
				 else
				 {
					 btnNewButton_3.setEnabled(true);
				 }
			}
		});
		btnNewButton_3.setBounds(150, 338, 112, 23);
		panel_2.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Load from mst");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNewButton_4.setEnabled(false);
				task1 = new import_from_mst_Task();
				task1.execute();
			}
		});
		btnNewButton_4.setBounds(10, 308, 130, 23);
		panel_2.add(btnNewButton_4);
		
		btnLoadFromMdp = new JButton("Load from mdp");
		btnLoadFromMdp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnLoadFromMdp.setEnabled(false);
				task2 = new import_from_mdp_Task();
				task2.execute();
			}
		});
		btnLoadFromMdp.setBounds(10, 338, 130, 23);
		panel_2.add(btnLoadFromMdp);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(BorderFactory.createTitledBorder("Export all Induktiv-Schleife to file"));
		panel_3.setBounds(10, 390, 272, 95);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		textField_3 = new JTextField();
		textField_3.setBounds(95, 27, 167, 20);
		panel_3.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblFilename = new JLabel("Filename:");
		lblFilename.setBounds(20, 30, 65, 14);
		panel_3.add(lblFilename);
		
		btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_3.getText().equals("")){
					JOptionPane.showConfirmDialog(null, "Please choose filename", "validate", JOptionPane.CANCEL_OPTION);
					return;
				}
				btnExport.setEnabled(false);
				task3 = new export_induktiv_Task();
				task3.execute();
			}
		});
		btnExport.setBounds(173, 61, 89, 23);
		panel_3.add(btnExport);
		
		button = new JButton(">>");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				Object source_selected_value [] = list1.getSelectedValues();
				if (source_selected_value.length == 0){
					JOptionPane.showConfirmDialog(null, "no source Induktiv selected", "validate", JOptionPane.CANCEL_OPTION);
					return;
				}else{				
				for (int i = 0; i < source_selected_value.length; i++) {
					   append_txt(editorPane, "push item: "+source_selected_value[i]);					   
					   listModel_des.addElement(source_selected_value[i]);
					   listModel_source.removeElementAt(get_index_of(listModel_source, source_selected_value[i]));
					}
					list1.setModel(listModel_source);
					list2.setModel(listModel_des);
				}
				append_txt(editorPane, "-----------------------------------------------");
			}
		});
		button.setBounds(292, 99, 49, 23);
		panel.add(button);
		
		button_1 = new JButton("<<");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				Object source_selected_value [] = list2.getSelectedValues();
				if (source_selected_value.length == 0){
					JOptionPane.showConfirmDialog(null, "no destination Induktiv selected", "validate", JOptionPane.CANCEL_OPTION);
					return;
				}else{				
				for (int i = 0; i < source_selected_value.length; i++) {
					   append_txt(editorPane, "pop item: "+source_selected_value[i]);
					   int l_index = 0;
					   while(Integer.parseInt(listModel_source.getElementAt(l_index).toString()) < Integer.parseInt(source_selected_value[i].toString())){
						   l_index++;
					   }
					   listModel_source.add(l_index, source_selected_value[i]);
					   listModel_des.removeElementAt(get_index_of(listModel_des, source_selected_value[i]));
					}
					list1.setModel(listModel_source);
					list2.setModel(listModel_des);
				}
				append_txt(editorPane, "-----------------------------------------------");
			}
		});
		button_1.setBounds(292, 133, 49, 23);
		panel.add(button_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(BorderFactory.createTitledBorder("Destination Induktiv-Schleife"));
		panel_4.setBounds(351, 11, 311, 474);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 26, 290, 406);
		panel_4.add(scrollPane_2);
		
		list2 = new JList();
		scrollPane_2.setViewportView(list2);
		
		btnClearAll = new JButton("Clear all");
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel_des = new DefaultListModel();
				listModel_source = new DefaultListModel();
				for(int i = 0; i< listModel_reset.getSize();i++){
					listModel_source.addElement(listModel_reset.getElementAt(i));
				}
				list1.setModel(listModel_source);
				list2.setModel(listModel_des);
				append_txt(editorPane, "/*clear all Induktiv Schleife*/");
				append_txt(editorPane, "-----------------------------------------------");
			}
		});
		btnClearAll.setBounds(211, 440, 89, 23);
		panel_4.add(btnClearAll);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(BorderFactory.createTitledBorder("Export Density to file"));
		panel_5.setBounds(672, 11, 425, 474);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Filename:");
		lblNewLabel.setBounds(10, 23, 68, 14);
		panel_5.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(151, 20, 264, 20);
		panel_5.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Execute");		
		btnNewButton.setBounds(326, 440, 89, 23);
		panel_5.add(btnNewButton);		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(listModel_des.isEmpty()){
					JOptionPane.showConfirmDialog(null, "Please push some Induktiv to calculate Density", "validate", JOptionPane.CANCEL_OPTION);
					return;
				}
				if(textField.getText().equals("")){
					JOptionPane.showConfirmDialog(null, "Please choose filename", "validate", JOptionPane.CANCEL_OPTION);
					return;
				}				
				btnNewButton.setEnabled(false);
				task5 = new export_density_Task();
				task5.execute();
			}
		});
		
		lblSelectFahrstreifen = new JLabel("Select Fahrstreifen:");
		lblSelectFahrstreifen.setBounds(10, 48, 124, 14);
		panel_5.add(lblSelectFahrstreifen);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(151, 45, 46, 20);
		panel_5.add(comboBox);
		
		lblNewLabel_2 = new JLabel("Date from:");
		lblNewLabel_2.setBounds(10, 103, 68, 14);
		panel_5.add(lblNewLabel_2);
		
		lblDateTo = new JLabel("Date to:");
		lblDateTo.setBounds(10, 305, 46, 14);
		panel_5.add(lblDateTo);	
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(75, 103, 102, 20);
		dateChooser.setDate(new Date(112,5,1));
		panel_5.add(dateChooser);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(75, 299, 102, 20);
		dateChooser_1.setDate(new Date(112,11,31));
		panel_5.add(dateChooser_1);
		
		lblHhmmss = new JLabel("HH:mm:ss");
		lblHhmmss.setBounds(187, 106, 62, 14);
		panel_5.add(lblHhmmss);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		comboBox_1.setBounds(255, 103, 46, 20);
		panel_5.add(comboBox_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		comboBox_2.setBounds(314, 103, 46, 20);
		panel_5.add(comboBox_2);
		
		comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		comboBox_3.setBounds(370, 103, 45, 20);
		panel_5.add(comboBox_3);
		
		lblHhmmss_1 = new JLabel("HH:mm:ss");
		lblHhmmss_1.setBounds(187, 305, 62, 14);
		panel_5.add(lblHhmmss_1);
		
		comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		comboBox_4.setSelectedIndex(23);
		comboBox_4.setBounds(255, 302, 46, 20);
		panel_5.add(comboBox_4);
		
		comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		comboBox_5.setSelectedIndex(59);
		comboBox_5.setBounds(310, 302, 50, 20);
		panel_5.add(comboBox_5);
		
		comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		comboBox_6.setSelectedIndex(59);
		comboBox_6.setBounds(370, 302, 45, 20);
		panel_5.add(comboBox_6);			
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(BorderFactory.createTitledBorder("Loging"));
		panel_6.setBounds(1108, 11, 451, 474);
		panel.add(panel_6);
		panel_6.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 21, 433, 385);
		panel_6.add(scrollPane_3);
		
		DefaultSyntaxKit.initKit();
		editorPane = new JEditorPane();
		scrollPane_3.setViewportView(editorPane);
		editorPane.setContentType("text/sql");		
		
		progressBar = new JProgressBar();
		progressBar.setBounds(10, 433, 433, 30);		
		panel_6.add(progressBar);				
		
		// END Tab Load density from DB
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Density Sprung Calculator", null, panel_1, null);
		panel_1.setLayout(null);
		
		lblNewLabel_1 = new JLabel("Choose csv file:");
		lblNewLabel_1.setBounds(20, 11, 95, 14);
		panel_1.add(lblNewLabel_1);
		
		filename = new JTextField();
		filename.setEnabled(false);
		filename.setBounds(153, 8, 364, 20);
		panel_1.add(filename);
		filename.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Browser");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//open csv file 
				fileChooser1.setFileFilter(filter);
				 int rueckgabeWert = fileChooser1.showOpenDialog(null);
				 if(rueckgabeWert == JFileChooser.APPROVE_OPTION)
			        {
			             // Ausgabe der ausgewaehlten Datei
					 filename.setText(fileChooser1.getSelectedFile().getAbsolutePath());
			        }
			}
		});
		btnNewButton_1.setBounds(527, 7, 137, 23);
		panel_1.add(btnNewButton_1);
		
		JLabel lblSetDensitySprung = new JLabel("Set Density Sprung:");
		lblSetDensitySprung.setBounds(20, 44, 130, 14);
		panel_1.add(lblSetDensitySprung);
		
		textField_2 = new JTextField();
		textField_2.setBounds(187, 43, 54, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		btnExecute = new JButton("Show Density on table without set Sprung");
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(filename.getText().equals("")){
					JOptionPane.showConfirmDialog(null, "please choose csv files", "validate", JOptionPane.CANCEL_OPTION);					
					return;
				}				
				btnExecute.setEnabled(false);
				task6 = new show_density_on_table_without_set_sprung();
				task6.execute();				
			}
		});
		btnExecute.setBounds(361, 40, 275, 23);
		panel_1.add(btnExecute);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 103, 1520, 382);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);	
		
		lblLkw = new JLabel("LKW:");
		lblLkw.setBounds(153, 46, 46, 14);
		panel_1.add(lblLkw);
		
		lblPkw = new JLabel("PKW:");
		lblPkw.setBounds(251, 46, 46, 14);
		panel_1.add(lblPkw);
		
		textField_1 = new JTextField();
		textField_1.setBounds(297, 43, 54, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		btnNewButton_2 = new JButton("Show on Chart without set  Sprung");
		btnNewButton_2.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent arg0) {				
				if(filename.getText().equals("")){
					JOptionPane.showConfirmDialog(null, "please choose csv files", "validate", JOptionPane.CANCEL_OPTION);					
					return;
				}
				btnNewButton_2.setEnabled(false);
				task8 = new show_density_on_chart_without_set_sprung();
				task8.execute();
			}
		});
		btnNewButton_2.setBounds(646, 40, 261, 23);
		panel_1.add(btnNewButton_2);
		
		progressBar_1 = new JProgressBar();
		progressBar_1.setBounds(20, 69, 1520, 23);
		panel_1.add(progressBar_1);
		
		btnNewButton_5 = new JButton("Show on chart with set Sprung");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(filename.getText().equals("")){
					JOptionPane.showConfirmDialog(null, "please choose csv files", "validate", JOptionPane.CANCEL_OPTION);					
					return;
				}
				btnNewButton_5.setEnabled(false);
				task7 = new show_density_on_chart_with_set_sprung();
				task7.execute();
			}
		});
		btnNewButton_5.setBounds(917, 40, 294, 23);
		panel_1.add(btnNewButton_5);
		
		btnNewButton_6 = new JButton("Sto\u00DFwellen calculation and show on chart");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				if(filename.getText().equals("")){
					JOptionPane.showConfirmDialog(null, "please choose csv files", "validate", JOptionPane.CANCEL_OPTION);					
					return;
				}
				btnNewButton_6.setEnabled(false);
				task9 = new show_stosswellen_on_chart();
				task9.execute();
			}
		});
		btnNewButton_6.setBounds(1220, 40, 320, 23);
		panel_1.add(btnNewButton_6);
	}
	
	// function append text to Jeditorpane
	public void append_txt(JEditorPane ed, String s){
		Document doc = ed.getDocument();
		try {
			doc.insertString(doc.getLength(), " \n "+s, null);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//get index of one value in JList
	public int get_index_of(DefaultListModel a, Object value){
		for(int i = 0; i < a.getSize();i++){
			if(a.getElementAt(i).equals(value)){
				return i;
			}
		}
		return -1;
	}	
	// function to reset progressBar status
	public void reset_progress(){
		progressBar.setIndeterminate(false);
		progressBar.setValue(0);
		progressBar.setForeground(new Color(163,184,204));
	}
	// function to reset progressBar_1 status
	public void reset_progress1(){
		progressBar_1.setIndeterminate(false);
		progressBar_1.setValue(0);
		progressBar_1.setForeground(new Color(163,184,204));
	}
	//funtion to calculate distand between to point
	public String distand_between(String site1, String site2){
		String result = null;
		sql = "SELECT (ST_Distance_Sphere( "+
				"ST_GeomFromText('POINT(' || (select ST_X(ST_TRANSFORM(coords_4326,4326)) FROM mst where site = 'R"+site1+"') || (select ST_Y(ST_TRANSFORM(coords_4326,4326)) FROM mst where site = 'R"+site1+"') || ')' ),"+
				"ST_GeomFromText('POINT(' || (select ST_X(ST_TRANSFORM(coords_4326,4326)) FROM mst where site = 'R"+site2+"') || (select ST_Y(ST_TRANSFORM(coords_4326,4326)) FROM mst where site = 'R"+site2+"') || ')' ) "+
				")/1000)::numeric(7,3) AS DISTANCE_IN_KM";
		Connection c = null;
	    Statement stmt = null;
	    try {
			c = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/detektordaten_hessen", "postgres", "Password2013");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while ( rs.next() ) {
				result = rs.getString("DISTANCE_IN_KM");
			}
			rs.close();			         
	        stmt.close();
	        c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  null;
		}
	    return result;
		
	}
	//function to get time periode between two time point : return in minute
	public String diff_in_minute(String first_time, String last_time){
		String result = null;
		sql = "SELECT (DATE_PART('day', '"+last_time+"'::timestamp - '"+first_time+"'::timestamp) * 24 + "+
				" DATE_PART('hour', '" +last_time+"'::timestamp - '"+first_time+"'::timestamp)) * 60 + " +
				" DATE_PART('minute', '"+last_time+"'::timestamp - '"+first_time+"'::timestamp) AS RESULT";
		//System.out.println(sql);
		Connection c = null;
	    Statement stmt = null;
	    try {
			c = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/detektordaten_hessen", "postgres", "Password2013");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while ( rs.next() ) {
				result = rs.getString("RESULT");
			}
			rs.close();			         
	        stmt.close();
	        c.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return  null;
		}
	    return result;
	}
	/**
	 * funtion to calculate if next point has Density Sprung
	 * return null or array[site,tsp] of next sprung point
	 */
	public String[] next_sprung(String next_point, String start_time,int duration_in_minute, float sprung, int fahr_streifen){		
		if(next_point == null){
			return null;
		}
		String sql = null;
		sql = "select site ,tsp ,(case when speed_lkw=0 then 0 else flow_lkw/speed_lkw::float end)::numeric(7,2) as density_lkw, "+
				"(case when speed_pkw=0 then 0 else (flow_pkw/speed_pkw)/"+fahr_streifen+"::float end)::numeric(7,2) as density_pkw "+
				" FROM mdp "+
				" WHERE site = '"+next_point +"' AND "+
				"  tsp > '"+start_time+"' AND " + 
				" tsp <= (select TIMESTAMP '"+start_time+"' + '"+duration_in_minute*60+" seconds') " +
				" ORDER BY tsp asc";
		
		boolean found = false;
		String site_i = null;		
		String density_lkw_i = null;
		String density_pkw_i = null;			
		String tsp_j = null;
		String density_lkw_j;
		String density_pkw_j;
		float density_sprung_lkw;
		float density_sprung_pkw;
		Connection c = null;
	    Statement stmt = null;
	    try {
			c = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/detektordaten_hessen", "postgres", "Password2013");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);			
			int count = 0;
			while ( rs.next() ) {
		    	if(count == 0){
		    		site_i =  rs.getString("site");		    		
		    		density_lkw_i = rs.getString("density_lkw");
		    		density_pkw_i = rs.getString("density_pkw");
		    	}else{
		    		tsp_j = rs.getString("tsp");
		    		density_lkw_j = rs.getString("density_lkw");
		    		density_pkw_j = rs.getString("density_pkw");
		    		density_sprung_lkw = Math.abs(Float.parseFloat(density_lkw_i) - Float.parseFloat(density_lkw_j));
					density_sprung_pkw = Math.abs(Float.parseFloat(density_pkw_i) - Float.parseFloat(density_pkw_j));					
					if(density_sprung_lkw>=sprung || density_sprung_pkw >=sprung){
						found = true;
						break;
					}else{						
						density_lkw_i = density_lkw_j;
						density_pkw_i = density_pkw_j;
					}
		    	}
	           count++; 			          
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException e) {			
			e.printStackTrace();
			return null;
		}
	    if(found){
	    	String[] a = {site_i,tsp_j};
	    	return a;
	    }
		return null;
	}
	
	// to create multi task show on progressbar
	// Task import InduktivSchleife from mst
	class import_from_mst_Task extends SwingWorker<Void, Void>{
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public Void doInBackground() throws Exception{
			reset_progress();
			progressBar.setIndeterminate(true);
			String sql;
			sql = "select \n distinct substring(site,2)::int as isch \n from mst \n order by isch asc";
			Connection c = null;
		    Statement stmt = null;
		    listModel_source = new DefaultListModel();
		    listModel_reset = new DefaultListModel();
		    try {
				c = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/detektordaten_hessen", "postgres", "Password2013");
				stmt = c.createStatement();
				append_txt(editorPane, "/*begin to run sql */ \n"+sql);
				ResultSet rs = stmt.executeQuery(sql);								 
				while ( rs.next() ) {
			       String site = rs.getString("isch");
			       listModel_source.addElement(site);
			       listModel_reset.addElement(site);
			    }
				list1 = new JList(listModel_source);
				scrollPane_1.setViewportView(list1);
				 rs.close();			         
		         stmt.close();
		         c.close();
		        progressBar.setIndeterminate(false);
		        progressBar.setValue(100);
		        append_txt(editorPane, "/*done!*/");
		        error = false;
			} catch (SQLException er) { 				
				System.out.println(er.getMessage());				
				append_txt(editorPane, "/*fails: "+er.getMessage()+"*/");				
				error = true;
				return null; 
			}
			return null;
		}
		@Override
		public void done(){
			append_txt(editorPane, "-----------------------------------------------");
			if(error){
				progressBar.setIndeterminate(false);
				progressBar.setValue(100);
				progressBar.setForeground(Color.red);	
				btnNewButton_4.setEnabled(true);
			}else{
				progressBar.setIndeterminate(false);
				progressBar.setValue(100);
				btnNewButton_4.setEnabled(true);
			}
		}
	}
	
	// Task  import InduktivSchleife from mst
	class import_from_mdp_Task extends SwingWorker<Void, Void>{
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public Void doInBackground(){
			reset_progress();
			progressBar.setIndeterminate(true);
			sql = "select \n distinct site \n from mdp \n order by site asc";
			Connection c = null;
		    Statement stmt = null;
		    listModel_source = new DefaultListModel();
		    listModel_reset = new DefaultListModel();
		    try {
				c = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/detektordaten_hessen", "postgres", "Password2013");
				stmt = c.createStatement();	
				append_txt(editorPane, "/*begin to run sql */ \n"+sql);
				ResultSet rs = stmt.executeQuery(sql);									 
				while ( rs.next() ) {
			       String site = rs.getString("site");
			       listModel_source.addElement(site);
			       listModel_reset.addElement(site);
			    }
				list1 = new JList(listModel_source);
				scrollPane_1.setViewportView(list1);
				 rs.close();			         
		         stmt.close();
		         c.close();
		        error = false;
		        append_txt(editorPane, "/*done!*/");
			} catch (SQLException er) { 
				System.out.println(er.getMessage());								
				append_txt(editorPane, "/*fails: "+er.getMessage()+"*/");
				error = true;
				return null; 
			}
			return null;
		}
		@Override	
		public void done(){
			append_txt(editorPane, "-----------------------------------------------");
			if(error){
				progressBar.setIndeterminate(false);
				progressBar.setValue(100);
				progressBar.setForeground(Color.red);	
				btnLoadFromMdp.setEnabled(true);
			}else{
				progressBar.setIndeterminate(false);
				progressBar.setValue(100);
				btnLoadFromMdp.setEnabled(true);
			}
		}
	}
	
	// Task  import InduktivSchleife from file
	class import_from_file_Task extends SwingWorker<Void, Void>{
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public Void doInBackground(){
			reset_progress();
			progressBar.setIndeterminate(true);
			append_txt(editorPane, "/*Begin to read file: "+fileChooser2.getSelectedFile().getAbsolutePath()+"*/");			
			listModel_source = new DefaultListModel();
			listModel_reset = new DefaultListModel();
			try {
				String sCurrentLine;
				FileInputStream fstream = new FileInputStream(fileChooser2.getSelectedFile().getAbsolutePath());
				@SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));				
				while ((sCurrentLine = br.readLine()) != null) {	
					listModel_source.addElement(sCurrentLine.trim());
					listModel_reset.addElement(sCurrentLine.trim());
				}
				list1 = new JList(listModel_source);
				scrollPane_1.setViewportView(list1);
				error = false;
		        append_txt(editorPane, "/*done!*/");
				
			} catch (FileNotFoundException ej) {
				ej.printStackTrace();
				error = true;
				append_txt(editorPane, ej.getMessage());
			} catch (IOException ej) {
				ej.printStackTrace();
				error = true;
				append_txt(editorPane, ej.getMessage());
			}
			return null;
		}
		@Override		
		public void done(){		
			append_txt(editorPane, "-----------------------------------------------");
			if(error){
				progressBar.setIndeterminate(false);
				progressBar.setValue(100);
				progressBar.setForeground(Color.red);	
				btnNewButton_3.setEnabled(true);
			}else{
				progressBar.setIndeterminate(false);
				progressBar.setValue(100);
				btnNewButton_3.setEnabled(true);
			}
		}
	}
	
	// Task  export InduktivSchleife to file
	class export_induktiv_Task extends SwingWorker<Void, Void>{
		@Override
		public Void doInBackground(){			
			reset_progress();
			progressBar.setIndeterminate(true);
			sql = "select \n distinct site \n from mdp \n order by site asc";
			Connection c = null;
		    Statement stmt = null;		    
		    try {
				c = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/detektordaten_hessen", "postgres", "Password2013");
				stmt = c.createStatement();
				append_txt(editorPane, "/*begin to run sql*/ \n "+sql);
				ResultSet rs = stmt.executeQuery(sql);	
				append_txt(editorPane, "/*Done!*/ \n /*Begin to write Induktiv to "+textField_3.getText()+".csv"+" file line by line*/ ");																 
				// write to CSV file
				try
				{													
				    FileWriter writer = new FileWriter(textField_3.getText()+".csv");				    
				    while ( rs.next() ) {
			            String site = rs.getString("site");			           				           
			            writer.append(site);				          
			            if(!rs.isLast()){
			            	writer.append("\n");
			            }
				    }				   
				    writer.flush();
				    writer.close();				   
				    append_txt(editorPane, "/*Done!*/");
				}
				catch(IOException et)
				{
				     et.printStackTrace();
				     append_txt(editorPane, "/*fails: "+et.getMessage()+"*/");
				     error = true;
				     return null;
				}	
				rs.close();			         
		        stmt.close();
		        c.close();
		        error = false;		       
			} catch (SQLException er) { 
				System.out.println(er.getMessage());								
				append_txt(editorPane, "/*fails: "+er.getMessage()+"*/");
				error = true;
				return null; 
			}
			return null;
		}
		@Override			
		public void done(){	
			append_txt(editorPane, "-----------------------------------------------");
			if(error){
				progressBar.setIndeterminate(false);
				progressBar.setValue(100);
				progressBar.setForeground(Color.red);	
				btnExport.setEnabled(true);
			}else{
				progressBar.setIndeterminate(false);
				progressBar.setValue(100);
				btnExport.setEnabled(true);
			}				
		}
	}
	
	// Task  export Density to file
	class export_density_Task extends SwingWorker<Void, Void>{
		@Override
		public Void doInBackground(){
			reset_progress();
			progressBar.setIndeterminate(true);
			String fahr_streifen = comboBox.getSelectedItem().toString();
			Object [] tmp_arr = listModel_des.toArray();
			SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyy-MM-dd");			
			String sql_cond3 = " AND tsp >= '"+fmt.format(dateChooser.getDate()).toString()+" "+comboBox_1.getSelectedItem().toString()+":"+comboBox_2.getSelectedItem().toString()+":"+comboBox_3.getSelectedItem().toString()+"' \n ";
			sql_cond3 = sql_cond3 + " AND tsp <= '"+fmt.format(dateChooser_1.getDate()).toString()+" "+comboBox_4.getSelectedItem().toString()+":"+comboBox_5.getSelectedItem().toString()+":"+comboBox_6.getSelectedItem().toString()+"' \n ";
			//build query base on destination Induktiv-Schleife	
			String sql_cond1 = " site in (";
			String sql_cond2 = " CASE \n ";
			for(int i = 0; i <tmp_arr.length; i++){
				sql_cond1 = sql_cond1 + String.valueOf(tmp_arr[i]) + ",";
				sql_cond2 = sql_cond2 + " WHEN site = " + String.valueOf(tmp_arr[i]) + " THEN " + String.valueOf(i+1) + " \n ";
			}
			sql_cond1 = sql_cond1.substring(0, sql_cond1.length()-1);
			sql_cond1 = sql_cond1 + ") \n ";
			sql_cond2 = sql_cond2 + " ELSE site END ASC ";
			sql = "SELECT \n"
					+ " site, tsp,  flow_lkw,  speed_lkw, flow_pkw, speed_pkw, \n "
					+ "(case when speed_lkw=0 then 0 else flow_lkw/speed_lkw::float end)::numeric(7,2) as density_lkw, \n "
					+ "(case when speed_pkw=0 then 0 else (flow_pkw/speed_pkw)/"+fahr_streifen+"::float end)::numeric(7,2) as density_pkw \n "
					+ "FROM mdp \n "
					+ "WHERE \n " + sql_cond1 + sql_cond3 + " AND concentration != -1 \n "
					+ "ORDER BY \n "
					+ sql_cond2 +", tsp asc";
					;		
			String sql1 = "SELECT  \n count(*) as num  \n FROM mdp  \n WHERE  \n "+ sql_cond1 + sql_cond3 + " AND concentration != -1  \n ";
			Connection c = null;
		    Statement stmt = null;
		    FileWriter writer;
			try {
				writer = new FileWriter(textField.getText()+".csv");
				// write first row = list of induktiv
				String ar_of_induk = "";
			    for(int i = 0; i <tmp_arr.length; i++){
			    	ar_of_induk = ar_of_induk + String.valueOf(tmp_arr[i])+";";
			    }
			    ar_of_induk = ar_of_induk.substring(0, ar_of_induk.length()-1);
			    writer.append(ar_of_induk+" \n");
			    //second row fahrstreifen				    
			    writer.append(fahr_streifen+" \n");
			    //third row write number of lines
			    try {
					c = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/detektordaten_hessen", "postgres", "Password2013");
					stmt = c.createStatement();
					append_txt(editorPane, "/*count number of rows*/ \n "+ sql1);
					ResultSet rs1 = stmt.executeQuery(sql1);
					while(rs1.next()){
				    	writer.append(rs1.getString("num")+" \n");
				    }
					rs1.close();
					append_txt(editorPane, "/*begin to run sql*/ \n "+sql);
					ResultSet rs = stmt.executeQuery(sql);
					//write rows
				    while ( rs.next() ) {
				    	String site 		= rs.getString("site");
			            String tsp 			= rs.getString("tsp");
			            String density_lkw 	= rs.getString("density_lkw");
			            String density_pkw 	= rs.getString("density_pkw");				           
			            writer.append(site+";"+tsp+";"+density_lkw+";"+density_pkw);				          
			            if(!rs.isLast()){
			            	writer.append("\n");
			            }
				    }				   
				    writer.flush();
				    writer.close();				   
				    append_txt(editorPane, "/*Done!*/");
				    rs.close();			         
			        stmt.close();
			        c.close();
			        error = false;
				} catch (SQLException er) {
					append_txt(editorPane, "/*fails: "+er.getMessage()+"*/");
					error = true;
					writer.flush();
				    writer.close();
					return null;
				}
			} catch (IOException et) {
				et.printStackTrace();
				append_txt(editorPane, "/*fails: "+et.getMessage()+"*/");
			    error = true;
			    return null;
			}		   
			return null;
		}
		@Override				
		public void done(){		
			append_txt(editorPane, "-----------------------------------------------");
			if(error){
				progressBar.setIndeterminate(false);
				progressBar.setValue(100);
				progressBar.setForeground(Color.red);	
				btnNewButton.setEnabled(true);
			}else{
				progressBar.setIndeterminate(false);
				progressBar.setValue(100);
				btnNewButton.setEnabled(true);
			}				
		}
	}
	
	// Task to show all density from file to table (without set Sprung)
	class show_density_on_table_without_set_sprung extends SwingWorker<Void, Void>{
		@Override
		public Void doInBackground() {
			reset_progress1();			
			line_nr = 0;
			// calculate density and write to table
			try{
				String sCurrentLine;
				FileInputStream fstream = new FileInputStream(filename.getText());
				@SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				String header[] = new String[] { "site", "tsp", "density_lkw", "density_pkw", "density_Sprung_LKW", "density_Sprung_PKW" };
				DefaultTableModel dtm = new DefaultTableModel(0, 0);
				dtm.setColumnIdentifiers(header);
				table.setModel(dtm);
				String[] items = null;
				int count = 0;
				int progress_status = 0;
				String site_i = null;
				String tsp_i = null;
				String density_lkw_i = null;
				String density_pkw_i = null;
				String site_j;
				String tsp_j;
				String density_lkw_j;
				String density_pkw_j;
				float density_sprung_lkw;
				float density_sprung_pkw;
				System.out.println("begin read file line by line to write in to table");
				while ((sCurrentLine = br.readLine()) != null) {
					if(line_nr == 0 || line_nr == 1 ){
						line_nr++;
						continue;
					}
					if(line_nr == 2){
						progressBar_1.setMinimum(0);
						progressBar_1.setMaximum((int)Integer.parseInt(sCurrentLine.trim()));
						line_nr++;
						continue;
					}					
					items = sCurrentLine.split(";");
					if(count == 0){
						site_i = items[0];
						tsp_i = items[1];
						density_lkw_i = items[2];
						density_pkw_i = items[3];
						dtm.addRow(new Object[] { site_i, tsp_i, density_lkw_i,density_pkw_i, "-", "-"});						
					}else{
						site_j = items[0];
						tsp_j = items[1];
						density_lkw_j = items[2];
						density_pkw_j = items[3];
						if(Integer.parseInt(site_j) == Integer.parseInt(site_i)){
							//calculate absolut value of density sprung to next point
							density_sprung_lkw = Math.abs(Float.parseFloat(density_lkw_i) - Float.parseFloat(density_lkw_j));
							density_sprung_pkw = Math.abs(Float.parseFloat(density_pkw_i) - Float.parseFloat(density_pkw_j));
							dtm.addRow(new Object[] { site_j, tsp_j, density_lkw_j,density_pkw_j, Float.toString(density_sprung_lkw), Float.toString(density_sprung_pkw)});							
							site_i = site_j;
							tsp_i = tsp_j;
							density_lkw_i = density_lkw_j;
							density_pkw_i = density_pkw_j;
						}else{
							count = -1;								
						}
					}					
					count++;					
					progressBar_1.setValue(progress_status);
					progress_status++;
					line_nr++;
				}
				System.out.println("done!");
				error = false;
			} catch (FileNotFoundException ej) {
				ej.printStackTrace();
				error = true;
				btnExecute.setEnabled(true);
			} catch (IOException ej) {
				ej.printStackTrace();
				error = true;
				btnExecute.setEnabled(true);
			} 
			return null;
		}
		@Override
		public void done(){
			if(error){				
				progressBar_1.setForeground(Color.red);					
				btnExecute.setEnabled(true);
			}else{
				progressBar_1.setValue(line_nr-3);
				btnExecute.setEnabled(true);
			}
		}		
	}
	
	// Task show show density on chart with set Sprung
	class show_density_on_chart_with_set_sprung extends SwingWorker<Void, Void>{
		@Override
		public Void doInBackground(){
			reset_progress1();			
			// how many rows in file
			line_nr = 0;		
			Font font = new Font("Dialog",Font.PLAIN,8);
			//read csv file, calculate and display on chart
			try{
				int sprunglkw = Integer.parseInt(textField_2.getText());
				int sprungpkw = Integer.parseInt(textField_1.getText());
				String sCurrentLine;
				FileInputStream fstream = new FileInputStream(filename.getText());
				@SuppressWarnings("resource")
				BufferedReader br1 = new BufferedReader(new InputStreamReader(fstream));
				String[] items = null;
				int count = 0;
				int progress_status = 0;
				String site_i = null;
				String tsp_i = null;
				String density_lkw_i = null;
				String density_pkw_i = null;
				String site_j;
				String tsp_j;
				String density_lkw_j;
				String density_pkw_j;
				float density_sprung_lkw;
				float density_sprung_pkw;
				//create chart									
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssX");
				TimeSeries series = null;
				TimeSeries series1 = null;
				TimeSeriesCollection result = null;						
					
				//**//
				@SuppressWarnings("unused")
				XYDataset dataset = null;
				CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new DateAxis("Time"));
				StandardXYItemRenderer renderer = null;						
				XYPlot subplot = null;
				NumberAxis rangeAxis = null;
				PlotOrientation orientation = PlotOrientation.VERTICAL;
				XYToolTipGenerator toolTipGenerator = new StandardXYToolTipGenerator();
				//**//
				System.out.println("begin read file line by line to draw chart");
				while ((sCurrentLine = br1.readLine()) != null) {
					if(line_nr == 0 || line_nr == 1 ){
						line_nr++;
						continue;
					}
					if(line_nr == 2){
						progressBar_1.setMinimum(0);
						progressBar_1.setMaximum((int)Integer.parseInt(sCurrentLine.trim()));
						line_nr++;
						continue;
					}
					items = sCurrentLine.split(";");
					if(count == 0){
						site_i = items[0];
						tsp_i = items[1];
						density_lkw_i = items[2];
						density_pkw_i = items[3];						
						series = new TimeSeries("P"+site_i.toString()+" LKW",Second.class);
						series1 = new TimeSeries("P"+site_i.toString()+" PKW",Second.class);
						//**//
						result = new TimeSeriesCollection();
						//**//
					}else{
						site_j = items[0];
						tsp_j = items[1];
						density_lkw_j = items[2];
						density_pkw_j = items[3];
						if(Integer.parseInt(site_j) == Integer.parseInt(site_i)){
							//calculate absolut value of density sprung to next point
							density_sprung_lkw = Math.abs(Float.parseFloat(density_lkw_i) - Float.parseFloat(density_lkw_j));
							density_sprung_pkw = Math.abs(Float.parseFloat(density_pkw_i) - Float.parseFloat(density_pkw_j));
							if(density_sprung_lkw>=(float)sprunglkw){										
								try {
									series.add(new Second(format.parse(tsp_j)), density_sprung_lkw);
								} catch (ParseException e) {											
									e.printStackTrace();
								}										
							}
							if(density_sprung_pkw>=(float)sprungpkw){										
								try {											
									series1.add(new Second( format.parse(tsp_j)), density_sprung_pkw);
								} catch (ParseException e) {
									e.printStackTrace();
								}									
							}
							site_i = site_j;
							tsp_i = tsp_j;
							density_lkw_i = density_lkw_j;
							density_pkw_i = density_pkw_j;
						}else{
							result.addSeries(series);
						 	result.addSeries(series1);
						 	//**//
						 	renderer = new StandardXYItemRenderer(StandardXYItemRenderer.DISCONTINUOUS_LINES, toolTipGenerator,null);
						 	renderer.setShapesFilled(Boolean.TRUE);
						 	rangeAxis = new NumberAxis();
						 	rangeAxis.setTickLabelFont(font);
							rangeAxis.setLabelFont(font);
						 	subplot = new XYPlot(result,null,rangeAxis, renderer);
						 	plot.add(subplot);
						 	//**//
							count = -1;									
						}
					}							
					count++;
					progressBar_1.setValue(progress_status);
					progress_status++;
					line_nr++;
				}			
				System.out.println("done!");
				//add the last one
				result.addSeries(series);
				result.addSeries(series1);
				//**//
				renderer = new StandardXYItemRenderer(StandardXYItemRenderer.DISCONTINUOUS_LINES, toolTipGenerator,null);
				renderer.setShapesFilled(Boolean.TRUE);
				rangeAxis = new NumberAxis();
				rangeAxis.setTickLabelFont(font);
				rangeAxis.setLabelFont(font);
				subplot = new XYPlot(result,null,rangeAxis, renderer);
				plot.add(subplot);
				plot.setGap(5.0);
				plot.setOrientation(orientation);
				JFreeChart chart = new JFreeChart("Show Sprung with condition LKW: "+sprunglkw+" PKW: "+sprungpkw, JFreeChart.DEFAULT_TITLE_FONT, plot, false);				
				ChartFrame  frame1 = new ChartFrame("Density", chart);
				Dimension fullScreen = Toolkit.getDefaultToolkit().getScreenSize(); 
				frame1.setPreferredSize(fullScreen);
				frame1.pack();
				frame1.setVisible(true);
				error = false;
				} catch (FileNotFoundException ej) {
					ej.printStackTrace();
					error = true;
					return null;
				} catch (IOException ej) {
					ej.printStackTrace();
					error = true;
					return null;
				} catch(NumberFormatException el){
					error = true;
					JOptionPane.showConfirmDialog(null, "Please enter numbers only for density Sprung", "validate", JOptionPane.CANCEL_OPTION);
					return null;
				}
			return null;
		}
		@Override
		public void done(){
			if(error){
				progressBar_1.setForeground(Color.red);
				btnNewButton_5.setEnabled(true);
			}else{
				progressBar_1.setValue(line_nr-3);
				btnNewButton_5.setEnabled(true);
			}
		}		
	}
	
	// Task to show all density on chart (without set Sprung)
	class show_density_on_chart_without_set_sprung extends SwingWorker<Void, Void>{
		@Override
		public Void doInBackground(){
			reset_progress1();			
			// how many rows in file
			line_nr = 0;	
			Font font = new Font("Dialog",Font.PLAIN,8);
			//read csv file, calculate and display on chart
			try{
				String sCurrentLine;
				FileInputStream fstream = new FileInputStream(filename.getText());
				@SuppressWarnings("resource")
				BufferedReader br1 = new BufferedReader(new InputStreamReader(fstream));
				String[] items = null;
				int count = 0;
				int progress_status = 0;
				String site_i = null;
				String tsp_i = null;
				String density_lkw_i = null;
				String density_pkw_i = null;
				String site_j;
				String tsp_j;
				String density_lkw_j;
				String density_pkw_j;
				float density_sprung_lkw;
				float density_sprung_pkw;
				//create chart									
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssX");
				TimeSeries series = null;
				TimeSeries series1 = null;
				TimeSeriesCollection result = null;						
					
				//**//
				@SuppressWarnings("unused")
				XYDataset dataset = null;
				XYItemRenderer renderer = null;
				NumberAxis rangeAxis = null;
				XYPlot subplot = null;
				CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new DateAxis("Time"));
				//StandardXYItemRenderer renderer = null;					
				
				PlotOrientation orientation = PlotOrientation.VERTICAL;
				XYToolTipGenerator toolTipGenerator = new StandardXYToolTipGenerator();
				//**//
				System.out.println("begin read file line by line to draw chart");
				while ((sCurrentLine = br1.readLine()) != null) {
					if(line_nr == 0 || line_nr == 1 ){
						line_nr++;
						continue;
					}
					if(line_nr == 2){
						progressBar_1.setMinimum(0);
						progressBar_1.setMaximum((int)Integer.parseInt(sCurrentLine.trim()));
						line_nr++;
						continue;
					}
					items = sCurrentLine.split(";");
					if(count == 0){
						site_i = items[0];
						tsp_i = items[1];
						density_lkw_i = items[2];
						density_pkw_i = items[3];
						series = new TimeSeries("P"+site_i.toString()+" LKW",Second.class);
						series1 = new TimeSeries("P"+site_i.toString()+" PKW",Second.class);
						//**//
						result = new TimeSeriesCollection();
						try {
							series.add(new Second(format.parse(tsp_i)), 0);
						} catch (ParseException e) {							
							e.printStackTrace();
							error = true;
							return null;
						}
						try {
							series1.add(new Second( format.parse(tsp_i)), 0);
						} catch (ParseException e) {
							e.printStackTrace();
							error = true;
							return null;
						}
						//**//
					}else{
						site_j = items[0];
						tsp_j = items[1];
						density_lkw_j = items[2];
						density_pkw_j = items[3];
						if(Integer.parseInt(site_j) == Integer.parseInt(site_i)){
							//calculate absolut value of density sprung to next point
							density_sprung_lkw = Float.parseFloat(density_lkw_i) - Float.parseFloat(density_lkw_j);
							density_sprung_pkw = Float.parseFloat(density_pkw_i) - Float.parseFloat(density_pkw_j);
							try {
								series.add(new Second(format.parse(tsp_j)), density_sprung_lkw);
							} catch (ParseException e) {											
								e.printStackTrace();
								error = true;
								return null;
							}										
							try {											
								series1.add(new Second( format.parse(tsp_j)), density_sprung_pkw);
							} catch (ParseException e) {
								error = true;
								e.printStackTrace();
							}						
							
							site_i = site_j;
							tsp_i = tsp_j;
							density_lkw_i = density_lkw_j;
							density_pkw_i = density_pkw_j;
						}else{
							result.addSeries(series);
						 	result.addSeries(series1);
						 	//**//
						 	renderer = new StandardXYItemRenderer(StandardXYItemRenderer.DISCONTINUOUS_LINES, toolTipGenerator,null);
						 	//renderer.setShapesFilled(Boolean.TRUE);
						 	rangeAxis = new NumberAxis(site_i);
						 	rangeAxis.setTickLabelFont(font);
							rangeAxis.setLabelFont(font);
						 	subplot = new XYPlot(result,null,rangeAxis, renderer);						 	
						 	plot.add(subplot);
						 	//**//
							count = -1;									
						}
					}							
					count++;
					progressBar_1.setValue(progress_status);
					progress_status++;
					line_nr++;
				}			
				System.out.println("done!");
				//add the last one
				result.addSeries(series);
				result.addSeries(series1);
				//**//
				renderer = new StandardXYItemRenderer(StandardXYItemRenderer.DISCONTINUOUS_LINES, toolTipGenerator,null);
				//renderer.setShapesFilled(Boolean.TRUE);
				rangeAxis = new NumberAxis(site_i);
				rangeAxis.setTickLabelFont(font);
				rangeAxis.setLabelFont(font);
				subplot = new XYPlot(result,null,rangeAxis, renderer);
				plot.add(subplot);
				plot.setGap(5.0);
				plot.setOrientation(orientation);
				JFreeChart chart = new JFreeChart("Show all Density", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
				ChartFrame  frame1 = new ChartFrame("Density", chart, true);
				Dimension fullScreen = Toolkit.getDefaultToolkit().getScreenSize(); 
				frame1.setPreferredSize(fullScreen);
				frame1.pack();
				frame1.setVisible(true);
				error = false;
			}catch (FileNotFoundException ej){
				ej.printStackTrace();
				error = true;
				return null;
			}catch (IOException ej) {
				ej.printStackTrace();
				error = true;
				return null;
			}
			return null;			
		}
		@Override
		public void done(){
			if(error){
				progressBar_1.setForeground(Color.red);
				btnNewButton_2.setEnabled(true);
			}else{
				progressBar_1.setValue(line_nr-3);
				btnNewButton_2.setEnabled(true);
			}
		}
	}
	
	// Task to show Stoﬂwelle on chart
	class show_stosswellen_on_chart extends SwingWorker<Void, Void>{
		@Override
		public Void doInBackground(){
			reset_progress1();		
			line_nr = 0;
			Font font = new Font("Dialog",Font.PLAIN,8);
			try {
				int sprunglkw = Integer.parseInt(textField_2.getText());
				int sprungpkw = Integer.parseInt(textField_1.getText());
				String sCurrentLine;
				FileInputStream fstream = new FileInputStream(filename.getText());
				@SuppressWarnings("resource")
				BufferedReader br1 = new BufferedReader(new InputStreamReader(fstream));
				String[] items = null;
				String[] induktiv_arr = null;
				int induktiv_index1 = 0;
				int fahr_streifen = 1;
				float distance = 0;//distance of 3 induktiv in Stoﬂwelle
				int time_distance = 1;//time between first point and last point
				int count = 0;
				int progress_status = 0;
				String site_i = null;
				String tsp_i = null;
				String density_lkw_i = null;
				String density_pkw_i = null;
				String site_j;
				String tsp_j;
				String density_lkw_j;
				String density_pkw_j;
				
				float density_sprung_lkw;
				float density_sprung_pkw;
				//create chart									
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssX");
				TimeSeries series = null;
				TimeSeries series1 = null;
				TimeSeriesCollection result = null;						
					
				//**//
				@SuppressWarnings("unused")
				XYDataset dataset = null;
				CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new DateAxis("Time"));
				StandardXYItemRenderer renderer = null;						
				XYPlot subplot = null;
				NumberAxis rangeAxis = null;
				PlotOrientation orientation = PlotOrientation.VERTICAL;
				XYToolTipGenerator toolTipGenerator = new StandardXYToolTipGenerator();
				//**//
				System.out.println("begin read file line by line to draw chart");
				while ((sCurrentLine = br1.readLine()) != null) {
					if(line_nr == 0 ){
						induktiv_arr = sCurrentLine.trim().split(";");
						line_nr++;
						continue;
					}
					if(line_nr==1){
						line_nr++;
						fahr_streifen = (int) Integer.parseInt(sCurrentLine.trim());
						continue;
					}
					if(line_nr == 2){
						progressBar_1.setMinimum(0);
						progressBar_1.setMaximum((int)Integer.parseInt(sCurrentLine.trim()));
						line_nr++;
						continue;
					}
					items = sCurrentLine.split(";");
					if(count == 0){
						site_i = items[0];
						tsp_i = items[1];
						density_lkw_i = items[2];
						density_pkw_i = items[3];						
						series = new TimeSeries("P"+site_i.toString()+" LKW",Second.class);
						series1 = new TimeSeries("P"+site_i.toString()+" PKW",Second.class);
						//**//
						result = new TimeSeriesCollection();
						//**//
					}else{
						site_j = items[0];
						tsp_j = items[1];
						density_lkw_j = items[2];
						density_pkw_j = items[3];
						if(Integer.parseInt(site_j) == Integer.parseInt(site_i)){
							//calculate absolut value of density sprung to next point
							density_sprung_lkw = Math.abs(Float.parseFloat(density_lkw_i) - Float.parseFloat(density_lkw_j));
							density_sprung_pkw = Math.abs(Float.parseFloat(density_pkw_i) - Float.parseFloat(density_pkw_j));
							if(density_sprung_pkw>=(float)sprungpkw){										
								if(induktiv_index1<induktiv_arr.length-2){	
									int loop_stoss = 1;
									int induktiv_index2 = induktiv_index1;
									int induktiv_index3 = induktiv_index1+1;									
									String tsp_tmp = tsp_j;
									boolean flag = true;
									while(loop_stoss<3){
										//loop 3 times to calculate srpung of next and next next point after distance_in_km*10 minute
										String [] reps = next_sprung(induktiv_arr[induktiv_index3],tsp_tmp,(int)Math.round(Float.parseFloat(distand_between(induktiv_arr[induktiv_index2],induktiv_arr[induktiv_index3]))*10),(float)density_sprung_pkw,fahr_streifen);
										distance = distance + Float.parseFloat(distand_between(induktiv_arr[induktiv_index2],induktiv_arr[induktiv_index3]));
										if(reps == null){
											flag = false;
											break;
										}
										tsp_tmp = reps[1];//time of next Sprung
										induktiv_index2++;//next induktiv
										induktiv_index3++;//next induvtiv
										loop_stoss++;
									}
									if(flag){
										time_distance = time_distance + Integer.parseInt(diff_in_minute(tsp_j, tsp_tmp));							
										try {
											//add Stoﬂwelle point with weight = Stoﬂwelle speed
											series.add(new Second(format.parse(tsp_j)), distance*60/time_distance);
										} catch (ParseException e) {											
											e.printStackTrace();
											error = true;
											return null;
										}
									}
								}																		
							}							
							site_i = site_j;
							tsp_i = tsp_j;
							density_lkw_i = density_lkw_j;
							density_pkw_i = density_pkw_j;
						}else{
							induktiv_index1++;
							result.addSeries(series);
						 	//**//
						 	renderer = new StandardXYItemRenderer(StandardXYItemRenderer.SHAPES, toolTipGenerator,null);
						 	renderer.setShapesFilled(Boolean.TRUE);
						 	rangeAxis = new NumberAxis(site_i);
						 	rangeAxis.setTickLabelFont(font);
							rangeAxis.setLabelFont(font);
						 	subplot = new XYPlot(result,null,rangeAxis, renderer);
						 	plot.add(subplot);
						 	//**//
							count = -1;									
						}
					}							
					count++;
					progressBar_1.setValue(progress_status);
					progress_status++;
					line_nr++;
				}			
				System.out.println("done!");
				//add the last one
				result.addSeries(series);
				result.addSeries(series1);
				//**//
				renderer = new StandardXYItemRenderer(StandardXYItemRenderer.SHAPES, toolTipGenerator,null);
				renderer.setShapesFilled(Boolean.TRUE);
				rangeAxis = new NumberAxis(site_i);
				rangeAxis.setTickLabelFont(font);
				rangeAxis.setLabelFont(font);
				subplot = new XYPlot(result,null,rangeAxis, renderer);
				plot.add(subplot);
				plot.setGap(5.0);
				plot.setOrientation(orientation);
				JFreeChart chart = new JFreeChart("Show Stoﬂwellen with condition PKW: "+sprungpkw, JFreeChart.DEFAULT_TITLE_FONT, plot, false);				
				ChartFrame  frame1 = new ChartFrame("Stoﬂwellen", chart);
				Dimension fullScreen = Toolkit.getDefaultToolkit().getScreenSize(); 
				frame1.setPreferredSize(fullScreen);
				frame1.pack();
				frame1.setVisible(true);
				error = false;
				} catch (FileNotFoundException ej) {
					ej.printStackTrace();
					error = true;
					return null;
				} catch (IOException ej) {
					ej.printStackTrace();
					error = true;
					return null;
				} catch(NumberFormatException el){
					error = true;
					JOptionPane.showConfirmDialog(null, "Please enter numbers only for density Sprung", "validate", JOptionPane.CANCEL_OPTION);
					return null;
				}
			return null;
		}
		@Override
		public void done(){
			if(error){
				progressBar_1.setForeground(Color.red);
				btnNewButton_6.setEnabled(true);
			}else{
				progressBar_1.setValue(line_nr-3);
				btnNewButton_6.setEnabled(true);
			}
		}
	}
}
