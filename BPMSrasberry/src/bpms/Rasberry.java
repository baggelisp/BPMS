package bpms;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.mysql.jdbc.PreparedStatement;

public class Rasberry {
	
	static Socket socket;

	public static void main(String[] args) {


		System.out.println("Rasberry connected");


		int Ndata = (int) (Math.random() * (15 - 5)) + 5;
		
		for (int i=1;i<Ndata;i++) {
			
			int sbp = (int) (Math.random() * (180 - 90)) + 90;
			int dbp = (int) (Math.random() * (90 - 40)) + 40;
			int hr = (int) (Math.random() * (180 - 50)) + 50;
			int id = (int) (Math.random() * (100 - 1)) + 1;
			
			try {//MM-dd-yyyy
				importBPMSdata(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()),new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) ,sbp,dbp,hr,id);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

		System.exit(0);
		

	}
	
	
	
	public static void importBPMSdata(String d,String t,int sBP,int dSP,int hr,int id) throws IOException{
		MsEntry a1=new MsEntry("rasberry",d,t,sBP,dSP,hr,id);

		try {
			sendToDatabase(a1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    

    
    public static void sendToDatabase(MsEntry obj) throws ClassNotFoundException {
    	
    	Class.forName("com.mysql.jdbc.Driver");
    	Connection conn = null;
    	PreparedStatement ps = null;
    	//PreparedStatement ps = null;
    	
    	try {
    		conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/bpms","root", "");
    		System.out.println("Connected to database...sending object");
    	} catch (SQLException e) {
    		System.out.print("Do not connect to DB - Error:"+e);
    	}

        try {
    		ps = (PreparedStatement) conn.prepareStatement("insert into  bpmsbook values (?,?,?,?,?,?)");
    		ps.setInt(1,obj.getUserID());
    		ps.setString(2,obj.getDate());
    		ps.setString(3,obj.getTime());
    		ps.setInt(4,obj.getSystolicBP());
    		ps.setInt(5,obj.getDiastolicBP());
    		ps.setInt(6,obj.getHeartRate());
    		ps.executeUpdate();
        
        } catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}finally {
            try {
                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }


    }
	
	
	

}
