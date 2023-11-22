package es.uco.pw.business.user;

import es.uco.pw.business.common.userType.UserType;

/**
 * Represents a user of the system.
 */
public class UserDTO {
    private String email;
    private String name;
    private String password;
    private UserType type;

    /**
	 * Constructor without parameters.
	 */
    public UserDTO(){}

    /**
     * Default constructor
     * @param email
     * @param name
     * @param password
     * @param type
     */
    public UserDTO(String email, String name, String password, UserType type){
        this.email = email;
        this.name = name;
        this.password = password;
        this.type = type;
    }

    public String getEmail(){
        return this.email;
    }

    public String getName(){
        return this.name;
    }

    public String getPassword(){
        return this.password;
    }

    public UserType getType(){
        return this.type;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setType(UserType type){
        this.type = type;
    }

    public String toString(){
        return "Usuario con nombre " + this.name + ", correo " + this.email + ", contraseña " + this.password + " y de tipo " + this.type;
    }

}
