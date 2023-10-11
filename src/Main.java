import classes.Campament;
import classes.Monitor;
import classes.Schendule;

import java.time.LocalDate;
import java.util.ArrayList;

import classes.Activity;
import classes.Level;

import classes.InscriptionsManager;

public class Main {
    public static void main(String[] args) throws Exception {
        LocalDate now = LocalDate.now();
		LocalDate tomorrow = now.plusDays(1);
		Campament c = new Campament(1, now, tomorrow);
		c.setMaxAssistants(200);
        
        Monitor m = new Monitor(1, "Paco", "Algar", false);
        ArrayList <Monitor> monitors = new ArrayList<Monitor>();        
        ArrayList <Activity> activities = new ArrayList<Activity>();

        Activity a = new Activity("A1", Level.CHILD, Schendule.AFTERNOON, 1001, 1);
        a.associateMonitor(m);
        activities.add(a);

        monitors.add(m);        
        monitors.add(m);

        c.setLevel(Level.CHILD);

        c.associateActivity(a);

        c.associateMonitor(m);

        InscriptionsManager inscriptionsManager = new InscriptionsManager();

        inscriptionsManager.enroll(null, c);
    }
}
