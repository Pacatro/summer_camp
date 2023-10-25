package factory;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import classes.Assistant;
import classes.Campament;
import enums.Schendule;

/**
 * A factory for creating early registration inscriptions for a camp.
 * This factory specializes in creating complete and partial inscriptions with early registration requirements.
 */
public class EarlyRegInscriptionFactory extends InscriptionFactory {
    @Override
    public CompleteInscription createCompleteInscription(Campament campament, Assistant assistant, Schendule schendule, LocalDate date){
        CompleteInscription cInscription = new CompleteInscription(schendule);

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
    public ParcialInscription createParcialInscription(Campament campament, Assistant assistant, LocalDate date){
        ParcialInscription pInscription = new ParcialInscription();

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
