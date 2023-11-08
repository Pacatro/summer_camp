package es.uco.pw.data.dao.common;

import java.util.ArrayList;

public interface IDAO<T,E> {
    public ArrayList<T> getAll() throws Exception;
    public T getById(E id) throws Exception;
    public void insert(T dto) throws Exception;
    public void update(T dto) throws Exception;
    // public void delete() throws Exception;
}
