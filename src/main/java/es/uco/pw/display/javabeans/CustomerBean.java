package es.uco.pw.display.javabeans;

import java.util.ArrayList;

import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.common.userType.UserType;

/**
 * The {@code CustomerBean} class represents customer information as a JavaBean.
 * It implements {@link java.io.Serializable} for serialization purposes.
 */
public class CustomerBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String emailUser = "";
	private UserType type = UserType.ASSISTANT, type2 = UserType.ADMIN;

	private ArrayList<CampamentDTO> campaments;
	/**
     * Gets the customer's email address.
     *
     * @return The email address of the customer.
     */
	public String getEmailUser() {
		return emailUser;
	}

	/**
     * Gets the user type of the customer.
     *
     * @return The user type of the customer.
     */
	public UserType getType() {
		return type;
	}

	public UserType getType2() {
		return type2;
	}

	public ArrayList<CampamentDTO> getCampaments() {
        return campaments;
    }

	/**
     * Sets the customer's email address.
     *
     * @param emailUser The new email address of the customer.
     */
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	/**
     * Sets the user type of the customer.
     *
     * @param type The new user type of the customer.
     */
	public void setType(UserType type) {
		this.type = type;
	}

	public void setType2(UserType type2) {
		this.type2 = type2;
	}

	public void setCampaments(ArrayList<CampamentDTO> campaments) {
        this.campaments = campaments;
    }
}