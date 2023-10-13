package classes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import factory.CompleteInscription;
import factory.EarlyRegInscriptionFactory;
import factory.InscriptionFactory;
import factory.LateRegInscriptionFactory;
import factory.ParcialInscription;

public class InscriptionsManager {
    public InscriptionsManager(){}

    public InscriptionFactory getFactory(Campament campament){
        LocalDate today = LocalDate.now();
        LocalDate campamentDate = campament.getInitDate();
        
        long dif = ChronoUnit.DAYS.between(today, campamentDate);

        if(dif >= 15)
            return new EarlyRegInscriptionFactory();
        else if(dif >= 2)
            return new LateRegInscriptionFactory();
        
        return null;
    }

    public double calcPrice(Campament campament, boolean isEspecial){
        double price = 300.0;
        ArrayList<Activity> activities = campament.getActivities();

        if(getFactory(campament).getClass().getSimpleName() == "EarlyRegInscriptionFactory")
            price -= 100.0;
        
        if(!isEspecial){
            for(int i = 0; i < activities.size(); i++)
                price += 20.0;
        }

        return price;    
    }

    public boolean canEnroll(Campament campament){
        LocalDate date = LocalDate.now();
        LocalDate campamentDate = campament.getInitDate();

        return date.isBefore(campamentDate);
    }

    public void enrollComplete(Campament campament, Assistant assistant, Schendule schendule, 
                               ArrayList<CompleteInscription> completesInscriptions){

        CompleteInscription completeInscription;
        ArrayList<Monitor> monitors = campament.getMonitors();
        InscriptionFactory factory = getFactory(campament);

        if(factory == null){
            System.out.println("No se ha podido crear la fabrica de inscripciones");
            return;
        }

        if(assistant.getAtention()){
            for(Monitor m : monitors){
                if(m.isEspecial())
                    campament.associateSpecialMonitor(m);
            }
        }

        if(!canEnroll(campament)){ 
            System.out.println("El campamento ya ha comenzado.");
            return;
        }

        completeInscription = factory.createCompleteInscription(campament, assistant, schendule, LocalDate.now());

        double price = calcPrice(campament, assistant.getAtention());
        completeInscription.setPrice(price);
        completesInscriptions.add(completeInscription);
    }

    public void enrollParcial(Campament campament, Assistant assistant, Schendule schendule, 
                              ArrayList<ParcialInscription> parcialsInscriptions){

        ParcialInscription parcialInscription;
        ArrayList<Monitor> monitors = campament.getMonitors();
        InscriptionFactory factory = getFactory(campament);

        if(factory == null){
            System.out.println("No se ha podido crear la fabrica de inscripciones");
            return;
        }

        if(assistant.getAtention()){
            for(Monitor m : monitors){
                if(m.isEspecial())
                    campament.associateSpecialMonitor(m);
            }
        }

        if(!canEnroll(campament)){ 
            System.out.println("El campamento ya ha comenzado.");
            return;
        }

        parcialInscription = factory.createParcialInscription(campament, assistant, LocalDate.now());

        double price = calcPrice(campament, assistant.getAtention());
        parcialInscription.setPrice(price);
        parcialsInscriptions.add(parcialInscription);
    }
}
