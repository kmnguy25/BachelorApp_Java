package core;

public class User_ {
	
	//field_below_id
	private int id;
	private String name;
	private String username;
	private String password;
	private boolean admin;
	private boolean bachelor;
	private boolean contestant;
	private String[] columns = new String[7];
	
	public User_() {
		
	}
	
	//constructor_without_id
	public User_(String Name, String Username, String Password, boolean Admin, boolean Bachelor,boolean Contestant) {
		super();
		this.name = Name;
		this.username = Username;
		this.password = Password;
		this.admin = Admin;
		this.bachelor = Bachelor;
		this.contestant = Contestant;
	}

	//constructor_with_id
	public User_(int Id, String Name, String Username, String Password, boolean Admin, boolean Bachelor,boolean Contestant) {
		super();
		this.id = Id;
		this.name = Name;
		this.username = Username;
		this.password = Password;
		this.admin = Admin;
		this.bachelor = Bachelor;
		this.contestant = Contestant;                
	}
	
	//set_get
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String Name) {
		this.name = Name;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String Username) {
		this.username = Username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String Password) {
		this.password = Password;
	}
	      
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean Admin) {
		this.admin = Admin;
	}      
	
	public boolean isBachelor() {
		return bachelor;
	}

	public void setBachelor(boolean Bachelor) {
		this.bachelor = Bachelor;
	}
	
	public boolean isContestant() {
		return contestant;
	}

	public void setContestant(boolean Contestant) {
		this.contestant = Contestant;
	}
	
	public String[] getColumns() {
		
		columns[0]= "user_id";
		columns[1]= "user_name";
		columns[2]= "user_userName";
		columns[3]= "user_password";
		columns[4]= "user_isAdmin";
		columns[5]= "user_isBachelor";
		columns[6]= "user_isContestant";
		
		return columns;
	}
	
	
	//return_string
	@Override
	public String toString() {
		return name;
	}
}
