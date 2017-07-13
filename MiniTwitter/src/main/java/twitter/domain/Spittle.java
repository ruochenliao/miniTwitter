package twitter.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Spittle {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long id;
	
	//@JoinColumn(name="spitter")
	@Column
	private int spitter;
	//private String user;
	
	@Column
	private String message;
	
	@Column
	private Date postedTime;

	public Spittle(String user, String message, Date postedTime) {
		
		this.spitter = spitter;
		this.message = message;
		this.postedTime = postedTime;
	}
	
	public long getId() {
		return this.id;
	}
	
	public int getSpitter(){
		return this.spitter;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public Date getPostedTime(){
		return this.postedTime;
	}
}
