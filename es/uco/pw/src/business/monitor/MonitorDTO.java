package business.monitor;

/**
 * Represents a monitor of the system.
 */
public class MonitorDTO{
	
    private int ID;
	private String name;
    private String surname;
    private boolean isEspecial;
	
	/**
	 * Constructor without parameters.
	 */
    public MonitorDTO() {}
	
	/**
	 * Default constructor
	 * @param ID
	 * @param name
	 * @param surname
	 * @param isEspecial
	 */
    public MonitorDTO(int ID, String name, String surname, boolean isEspecial) {
		this.ID = ID;
		this.name = name;
		this.surname = surname;
        this.isEspecial = isEspecial;
	}

    public int getID() {
		return ID;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public boolean isEspecial() {
		return isEspecial;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

    public void setNombre(String name) {
        this.name = name;
    }

    public void setisEspecial(boolean isEspecial) {
        this.isEspecial = isEspecial;
    }

    public String toString() {
		String monitorInfo = "La información del monitor es: " + this.ID + " " + this.name + " " + this.surname;
		return monitorInfo;
	}

}