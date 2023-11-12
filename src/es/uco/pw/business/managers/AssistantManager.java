package es.uco.pw.business.managers;
import java.time.LocalDate;
import java.util.ArrayList;

import es.uco.pw.business.assistant.AssistantDTO;
import es.uco.pw.business.common.exceptions.BusinessException;
import es.uco.pw.data.dao.assistant.AssistantDAO;

/**
 * Manages operations related to assistants in the system, such as registration, search, modification, and printing.
 */
public class AssistantManager {

    /**
     * Registers a new assistant in the system.
     *
     * @param a   The assistant to be registered.
     */
    public void register(AssistantDTO a)throws Exception{
        try{
            AssistantDAO dao=new AssistantDAO();
            dao.insert(a);
        }catch (Exception e){ BusinessException.handleException(e); }
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
     */
    public void modify(int id, String newname, String newsurname, LocalDate newdate, boolean newatention)throws Exception{
        try{
            AssistantDAO dao=new AssistantDAO();
            AssistantDTO dto=new AssistantDTO(id, newname, newsurname, newdate, newatention);
            dao.update(dto);
        }catch (Exception e){ BusinessException.handleException(e); }
    }

    /**
     * Retrieves a list of AssistantDTO objects
     * 
     * @return An ArrayList of AssistantDTO objects.
     */
    public ArrayList<AssistantDTO> getAll()throws Exception{
        ArrayList<AssistantDTO> register = new ArrayList<>();
        try{
            AssistantDAO dao=new AssistantDAO();
            register=dao.getAll();
        }catch (Exception e){ BusinessException.handleException(e); }
        return register;
    }

    /**
     * Retrieves an AssistantDTO object based on its identifier.
     * 
     * @param id The identifier of the AssistantDTO object to retrieve.
     * @return The AssistantDTO object corresponding to the provided identifier.
     */
    public AssistantDTO getById(int id) throws Exception{
        AssistantDTO assistantDTO = new AssistantDTO();
        try {
            AssistantDAO assistantDAO = new AssistantDAO();
            assistantDTO = assistantDAO.getById(id);
        } catch (Exception e) { BusinessException.handleException(e); }
        return assistantDTO;
    }

}
