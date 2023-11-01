package es.uco.pw.business.managers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import es.uco.pw.business.activity.ActivityDTO;
import es.uco.pw.business.assistant.AssistantDTO;
import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.monitor.MonitorDTO;
import es.uco.pw.business.schendule.Schendule;
import es.uco.pw.business.factory.CompleteInscriptionDTO;
import es.uco.pw.business.factory.EarlyRegInscriptionFactory;
import es.uco.pw.business.factory.InscriptionFactory;
import es.uco.pw.business.factory.LateRegInscriptionFactory;
import es.uco.pw.business.factory.ParcialInscriptionDTO;

import es.uco.pw.data.dao.InscriptionDAO;

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
     * Add a new registration for a new inscription to the list of completed inscriptions for a campament.
     * @param campament
     * @param assistant
     * @param schendule
     * @param completesInscriptions
     * @throws Exception
     */
    public void enrollComplete(CampamentDTO campament, AssistantDTO assistant, Schendule schendule) throws Exception{
        CompleteInscriptionDTO completeInscriptionDTO;
        ArrayList<MonitorDTO> monitors = campament.getMonitors();
        InscriptionFactory factory = getFactory(campament);

        if(factory == null){
            System.out.println("No se ha podido crear la fabrica de inscripciones");
            return;
        }

        // if(assistant.getAtention()){
        //     for(MonitorDTO m : monitors){
        //         if(m.isEspecial())
        //             campament.associateSpecialMonitor(m);
        //     }
        // }

        if(!canEnroll(campament)){ 
            System.out.println("El campamento ya ha comenzado.");
            return;
        }

        completeInscriptionDTO = factory.createCompleteInscription(campament, assistant, schendule, LocalDate.now());

        double price = calcPrice(campament, assistant.getAtention());
        completeInscriptionDTO.setPrice(price);
        
        InscriptionDAO iDao = new InscriptionDAO();

        iDao.insert(completeInscriptionDTO);
    }

    /**
     * Add a new registration for a new inscription to the list of parcials inscriptions for a campament.
     * @param campament
     * @param assistant
     * @param schendule
     * @param parcialsInscriptions
     */
    public void enrollParcial(CampamentDTO campament, AssistantDTO assistant, Schendule schendule) throws Exception{

        ParcialInscriptionDTO parcialInscriptionDTO;
        ArrayList<MonitorDTO> monitors = campament.getMonitors();
        InscriptionFactory factory = getFactory(campament);

        if(factory == null){
            System.out.println("No se ha podido crear la fabrica de inscripciones");
            return;
        }

        // if(assistant.getAtention()){
        //     for(MonitorDTO m : monitors){
        //         if(m.isEspecial())
        //             campament.associateSpecialMonitor(m);
        //     }
        // }

        if(!canEnroll(campament)){ 
            System.out.println("El campamento ya ha comenzado.");
            return;
        }

        parcialInscriptionDTO = factory.createParcialInscription(campament, assistant, LocalDate.now());

        double price = calcPrice(campament, assistant.getAtention());
        parcialInscriptionDTO.setPrice(price);

        InscriptionDAO iDao = new InscriptionDAO();

        iDao.insert(parcialInscriptionDTO);
    }
}
