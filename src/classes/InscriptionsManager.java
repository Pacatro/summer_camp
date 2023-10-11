package classes;

import java.util.Scanner;

import factory.CompleteInscription;
import factory.EarlyRegInscriptionFactory;
import factory.Inscription;
import factory.LateRegInscriptionFactory;
import factory.ParcialInscription;

public class InscriptionsManager {
    public void enroll(Assistant assistant, Campament campament){
        Scanner scanner = new Scanner(System.in);
        EarlyRegInscriptionFactory earlyRegInscriptionFactory = new EarlyRegInscriptionFactory();
        LateRegInscriptionFactory lateRegInscriptionFactory = new LateRegInscriptionFactory();
        Inscription inscription = null;

        System.out.println("Introduzca la modalidad de la inscripcion:");
        System.out.println("1. Registro temprano");
        System.out.println("2. Registro tardío");
        int regOption = scanner.nextInt();

        System.out.println("Introduzca el tipo de inscripcion:");
        System.out.println("1. Inscripcion completa");
        System.out.println("2. Inscripcion parcial");
        int typeOption = scanner.nextInt();

        if (regOption == 1 && typeOption == 1) {
            inscription = earlyRegInscriptionFactory.createCompleteInscription();
        } else if (regOption == 1 && typeOption == 2) {
            inscription = earlyRegInscriptionFactory.createParcialInscription();
        } else if (regOption == 2 && typeOption == 1) {
            inscription = lateRegInscriptionFactory.createCompleteInscription();
        } else if (regOption == 2 && typeOption == 2) {
            inscription = lateRegInscriptionFactory.createParcialInscription();
        }

        System.out.println(inscription);
    }    

}
