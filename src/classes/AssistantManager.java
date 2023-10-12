package classes;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import classes.Assistant;

public class AssistantManager {
    private List<Assistant> registerAssistants;

    public AssistantManager(){registerAssistants=new ArrayList<>();}

    public boolean register(Assistant id){
        if(!registerAssistants.contains(id)){
            registerAssistants.add(id);
            return true;
        }
        return false;
    }

    public Assistant search(int id) {
        for (Assistant a : registerAssistants){
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }
 
    public boolean modify(int id, String newname, String newsurname, LocalDate newdate, boolean newatention){
        Assistant a1=search(id);
        if(a1==true){
            a1.setName(newname);
            a1.setSurname(newsurname);
            a1.setDate(newdate);
            a1.setAtention(newatention);
            return true;
        }
        return false;
    }

    public void print(ArrayList<Assistant> register){
        for(Assistant a : registerAssistants){
            System.out.println("ID: " + a.getId());
            System.out.println("Nombre: " + a.getName());
            System.out.println("Apellido: " + a.getSurname());
            System.out.println("Fecha: " + a.getDate());
            System.out.println("Atención: " + a.getAtention());
            System.out.println();
        }
    }

}
