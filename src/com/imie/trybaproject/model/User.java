package com.imie.trybaproject.model;

public class User {

	private long id;
	private String login;
	private String password;
	private String firstname;
	private String lastname;
	private String type;
	private Station currentStation;

	public User(){
		
	}
	
	public User(String login, String password, String firstname,
			String lastname, String type) {
		super();
		this.login = login;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.type = type;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Station getCurrentStation() {
		return currentStation;
	}

	public void setCurrentStation(Station currentStation) {
		this.currentStation = currentStation;
	}

	public String getSerializableString(){
		StringBuilder sb = new StringBuilder();
		String separator = "~";
		
		sb.append(getId());
		sb.append(separator);
		sb.append(getLogin());
		sb.append(separator);
		sb.append(getPassword());
		sb.append(separator);
		sb.append(getFirstname());
		sb.append(separator);
		sb.append(getLastname());
		sb.append(separator);
		sb.append(getType());
		if(currentStation != null){
			sb.append(separator);
			sb.append(currentStation.getSerializableString());
		}
		return sb.toString();
	}
	
	public int getSerializableStringLength(){
		return 6;
	}
	
	public void setWithSerializableString(String str) throws Exception{
		String[] userStr = str.split("~");
	
		if(userStr.length >= this.getSerializableStringLength()){
			if(userStr.length >= this.getSerializableStringLength()){
				this.setId(Integer.parseInt(userStr[0]));
				this.setLogin(userStr[1]);
				this.setPassword(userStr[2]);
				this.setFirstname(userStr[3]);
				this.setLastname(userStr[4]);
				this.setType(userStr[5]);
			}
			this.currentStation = new Station();
			if(userStr.length >= this.getSerializableStringLength() + 
						getCurrentStation().getSerializableStringLength()){
				StringBuilder sb = new StringBuilder();
				String separator = "~";
				sb.append(userStr[6]);
				sb.append(separator);
				sb.append(userStr[7]);
				sb.append(separator);
				sb.append(userStr[8]);
				sb.append(separator);
				sb.append(userStr[9]);
				this.currentStation.setWithSerializableString(sb.toString());
			}
			if(userStr.length >= this.getSerializableStringLength() + 
				getCurrentStation().getSerializableStringLengthWithTampon()){
				StringBuilder sb = new StringBuilder();
				String separator = "~";
				sb.append(userStr[10]);
				sb.append(separator);
				sb.append(userStr[11]);
				sb.append(separator);
				sb.append(userStr[12]);
				this.currentStation.setTamponWithSerializableString(sb.toString());
			}
		}else{
			throw new Exception("Il manque des paramètre dans la chaine de " +
					"l'utilisateur. Count :" + userStr.length);
		}
	}
	
	public String toString(){
		return getFirstname() + " " + getLastname();
	}
}
