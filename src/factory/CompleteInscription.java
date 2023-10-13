package factory;

import classes.Schendule;

public class CompleteInscription extends Inscription {
    public CompleteInscription(Schendule schendule){
        setSchendule(schendule);
    }

    @Override
    public String toString(){
        return "Inscripcion para el participante " + this.getIdParticipant() 
                + ", en el campamento " + this.getIdCampament() + ", con fecha de inicio " + this.getDate() 
                + ", horario de mañanas y tardes" + " y precio " + this.getPrice() + "€.";
    }
}