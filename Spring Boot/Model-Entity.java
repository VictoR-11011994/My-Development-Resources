// Example

package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user") // if no name specified by default it will read the class name ex. "User" on DB but there might be some problems in 
                      // Linux by reading "User" -> "U..." (uppercase), to avoid that is better to use @Table(name = "the-name-here")
public class User {
	
	private String name;

}
