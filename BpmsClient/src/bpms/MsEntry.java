package bpms;

import java.io.Serializable;

public class MsEntry implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String date;
	private String time;
	private int systolicBP ;
	private int diastolicBP ;
	private int heartRate;
	private int userID;
	
	private String type;
	
	

	public MsEntry(String type,String date,String time,int systolicBP,int diastolicBP,int heartRate,int userID){
		
		this.type=type;
		this.date=date;
		this.time=time;
		this.systolicBP=systolicBP;
		this.diastolicBP=diastolicBP;
		this.heartRate=heartRate;
		this.userID=userID;
		
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getSystolicBP() {
		return systolicBP;
	}

	public void setSystolicBP(int systolicBP) {
		this.systolicBP = systolicBP;
	}

	public int getDiastolicBP() {
		return diastolicBP;
	}

	public void setDiastolicBP(int diastolicBP) {
		this.diastolicBP = diastolicBP;
	}

	public int getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

}
