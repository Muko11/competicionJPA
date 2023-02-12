package model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table(name = "atleta")
public class Atleta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String fullName;

	@Column
	private int age;

	@Column
	private String genre;

	@Column
	private int benchPress;

	@Column
	private int deadlift;

	@Column
	private int squat;

	@Column
	private int total;

	public Atleta() {
		super();
	}

	public Atleta(int id, String fullName, int age, String genre, int benchPress, int deadlift, int squat, int total) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.age = age;
		this.genre = genre;
		this.benchPress = benchPress;
		this.deadlift = deadlift;
		this.squat = squat;
		this.total = total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getBenchPress() {
		return benchPress;
	}

	public void setBenchPress(int benchPress) {
		this.benchPress = benchPress;
	}

	public int getDeadlift() {
		return deadlift;
	}

	public void setDeadlift(int deadlift) {
		this.deadlift = deadlift;
	}

	public int getSquat() {
		return squat;
	}

	public void setSquat(int squat) {
		this.squat = squat;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Atleta [id=" + id + ", fullName=" + fullName + ", age=" + age + ", genre=" + genre + ", benchPress="
				+ benchPress + ", deadlift=" + deadlift + ", squat=" + squat + ", total=" + total + "]";
	}

}
