//-----------------------------------
//Name: Bastian Struggl
//Projektkname: PersonAdministration OOP / Spring / JPA / Klasse: CSVService
//Datum: 27.06.2021
//-----------------------------------

package com.example.personAdministration.services;

import java.io.BufferedReader;
import java.io.FileReader;
import org.springframework.stereotype.Service;
import com.example.personAdministration.data.Person;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVService {

	// Dieser weist jedem Datensatz sozusagen eine ID zu
	public static int counterID = 0;
	// Array mit vorgegebenen index-zu-Farbwerten
	public static String[] farben = { "blau", "grün", "violett", "rot", "gelb", "türkis", "weiß" };

	// Gibt eine Liste mit Personen in dem von dem Projektgeber gewünschten Format
	// zurück
	public List<Person> getPersonList(String fileName) {

		List<Person> personAl = new ArrayList<Person>();

		{

			String line = "";
			String splitBy = ",";

			try {
				// Parst eine CSV-File in den Bufferer REader
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				while ((line = br.readLine()) != null) // Gibt einen Boolean zurück
				{
					String[] personARRAY = line.split(splitBy); // Nutze Komma als Seperator
					/**
					 * Falls Werte fehlen, werden diese Leerstellen durch NULLs ersetzt um einen
					 * Programmabsturz zu vermeiden Man könnte aber auch leere Strings "" verwenden
					 */
					Person person = null;

					if (personARRAY.length == 4) {

						person = new Person(counterID, personARRAY[0], personARRAY[1],
								zipcodeOderCityRaustrennen(personARRAY[2], 1),
								zipcodeOderCityRaustrennen(personARRAY[2], 2), idDerFarbezuweisen(personARRAY[3]));
						personAl.add(person);
						counterID++;
					} else if (personARRAY.length == 3) {

						person = new Person(counterID, personARRAY[0], personARRAY[1],
								zipcodeOderCityRaustrennen(personARRAY[2], 1),
								zipcodeOderCityRaustrennen(personARRAY[2], 2), null);
						personAl.add(person);
						counterID++;
					} else if (personARRAY.length == 2) {

						person = new Person(counterID, personARRAY[0], personARRAY[1], null, null, null);
						personAl.add(person);
						counterID++;
					} else if (personARRAY.length == 1) {

						person = new Person(counterID, personARRAY[0], null, null, null, null);
						personAl.add(person);
						counterID++;
					} else if (personARRAY.length == 0) {

						person = new Person(counterID, null, null, null, null, null);
						personAl.add(person);
						counterID++;
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		counterID = 0;
		// Gebe die fertige Liste der Personen zurück
		return personAl;
	}

	// die Farb-ID aus der CSV-File der ausgeschriebenen Farbe aus dem Array
	// "farben" zuweisen und zurückgeben
	public static String idDerFarbezuweisen(String id) {
		for (int i = 0; i < farben.length; i++) {

			// Leerzeichen aus String entfernen
			String idGetrimmed = id.trim();
			int x = Integer.parseInt(idGetrimmed);
			if (i == x) {
				return farben[i];
			}
		}
		return "Fehler";
	}

	public static String zipcodeOderCityRaustrennen(String zipcodeOderCity, int zipcodeOderCityID) {

		// Trenne die beiden Strings an dem ersten Leerzeichen
		String zipcode = "Fehler", city = "Fehler";
		int counterForGaps = 0;

		for (int i = 0; i < zipcodeOderCity.length(); i++) {

			if (zipcodeOderCity.charAt(i) == ' ' && counterForGaps == 0) {
				// Sollte erstes Zeichen ein Leerzeichen sein, überspringe die Runde mit continue
				if (i == 0) {
					continue;
				}
				// Ersten String/Hälfte (zipcode) heraustrennen mit Substring
				zipcode = zipcodeOderCity.substring(0, (i));
				// Zweiten String/Hälfte (city) heraustrennen mit Substring
				city = zipcodeOderCity.substring(i + 1);

				counterForGaps++;
			}
		}

		if (zipcodeOderCityID == 1) {
			// Rückgabe des zipcodes (1)
			return zipcode;

		} else {
			// Rückgabe der city (2)
			return city;
		}
	}

}
