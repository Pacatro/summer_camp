package es.uco.pw.business.factory;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import es.uco.pw.business.assistant.AssistantDTO;
import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.common.exceptions.BusinessException;
import es.uco.pw.business.common.schedule.Schedule;

/**
 * A factory for creating early registration inscriptions for a camp.
 * This factory specializes in creating complete and partial inscriptions with early registration requirements.
 */
public class EarlyRegInscriptionFactory extends InscriptionFactory {
    @Override
    public CompleteInscriptionDTO createCompleteInscription(CampamentDTO campament, AssistantDTO assistant, Schedule schendule, LocalDate date) throws Exception {
        CompleteInscriptionDTO cInscription = new CompleteInscriptionDTO(schendule);

        LocalDate campamentDate = campament.getInitDate();

        cInscription.setIdCampament(campament.getId());
        cInscription.setIdParticipant(assistant.getId());

        long dif = ChronoUnit.DAYS.between(date, campamentDate);

        if(dif < 15) throw new BusinessException("Fecha incorrecta");

        cInscription.setDate(date);
        
        return cInscription;
    }

    @Override
    public ParcialInscriptionDTO createParcialInscription(CampamentDTO campament, AssistantDTO assistant, LocalDate date) throws Exception {
        ParcialInscriptionDTO pInscription = new ParcialInscriptionDTO();

        LocalDate campamentDate = campament.getInitDate();

        pInscription.setIdCampament(campament.getId());
        pInscription.setIdParticipant(assistant.getId());

        long dif = ChronoUnit.DAYS.between(date, campamentDate);

        if(dif < 15) throw new BusinessException("Fecha incorrecta");

        pInscription.setDate(date);
        
        return pInscription;
    }
}
