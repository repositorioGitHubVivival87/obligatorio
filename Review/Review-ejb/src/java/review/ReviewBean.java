/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package review;

import herramientas.Utils;
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
public class ReviewBean {
    private static final String ADMIN = "ADMIN";

    @PersistenceContext
    private EntityManager em;

    public ReviewBean() {
    }

    @PostConstruct
    private void init() {
        System.out.println("INSTANCIA REVIEW BEAN");
    }
    
    public ReviewEntity agregarReview(String comentario, Integer rating, String estado) {
        ReviewEntity review = null;
        try {
            

        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********ALTA*REVIEW************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return review;
    }

    public ReviewEntity modificarReview(Integer id, String comentario, Integer rating, String estado) {
        ReviewEntity review = null;
        try {
           

        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********MODIFICACION*REVIEW************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return review;
    }

    public boolean eliminarReview(Integer id) {
        boolean ret = false;
        try {
            ReviewEntity review = em.find(ReviewEntity.class, id);
            em.remove(review);
            ret = true;
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********ELIMINACION*REVIEW************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return ret;
    }

    public List<ReviewEntity> listarReview() {
        List<ReviewEntity> list = new ArrayList();
        try {
            list = em
                    .createQuery("select c from ReviewEntity c")
                    .getResultList();
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********LISTAR*REVIEW************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return list;
    }

    public Review buscarReview(Integer id) {
        Review review = new Review();
        try {

        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********BUSCAR*REVIEW*POR*ID************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return review;
    }   

    public String validarCliente(Integer idCliente) {
        return "";
    }

    public String validarReviewNoDuplicado(Integer idEnvio) {
        //valida que no haya otra review para ese idEnvio
        return "";
    }
    
    public String validarComentario(String comentario) {
        //valida que tenga una cantidad minima de palabras
        return "";
    }
    
    public String validarBuenVocabulario(String comentario) {
        //valida que las palabras no esten en una "lista negra de palabras"
        return "";
    }
    
    public String calificarSemantica(String comentario) {
        //valida que la semantica del comentario sea correcto
        return "";
    }
    
    public String notificarReviewCreada(Integer idReview) {
        //Se envia la review a los distintos destinatarios para disparar otros procesos de negocio. 
        //Se comunica con el modulo Notification
        return "";
    }

}
