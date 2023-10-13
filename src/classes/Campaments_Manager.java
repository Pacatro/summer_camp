package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Campaments_Manager {

    public void createActivity(ArrayList<Activity> activities) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Schendule schendule;
        Level level;

        System.out.println("Creación de una nueva actividad:");
        System.out.print("Nombre de la actividad: ");
        String name = scanner.nextLine();

        System.out.print("Elige una opción de nivel (infantil, juvenil, adolescente): ");
        String opcion2 = scanner.nextLine();
        if (opcion2.equalsIgnoreCase("infantil")) {
            System.out.println("Has elegido la opción 'infantil'.");
            level = Level.CHILD;
        } else if (opcion2.equalsIgnoreCase("juvenil")) {
            System.out.println("Has elegido la opción 'juvenil'.");
            level = Level.YOUTH;
        } else if (opcion2.equalsIgnoreCase("adolescente")) {
            System.out.println("Has elegido la opción 'adolescente'.");
            level = Level.TEENAGER;
        } else {
            System.out.println("Opción no válida. Debes elegir 'infantil', 'juvenil' o 'adolescente'.");
            return;
        }

        System.out.print("Elige una opción (mañana/tarde): ");
        String opcion = scanner.nextLine();
        if (opcion.equalsIgnoreCase("mañana")) {
            System.out.println("Has elegido la opción 'mañana'.");
            schendule = Schendule.MORNING;
        } else if (opcion.equalsIgnoreCase("tarde")) {
            System.out.println("Has elegido la opción 'tarde'.");
            schendule = Schendule.AFTERNOON;
        } else {
            System.out.println("Opción no válida. Debes elegir 'mañana' o 'tarde'.");
            return;
        }

        System.out.print("Máximos participantes de la actividad: ");
        int max_participants = scanner.nextInt();

        System.out.print("Número de monitores: ");
        int num_monitors = scanner.nextInt();

        Activity newActivity = new Activity(name, level, schendule, max_participants, num_monitors);
        activities.add(newActivity);
    }

    public void createMonitor(ArrayList<Monitor> monitors) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Creación de un nuevo monitor:");
        System.out.print("ID del monitor: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nombre del monitor: ");
        String name = scanner.nextLine();

        System.out.print("Apellido del monitor: ");
        String surname = scanner.nextLine();

        System.out.print("¿Es un monitor de atención especial? (true/false): ");
        boolean isEspecial = scanner.nextBoolean();

        Monitor newMonitor = new Monitor(id, name, surname, isEspecial);
        monitors.add(newMonitor);

    }

    public void createCampaments(ArrayList<Campament> campaments) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Creación de un nuevo campamento:");
        System.out.print("ID del campamento: ");
        int id = scanner.nextInt();

        System.out.print("Fecha de inicio (AAAA-MM-DD): ");
        String initDateStr = scanner.next();
        LocalDate initDate = LocalDate.parse(initDateStr);

        System.out.print("Fecha de finalización (AAAA-MM-DD): ");
        String finalDateStr = scanner.next();
        LocalDate finalDate = LocalDate.parse(finalDateStr);

        Campament nuevoCampamento = new Campament(id, initDate, finalDate);
        campaments.add(nuevoCampamento);

    }

    // si es especial no lo muestro.
    public void associateMonitorsToActivities(ArrayList<Activity> activities, ArrayList<Monitor> monitors) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Asociación de monitores a actividades:");
        for (int k = 0; k < activities.size(); k++) {
            System.out.println("Actividad: " + k + ")" + activities.get(k).getname());
        }
        System.out.println("Selecciona una actividad:");
        int selectedActivityIndex = scanner.nextInt();
        if (selectedActivityIndex >= 0 && selectedActivityIndex < activities.size()) {
            Activity activity = activities.get(selectedActivityIndex);
            System.out.print("Número de monitores a asociar a esta actividad: ");

            int numMonitorsNeeded = activity.getNumMonitors();
            System.out.println("Número de monitores a asociar a esta actividad: " + numMonitorsNeeded);

            for (int i = 0; i < numMonitorsNeeded; i++) {
                System.out.println("Selecciona un monitor:");

                // Mostrar la lista de monitores disponibles
                for (int j = 0; j < monitors.size(); j++) {
                    if (!monitors.get(j).isEspecial()) {
                        System.out.println(j + ". " + monitors.get(j).getName());
                    }
                }

                int selectedMonitorIndex = scanner.nextInt();

                // Asociar el monitor seleccionado a la actividad
                if (selectedMonitorIndex >= 0 && selectedMonitorIndex < monitors.size()) {
                    Monitor selectedMonitor = monitors.get(selectedMonitorIndex);
                    activity.associateMonitor(selectedMonitor);
                } else {
                    System.out.println("Índice de monitor no válido. No se asignó ningún monitor.");
                }
            }
        }

    }

    // En la clase Campaments_Manager

    public void associateActivitiesToCampaments(ArrayList<Campament> campaments, ArrayList<Activity> activities) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Asociación de actividades a campamentos:");

        for (int k = 0; k < campaments.size(); k++) {
            System.out.println("Campamento: " + k + ")" + campaments.get(k).getId());
        }

        System.out.println("Selecciona un campamento:");
        int selectedCampamentIndex = scanner.nextInt();
        scanner.nextLine();

        if (selectedCampamentIndex >= 0 && selectedCampamentIndex < campaments.size()) {
            Campament selectedCampament = campaments.get(selectedCampamentIndex);
            Level campamentLevel = selectedCampament.getLevel();
            System.out.println("Número de actividades a asociar a este campamento:");

            // Mostrar la lista de actividades disponibles que coinciden en nivel con el
            // campamento
            for (int j = 0; j < activities.size(); j++) {
                if (activities.get(j).getLevel().equals(campamentLevel)) {
                    System.out.println(j + ". " + activities.get(j).getname());
                }
            }

            System.out.println("Selecciona una actividad:");
            int selectedActivityIndex = scanner.nextInt();

            if (selectedActivityIndex >= 0 && selectedActivityIndex < activities.size()) {
                Activity selectedActivity = activities.get(selectedActivityIndex);
                if (selectedActivity.getLevel().equals(campamentLevel)) {
                    selectedCampament.associateActivity(selectedActivity);
                } else {
                    System.out.println("El nivel de la actividad no coincide con el del campamento.");
                }
            } else {
                System.out.println("Índice de actividad no válido. No se asignó ninguna actividad.");
            }
        }

    }

    public void associateMonitorsToCampaments(ArrayList<Campament> campaments, ArrayList<Monitor> monitors) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Asociación de monitores a campamentos:");

        for (int k = 0; k < campaments.size(); k++) {
            System.out.println("Campamento: " + k + ")" + campaments.get(k).getId());
        }

        System.out.println("Selecciona un campamento:");
        int selectedCampamentIndex = scanner.nextInt();
        scanner.nextLine();

        if (selectedCampamentIndex >= 0 && selectedCampamentIndex < campaments.size()) {
            Campament selectedCampament = campaments.get(selectedCampamentIndex);

            for (int j = 0; j < monitors.size(); j++) {
                System.out.println(j + ". " + monitors.get(j).getName());
            }

            System.out.println("Selecciona un monitor:");
            int selectedMonitorIndex = scanner.nextInt();

            if (selectedMonitorIndex >= 0 && selectedMonitorIndex < monitors.size()) {
                Monitor selectedMonitor = monitors.get(selectedMonitorIndex);
                ArrayList<Monitor> ActivityMonitors = new ArrayList<Monitor>();
                for (Activity activity : selectedCampament.getActivities()) {
                    for (Monitor monitor : activity.getMonitors()) {
                        if (!ActivityMonitors.contains(monitor)) {
                            ActivityMonitors.add(monitor);
                        }
                    }
                }
                if(selectedMonitor.isEspecial() && (selectedCampament.existsEspecialAssistant() &&! ActivityMonitors.contains(selectedMonitor)) ||
                    !selectedMonitor.isEspecial() && ActivityMonitors.contains(selectedMonitor)){
                        selectedCampament.associateMonitor(selectedMonitor);
                    }
            } else {
                System.out.println("Índice de monitor no válido. No se asignó ningún monitor.");
            }
        }
    }
    
}




