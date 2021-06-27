//-----------------------------------
//Name: Bastian Struggl
//Projektkname: PersonAdministration OOP / Spring / JPA / Klasse: MyController
//Datum: 27.06.2021
//-----------------------------------

package com.example.personAdministration.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.personAdministration.data.Person;
import com.example.personAdministration.services.MyService;

@RestController
public class MyController {

	// @Autowired = Injection
	@Autowired
	private MyService myService;

	// verweist auf den srcPath in der application.properties-Datei
	@Value("${sourcePath}")
	private String srcPath;

	/**
	 * Gibt alle Personen aus der CSV-File zurück http://localhost:8080/persons
	 */
	@GetMapping("/persons")
	public List<Person> getAllPersonCont() {
		return myService.getPersons(srcPath);
	}

	/**
	 * Gibt eine spezifische Person aus der CSV-File zurück
	 * http://localhost:8080/persons/{id}
	 */
	@GetMapping("/persons/{id}")
	public Person getOnePersonCont(@PathVariable int id) {
		return myService.getPerson(srcPath, id);
	}

	/**
	 * Gibt eine oder mehrere Personen einer spezifischen Farbe aus der CSV-File
	 * zurück http://localhost:8080/persons/color/{color}
	 */
	@GetMapping("/persons/color/{color}")
	public List<Person> getAllPersonForColorCont(@PathVariable String color) {
		return myService.getPersonsForColor(srcPath, color);
	}

	// ---- #### Ab hier folgen die Get/Post/Delete-Methoden für die Datenbankanbindung #### ----
	// Ich habe das ganze mal unabhängig von der CSV-Datei mit einer Datenbank erstellt für die wichtigsten Funktionen
	
	/**
	 * Gibt alle Personen aus der Datenbank aus [GET]
	 * http://localhost:8080/personsDB
	 */
	@GetMapping("/personsDB")
	public List<Person> getAllPersonDBCont() {
		return myService.getAllPersonDB();
	}
	
	/**
	 * Gibt eine spezifische Person in die Datenbank ein [POST]
	 * http://localhost:8080/personsDB
	 */
	@PostMapping("/personsDB")
	public Person createPersonCont(@RequestBody Person person) {
		return myService.createPerson(person);
	}

	/**
	 * Löscht eine spezifische Person [DELETE] http://localhost:8080/personsDB
	 */
	@DeleteMapping("/personsDB/{id}")
	public void deletePersonCont(@PathVariable int id) {
	    myService.deletePerson(id);
	}

}
