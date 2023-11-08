package es.uco.pw.business.factory;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import es.uco.pw.business.assistant.AssistantDTO;
import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.schendule.Schedule;

/**
 * A factory for creating late registration inscriptions for a camp.
 * This factory specializes in creating complete and partial inscriptions with late registration requirements.
 */
public class LateRegInscriptionFactory extends InscriptionFactory {
    @Override
    public CompleteInscriptionDTO createCompleteInscription(CampamentDTO campament, AssistantDTO assistant, Schedule schendule, LocalDate date){
        CompleteInscriptionDTO cInscription = new CompleteInscriptionDTO(schendule);

        LocalDate campamentDate = campament.getInitDate();

        cInscription.setIdCampament(campament.getId());
        cInscription.setIdParticipant(assistant.getId());

        long dif = ChronoUnit.DAYS.between(date, campamentDate);

        if (dif < 2 || dif < 15) {
            System.out.println("Fecha incorrecta");
            return null;
        }

        cInscription.setDate(date);
        
        return cInscription;
    }

    @Override
    public ParcialInscriptionDTO createParcialInscription(CampamentDTO campament, AssistantDTO assistant, LocalDate date){
        ParcialInscriptionDTO pInscription = new ParcialInscriptionDTO();

        LocalDate campamentDate = campament.getInitDate();

        pInscription.setIdCampament(campament.getId());
        pInscription.setIdParticipant(assistant.getId());

        long dif = ChronoUnit.DAYS.between(date, campamentDate);

        if (dif < 2 || dif < 15) {
            System.out.println("Fecha incorrecta");
            return null;
        }

        pInscription.setDate(date);
        
        return pInscription;
    }
}
