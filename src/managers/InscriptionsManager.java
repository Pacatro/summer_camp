package managers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import classes.Activity;
import classes.Assistant;
import classes.Campament;
import classes.Monitor;
import enums.Schendule;
import factory.CompleteInscription;
import factory.EarlyRegInscriptionFactory;
import factory.InscriptionFactory;
import factory.LateRegInscriptionFactory;
import factory.ParcialInscription;

/**
 * Manages the completes and parcials inscriptions lists.
 */
public class InscriptionsManager {
    public InscriptionsManager(){}

    /**
     * Gets the inscription factory depending on the init date of the campament and the actual date.
     * @param campament
     * @return The inscription factory
     */
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

    /**
     * Calculate the price of the inscription.
     * @param campament
     * @param isEspecial
     * @return The final price of the inscription.
     */
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

    /**
     * Determinate if the date of the inscription is before the init date of the campament.
     * @param campament
     * @return True if the date of the inscription is before the init date of the campament.
     */
    public boolean canEnroll(Campament campament){
        LocalDate date = LocalDate.now();
        LocalDate campamentDate = campament.getInitDate();

        return date.isBefore(campamentDate);
    }

    /**
     * Add a new registration for a new inscription to the list of completed inscriptions for a campament.
     * @param campament
     * @param assistant
     * @param schendule
     * @param completesInscriptions
     */
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

    /**
     * Add a new registration for a new inscription to the list of parcials inscriptions for a campament.
     * @param campament
     * @param assistant
     * @param schendule
     * @param parcialsInscriptions
     */
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
