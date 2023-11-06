
package objeto_usuario;

import java.util.ArrayList;

public class UserAdmin extends Usuarios{
    
    private ArrayList<Integer>ids=new ArrayList<>();

    public UserAdmin(String nombre_usuario, String contraseña, int tipo_usuario, int edad, String nombre_real) {
        super(nombre_usuario, contraseña, tipo_usuario, edad, nombre_real);
    }

    public void AgregarId(int id) {
        ids.add(id);
        for (Integer sas : ids) {
            System.out.println(sas);
        }
    }
    public boolean revisar(int code){
        for (int i = 0; i < ids.size(); i++) {
             if(ids.get(i)!=null&&ids.get(i)==code){
                 return true;
             }
        }
       return false;
    }
    public ArrayList getIds(){
        return ids;
    }
}
