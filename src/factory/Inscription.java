package factory;

import java.time.LocalDate;

import classes.Schendule;

/**
 * Represents a model inscription.
 * This abstract class defines the attributes and methods common to all types of inscriptions.
 */
public abstract class Inscription {
    
    /* Atributtes */
    
    private int id_participant;

    private int id_campament;

    private LocalDate date;

    private double price;

    private boolean cancellation;

    private Schendule schendule;

    /* Constructor */

    /**
     * Default constructor for an Inscription.
     * Initializes default values for some attributes.
     */
    public Inscription(){
        this.id_participant = 0;
        this.id_campament = 0;
        this.date = LocalDate.now();
        this.price = 0.0;
    }

    /* Gets y Sets */

    public int getIdParticipant(){
        return this.id_participant;
    }

    public int getIdCampament(){
        return this.id_campament;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public double getPrice(){
        return this.price;
    }

    public boolean getCancellation(){
        return this.cancellation;
    }

    public Schendule getSchendule() {
        return this.schendule;
    }

    public void setIdParticipant(int id_participant){
        this.id_participant = id_participant;
    }

    public void setIdCampament(int id_campament){
        this.id_campament = id_campament;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setCancellation(boolean cancellation) {
        this.cancellation = cancellation;
    }

    public void setSchendule(Schendule schendule) {
        this.schendule = schendule;
    }

    public String toString(){
        return "Soy una inscription";
    }
}