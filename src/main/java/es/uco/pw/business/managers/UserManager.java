package es.uco.pw.business.managers;

import es.uco.pw.business.user.UserDTO;
import es.uco.pw.data.dao.user.UserDAO;

public class UserManager {
    public boolean signup(UserDTO user) throws Exception{
        UserDAO dao = new UserDAO();

        if(dao.getById(user.getEmail()) != null){
            return false;
        }

        dao.insert(user);

        return true;
    }

    public boolean signin(String email, String password) throws Exception{
        UserDAO dao = new UserDAO();
        UserDTO user = dao.getById(email);

        if(user == null){
            return false;
        }
        
        if(!user.getPassword().equals(password)){
            return false;
        }

        return true;

    }

    public boolean update(UserDTO user) throws Exception{
        UserDAO dao = new UserDAO();

        if(dao.getById(user.getEmail()) == null){
            return false;
        }

        dao.update(user);

        return true;
    }
}
