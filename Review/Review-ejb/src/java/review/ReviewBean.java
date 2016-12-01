/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package review;

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
public class ReviewBean {
    private static final String ADMIN = "ADMIN";
    Utils util = new Utils();
    
    @PersistenceContext
    private EntityManager em;

    public ReviewBean() {
    }

    @PostConstruct
    private void init() {
        System.out.println("INSTANCIA REVIEW BEAN");
    }
    
    public ReviewEntity calificarEnvio(Integer idEnvio, Integer idCadete, Integer rating, 
            String comentario, Integer idCliente) {
        ReviewEntity review = null;
        try {
            //Aqui llamamos al metodo que expone el modulo Client para validar el cliente
            //Verificamos que no exista una review para esta envio
            //Validamos el comentario
            //Detectamos palabras inadecuadas
            //Calificamos la semantica
            //Actualizamos el estado de la review y los datos
            review = new ReviewEntity();
            review.setComentario(comentario);
            review.setEstado(comentario);
            review.setRating(rating);
            //Actualizamos el rating del Cadete mediante el metodo que expone el modulo cadet
            //Notificamos la review
            em.persist(review);
            //}
        } catch (Exception exe) {
            Utils.logError(" ***********Calificar*Envio************");
            Utils.logError(exe.getMessage());
        }
        return review;
    }

    public ReviewEntity modificarReview(Integer id, String comentario, Integer rating, String estado) {
        ReviewEntity review = null;
        try {
           //if (esCliente(usuario, contrasena)) {
            review = em.find(ReviewEntity.class, id);
            review.setComentario(comentario);
            review.setEstado(estado);
            review.setRating(rating);
            
            em.merge(review);
            //}

        } catch (Exception exe) {
            Utils.logError(" ***********MODIFICACION*REVIEW************");
            Utils.logError("Error:" + exe.getMessage());
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
            Utils.logError(" ***********ELIMINACION*REVIEW************");
            Utils.logError("Error:" + exe.getMessage());
        }
        return ret;
    }

    public List<ReviewEntity> listarReview() {
        List<ReviewEntity> list = new ArrayList();
        try {
            list = em
                    .createNativeQuery("select * from ReviewEntity c")
                    .getResultList();
        } catch (Exception exe) {
            Utils.logError(" ***********LISTAR*REVIEW************");
            Utils.logError(exe.getMessage());
        }
        return list;
    }

    public List<ReviewEntity> listarEnviosPendientesCalificacion(Integer idCliente) {
        List<ReviewEntity> list = new ArrayList();
        try {
            list = em
                    .createNativeQuery("select * from ReviewEntity c")
                    .getResultList();
        } catch (Exception exe) {
            Utils.logError(" ***********LISTAR*REVIEW************");
            Utils.logError("Error:" + exe.getMessage());
        }
        return list;
    }        
            
    public Review buscarReview(Integer id) {
        Review review = new Review();
        try {
            ReviewEntity ent = em.find(ReviewEntity.class, id);
            ent.setComentario(ent.getComentario());
            ent.setEstado(ent.getEstado());
            ent.setRating(ent.getRating());
            
        } catch (Exception exe) {
            Utils.logError(" ***********BUSCAR*REVIEW*POR*ID************");
            Utils.logError("Error:" + exe.getMessage());
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
