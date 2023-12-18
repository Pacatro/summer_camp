package es.uco.pw.business.managers;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.business.assistant.AssistantDTO;
import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.common.exceptions.BusinessException;
import es.uco.pw.data.dao.assistant.AssistantDAO;
import es.uco.pw.data.dao.campament.CampamentDAO;

/**
 * Manages operations related to assistants in the system, such as registration, search, modification, and printing.
 */
public class AssistantManager {

    private Properties sqlProperties;
    private Properties configProperties;
    
    public AssistantManager(Properties sqlProperties, Properties configProperties){
        this.configProperties = configProperties;
        this.sqlProperties = sqlProperties;
    }

    /**
     * Registers a new assistant in the system.
     *
     * @param a   The assistant to be registered.
     */
    public void register(AssistantDTO a)throws Exception{
        try{
            AssistantDAO dao = new AssistantDAO(this.sqlProperties, this.configProperties);
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
     * @param newemail    The new email of the assistant.
     */
    public void modify(int id, String newname, String newsurname, LocalDate newdate, boolean newatention, String newemail)throws Exception{
        try{
            AssistantDAO dao=new AssistantDAO(this.sqlProperties, this.configProperties);
            AssistantDTO dto=new AssistantDTO(id, newname, newsurname, newdate, newatention, newemail);
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
            AssistantDAO dao=new AssistantDAO(this.sqlProperties, this.configProperties);
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
            AssistantDAO assistantDAO = new AssistantDAO(this.sqlProperties, this.configProperties);
            assistantDTO = assistantDAO.getById(id);
        } catch (Exception e) { BusinessException.handleException(e); }
        return assistantDTO;
    }

    public ArrayList<CampamentDTO> getCampaments(String email) throws Exception{
        AssistantDTO assistantDTO = new AssistantDTO();
        AssistantDAO assistantDAO = new AssistantDAO(this.sqlProperties, this.configProperties);
        assistantDTO = assistantDAO.getByEmail(email);
        
        CampamentDAO campamentDAO = new CampamentDAO(this.sqlProperties, this.configProperties);
        ArrayList<CampamentDTO> campaments = campamentDAO.getByAssistant(assistantDTO.getId());

        return campaments;
    }

    public AssistantDTO getByEmail(String email) throws Exception{
        AssistantDTO assist = new AssistantDTO();
        
        try{
            AssistantDAO dao = new AssistantDAO(this.sqlProperties, this.configProperties);
            assist = dao.getByEmail(email);
        }catch(Exception e) {BusinessException.handleException(e);}
        
        return assist;
    }

}
