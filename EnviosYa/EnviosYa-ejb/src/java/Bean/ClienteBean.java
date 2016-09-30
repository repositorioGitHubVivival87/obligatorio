/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Entidades.Cliente;
import Entity.ClienteEntity;
import Herramientas.Utiles;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Vivi
 */
@Stateless
@LocalBean
public class ClienteBean {

    private static final String ADMIN = "ADMIN";

//    @Resource(lookup = "jms/ConnectionFactory")
//    private ConnectionFactory cf;
//    
//    @Resource(lookup = "jms/Queue")
//    private Queue queue;
//    
//    @Resource(lookup = "jms/Topic")
//    private Topic topic;
    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    private void init() {
        System.out.println("INSTANCIA CLIENTE BEAN");
    }

    public ClienteBean() {
    }

    public ClienteEntity agregarCliente(int ci, String nombre, String apellido, String email) {
        ClienteEntity c = new ClienteEntity();
        try {
            c.setCi(ci);
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setEmail(email);

            em.persist(c);

        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********ALTA*CLIENTE************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return c;
    }

    public ClienteEntity modificarCliente(Integer id, Integer ci, String nombre, String apellido, String email) {
        ClienteEntity c = null;
        try {
            c = em.find(ClienteEntity.class, id);
            c.setCi(ci);
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setEmail(email);

            em.merge(c);

        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********MODIFICACION*CLIENTE************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return c;
    }

    public boolean eliminarCliente(Integer id) {
        boolean ret = false;
        try {
            ClienteEntity c = em.find(ClienteEntity.class, id);
            em.remove(c);
            ret = true;

        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********ELIMINACION*CLIENTE************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return ret;
    }

    public List<ClienteEntity> listarClientes() {
        List<ClienteEntity> list = new ArrayList();
        try {
            list = em
                    .createQuery("select c from ClienteEntity c")
                    .getResultList();
        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********LISTAR*CLIENTES*************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return list;
    }

    public Cliente buscarClientes(Integer id) {
        Cliente c = new Cliente();
        try {
            ClienteEntity ent = em.find(ClienteEntity.class, id);
            c.setId(ent.getId());
            c.setNombre(ent.getNombre());

        } catch (Exception exe) {
            Utiles.logWS("EnviosYa", " ***********BUSCAR*CLIENTE*POR*ID************");
            Utiles.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return c;
    }

}
