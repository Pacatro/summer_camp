package classes;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import classes.Assistant;

public class AssistantManager {

    public boolean register(Assistant id, ArrayList<Assistant> list){
        for(int i=0; i<list.size();i++){
            if(list.get(i)==id){
                return false;
            }
        }
        list.add(id);
        return true;
    }

    public Assistant search(int id, ArrayList<Assistant> list) {
        for (Assistant a : list){
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }
 
    public boolean modify(int id, String newname, String newsurname, LocalDate newdate, boolean newatention, ArrayList<Assistant> list){
        Assistant a1=search(id, list);
        if(a1!=null){
            a1.setName(newname);
            a1.setSurname(newsurname);
            a1.setDate(newdate);
            a1.setAtention(newatention);
            return true;
        }
        return false;
    }

    public void print(ArrayList<Assistant> register){
        for(Assistant a : register){
            System.out.println("ID: " + a.getId());
            System.out.println("Nombre: " + a.getName());
            System.out.println("Apellido: " + a.getSurname());
            System.out.println("Fecha: " + a.getDate());
            System.out.println("Atención: " + a.getAtention());
            System.out.println();
        }
    }

}
