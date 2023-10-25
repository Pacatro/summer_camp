package business.factory;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import business.assistant.AssistantDTO;
import business.campament.CampamentDTO;
import business.schendule.Schendule;

/**
 * A factory for creating early registration inscriptions for a camp.
 * This factory specializes in creating complete and partial inscriptions with early registration requirements.
 */
public class EarlyRegInscriptionFactory extends InscriptionFactory {
    @Override
    public CompleteInscriptionDTO createCompleteInscription(CampamentDTO campament, AssistantDTO assistant, Schendule schendule, LocalDate date){
        CompleteInscriptionDTO cInscription = new CompleteInscriptionDTO(schendule);

        //LocalDate date = LocalDate.now();
        LocalDate campamentDate = campament.getInitDate();

        cInscription.setIdCampament(campament.getId());
        cInscription.setIdParticipant(assistant.getId());

        long dif = ChronoUnit.DAYS.between(date, campamentDate);

        if(dif < 15){
            System.out.println("Fecha incorrecta");
            return null;
        }

        cInscription.setDate(date);
        
        return cInscription;
    }

    @Override
    public ParcialInscriptionDTO createParcialInscription(CampamentDTO campament, AssistantDTO assistant, LocalDate date){
        ParcialInscriptionDTO pInscription = new ParcialInscriptionDTO();

        //LocalDate date = LocalDate.now();
        LocalDate campamentDate = campament.getInitDate();

        pInscription.setIdCampament(campament.getId());
        pInscription.setIdParticipant(assistant.getId());

        long dif = ChronoUnit.DAYS.between(date, campamentDate);

        if(dif < 15){
            System.out.println("Fecha incorrecta");
            return null;
        }

        pInscription.setDate(date);
        
        return pInscription;
    }
}
