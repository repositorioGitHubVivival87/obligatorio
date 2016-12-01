/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import tools.Utils;
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
public class PaymentBean {

    private static final String ADMIN = "ADMIN";

    @PersistenceContext
    private EntityManager em;

    public PaymentBean() {
    }

    @PostConstruct
    private void init() {
        System.out.println("INSTANCIA PAYMENT BEAN");
    }

    public PaymentEntity agregarMedioPago(Integer idCliente, String sello, String nombreembosado, 
            Integer fechavencimiento, Integer cvv, Integer nroTarjeta) {
        PaymentEntity medio = null;
        try {
            medio = new PaymentEntity();
            medio.setSello(sello);
            medio.setNombreembosado(nombreembosado);
            medio.setFechavencimiento(fechavencimiento);
            medio.setCvv(cvv);
            medio.setNroTarjeta(nroTarjeta);
           
            em.persist(medio);
            
        } catch (Exception exe) {
            Utils.logError(" ***********ALTA*MEDIO*DE*PAGO************");
            Utils.logError(exe.getMessage());
        }
        return medio;
    }

    public PaymentEntity modificarMedioDePago(Integer idCliente, Integer id, String sello, String nombreembosado,
            Integer fechavencimiento, Integer cvv, Integer nroTarjeta) {
        PaymentEntity mdp = null;
        boolean estaNroTarj = false;
        try {
            List<PaymentEntity> list = em
                    .createQuery("select p.* from PaymentEntity p where cliente_id = " + idCliente)
                    .getResultList();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getNroTarjeta().equals(nroTarjeta)
                        && list.get(i).getCvv().equals(cvv)) {
                    estaNroTarj = true;
                }
            }
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId().equals(id)
                        && list.get(i).getNroTarjeta().equals(nroTarjeta)
                        && list.get(i).getCvv().equals(cvv)) {
                    mdp = em.find(PaymentEntity.class, id);
                    mdp.setSello(sello);
                    mdp.setNombreembosado(nombreembosado);
                    mdp.setFechavencimiento(fechavencimiento);
                    em.merge(mdp);
                } else {
                    if (list.get(i).getId().equals(id) && !estaNroTarj) {
                        mdp = em.find(PaymentEntity.class, id);
                        mdp.setNroTarjeta(nroTarjeta);
                        mdp.setCvv(cvv);
                        mdp.setSello(sello);
                        mdp.setNombreembosado(nombreembosado);
                        mdp.setFechavencimiento(fechavencimiento);
                        em.merge(mdp);
                    }
                }
            }
        } catch (Exception exe) {
            Utils.logError(" ***********MODIFICACION*MEDIO*DE*PAGO************");
            Utils.logError(exe.getMessage());
        }
        return mdp;
    }

    public boolean eliminarMedioDePago(Integer id) {
        boolean ret = false;
        try {
            PaymentEntity mdp = em.find(PaymentEntity.class, id);
            em.remove(mdp);
            ret = true;

        } catch (Exception exe) {
            Utils.logError(" ***********ELIMINACION*MEDIO*DE*PAGO************");
            Utils.logError(exe.getMessage());
        }
        return ret;
    }

    public List<PaymentEntity> listarMediosDePagoPorCliente(Integer idCliente) {
        List<PaymentEntity> list = new ArrayList();
        try {
            list = em
                    .createNativeQuery("select p.* from PaymentEntity p where p.cliente_id = " + idCliente)
                    .getResultList();
        } catch (Exception exe) {
            Utils.logError(" ***********LISTAR*MEDIOS*DE*PAGO*************");
            Utils.logError(exe.getMessage());
        }
        return list;
    }

}
