package classes;

import java.time.LocalDate;
import java.util.ArrayList;
import factory.Inscription;
import factory.EarlyRegInscriptionFactory;
import factory.InscriptionFactory;
import factory.LateRegInscriptionFactory;

public class InscriptionsManager {
    public InscriptionsManager(){}

    public InscriptionFactory getFactory(Campament campament){
        LocalDate date = LocalDate.now();
        LocalDate campamentDate = campament.getInitDate();
        
        int dif = date.compareTo(campamentDate);

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

    public void enroll(Campament campament, Assistant assistant, Schendule schendule, 
                       String type, ArrayList<Inscription> inscriptions){

        Inscription inscription;
        ArrayList<Monitor> monitors = campament.getMonitors();
        InscriptionFactory factory = getFactory(campament);

        if(factory == null)
            return;

        if(assistant.getAtention()){
            for(Monitor m : monitors){
                if(m.isEspecial())
                    campament.associateSpecialMonitor(m);
            }
        }

        if(!canEnroll(campament)) return;

        if(type == "parcial")
            inscription = factory.createParcialInscription(campament, assistant, LocalDate.now());
        else
            inscription = factory.createCompleteInscription(campament, assistant, schendule, LocalDate.now());

        double price = calcPrice(campament, assistant.getAtention());
        inscription.setPrice(price);
        inscriptions.add(inscription);
    }
}
