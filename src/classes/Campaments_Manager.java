package classes;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Campaments_Manager {
    public void createActivity(ArrayList<Activity> activities, String name, Level level, Schendule schendule,
            int max_participants, int num_monitors) throws Exception {
        /*
         * Scanner scanner = new Scanner(System.in);
         * Schendule schendule;
         * Level level;
         * 
         * System.out.println("Creación de una nueva actividad:");
         * System.out.print("Nombre de la actividad: ");
         * String name = scanner.nextLine();
         * 
         * System.out.
         * print("Elige una opción de nivel (infantil, juvenil, adolescente): ");
         * String opcion2 = scanner.nextLine();
         * if (opcion2.equalsIgnoreCase("infantil")) {
         * System.out.println("Has elegido la opción 'infantil'.");
         * level = Level.CHILD;
         * } else if (opcion2.equalsIgnoreCase("juvenil")) {
         * System.out.println("Has elegido la opción 'juvenil'.");
         * level = Level.YOUTH;
         * } else if (opcion2.equalsIgnoreCase("adolescente")) {
         * System.out.println("Has elegido la opción 'adolescente'.");
         * level = Level.TEENAGER;
         * } else {
         * System.out.
         * println("Opción no válida. Debes elegir 'infantil', 'juvenil' o 'adolescente'."
         * );
         * return;
         * }
         * 
         * System.out.print("Elige una opción (mañana/tarde): ");
         * String opcion = scanner.nextLine();
         * if (opcion.equalsIgnoreCase("mañana")) {
         * System.out.println("Has elegido la opción 'mañana'.");
         * schendule = Schendule.MORNING;
         * } else if (opcion.equalsIgnoreCase("tarde")) {
         * System.out.println("Has elegido la opción 'tarde'.");
         * schendule = Schendule.AFTERNOON;
         * } else {
         * System.out.println("Opción no válida. Debes elegir 'mañana' o 'tarde'.");
         * return;
         * }
         * 
         * System.out.print("Máximos participantes de la actividad: ");
         * int max_participants = scanner.nextInt();
         * 
         * System.out.print("Número de monitores: ");
         * int num_monitors = scanner.nextInt();
         */

        Activity newActivity = new Activity(name, level, schendule, max_participants, num_monitors);
        activities.add(newActivity);
    }

    public void createMonitor(ArrayList<Monitor> monitors, int id, String name, String surname, boolean isEspecial)
            throws Exception {

        /*
         * Scanner scanner = new Scanner(System.in);
         * 
         * System.out.println("Creación de un nuevo monitor:");
         * System.out.print("ID del monitor: ");
         * int id = scanner.nextInt();
         * scanner.nextLine();
         * 
         * System.out.print("Nombre del monitor: ");
         * String name = scanner.nextLine();
         * 
         * System.out.print("Apellido del monitor: ");
         * String surname = scanner.nextLine();
         * 
         * System.out.print("¿Es un monitor de atención especial? (true/false): ");
         * boolean isEspecial = scanner.nextBoolean();
         */

        Monitor newMonitor = new Monitor(id, name, surname, isEspecial);
        monitors.add(newMonitor);

    }

    public void createCampaments(ArrayList<Campament> campaments, int id, LocalDate initDate, LocalDate finalDate,
            Level level) throws Exception {
        /*
         * Scanner scanner = new Scanner(System.in);
         * 
         * System.out.println("Creación de un nuevo campamento:");
         * System.out.print("ID del campamento: ");
         * int id = scanner.nextInt();
         * 
         * System.out.print("Fecha de inicio (AAAA-MM-DD): ");
         * String initDateStr = scanner.next();
         * LocalDate initDate = LocalDate.parse(initDateStr);
         * 
         * System.out.print("Fecha de finalización (AAAA-MM-DD): ");
         * String finalDateStr = scanner.next();
         * LocalDate finalDate = LocalDate.parse(finalDateStr);
         * 
         * System.out.print("Elige una opción (infantil/juvenil/adolescente): ");
         * //////////////////////////////////////PACO ESTO A LOS MENUS
         * String opcion = scanner.nextLine();
         * if (opcion.equalsIgnoreCase("infantil")) {
         * System.out.println("Has elegido la opción 'infantil'.");
         * level= Level.CHILD;
         * } else if (opcion.equalsIgnoreCase("juvenil")) {
         * System.out.println("Has elegido la opción 'juvenil'.");
         * level= Level.YOUTH;
         * } else if (opcion.equalsIgnoreCase("adolescente")) {
         * System.out.println("Has elegido la opción 'adolescente'.");
         * level= Level.TEENAGER;
         * } else {
         * System.out.
         * println("Opción no válida. Debes elegir 'infantil', 'juvenil' o 'adolescente'."
         * );
         * return;
         * }
         * 
         */

        Campament nuevoCampamento = new Campament(id, initDate, finalDate, level);
        campaments.add(nuevoCampamento);

    }

    // si es especial no lo muestro.
    public void associateMonitorsToActivities(ArrayList<Activity> activities, ArrayList<Monitor> monitors) {
        // inicio de seleccion de actividad
        Scanner scanner = new Scanner(System.in);

        // System.out.println("Asociación de monitores a actividades:");
        for (int k = 0; k < activities.size(); k++) {
            System.out.println("Actividad: " + k + ")" + activities.get(k).getname());
        }
        System.out.println("Selecciona una actividad:");
        int selectedActivityIndex = scanner.nextInt();

        while (!(selectedActivityIndex >= 0 && selectedActivityIndex < activities.size())) {
            System.out.println("Índice de actividad no válido. No se asignó ninguna actividad.");
            System.out.println("Selecciona una actividad:");
            selectedActivityIndex = scanner.nextInt();
        }

        Activity activity = activities.get(selectedActivityIndex);
        // fin de seleccion de actividad

        int numMonitorsNeeded = activity.getNumMonitors();
        System.out.println("Número de monitores a asociar a esta actividad: " + numMonitorsNeeded);

        // TODO Mover al menu
        // se tiene que rellenar toda la actividad con el numero de monitores que
        // requiera. El bucle de pedir monitor va en el menu.
        // Se comenta el bucle y se lleva al menu para poder separar entre menu y
        // funcion.
        // for (int i = 0; i < numMonitorsNeeded; i++) {
        System.out.println("Selecciona un monitor:");

        // Mostrar la lista de monitores disponibles
        for (int j = 0; j < monitors.size(); j++) {
            if (!monitors.get(j).isEspecial()) {
                System.out.println(j + ". " + monitors.get(j).getName());
            }
        }

        int selectedMonitorIndex = scanner.nextInt();
        while (!(selectedMonitorIndex >= 0 && selectedMonitorIndex < monitors.size()
                && monitors.get(selectedMonitorIndex).isEspecial() == false)) {
            System.out.println("Índice de monitor no válido.");
            System.out.println("Selecciona un monitor:");
            selectedMonitorIndex = scanner.nextInt();
        }
        // funcionalidad que no es de menu
        Monitor selectedMonitor = monitors.get(selectedMonitorIndex);
        boolean isMonitorAdded = activity.associateMonitor(selectedMonitor);
        if (!isMonitorAdded) {
            System.out.println("No se admiten mas monitores en esta actividad.");
            return;
        }
        // fin de funcionalidad que no es de menu

        // }

        scanner.close();

    }

    // En la clase Campaments_Manager

    public void associateActivitiesToCampaments(ArrayList<Campament> campaments, ArrayList<Activity> activities) {
        Scanner scanner = new Scanner(System.in);
        // System.out.println("Asociación de actividades a campamentos:");

        for (int k = 0; k < campaments.size(); k++) {
            System.out.println("Campamento: " + k + ")" + campaments.get(k).getId());
        }

        System.out.println("Selecciona un campamento:");
        int selectedCampamentIndex = scanner.nextInt();
        scanner.nextLine();

        while (!(selectedCampamentIndex >= 0 && selectedCampamentIndex < campaments.size())) {
            System.out.println("Índice del campamento no válido.");
            System.out.println("Selecciona un campamento:");
            selectedCampamentIndex = scanner.nextInt();
            scanner.nextLine();
        }
        Campament selectedCampament = campaments.get(selectedCampamentIndex);
        Level campamentLevel = selectedCampament.getLevel();

        for (int j = 0; j < activities.size(); j++) {
            if (activities.get(j).getLevel().equals(campamentLevel)) {
                System.out.println(j + ". " + activities.get(j).getname());
            }
        }

        System.out.println("Selecciona una actividad:");
        int selectedActivityIndex = scanner.nextInt();
        scanner.nextLine();

        while (!(selectedActivityIndex >= 0 && selectedActivityIndex < activities.size())) {
            System.out.println("Índice de actividad no válido.");
            System.out.println("Selecciona una actividad:");
            selectedActivityIndex = scanner.nextInt();
            scanner.nextLine();
        }

        Activity selectedActivity = activities.get(selectedActivityIndex);
        selectedCampament.associateActivity(selectedActivity);
        scanner.close();
    }

    public void associateMonitorsToCampaments(ArrayList<Campament> campaments, ArrayList<Monitor> monitors) {
        Scanner scanner = new Scanner(System.in);
        // System.out.println("Asociación de monitores a campamentos:");

        for (int k = 0; k < campaments.size(); k++) {
            System.out.println("Campamento: " + k + ")" + campaments.get(k).getId());
        }

        System.out.println("Selecciona un campamento:");
        int selectedCampamentIndex = scanner.nextInt();
        scanner.nextLine();

        while (!(selectedCampamentIndex >= 0 && selectedCampamentIndex < campaments.size())) {
            System.out.println("Índice del campamento no válido.");
            System.out.println("Selecciona un campamento:");
        }

        Campament selectedCampament = campaments.get(selectedCampamentIndex);

        for (int j = 0; j < monitors.size(); j++) {
            System.out.println(j + ". " + monitors.get(j).getName());
        }

        System.out.println("Selecciona un monitor:");
        int selectedMonitorIndex = scanner.nextInt();
        scanner.nextLine();

        while (!(selectedMonitorIndex >= 0 && selectedMonitorIndex < monitors.size())) {
            System.out.println("Índice de monitor no válido.");
            System.out.println("Selecciona un monitor:");
            selectedMonitorIndex = scanner.nextInt();
            scanner.nextLine();
        }

        Monitor selectedMonitor = monitors.get(selectedMonitorIndex);
        ArrayList<Monitor> activityMonitors = selectedCampament.getAllActivityMonitors();

    /*
Asociar monitores a campamentos, entre aquellos que están asignados a las actividades que conforman el campamento en un momento determinado. 
Esto es, si se eliminase una actividad del campamento, su monitor no podría figurar como monitor responsable si no está asignado a otra actividad del mismo campamento.

Asociar monitores de atención especial a campamentos, entre aquellos que tengan tal indicación y que no consten como asignados a actividades del campamento. 
La asociación del monitor de atención especial será únicamente necesaria cuando en el campamento se haya inscrito, al menos, un asistente con necesidades especiales.
    */
        if (selectedMonitor.isEspecial() && (selectedCampament.existsEspecialAssistant() && !activityMonitors.contains(selectedMonitor))
            ||
            !selectedMonitor.isEspecial() && activityMonitors.contains(selectedMonitor)) {
                selectedCampament.associateMonitor(selectedMonitor);
        }

        scanner.close();
    }



}
