//-----------------------------------
//Name: Bastian Struggl
//Projektkname: PersonAdministration OOP / Spring / JPA / Klasse: Person
//Datum: 27.06.2021
//-----------------------------------

package com.example.personAdministration.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String lastname;
	private String zip;
	private String city;
	private String color;

	// Constructor
	public Person() {

	}

	public Person(int id, String name, String lastname, String zip, String city, String color) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.zip = zip;
		this.city = city;
		this.color = color;
	}

	// Getter & Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", lastname=" + lastname + ", zip=" + zip + ", city=" + city
				+ ", color=" + color + "]";
	}

}
