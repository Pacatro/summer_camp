package es.uco.pw.display.javabeans;

import es.uco.pw.business.common.userType.UserType;

/**
 * The {@code CustomerBean} class represents customer information as a JavaBean.
 * It implements {@link java.io.Serializable} for serialization purposes.
 */
public class CustomerBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String name = "";
	private String emailUser = "";
	private UserType type = UserType.ASSISTANT;

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

	/**
     * Gets the user name of the customer.
     *
     * @return The user name of the customer.
     */
	public String getName() {
		return name;
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

	/**
     * Sets the user name of the customer.
     *
     * @param name The new user name of the customer.
     */
	public void setName(String name) {
		this.name = name;
	}
}