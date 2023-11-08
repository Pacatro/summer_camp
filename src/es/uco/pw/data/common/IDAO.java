package es.uco.pw.data.common;

import java.util.ArrayList;

/**
 * This interface represents a Data Access Object (DAO) common interface for managing objects of type T.
 *
 * @param <T> The type of object that the DAO manages.
 * @param <E> The type of identifier or key used to retrieve objects of type T.
 */
public interface IDAO<T,E> {
    
    /**
     * Retrieves a list of all objects of type T from the data source.
     *
     * @return An ArrayList containing all objects of type T.
     * @throws Exception if an error occurs during the retrieval process.
     */
    public ArrayList<T> getAll() throws Exception;

    /**
     * Retrieves an object of type T based on its identifier or key of type E.
     *
     * @param id The identifier or key used to retrieve the object.
     * @return An object of type T.
     * @throws Exception if an error occurs during the retrieval process.
     */
    public T getById(E id) throws Exception;

    /**
     * Inserts an object of type T into the data source.
     *
     * @param dto The object of type T to insert.
     * @throws Exception if an error occurs during the insertion process.
     */
    public void insert(T dto) throws Exception;

    /**
     * Updates an existing object of type T in the data source.
     *
     * @param dto The object of type T to update.
     * @throws Exception if an error occurs during the update process.
     */
    public void update(T dto) throws Exception;

    /**
     * Deletes an object of type T from the data source.
     *
     * @param dto The object of type T to delete.
     * @throws Exception if an error occurs during the deletion process.
     */
    // public void delete(T dto) throws Exception;
}