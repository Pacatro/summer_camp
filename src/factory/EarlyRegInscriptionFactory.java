package factory;

import java.time.LocalDate;

import classes.Schendule;

public class EarlyRegInscriptionFactory extends InscriptionFactory {
    @Override
    public CompleteInscription createCompleteInscription(){
        CompleteInscription cInscription = new CompleteInscription(Schendule.MORNING);

        LocalDate date = LocalDate.now();
        LocalDate campamenDate = LocalDate.now().plusDays(20);

        cInscription.setIdCampament(200);
        cInscription.setIdParticipant(500);
        cInscription.setPrice(100.56);

        //TODO: Comprobar que el campamento existe en la base de datos

        int dif = campamenDate.compareTo(date);

        if(dif < 15){
            System.out.println("Fecha incorrecta");
        }

        cInscription.setDate(date);
        
        return cInscription;
    }

    @Override
    public ParcialInscription createParcialInscription(){
        ParcialInscription pInscription = new ParcialInscription();
        LocalDate date = LocalDate.now();
        LocalDate campamenDate = LocalDate.now().plusDays(20);

        pInscription.setIdCampament(200);
        pInscription.setIdParticipant(500);
        pInscription.setPrice(100.56);

        //TODO: Comprobar que el campamento existe en la base de datos

        int dif = campamenDate.compareTo(date);

        if(dif < 15){
            System.out.println("Fecha incorrecta");
        }

        return pInscription;
    }
}
