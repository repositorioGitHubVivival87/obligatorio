/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Entidades.Cadete;
import java.util.List;

/**
 *
 * @author Vivi
 */
public class CadeteBean {
    public Cadete agregarCadete(Integer ci, String nombre, String apellido, String email){
        Cadete c = new Cadete();
        c.setCi(ci);
        c.setNombre(nombre);
        c.setApellido(apellido);
        c.setEmail(email);
      //  em.persist(u);        
        return c;
    }
            
    public Cadete modificarCadete(Integer id, Integer ci, String nombre, String apellido, String email){
//        CadeteEntity u = em.find(CadeteEntity.class, id);
//        u.setNombre(nombre);
//        em.merge(u);
        return null;
    }
    
    public boolean eliminarUsuario(Long id){
        return true;
    }
    
    public List<Cadete> listarCadetes(){
        List<Cadete> list = null; //em.createQuery("select c from Cadete c").getResultList();
        
        return list;
    }
    
    public Cadete buscarCadetes(Integer id){
        return null;
    }
}
