package factory;

import classes.Schendule;

public class ParcialInscription extends Inscription{
    public ParcialInscription(){
        setSchendule(Schendule.MORNING);
    }

    @Override
    public String toString(){
        return "Inscripcion para el participante " + this.getIdParticipant() 
                + ", en el campamento " + this.getIdCampament() + ", con fecha de inicio " + this.getDate() 
                + ", horario de mañanas" + " y precio " + this.getPrice() + "€.";
    }
}
