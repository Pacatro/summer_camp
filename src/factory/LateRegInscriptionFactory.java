package factory;

import java.time.LocalDate;

import classes.Assistant;
import classes.Campament;
import classes.Schendule;

public class LateRegInscriptionFactory extends InscriptionFactory {
    @Override
    public CompleteInscription createCompleteInscription(Campament campament, Assistant assistant, Schendule schendule, LocalDate date){
        CompleteInscription cInscription = new CompleteInscription(schendule);

        //LocalDate date = LocalDate.now();
        LocalDate campamentDate = campament.getInitDate();

        cInscription.setIdCampament(campament.getId());
        cInscription.setIdParticipant(assistant.getId());

        int dif = date.compareTo(campamentDate);

        if (dif < -2 || dif >= 15) {
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

        int dif = campamentDate.compareTo(date);

        if (dif < -2 || dif >= 15) {
            System.out.println("Fecha incorrecta");
            return null;
        }

        pInscription.setDate(date);
        
        return pInscription;
    }
}
