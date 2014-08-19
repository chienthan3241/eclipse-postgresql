package density_pkg;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
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

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class Density_Calc extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxNewCheckBox_1;
	private JCheckBox chckbxNewCheckBox_2;
	private JCheckBox chckbxNewCheckBox_3;
	private JCheckBox chckbxNewCheckBox_4;
	private JCheckBox chckbxNewCheckBox_5;
	private JCheckBox chckbxNewCheckBox_6;
	private JCheckBox chckbxNewCheckBox_7;
	private JCheckBox chckbxNewCheckBox_8;
	private JCheckBox chckbxNewCheckBox_9;
	private JCheckBox chckbxNewCheckBox_10;
	private JCheckBox chckbxNewCheckBox_11;
	private JCheckBox chckbxNewCheckBox_12;
	private JCheckBox chckbxNewCheckBox_13;
	private JCheckBox chckbxNewCheckBox_14;
	private JCheckBox chckbxNewCheckBox_15;
	private JCheckBox chckbxNewCheckBox_16;
	private JCheckBox chckbxNewCheckBox_17;
	private JCheckBox chckbxNewCheckBox_18;
	private JLabel lblNewLabel_1;
	private JTextField filename;
	private JTextField textField_2;
	private JTable table;
	private JFileChooser fileChooser1 = new JFileChooser();
	private FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv", "csv");
	private JLabel lblLkw;
	private JLabel lblPkw;
	private JTextField textField_1;
	private JButton btnNewButton_2;
	
	
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
	public Density_Calc() {
		setTitle("Density Calc");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.NORTH);
		
		//tab panel1 to load density from DB
		JPanel panel = new JPanel();
		tabbedPane.addTab("Load density from DB", null, panel, null);
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(100, 480));
		
		chckbxNewCheckBox = new JCheckBox("Check all");
		chckbxNewCheckBox.setSelected(true);
		//Add action listener
		chckbxNewCheckBox.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
			        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
			        boolean selected = abstractButton.getModel().isSelected();	
			        if(selected){
			        	chckbxNewCheckBox_1.setSelected(true);
			        	chckbxNewCheckBox_2.setSelected(true);
			        	chckbxNewCheckBox_3.setSelected(true);
			        	chckbxNewCheckBox_4.setSelected(true);
			        	chckbxNewCheckBox_5.setSelected(true);
			        	chckbxNewCheckBox_6.setSelected(true);
			        	chckbxNewCheckBox_7.setSelected(true);
			        	chckbxNewCheckBox_8.setSelected(true);
			        	chckbxNewCheckBox_9.setSelected(true);
			        	chckbxNewCheckBox_10.setSelected(true);
			        	chckbxNewCheckBox_11.setSelected(true);
			        	chckbxNewCheckBox_12.setSelected(true);
			        	chckbxNewCheckBox_13.setSelected(true);
			        	chckbxNewCheckBox_14.setSelected(true);
			        	chckbxNewCheckBox_15.setSelected(true);
			        	chckbxNewCheckBox_16.setSelected(true);
			        	chckbxNewCheckBox_17.setSelected(true);
			        	chckbxNewCheckBox_18.setSelected(true);
			        	
			        }else{
			        	chckbxNewCheckBox_1.setSelected(false);
			        	chckbxNewCheckBox_2.setSelected(false);
			        	chckbxNewCheckBox_3.setSelected(false);
			        	chckbxNewCheckBox_4.setSelected(false);
			        	chckbxNewCheckBox_5.setSelected(false);
			        	chckbxNewCheckBox_6.setSelected(false);
			        	chckbxNewCheckBox_7.setSelected(false);
			        	chckbxNewCheckBox_8.setSelected(false);
			        	chckbxNewCheckBox_9.setSelected(false);
			        	chckbxNewCheckBox_10.setSelected(false);
			        	chckbxNewCheckBox_11.setSelected(false);
			        	chckbxNewCheckBox_12.setSelected(false);
			        	chckbxNewCheckBox_13.setSelected(false);
			        	chckbxNewCheckBox_14.setSelected(false);
			        	chckbxNewCheckBox_15.setSelected(false);
			        	chckbxNewCheckBox_16.setSelected(false);
			        	chckbxNewCheckBox_17.setSelected(false);
			        	chckbxNewCheckBox_18.setSelected(false);
			        }
			   }
			});
		chckbxNewCheckBox.setBounds(30, 25, 97, 23);
		panel.add(chckbxNewCheckBox);
		
		chckbxNewCheckBox_1 = new JCheckBox("R2008002");
		chckbxNewCheckBox_1.setSelected(true);
		//Add action listener
		chckbxNewCheckBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();	
				if(!selected){
					chckbxNewCheckBox.setSelected(false);
				}
			}
		});
		chckbxNewCheckBox_1.setBounds(30, 63, 97, 23);
		panel.add(chckbxNewCheckBox_1);
		
		chckbxNewCheckBox_2 = new JCheckBox("R2007853");
		chckbxNewCheckBox_2.setSelected(true);
		//Add action listener
				chckbxNewCheckBox_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
						boolean selected = abstractButton.getModel().isSelected();	
						if(!selected){
							chckbxNewCheckBox.setSelected(false);
						}
					}
				});
		chckbxNewCheckBox_2.setBounds(163, 63, 97, 23);
		panel.add(chckbxNewCheckBox_2);
		
		chckbxNewCheckBox_3 = new JCheckBox("R2008259");
		chckbxNewCheckBox_3.setSelected(true);
		//Add action listener
				chckbxNewCheckBox_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
						boolean selected = abstractButton.getModel().isSelected();	
						if(!selected){
							chckbxNewCheckBox.setSelected(false);
						}
					}
				});
		chckbxNewCheckBox_3.setBounds(308, 63, 97, 23);
		panel.add(chckbxNewCheckBox_3);
		
		chckbxNewCheckBox_4 = new JCheckBox("R2007956");
		chckbxNewCheckBox_4.setSelected(true);
		//Add action listener
				chckbxNewCheckBox_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
						boolean selected = abstractButton.getModel().isSelected();	
						if(!selected){
							chckbxNewCheckBox.setSelected(false);
						}
					}
				});
		chckbxNewCheckBox_4.setBounds(461, 63, 97, 23);
		panel.add(chckbxNewCheckBox_4);
		
		chckbxNewCheckBox_5 = new JCheckBox("R2007865");
		chckbxNewCheckBox_5.setSelected(true);
		//Add action listener
				chckbxNewCheckBox_5.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
						boolean selected = abstractButton.getModel().isSelected();	
						if(!selected){
							chckbxNewCheckBox.setSelected(false);
						}
					}
				});
		chckbxNewCheckBox_5.setBounds(629, 63, 97, 23);
		panel.add(chckbxNewCheckBox_5);
		
		chckbxNewCheckBox_6 = new JCheckBox("R2008265");
		chckbxNewCheckBox_6.setSelected(true);
		//Add action listener
				chckbxNewCheckBox_6.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
						boolean selected = abstractButton.getModel().isSelected();	
						if(!selected){
							chckbxNewCheckBox.setSelected(false);
						}
					}
				});
		chckbxNewCheckBox_6.setBounds(781, 63, 97, 23);
		panel.add(chckbxNewCheckBox_6);
		
		chckbxNewCheckBox_7 = new JCheckBox("R2007871");
		chckbxNewCheckBox_7.setSelected(true);
		//Add action listener
				chckbxNewCheckBox_7.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
						boolean selected = abstractButton.getModel().isSelected();	
						if(!selected){
							chckbxNewCheckBox.setSelected(false);
						}
					}
				});
		chckbxNewCheckBox_7.setBounds(952, 63, 97, 23);
		panel.add(chckbxNewCheckBox_7);
		
		chckbxNewCheckBox_8 = new JCheckBox("R2007938");
		chckbxNewCheckBox_8.setSelected(true);
		//Add action listener
				chckbxNewCheckBox_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
						boolean selected = abstractButton.getModel().isSelected();	
						if(!selected){
							chckbxNewCheckBox.setSelected(false);
						}
					}
				});
		chckbxNewCheckBox_8.setBounds(1141, 63, 97, 23);
		panel.add(chckbxNewCheckBox_8);
		
		chckbxNewCheckBox_9 = new JCheckBox("R2008313");
		chckbxNewCheckBox_9.setSelected(true);
		//Add action listener
				chckbxNewCheckBox_9.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
						boolean selected = abstractButton.getModel().isSelected();	
						if(!selected){
							chckbxNewCheckBox.setSelected(false);
						}
					}
				});
		chckbxNewCheckBox_9.setBounds(1333, 63, 97, 23);
		panel.add(chckbxNewCheckBox_9);
		
		chckbxNewCheckBox_10 = new JCheckBox("R2007932");
		chckbxNewCheckBox_10.setSelected(true);
		//Add action listener
				chckbxNewCheckBox_10.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
						boolean selected = abstractButton.getModel().isSelected();	
						if(!selected){
							chckbxNewCheckBox.setSelected(false);
						}
					}
				});
		chckbxNewCheckBox_10.setBounds(30, 105, 97, 23);
		panel.add(chckbxNewCheckBox_10);
		
		chckbxNewCheckBox_11 = new JCheckBox("R2008307");
		chckbxNewCheckBox_11.setSelected(true);
		//Add action listener
		chckbxNewCheckBox_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();	
				if(!selected){
					chckbxNewCheckBox.setSelected(false);
				}
			}
		});
		chckbxNewCheckBox_11.setBounds(163, 105, 97, 23);
		panel.add(chckbxNewCheckBox_11);
		
		chckbxNewCheckBox_12 = new JCheckBox("R2007889");
		chckbxNewCheckBox_12.setSelected(true);
		//Add action listener
		chckbxNewCheckBox_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();	
				if(!selected){
					chckbxNewCheckBox.setSelected(false);
				}
			}
		});
		chckbxNewCheckBox_12.setBounds(308, 105, 97, 23);
		panel.add(chckbxNewCheckBox_12);
		
		chckbxNewCheckBox_13 = new JCheckBox("R2007920");
		chckbxNewCheckBox_13.setSelected(true);
		//Add action listener
		chckbxNewCheckBox_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();	
				if(!selected){
					chckbxNewCheckBox.setSelected(false);
				}
			}
		});
		chckbxNewCheckBox_13.setBounds(461, 105, 97, 23);
		panel.add(chckbxNewCheckBox_13);
		
		chckbxNewCheckBox_14 = new JCheckBox("R2008301");
		chckbxNewCheckBox_14.setSelected(true);
		//Add action listener
		chckbxNewCheckBox_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();	
				if(!selected){
					chckbxNewCheckBox.setSelected(false);
				}
			}
		});
		chckbxNewCheckBox_14.setBounds(629, 105, 97, 23);
		panel.add(chckbxNewCheckBox_14);
		
		chckbxNewCheckBox_15 = new JCheckBox("R2007895");
		chckbxNewCheckBox_15.setSelected(true);
		//Add action listener
		chckbxNewCheckBox_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();	
				if(!selected){
					chckbxNewCheckBox.setSelected(false);
				}
			}
		});
		chckbxNewCheckBox_15.setBounds(781, 105, 97, 23);
		panel.add(chckbxNewCheckBox_15);
		
		chckbxNewCheckBox_16 = new JCheckBox("R2007914");
		chckbxNewCheckBox_16.setSelected(true);
		//Add action listener
		chckbxNewCheckBox_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();	
				if(!selected){
					chckbxNewCheckBox.setSelected(false);
				}
			}
		});
		chckbxNewCheckBox_16.setBounds(952, 105, 97, 23);
		panel.add(chckbxNewCheckBox_16);
		
		chckbxNewCheckBox_17 = new JCheckBox("R2008295");
		chckbxNewCheckBox_17.setSelected(true);
		//Add action listener
		chckbxNewCheckBox_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();	
				if(!selected){
					chckbxNewCheckBox.setSelected(false);
				}
			}
		});
		chckbxNewCheckBox_17.setBounds(1141, 105, 97, 23);
		panel.add(chckbxNewCheckBox_17);
		
		chckbxNewCheckBox_18 = new JCheckBox("R2007902");
		chckbxNewCheckBox_18.setSelected(true);
		//Add action listener
		chckbxNewCheckBox_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();	
				if(!selected){
					chckbxNewCheckBox.setSelected(false);
				}
			}
		});
		chckbxNewCheckBox_18.setBounds(1333, 105, 97, 23);
		panel.add(chckbxNewCheckBox_18);
		
		JLabel lblNewLabel = new JLabel("Filename:");
		lblNewLabel.setBounds(30, 170, 68, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(110, 167, 150, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Execute");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!chckbxNewCheckBox.isSelected() && !chckbxNewCheckBox_1.isSelected() && !chckbxNewCheckBox_2.isSelected() && !chckbxNewCheckBox_3.isSelected() 
				&& !chckbxNewCheckBox_4.isSelected() && !chckbxNewCheckBox_5.isSelected() && !chckbxNewCheckBox_6.isSelected() && !chckbxNewCheckBox_7.isSelected() 
				&& !chckbxNewCheckBox_8.isSelected() && !chckbxNewCheckBox_9.isSelected() && !chckbxNewCheckBox_10.isSelected() && !chckbxNewCheckBox_11.isSelected()
				&& !chckbxNewCheckBox_12.isSelected() && !chckbxNewCheckBox_13.isSelected() && !chckbxNewCheckBox_14.isSelected() && !chckbxNewCheckBox_15.isSelected()
				&& !chckbxNewCheckBox_16.isSelected() && !chckbxNewCheckBox_17.isSelected() && !chckbxNewCheckBox_18.isSelected() ){
					JOptionPane.showConfirmDialog(null, "Please choose Induktivschleife", "validate", JOptionPane.CANCEL_OPTION);
					return;
				}
				if(textField.getText().equals("")){
					JOptionPane.showConfirmDialog(null, "Please choose filename", "validate", JOptionPane.CANCEL_OPTION);
					return;
				}
				//run sql and write to file
				Integer[] Induktivschleife = {2008002,2007853,2008259,2007956,2007865,2008265,2007871,2007938,2008313,2007932,2008307,2007889,2007920,2008301,2007895,2007914,2008295,2007902};
				String sql;
				if(chckbxNewCheckBox.isSelected()){
					String sql_cond1 = " site in (";
					String sql_cond2 = " CASE ";
					for(int i = 0; i <Induktivschleife.length; i++){
						sql_cond1 = sql_cond1 + String.valueOf(Induktivschleife[i]) + ",";
						sql_cond2 = sql_cond2 + " WHEN site = " + String.valueOf(Induktivschleife[i]) + " THEN " + String.valueOf(i+1) + " ";
					}
					sql_cond1 = sql_cond1.substring(0, sql_cond1.length()-1);
					sql_cond1 = sql_cond1 + ") ";
					sql_cond2 = sql_cond2 + " ELSE site END ASC ";
					
					sql = " SELECT "
							+ " site, tsp,  flow_lkw,  speed_lkw, flow_pkw, speed_pkw, "
							+ "(case when speed_lkw=0 then 0 else flow_lkw*60/speed_lkw::float end)::numeric(7,2) as density_lkw,  "
							+ "(case when speed_pkw=0 then 0 else flow_pkw*60/speed_pkw::float end)::numeric(7,2) as density_pkw "
							+ "FROM mdp "
							+ "WHERE " + sql_cond1 + " AND concentration != -1 "
							+ "ORDER BY "
							+ sql_cond2 +", tsp asc";
							;
							//System.out.println(sql);
				}else{
					JCheckBox[] checkboxs = {chckbxNewCheckBox_1,chckbxNewCheckBox_2,chckbxNewCheckBox_3,chckbxNewCheckBox_4,chckbxNewCheckBox_5,chckbxNewCheckBox_6,
											 chckbxNewCheckBox_7,chckbxNewCheckBox_8,chckbxNewCheckBox_9,chckbxNewCheckBox_10,chckbxNewCheckBox_11,chckbxNewCheckBox_12,
											 chckbxNewCheckBox_13,chckbxNewCheckBox_14,chckbxNewCheckBox_15,chckbxNewCheckBox_16,chckbxNewCheckBox_17,chckbxNewCheckBox_18};
					String sql_cond1 = " site in (";
					String sql_cond2 = " CASE ";
					for (int i = 0; i < checkboxs.length; i ++ ){
						if(checkboxs[i].isSelected()){
							sql_cond1 = sql_cond1 + String.valueOf(Induktivschleife[i]) + ",";
							sql_cond2 = sql_cond2 + " WHEN site = " + String.valueOf(Induktivschleife[i]) + " THEN " + String.valueOf(i+1) + " ";
						}
					}
					sql_cond1 = sql_cond1.substring(0, sql_cond1.length()-1);
					sql_cond1 = sql_cond1 + ") ";
					sql_cond2 = sql_cond2 + " ELSE site END ASC ";
					sql = " SELECT "
							+ " site, tsp,  flow_lkw,  speed_lkw, flow_pkw, speed_pkw, "
							+ "(case when speed_lkw=0 then 0 else flow_lkw*60/speed_lkw::float end)::numeric(7,2) as density_lkw,  "
							+ "(case when speed_pkw=0 then 0 else flow_pkw*60/speed_pkw::float end)::numeric(7,2) as density_pkw "
							+ "FROM mdp "
							+ "WHERE " + sql_cond1 + " AND concentration != -1 "
							+ "ORDER BY "
							+ sql_cond2 +", tsp asc";
							;
							//System.out.println(sql);
				}
				
				/*
				sql = " SELECT "
						+ "distinct site, tsp,  flow_lkw,  speed_lkw, flow_pkw, speed_pkw, "
						+ "(case when speed_lkw=0 then 0 else flow_lkw*60/speed_lkw::float end)::numeric(7,2) as density_lkw,  "
						+ "(case when speed_pkw=0 then 0 else flow_pkw*60/speed_pkw::float end)::numeric(7,2) as density_pkw "
						+ "FROM mdp "
						+ "WHERE site in (2000000,2000003) "
						+ "ORDER BY "
						+ "site asc,"
						+ "tsp asc";
				*/
				Connection c = null;
			    Statement stmt = null;
			    System.out.println(sql);
			    try {
					c = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/detektordaten_hessen", "postgres", "Password2013");
					stmt = c.createStatement();					
					ResultSet rs = stmt.executeQuery(sql);										 
					// write to CSV file
					try
					{
						System.out.println("Begin write to csv...");									
					    FileWriter writer = new FileWriter(textField.getText()+".csv");
					    
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
					    System.out.println("done!");
					}
					catch(IOException et)
					{
					     et.printStackTrace();
					}
					 rs.close();			         
			         stmt.close();
			         c.close();
				} catch (SQLException er) { 					
					return; 
				}
				
			}
		});
		btnNewButton.setBounds(30, 221, 89, 23);
		panel.add(btnNewButton);
		// END Tab Load density from DB
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Density Sprung Calculator", null, panel_1, null);
		panel_1.setLayout(null);
		
		lblNewLabel_1 = new JLabel("Choose json file:");
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
		lblSetDensitySprung.setBounds(20, 44, 137, 14);
		panel_1.add(lblSetDensitySprung);
		
		textField_2 = new JTextField();
		textField_2.setBounds(283, 41, 86, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnExecute = new JButton("Execute");
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(filename.getText().equals("")){
					JOptionPane.showConfirmDialog(null, "please choose json files", "validate", JOptionPane.CANCEL_OPTION);					
					return;
				}				
				
				try{
					//read csv file, calculate and display on table
					int sprunglkw = Integer.parseInt(textField_2.getText());
					int sprungpkw = Integer.parseInt(textField_1.getText());					
					
					try {
						String sCurrentLine;
						FileInputStream fstream = new FileInputStream(filename.getText());
						@SuppressWarnings("resource")
						BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
						String[] items = null;
						int count = 0;						
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
						DefaultTableModel dtm = new DefaultTableModel(0, 0);
						//add header of the table
						String header[] = new String[] { "site", "tsp", "density_lkw", "density_pkw", "density_Sprung_LKW", "density_Sprung_PKW" };
						//add header in table model
						dtm.setColumnIdentifiers(header);
						table.setModel(dtm);
						System.out.println("begin read file line by line");
						while ((sCurrentLine = br.readLine()) != null) {
							items = sCurrentLine.split(";");
							if(count == 0){
								site_i = items[0];
								tsp_i = items[1];
								density_lkw_i = items[2];
								density_pkw_i = items[3];
							}else{
								site_j = items[0];
								tsp_j = items[1];
								density_lkw_j = items[2];
								density_pkw_j = items[3];
								if(Integer.parseInt(site_j) == Integer.parseInt(site_i)){
									//calculate absolut value of density sprung to next point
									density_sprung_lkw = Math.abs(Float.parseFloat(density_lkw_i) - Float.parseFloat(density_lkw_j));
									density_sprung_pkw = Math.abs(Float.parseFloat(density_pkw_i) - Float.parseFloat(density_pkw_j));
									if(density_sprung_lkw>=(float)sprunglkw || density_sprung_pkw>=(float)sprungpkw){
										dtm.addRow(new Object[] { site_i, tsp_i, density_lkw_i,density_pkw_i, Float.toString(density_sprung_lkw), Float.toString(density_sprung_pkw)});
									}
									site_i = site_j;
									tsp_i = tsp_j;
									density_lkw_i = density_lkw_j;
									density_pkw_i = density_pkw_j;
								}else{
									count = -1;
									
								}
							}
							
							count++;
						}
						
					} catch (FileNotFoundException ej) {
						ej.printStackTrace();
					} catch (IOException ej) {
						ej.printStackTrace();
					} 
					
				}catch(NumberFormatException el){
					JOptionPane.showConfirmDialog(null, "Please enter numbers only for density Sprung", "validate", JOptionPane.CANCEL_OPTION);
				}
				
			}
		});
		btnExecute.setBounds(527, 38, 137, 23);
		panel_1.add(btnExecute);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 103, 1520, 398);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);	
		
		lblLkw = new JLabel("LKW:");
		lblLkw.setBounds(242, 44, 46, 14);
		panel_1.add(lblLkw);
		
		lblPkw = new JLabel("PKW:");
		lblPkw.setBounds(385, 44, 46, 14);
		panel_1.add(lblPkw);
		
		textField_1 = new JTextField();
		textField_1.setBounds(431, 41, 86, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		btnNewButton_2 = new JButton("show on Chart");
		btnNewButton_2.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if(filename.getText().equals("")){
					JOptionPane.showConfirmDialog(null, "please choose json files", "validate", JOptionPane.CANCEL_OPTION);					
					return;
				}
				try{
					//read csv file, calculate and display on chart
					int sprunglkw = Integer.parseInt(textField_2.getText());
					int sprungpkw = Integer.parseInt(textField_1.getText());
					try {
						String sCurrentLine;
						FileInputStream fstream = new FileInputStream(filename.getText());
						@SuppressWarnings({ "resource", "unused" })
						BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
						@SuppressWarnings("resource")
						BufferedReader br1 = new BufferedReader(new InputStreamReader(fstream));
						String[] items = null;
						int count = 0;
						int step = 0;
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
						while ((sCurrentLine = br1.readLine()) != null) {
							
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
											series.add(new Second(format.parse(tsp_i)), density_sprung_lkw);
										} catch (ParseException e) {											
											e.printStackTrace();
										}										
									}
									if(density_sprung_pkw>=(float)sprungpkw){
										if(step == 1){
										try {
											//series1.add(new Second( format.parse(tsp_i).getSeconds(), format.parse(tsp_i).getMinutes(),format.parse(tsp_i).getHours(),format.parse(tsp_i).getDate(),format.parse(tsp_i).getMonth()+1,format.parse(tsp_i).getYear()+1900), density_sprung_pkw);
											series1.add(new Second( format.parse(tsp_i)), density_sprung_pkw);
										} catch (ParseException e) {
											e.printStackTrace();
										}
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
							 		renderer = new StandardXYItemRenderer(StandardXYItemRenderer.SHAPES, toolTipGenerator,null);
							 		renderer.setShapesFilled(Boolean.TRUE);
							 		rangeAxis = new NumberAxis();
							 		subplot = new XYPlot(result,null,rangeAxis, renderer);
							 		plot.add(subplot);
							 		//**//
									count = -1;
									step++;
								}
							}							
							count++;
						}			
							//add the last one
							result.addSeries(series);
							result.addSeries(series1);
							//**//
							renderer = new StandardXYItemRenderer(StandardXYItemRenderer.SHAPES, toolTipGenerator,null);
							renderer.setShapesFilled(Boolean.TRUE);
					 		rangeAxis = new NumberAxis();
					 		subplot = new XYPlot(result,null,rangeAxis, renderer);
					 		plot.add(subplot);
					 		plot.setGap(5.0);
					 		plot.setOrientation(orientation);
					 		JFreeChart chart = new JFreeChart("Density", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
					 		//**//
							//Chart config
							/*
						 	XYDataset dataset = (XYDataset)(result);
						 	PlotOrientation orientation = PlotOrientation.VERTICAL;
						 	DateAxis xAxis = new DateAxis("Time");
						 	xAxis.setDateFormatOverride(new SimpleDateFormat("dd/MM/yyyy"));
						 	NumberAxis yAxis = new NumberAxis("Sprung");
						 	yAxis.setAutoRangeIncludesZero(false);
						 	XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
						 	XYToolTipGenerator toolTipGenerator = new StandardXYToolTipGenerator();
						 	XYURLGenerator urlGenerator = null;						 	
						 	StandardXYItemRenderer renderer = new StandardXYItemRenderer(StandardXYItemRenderer.SHAPES, toolTipGenerator, urlGenerator);
						 	renderer.setShapesFilled(Boolean.TRUE);
						    plot.setRenderer(renderer);
						    plot.setOrientation(orientation);
						    
						    JFreeChart chart = new JFreeChart("Density", JFreeChart.DEFAULT_TITLE_FONT, plot, true);					 
						    */
						    //Show chart
						    ChartFrame  frame1 = new ChartFrame("scaterPlot", chart);
						    Dimension fullScreen = Toolkit.getDefaultToolkit().getScreenSize(); 
							frame1.setPreferredSize(fullScreen);
							frame1.pack();
							frame1.setVisible(true);
						
					} catch (FileNotFoundException ej) {
						ej.printStackTrace();
					} catch (IOException ej) {
						ej.printStackTrace();
					} 
				}catch(NumberFormatException el){
					JOptionPane.showConfirmDialog(null, "Please enter numbers only for density Sprung", "validate", JOptionPane.CANCEL_OPTION);
				}
				
			}
		});
		btnNewButton_2.setBounds(527, 72, 137, 23);
		panel_1.add(btnNewButton_2);
	}
}
