package es.uco.pw.business.factory;

import es.uco.pw.business.schendule.Schendule;

/**
 * Represents a complete inscription for a camp with both morning and afternoon schedule.
 * This class extends the general Inscription class to provide specific functionality
 * for complete inscriptions with a detailed string representation.
 */
public class CompleteInscriptionDTO extends Inscription {
    
    /**
     * Constructs a complete inscription with the provided schedule.
     *
     * @param schendule The schedule associated with the complete inscription.
     */
    public CompleteInscriptionDTO(Schendule schendule){
        setSchendule(schendule);
    }

    @Override
    public String toString(){
        return "Inscripcion para el participante " + this.getIdParticipant() 
                + ", en el campamento " + this.getIdCampament() + ", con fecha de inicio " + this.getDate() 
                + ", horario de mañanas y tardes" + " y precio " + this.getPrice() + "€.";
    }
}
