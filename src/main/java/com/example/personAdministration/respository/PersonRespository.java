package com.example.personAdministration.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.personAdministration.data.Person;


@Repository		// Person = Welche Objekte sollen gespeichert werden? | Identifier = muss angegeben werden (Primary Key | Id)   
public interface PersonRespository extends JpaRepository<Person, Integer> {

    
}
