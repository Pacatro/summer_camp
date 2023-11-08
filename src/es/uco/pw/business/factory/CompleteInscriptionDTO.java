package es.uco.pw.business.factory;

import es.uco.pw.business.schedule.Schedule;

/**
 * Represents a complete inscription for a camp with both morning and afternoon schedule.
 * This class extends the general Inscription class to provide specific functionality
 * for complete inscriptions with a detailed string representation.
 */
public class CompleteInscriptionDTO extends InscriptionDTO {

    /**
     * Constructs a complete inscription without parameters
     */
    public CompleteInscriptionDTO(){}

    /**
     * Constructs a complete inscription with the provided schedule.
     *
     * @param schedule The schedule associated with the complete inscription.
     */
    public CompleteInscriptionDTO(Schedule schedule){
        setSchedule(schedule);
    }

    @Override
    public String toString(){
        return "Inscripcion para el participante " + this.getIdParticipant() 
                + ", en el campamento " + this.getIdCampament() + ", con fecha de inicio " + this.getDate() 
                + ", horario de mañanas y tardes" + " y precio " + this.getPrice() + "€.";
    }
}
