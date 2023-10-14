package classes;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Campaments_Manager {
    
    public void createActivity(ArrayList<Activity> activities, String name, Level level, Schendule schendule, int max_participants, int num_monitors) throws Exception {
        
        Activity newActivity = new Activity(name, level, schendule, max_participants, num_monitors);
        activities.add(newActivity);
    }

    public void createMonitor(ArrayList<Monitor> monitors, int id, String name, String surname, boolean isEspecial)
            throws Exception {

        Monitor newMonitor = new Monitor(id, name, surname, isEspecial);
        monitors.add(newMonitor);

    }

    public void createCampaments(ArrayList<Campament> campaments, int id, LocalDate initDate, LocalDate finalDate,
            Level level) throws Exception {
        
        Campament nuevoCampamento = new Campament(id, initDate, finalDate, level);
        campaments.add(nuevoCampamento);

    }
    
    public void associateMonitorsToActivities(ArrayList<Activity> activities, ArrayList<Monitor> monitors, int selectedMonitorIndex, Activity activity) {
        
        Monitor selectedMonitor = monitors.get(selectedMonitorIndex);
        boolean isMonitorAdded = activity.associateMonitor(selectedMonitor);
        if (!isMonitorAdded) {
            System.out.println("No se admiten mas monitores en esta actividad.");
            return;
        }
    }

    public void associateActivitiesToCampaments(ArrayList<Campament> campaments, ArrayList<Activity> activities, int selectedActivityIndex, Campament selectedCampament) {
        
        Activity selectedActivity = activities.get(selectedActivityIndex);
        selectedCampament.associateActivity(selectedActivity);
    }

    
    public void associateMonitorsToCampaments(ArrayList<Campament> campaments, ArrayList<Monitor> monitors, int selectedMonitorIndex, Campament selectedCampament) {
        Scanner scanner = new Scanner(System.in);

        Monitor selectedMonitor = monitors.get(selectedMonitorIndex);
        ArrayList<Monitor> activityMonitors = selectedCampament.getAllActivityMonitors();

        if (selectedMonitor.isEspecial() && (selectedCampament.existsEspecialAssistant() && !activityMonitors.contains(selectedMonitor))
            ||
            !selectedMonitor.isEspecial() && activityMonitors.contains(selectedMonitor)) {
                selectedCampament.associateMonitor(selectedMonitor);
        }
    }



}
