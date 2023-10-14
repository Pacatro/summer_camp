package factory;

import enums.Schendule;

/**
 * Represents a partial inscription for a camp with morning schedule.
 * This class extends the general Inscription class to provide specific functionality
 * for partial inscriptions with a detailed string representation.
 */
public class ParcialInscription extends Inscription{
    
    /**
     * Constructs a partial inscription with a morning schedule.
     */
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
