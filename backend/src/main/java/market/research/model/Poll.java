package market.research.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Poll {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private boolean age;
	private boolean smoker;
	private int cigarettes;
	private Date date;
	
	@ManyToOne
	public User user;
	
	@ManyToOne
	public Cigarettes cigarette;
	
	public Poll() {
		
	}
	
	public Poll(User user, boolean age) {
		this.age = age;
		this.smoker = false;
		this.cigarettes = 0;
		this.cigarette = null;
		this.user = user;
		this.date = new Date();
	}
	
	public Poll(User user, boolean age, boolean smoker) {
		this.age = age;
		this.smoker = smoker;
		this.cigarettes = 0;
		this.cigarette = null;
		this.user = user;
		this.date = new Date();
	}
	
	public Poll(User user, boolean age, boolean smoker, int cigarette, Cigarettes cig) {
		this.age = age;
		this.smoker = smoker;
		this.cigarettes = cigarette;
		this.cigarette = cig;
		this.user = user;
		this.date = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isAge() {
		return age;
	}

	public void setAge(boolean age) {
		this.age = age;
	}

	public boolean isSmoker() {
		return smoker;
	}

	public void setSmoker(boolean smoker) {
		this.smoker = smoker;
	}

	public int getCigarettes() {
		return cigarettes;
	}

	public void setCigarettes(int cigarettes) {
		this.cigarettes = cigarettes;
	}

	public Cigarettes getCigarette() {
		return cigarette;
	}

	public void setCigarette(Cigarettes cigarette) {
		this.cigarette = cigarette;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Poll [id=" + id + ", age=" + age + ", smoker=" + smoker + ", cigarettes=" + cigarettes + ", date="
				+ date + ", user=" + user + ", cigarette=" + cigarette + "]";
	}
}
