package es.uco.pw.business.assistant;
import java.time.LocalDate;

/**
 * Represents a assistant of the system.
 */
public class AssistantDTO {

	/*Atributos */

	private int id;
	private String name;
	private String surname;
	private LocalDate date;
	private boolean atention;

	/*Constructores*/

	public AssistantDTO(){}

	public AssistantDTO(int id, String name, String surname, LocalDate date, boolean atention){
		this.id=id;
		this.name=name;
		this.surname=surname;
		this.date=date;
		this.atention=atention;
	}

	/*get/set */

	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}

	public String getSurname(){
		return surname;
	}

	public LocalDate getDate(){
		return date;
	}
	
	public boolean getAtention(){
		return atention;
	}

	public void setId(int id){
		this.id = id;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setSurname(String surname){
		this.surname = surname;
	}

	public void setDate(LocalDate date){
		this.date = date;
	}

	public void setAtention(boolean atention){
		this.atention = atention;
	}

	/* To String */

	public String toString() {
		String personInfo = "Nombre: " + this.name + " " + this.surname + ". Fecha nacimiento " + this.date + ", id " + this.id; // Another way to concat strings
		
		return personInfo;
	}
}
