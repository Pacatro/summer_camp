package factory;

import java.time.LocalDate;

public class ParcialInscriptionFactory extends InscriptionFactory{
    @Override
    public EarlyRegInscription createEarlyRegInscription(){
        EarlyRegInscription eInscription = new EarlyRegInscription();
        //Si esta bien hecha, debemos implementar un sistema para pedir los datos por pantalla. 
        LocalDate date = LocalDate.now();
        LocalDate campamenDate = LocalDate.now().plusDays(20);

        eInscription.setIdCampament(200);
        eInscription.setIdParticipant(500);
        eInscription.setPrice(100.56);

        //TODO: Comprobar id del campamento en la base de datos para saber si es de mañanas o de tardes.

        int dif = campamenDate.compareTo(date);

        if(dif < 15){
            System.out.println("Fecha incorrecta");
        }

        eInscription.setDate(date);
        return eInscription;
    }

    @Override
    public LateRegInscription createLateRegInscription(){
        LateRegInscription lInscription = new LateRegInscription();
        LocalDate date = LocalDate.now();
        LocalDate campamenDate = LocalDate.now().plusDays(20);

        lInscription.setIdCampament(200);
        lInscription.setIdParticipant(500);
        lInscription.setPrice(100.56);

        //TODO: Comprobar id del campamento en la base de datos para saber si es de mañanas o de tardes.

        int dif = campamenDate.compareTo(date);

        if(dif < 15 || dif < 2){
            System.out.println("Fecha incorrecta");
        }

        return lInscription;
    }
}
