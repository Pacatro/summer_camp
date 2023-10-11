package factory;

public abstract class InscriptionFactory {
    public abstract CompleteInscription createCompleteInscription();
    public abstract ParcialInscription createParcialInscription();
}
