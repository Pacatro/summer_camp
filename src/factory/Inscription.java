package factory;

import java.time.LocalDate;

public class Inscription {
    private String id_participant;

    private String id_campament;

    private LocalDate date;

    private float price;

    private boolean cancellation;

    public Inscription(){
        this.id_participant = "";
        this.id_campament = "";
        this.date = LocalDate.now();
        this.price = 0f;
    }
}
