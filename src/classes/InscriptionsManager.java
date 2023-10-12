package classes;

import java.time.LocalDate;
import java.util.ArrayList;
import factory.Inscription;
import factory.EarlyRegInscriptionFactory;
import factory.InscriptionFactory;
import factory.LateRegInscriptionFactory;

public class InscriptionsManager {
    private ArrayList<Monitor> monitors;

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

    public double calcPrice(Campament campament){
        double price = 300.0;
        ArrayList<Activity> activities = campament.getActivities();

        if(getFactory(campament).getClass().getSimpleName() == "EarlyRegInscriptionFactory")
            price -= 100.0;
        
        for(int i = 0; i < activities.size(); i++)
            price += 30.0;

        return price;    
    }

    public void enroll(Campament campament, Assistant assistant, Schendule schendule, 
                       String type, ArrayList<Inscription> inscriptions){

        Inscription inscription;
        InscriptionFactory factory = getFactory(campament);

        if(factory == null)
            return;

        if(assistant.getAtention()){
            for(Monitor m : this.monitors){
                if(m.isEspecial())
                    campament.associateSpecialMonitor(m);
            }
        }

        if(type == "parcial")
            inscription = factory.createParcialInscription(campament, assistant);
        else
            inscription = factory.createCompleteInscription(campament, assistant, schendule);

        double price = calcPrice(campament);
        inscription.setPrice(price);
        inscriptions.add(inscription);
    }
}
