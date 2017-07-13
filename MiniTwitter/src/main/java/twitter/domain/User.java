package twitter.domain;

public class User {
	private long id;
	private String username;
	private String password;
	private String realName;
	public User(long id, String username, String password, String realName ){
		this.id = id;
		this.username = username;
		this.password = password;
		this.realName = realName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}

	
}
