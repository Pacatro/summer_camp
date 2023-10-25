package managers;
import java.time.LocalDate;
import java.util.ArrayList;

import classes.Assistant;

/**
 * Manages operations related to assistants in the system, such as registration, search, modification, and printing.
 */
public class AssistantManager {

    /**
     * Registers a new assistant in the system.
     *
     * @param a   The assistant to be registered.
     * @param list The list of assistants in the system.
     * @return true if the assistant is successfully registered, false if the assistant already exists in the list.
     */
    public boolean register(Assistant a, ArrayList<Assistant> list){
        for(int i=0; i<list.size();i++){
            if(list.get(i) == a){
                return false;
            }
        }
        list.add(a);
        return true;
    }

    /**
     * Searches for an assistant by ID in the list of assistants.
     *
     * @param id   The ID of the assistant to search for.
     * @param list The list of assistants to search within.
     * @return The assistant with the specified ID, or null if not found.
     */
    public Assistant search(int id, ArrayList<Assistant> list) {
        for (Assistant a : list){
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }
 
    /**
     * Modifies the details of an existing assistant in the system.
     *
     * @param id          The ID of the assistant to be modified.
     * @param newname     The new name of the assistant.
     * @param newsurname  The new surname of the assistant.
     * @param newdate     The new date of birth of the assistant.
     * @param newatention The new attention status of the assistant.
     * @param list        The list of assistants in the system.
     * @return true if the assistant is successfully modified, false if the assistant is not found.
     */
    public boolean modify(int id, String newname, String newsurname, LocalDate newdate, boolean newatention, ArrayList<Assistant> list){
        Assistant a1=search(id, list);
        if(a1!=null){
            a1.setName(newname);
            a1.setSurname(newsurname);
            a1.setDate(newdate);
            a1.setAtention(newatention);
            return true;
        }
        return false;
    }

    /**
     * Prints the details of all registered assistants in the system.
     *
     * @param register The list of registered assistants to be printed.
     */
    public void print(ArrayList<Assistant> register){
        for(Assistant a : register){
            System.out.println("ID: " + a.getId());
            System.out.println("Nombre: " + a.getName());
            System.out.println("Apellido: " + a.getSurname());
            System.out.println("Fecha: " + a.getDate());
            System.out.println("Atención: " + a.getAtention());
            System.out.println();
        }
    }

}
