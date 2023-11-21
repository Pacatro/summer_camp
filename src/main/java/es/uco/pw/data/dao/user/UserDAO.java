package es.uco.pw.data.dao.user;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.business.common.userType.UserType;
import es.uco.pw.business.user.UserDTO;
import es.uco.pw.data.common.ConnectionDB;
import es.uco.pw.data.common.DataException;
import es.uco.pw.data.common.IDAO;

public class UserDAO implements IDAO<UserDTO,String>{
    @Override
    public void insert(UserDTO dto) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("sql.properties"));
        String sql = properties.getProperty("INSERT_USER");

        ConnectionDB connDB = new ConnectionDB();
        Connection conn = connDB.getConnection();

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, dto.getEmail());
        ps.setString(2, dto.getName());
        ps.setString(3, dto.getPassword());
        ps.setString(4, dto.getType().toString());

        int rowsAffected = ps.executeUpdate();

        if(rowsAffected <= 0)
            throw new DataException("No se puede insertar el usuario");

        connDB.disconnect();
    }

    @Override
    public void update(UserDTO dto) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("sql.properties"));

        ConnectionDB connDB = new ConnectionDB();
        Connection conn = connDB.getConnection();

        String sql = properties.getProperty("UPDATE_USER");
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, dto.getName());
        ps.setString(2, dto.getPassword());
        ps.setString(3, dto.getType().toString());
        ps.setString(4, dto.getEmail());

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected <= 0)
            throw new DataException("No se pudo actualizar la informacion del usuario.");
        
        connDB.disconnect();
    }

    @Override
    public UserDTO getById(String id) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("sql.properties"));

        ConnectionDB connDB = new ConnectionDB();
        Connection conn = connDB.getConnection();

        String sql = properties.getProperty("GETBYID_USER");
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, id);

        if(!ps.execute())
            throw new DataException("No existe ningun usuario con email: " + id + ".");

        ResultSet rs = ps.executeQuery();

        UserDTO user = null;

        while(rs.next()){
            user = new UserDTO(rs.getString("email"), rs.getString("name"), rs.getString("password"), UserType.valueOf(rs.getString("type")));
        }

        connDB.disconnect();

        return user;
    }

    @Override
    public ArrayList<UserDTO> getAll() throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

}
