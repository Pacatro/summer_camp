package es.uco.pw.business.factory;

import java.time.LocalDate;

import es.uco.pw.business.schendule.Schendule;

/**
 * Represents a model inscription.
 * This abstract class defines the attributes and methods common to all types of inscriptions.
 */
public abstract class InscriptionDTO {
    
    /* Atributtes */
    
    private int participantId;

    private int campamentId;

    private LocalDate date;

    private double price;

    private boolean cancellation;

    private Schendule schendule;

    /* Constructor */

    /**
     * Default constructor for an Inscription.
     * Initializes default values for some attributes.
     */
    public InscriptionDTO(){
        this.participantId = 0;
        this.campamentId = 0;
        this.date = LocalDate.now();
        this.price = 0.0;
    }

    /* Gets y Sets */

    public int getIdParticipant(){
        return this.participantId;
    }

    public int getIdCampament(){
        return this.campamentId;
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

    public void setIdParticipant(int participantId){
        this.participantId = participantId;
    }

    public void setIdCampament(int campamentId){
        this.campamentId = campamentId;
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