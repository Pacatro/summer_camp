package factory;

public class LateRegInscription extends Inscription{
    public LateRegInscription(){
        setCancellation(false);
    }
    
    public String toString(){
        String infoLate= "La informacion de la inscripcion es: "+ getIdParticipant()+ getIdCampament() + " con fecha "+ getDate()+ ", precio "+ getPrice()+ " y no se puede cancelar.";
        return infoLate;
    }
} 

