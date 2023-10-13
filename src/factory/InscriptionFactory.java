package factory;

import classes.Assistant;
import classes.Campament;
import classes.Schendule;

import java.time.LocalDate;

public abstract class InscriptionFactory {
    public abstract CompleteInscription createCompleteInscription(Campament campament, Assistant assistant, Schendule schendule, LocalDate date);
    public abstract ParcialInscription createParcialInscription(Campament campament, Assistant assistant, LocalDate date);
}
