package es.uco.pw.business.factory;

import es.uco.pw.business.assistant.AssistantDTO;
import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.business.schendule.Schendule;

import java.time.LocalDate;

/**
 * This abstract class represents an inscription factory for a camp.
 * It provides abstract methods to create complete and partial inscriptions,
 * allowing the creation of specific inscription objects for a given camp, assistant, and schedule.
 */
public abstract class InscriptionFactory {
    
    /**
     * Creates a complete inscription for a given camp, assistant, schedule, and specific date.
     *
     * @param campament  The camp to which the assistant is enrolling.
     * @param assistant  The assistant being enrolled in the camp.
     * @param schendule  The schedule or program in which the assistant will participate.
     * @param date       The date on which the inscription is made.
     * @return An instance of CompleteInscription representing the complete inscription.
     */
    public abstract CompleteInscriptionDTO createCompleteInscription(CampamentDTO campament, AssistantDTO assistant, Schendule schendule, LocalDate date) throws Exception;
    
    /**
     * Creates a partial inscription for a given camp and assistant on a specific date.
     *
     * @param campament  The camp to which the assistant is enrolling.
     * @param assistant  The assistant being enrolled in the camp.
     * @param date       The date on which the inscription is made.
     * @return An instance of ParcialInscription representing the partial inscription.
     */
    public abstract ParcialInscriptionDTO createParcialInscription(CampamentDTO campament, AssistantDTO assistant, LocalDate date) throws Exception;
}
