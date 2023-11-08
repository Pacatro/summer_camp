package es.uco.pw.business.factory;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import es.uco.pw.business.assistant.AssistantDTO;
import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.schendule.Schedule;

/**
 * A factory for creating early registration inscriptions for a camp.
 * This factory specializes in creating complete and partial inscriptions with early registration requirements.
 */
public class EarlyRegInscriptionFactory extends InscriptionFactory {
    @Override
    public CompleteInscriptionDTO createCompleteInscription(CampamentDTO campament, AssistantDTO assistant, Schedule schendule, LocalDate date) throws Exception {
        CompleteInscriptionDTO cInscription = new CompleteInscriptionDTO(schendule);

        //LocalDate date = LocalDate.now();
        LocalDate campamentDate = campament.getInitDate();

        cInscription.setIdCampament(campament.getId());
        cInscription.setIdParticipant(assistant.getId());

        long dif = ChronoUnit.DAYS.between(date, campamentDate);

        if(dif < 15) throw new Exception("Fecha incorrecta");

        cInscription.setDate(date);
        
        return cInscription;
    }

    @Override
    public ParcialInscriptionDTO createParcialInscription(CampamentDTO campament, AssistantDTO assistant, LocalDate date) throws Exception {
        ParcialInscriptionDTO pInscription = new ParcialInscriptionDTO();

        //LocalDate date = LocalDate.now();
        LocalDate campamentDate = campament.getInitDate();

        pInscription.setIdCampament(campament.getId());
        pInscription.setIdParticipant(assistant.getId());

        long dif = ChronoUnit.DAYS.between(date, campamentDate);

        if(dif < 15) throw new Exception("Fecha incorrecta");

        pInscription.setDate(date);
        
        return pInscription;
    }
}
