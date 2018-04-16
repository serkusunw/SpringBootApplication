package pl.serkus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private int id;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "postal_code")
	private String postal_code;
	
	@Column(name = "number")
	private String number;
	
	@OneToOne(mappedBy="address")
	private User user;
	
	public Address(Integer id, String city, String street, String postal_code, String number) {
		this.id = id;
		this.city = city;
		this.street = street;
		this.postal_code = postal_code;
		this.number = number;
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address() {
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getPostal_code() {
		return postal_code;
	}


	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}
}
