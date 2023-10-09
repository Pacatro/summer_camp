package factory;

import java.time.LocalDate;

public class CompleteInscriptionFactory extends InscriptionFactory {
    @Override
    public EarlyRegInscription createEarlyRegInscription(){
        EarlyRegInscription eInscription = new EarlyRegInscription();
        //Si esta bien hecha, debemos implementar un sistema para pedir los datos por pantalla. 
        LocalDate date = LocalDate.now();
        LocalDate campamenDate = LocalDate.now().plusDays(20);

        eInscription.setIdCampament(200);
        eInscription.setIdParticipant(500);
        eInscription.setPrice(100.56);

        int dif = campamenDate.compareTo(date);

        if(dif < 15){
            System.out.println("FURULA");
        } else {
            System.out.println("NO FURULA");
        }

        eInscription.setDate(date);
        return eInscription;
    }

    @Override
    public LateRegInscription createLateRegInscription(){
        LateRegInscription lInscription = new LateRegInscription();
        return lInscription;
    }
}
