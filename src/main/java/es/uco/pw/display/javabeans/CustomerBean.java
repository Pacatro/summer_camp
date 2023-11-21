package es.uco.pw.display.javabeans;

import es.uco.pw.business.common.userType.UserType;

public class CustomerBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String emailUser = "";
	private UserType type = UserType.ASSISTANT;

	public String getEmailUser() {
		return emailUser;
	}

	public UserType getType() {
		return type;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public void setType(UserType type) {
		this.type = type;
	}
}