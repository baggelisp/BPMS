package bpms;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Client_Gui extends JFrame {
	public static JTable table;
	MsEntry receivedObj=null;
	
	public Client_Gui() {
		JFrame frame=new JFrame("BPMS");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 665, 336);
		frame.setLayout(null);

		table = new JTable();
		table.setBackground(Color.LIGHT_GRAY);
	    table.setForeground(Color.black);
		
		// create a table model and set a Column Identifiers to this model 
		String[] columnNames = {"ID", "Date",
                "Time",
                "systolicBP",
                "diastolicBP","hartRate"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        
        table.setModel(model);

		JScrollPane sp=new JScrollPane(table);    
		sp.setBounds(5,5,635,180);
	    frame.add(sp);  
	    
		JButton Bupdate = new JButton("Update");
		Bupdate.setBounds(40, 250, 110, 20);
		frame.add(Bupdate);
		Object[] row = new Object[6];

		/////////////////////////////////////////////////////////////
		
		
		Bupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Client.bpBook.clear();
				model.setRowCount(0);
				
				try {
					Client.readFromDatabase();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//UPDATE ROWS
				for (int i=0;i<Client.bpBook.size();i++){
					 row[0] = Client.bpBook.get(i).getUserID();
		             row[1] = Client.bpBook.get(i).getDate();
		             row[2] = Client.bpBook.get(i).getTime();
		             row[3] = Client.bpBook.get(i).getSystolicBP();
		             row[4] = Client.bpBook.get(i).getDiastolicBP();
		             row[5] =Client.bpBook.get(i).getHeartRate();
		             model.addRow(row);
  
				}

		} 	
		});
		
		JButton DeleteAll = new JButton("Delete all");
		DeleteAll.setBounds(500, 250, 100, 20);
		frame.add(DeleteAll);

		DeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				model.setRowCount(0);	
				Client.bpBook.clear();

			}
				
		});

		JButton save = new JButton("Save to file");
		save.setBounds(200, 250, 100, 20);
		frame.add(save);

		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Client.saveToFile();
			}
				
		});
		
		
		JButton load = new JButton("Load from file");
		load.setBounds(345, 250, 120, 20);
		frame.add(load);

		load.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				Client.bpBook.clear();
				model.setRowCount(0);
				
				Client.loadFromFile();
				
				//UPDATE ROWS
				for (int i=0;i<Client.bpBook.size();i++){
					 row[0] = Client.bpBook.get(i).getUserID();
		             row[1] = Client.bpBook.get(i).getDate();
		             row[2] = Client.bpBook.get(i).getTime();
		             row[3] = Client.bpBook.get(i).getSystolicBP();
		             row[4] = Client.bpBook.get(i).getDiastolicBP();
		             row[5] =Client.bpBook.get(i).getHeartRate();
		             model.addRow(row);
				}

				
			}
		});

		frame.setVisible(true);
		}
}
