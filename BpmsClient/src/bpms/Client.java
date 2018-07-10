package bpms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

//			CLIENT

public class Client {
	static LinkedList<MsEntry> bpBook = new LinkedList<MsEntry>();
	static Socket socket;
    public static void main(String[] args){

        	System.out.println("Client's gui opened");
        	new Client_Gui();

    }
    
    //Client functions 
    
    public static void importBPMSdata(String d,String t,int sBP,int dSP,int hr,int id) throws IOException{
		MsEntry a1=new MsEntry("measure",d,t,sBP,dSP,hr,id);
		bpBook.add(a1);
	}
    
    
    
    ///////////////		CLIENT FUNCTIONS WITH DATABASE		/////////////////
    
    
    public static void readFromDatabase() throws IOException, ClassNotFoundException {

    	//connect to database
    	Class.forName("com.mysql.jdbc.Driver");
    	Connection conn = null;
    	
    	try {
    		conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/bpms","root", "");
    		System.out.println("Connected to database...getting objects");
    	} catch (SQLException e) {
    		System.out.print("Do not connect to DB - Error:"+e);
    	}
    	
        String query= "SELECT * FROM bpmsbook ORDER BY date,time DESC";
       
        Statement stm;
		try {
			
			
			stm = (Statement) conn.createStatement();
			ResultSet rs= (ResultSet) stm.executeQuery(query);
			//rs.
			while (rs.next()) {
				importBPMSdata(rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(1));
				 
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    	
    }
    
    
    public static void saveToFile() {
    	
		String json = new Gson().toJson(bpBook);
		 // Writing to a file  
	    File file=new File("m.json");  
	    
	    try {
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);  
			fileWriter.write(json);  
			fileWriter.flush();  
			fileWriter.close();  
	    
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	
    }
    
    public static void loadFromFile() {
    	
		final java.lang.reflect.Type listType = new TypeToken<LinkedList<MsEntry>>() {}.getType();
		Gson gson = new Gson();
		JsonReader reader = null;
		
		try {
			reader = new JsonReader(new FileReader("m.json"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bpBook= gson.fromJson(reader, listType);
    	
    }
    
    

}
