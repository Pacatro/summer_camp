package es.uco.pw.business.managers;

import es.uco.pw.business.common.exceptions.BusinessException;
import es.uco.pw.business.user.UserDTO;
import es.uco.pw.data.dao.user.UserDAO;

public class UserManager {
    public boolean signup(UserDTO user) throws Exception{
        UserDAO dao = new UserDAO();

        if(dao.getById(user.getEmail()) != null){
            throw new BusinessException("Ya existe un usuario con mail " + user.getEmail());
        }

        dao.insert(user);

        return true;
    }
}
