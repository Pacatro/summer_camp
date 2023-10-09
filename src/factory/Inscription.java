package factory;

import java.time.LocalDate;

public class Inscription {
    
    /* Atributtes */
    
    private int id_participant;

    private int id_campament;

    private LocalDate date;

    private double price;

    private boolean cancellation;

    /* Constructor */

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

}
