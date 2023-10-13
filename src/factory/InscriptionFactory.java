package factory;

import classes.Assistant;
import classes.Campament;
import classes.Schendule;

public abstract class InscriptionFactory {
    public abstract CompleteInscription createCompleteInscription(Campament campament, Assistant assistant, Schendule schendule);
    public abstract ParcialInscription createParcialInscription(Campament campament, Assistant assistant);
}
