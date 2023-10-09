package factory;

public class EarlyRegInscription extends Inscription{
    public EarlyRegInscription(){
        setCancellation(false);
    }
    
    public String toString(){
        String infoEarly= "La informacion de la inscripcion es: "+ getIdParticipant() + " " + getIdCampament() + " con fecha "+ getDate()+ ", precio "+ getPrice()+ " y se puede cancelar.";
        return infoEarly;
    }
}
