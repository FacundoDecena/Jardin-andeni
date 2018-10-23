/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesBase.Managers;

import ClasesBase.Pago;
import DAO.ImplementacionDAO;

/**
 *
 * @author Maxi
 */
public class ManagerPago {
    private static ManagerPago mp = null;
    
    private ManagerPago(){
    }
    
    public static ManagerPago getManager(){
        if(mp==null)
            mp = new ManagerPago();
        return mp;  
    }
    
    public float obtenerValorInscripcion(){
        return ImplementacionDAO.getDAO().obtenerValorInscripcion();
    }

    public void altaPago(Pago pago) {
        ImplementacionDAO.getDAO().altaPago(pago);
    }
    
}
