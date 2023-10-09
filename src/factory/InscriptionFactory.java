package factory;

public abstract class InscriptionFactory {
    public abstract EarlyRegInscription createEarlyRegInscription();    
    public abstract LateRegInscription createLateRegInscription();
}
