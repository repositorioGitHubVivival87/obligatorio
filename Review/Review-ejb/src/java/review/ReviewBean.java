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
    
    public ReviewPersistence agregarReview(String comentario, Integer rating, String estado) {
        ReviewPersistence review = null;
        try {
            

        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********ALTA*REVIEW************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return review;
    }

    public ReviewPersistence modificarReview(Integer id, String comentario, Integer rating, String estado) {
        ReviewPersistence review = null;
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
            ReviewPersistence review = em.find(ReviewPersistence.class, id);
            em.remove(review);
            ret = true;
        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********ELIMINACION*REVIEW************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return ret;
    }

    public List<ReviewPersistence> listarReview() {
        List<ReviewPersistence> list = new ArrayList();
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

    public ReviewEntity buscarReview(Integer id) {
        ReviewEntity review = new ReviewEntity();
        try {

        } catch (Exception exe) {
            Utils.logWS("EnviosYa", " ***********BUSCAR*REVIEW*POR*ID************");
            Utils.logWS("EnviosYa", "Error:" + exe.getMessage());
        }
        return review;
    }   
}
