package es.uco.pw.business.managers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import es.uco.pw.business.factory.EarlyRegInscriptionFactory;
import es.uco.pw.business.factory.InscriptionFactory;
import es.uco.pw.business.factory.LateRegInscriptionFactory;

import es.uco.pw.business.activity.ActivityDTO;
import es.uco.pw.business.assistant.AssistantDTO;
import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.common.exceptions.BusinessException;
import es.uco.pw.business.common.schedule.Schedule;
import es.uco.pw.business.factory.CompleteInscriptionDTO;
import es.uco.pw.business.factory.ParcialInscriptionDTO;
import es.uco.pw.data.dao.inscription.CompleteInscriptionDAO;
import es.uco.pw.data.dao.inscription.ParcialInscriptionDAO;

/**
 * Manages the completes and parcials inscriptions lists.
 */
public class InscriptionsManager {
    public InscriptionsManager(){}

    /**
     * Gets the inscription factory depending on the init date of the campament and the actual date.
     * @param campament
     * @return The inscription factory
     */
    public InscriptionFactory getFactory(CampamentDTO campament){
        LocalDate today = LocalDate.now();
        LocalDate campamentDate = campament.getInitDate();
        
        long dif = ChronoUnit.DAYS.between(today, campamentDate);

        if(dif >= 15)
            return new EarlyRegInscriptionFactory();
        else if(dif >= 2)
            return new LateRegInscriptionFactory();
        
        return null;
    }

    /**
     * Calculate the price of the inscription.
     * @param campament
     * @param isEspecial
     * @return The final price of the inscription.
     */
    public double calcPrice(CampamentDTO campament, boolean isEspecial){
        double price = 300.0;
        ArrayList<ActivityDTO> activities = campament.getActivities();

        if(getFactory(campament) instanceof EarlyRegInscriptionFactory)
            price -= 100.0;
        
        if(!isEspecial){
            for(int i = 0; i < activities.size(); i++)
                price += 20.0;
        }

        return price;    
    }

    /**
     * Determinate if the date of the inscription is before the init date of the campament.
     * @param campament
     * @return True if the date of the inscription is before the init date of the campament.
     */
    public boolean canEnroll(CampamentDTO campament){
        LocalDate date = LocalDate.now();
        LocalDate campamentDate = campament.getInitDate();

        return date.isBefore(campamentDate);
    }

    /**
     * Enrolls an assistant in a campament with a complete inscription.
     *
     * @param campament The CampamentDTO object representing the campament.
     * @param assistant The AssistantDTO object representing the assistant.
     * @param schedule The Schedule object representing the schedule.
     */
    public void enrollComplete(CampamentDTO campament, AssistantDTO assistant, Schedule schendule) throws Exception {
        CompleteInscriptionDTO completeInscriptionDTO;
        InscriptionFactory factory = getFactory(campament);

        if(factory == null)
            throw new BusinessException("Es demasiado tarde para apuntarse a este campamento.");

        if(!canEnroll(campament))
            throw new BusinessException("El campamento ya ha comenzado.");
        
        completeInscriptionDTO = factory.createCompleteInscription(campament, assistant, schendule, LocalDate.now());

        double price = calcPrice(campament, assistant.getAtention());
        completeInscriptionDTO.setPrice(price);
        
        CompleteInscriptionDAO iDao = new CompleteInscriptionDAO();

        iDao.insert(completeInscriptionDTO);
    }

    /**
     * Enrolls an assistant in a campament with a partial inscription.
     *
     * @param campament The CampamentDTO object representing the campament.
     * @param assistant The AssistantDTO object representing the assistant.
     */
    public void enrollParcial(CampamentDTO campament, AssistantDTO assistant) throws Exception {
        ParcialInscriptionDTO parcialInscriptionDTO;
        InscriptionFactory factory = getFactory(campament);

        if(factory == null)
            throw new BusinessException("Es demasiado tarde para apuntarse a este campamento.");

        if(!canEnroll(campament))
            throw new BusinessException("El campamento ya ha comenzado.");

        parcialInscriptionDTO = factory.createParcialInscription(campament, assistant, LocalDate.now());

        double price = calcPrice(campament, assistant.getAtention());
        parcialInscriptionDTO.setPrice(price);

        ParcialInscriptionDAO iDao = new ParcialInscriptionDAO();
        iDao.insert(parcialInscriptionDTO);
    }
}
