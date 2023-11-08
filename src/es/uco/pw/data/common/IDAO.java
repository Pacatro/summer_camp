package es.uco.pw.data.common;

import java.util.ArrayList;

public interface IDAO<T> {
    public ArrayList<T> getAll() throws Exception;
    public T getById(int id) throws Exception;
    public void insert(T dto) throws Exception;
    public void update(T dto) throws Exception;
    // public void delete(T dto) throws Exception;
}
