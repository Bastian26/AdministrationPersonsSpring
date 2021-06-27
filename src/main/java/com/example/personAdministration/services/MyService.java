//-----------------------------------
//Name: Bastian Struggl
//Projektkname: PersonAdministration OOP / Spring / JPA / Klasse: MyService
//Datum: 27.06.2021
//-----------------------------------

package com.example.personAdministration.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.personAdministration.data.Person;
import com.example.personAdministration.respository.PersonRespository;

@Service
public class MyService {

	@Autowired
	private CSVService csvService;

	@Autowired
	PersonRespository personRespository;

	/** Gebe alle Personen aus der CSV-File zurück 
	 * 
	 * **/
	public List<Person> getPersons(String csvFile) {
		return csvService.getPersonList(csvFile);
	}

	/** Gebe eine spezifische Person aus der CSV-File zurück 
	 * 
	 * **/
	public Person getPerson(String csvFile, int id) {

		ArrayList<Person> personen = (ArrayList<Person>) csvService.getPersonList(csvFile);
		/*
		 * druchlaufe Personen-ArrayList und prüfe, ob die gesuchte ID mit der ID der
		 * Person übereinstimmt ,bei Übereinstimmung gebe jeweilige Person zurück
		 */
		for (int i = 0; i < personen.size(); i++) {
			if (personen.get(i).getId() == id) {
				return personen.get(i);
			}
		}

		// Wurde keine Person gefunden, gebe NULL zurück
		return null;
	}

	/** Gebe eine oder mehrere Personen einer Farbe aus der CSV-File zurück 
	 *
	 ***/
	public List<Person> getPersonsForColor(String csvFile, String color) {

		List<Person> personen = (ArrayList<Person>) csvService.getPersonList(csvFile);
		List<Person> personenFuerFarbe = new ArrayList<Person>();

		for (int i = 0; i < personen.size(); i++) {

			// Wenn Farbe nicht NULL ist
			if ((personen.get(i).getColor() != null)) {
				if (personen.get(i).getColor().equals(color)) {

					personenFuerFarbe.add(personen.get(i));
					System.out.println(personenFuerFarbe.size());
				}
			}
		}

		System.out.println("durchgelaufen");
		return personenFuerFarbe;
	}

	// ---- #### Ab hier folgen die Get/Post/Put/Delete-Methoden für die
	// Datenbankanbindung #### ----
	// Ich habe das ganze mal unabhänig von der CSV-Datei mit einer Datenbank
	// erstellt für die wichtigsten CRUD-Funktionen

	/**
	 * Gibt alle Personen aus der Datenbank aus [GET]
	 * http://localhost:8080/personsDB
	 */
	@GetMapping("/personsDB")
	public List<Person> getAllPersonDB() {
		// Auf PersonRepository zugreifen
		List<Person> personList = personRespository.findAll();
		return personList;
	}
	
	/**
	 * Gibt eine spezifische Person in die Datenbank ein [POST]
	 * http://localhost:8080/personsDB
	 */
	public Person createPerson(Person person) {
		return personRespository.save(person);
	}

	/**
	 * Löscht eine spezifische Person [DELETE] http://localhost:8080/personsDB
	 */
	@DeleteMapping("/personsDB/{id}")
	public void deletePerson(@PathVariable int id) {
		personRespository.deleteById(id);
	}

}
