package factory;

import java.time.LocalDate;

public class Inscription {
    
    /* Atributtes */
    
    private String id_participant;

    private String id_campament;

    private LocalDate date;

    private float price;

    private boolean cancellation;

    /* Constructor */

    public Inscription(){
        this.id_participant = "";
        this.id_campament = "";
        this.date = LocalDate.now();
        this.price = 0f;
    }

    /* Gets y Sets */

    public String getIdParticipant(){
        return this.id_participant;
    }

    public String getIdCampament(){
        return this.id_campament;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public float getPrice(){
        return this.price;
    }

    public boolean getCancellation(){
        return this.cancellation;
    }

    public void setIdParticipant(String id_participant){
        this.id_participant = id_participant;
    }

    public void setIdCampament(String id_campament){
        this.id_campament = id_campament;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public void setPrice(float price){
        this.price = price;
    }

}
