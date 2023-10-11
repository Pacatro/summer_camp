package classes;

import java.util.Scanner;
import factory.EarlyRegInscriptionFactory;
import factory.LateRegInscriptionFactory;

public class InscriptionsManager {
    public void enroll(Assistant assistant, Campament campament){
        Scanner scanner = new Scanner(System.in);
        int option;    
        
        System.out.println("Introduzca la modalidad de la inscripcion:");
        System.out.println("1. Registro temprano");
        System.out.println("1. Registro tardio");
        option = scanner.nextInt();

        if(option == 1){
            EarlyRegInscriptionFactory earlyRegInscriptionFactory = new EarlyRegInscriptionFactory();
        }



    }
    

}
