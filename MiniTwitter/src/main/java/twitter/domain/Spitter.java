package twitter.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;


@Entity
public class Spitter {
	
	public Spitter() {}

	@Id
	@Column(name="id")
	@GeneratedValue
	private Long id;

	@Column(name="username")
	private String username;

	@Column(name="password")
	private String password;

	@Column(name="enabled")
	private boolean enabled;
	
	@Column(name="fullname")
	private String fullName;

	@Column(name="email")
	private String email;

	@Column(name="updateByEmail")
	private boolean updateByEmail;

	public Spitter(String username, String password, String fullName,
			String email, boolean updateByEmail) {
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.updateByEmail = true;
		this.enabled = true;
	}
	public Spitter(String username, String password, String fullName,
			String email) {
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.updateByEmail = true;
		this.enabled = true;
	}

	public void setFullName(String fullName){
		this.fullName = fullName;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setUpdateByEmail(Boolean isUpdated){
		this.updateByEmail = isUpdated;
	}
	
	public void setEnabled(Boolean enabled){
		this.enabled = enabled;
	}
	
	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFullName() {
		return fullName;
	}

	public String getEmail() {
		return email;
	}

	public boolean isUpdateByEmail() {
		return updateByEmail;
	}
	public boolean isEnabled(){
		return enabled;
	}
}
