package ClasesBase.Managers;

import ClasesBase.Sala;
import DAO.ImplementacionDAO;

/**
 *
 * @author Facu SSD
 */
public class ManagerSala {

    private static ManagerSala manager;
    
    private ManagerSala(){

    }
    
    public static ManagerSala getManagerSala(){
        if(manager == null)
            manager = new ManagerSala();
        return manager;
    }
    
    public Sala getSala(int idSala){
        ImplementacionDAO dao = ImplementacionDAO.getDAO();
        
        return dao.obtenerSala(idSala);
    }
    
}
