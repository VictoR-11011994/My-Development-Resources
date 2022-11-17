// Example

package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user") // if no name specified by default it will read the class name ex. "User" on DB but there might be some problems in 
                      // Linux (phpMyAdmin) by reading "User" -> "U..." (uppercase), to avoid that is better to use @Table(name = "the-name-here") all "lowercase"
		      // on Windows using (MySQL Workbench) it will automatically create/recognize the data with "lowercase": "user"
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // .AUTO
	private Long id;
	
	private String name;

}
