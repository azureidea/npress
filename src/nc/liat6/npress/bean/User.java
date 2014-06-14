package nc.liat6.npress.bean;

public class User{

	private long id;
	private String name;
	private String password;
	private String account;
	private int admin;

	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getAccount(){
		return account;
	}

	public void setAccount(String account){
		this.account = account;
	}

	public int getAdmin(){
		return admin;
	}

	public void setAdmin(int admin){
		this.admin = admin;
	}

}
