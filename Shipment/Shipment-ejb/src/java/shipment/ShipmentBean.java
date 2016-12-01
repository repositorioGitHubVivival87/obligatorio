/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shipment;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import tools.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * HttpResponse
 *
 * @author Vivi
 */
@Stateless
@LocalBean
public class ShipmentBean {

    private static final String ADMIN = "ADMIN";
    Utils util = new Utils();

    @PersistenceContext
    private EntityManager em;

    @Resource(lookup = "jms/ConnectionFactory")
    private ConnectionFactory cf;

    @Resource(lookup = "jms/Topic")
    private Topic topic;

    public ShipmentBean() {
    }

    @PostConstruct
    private void init() {
        System.out.println("INSTANCIA SHIPMENT BEAN");
    }

    private void encolar(String texto) {
        try {
            Connection connection = cf.createConnection();
            Session session = connection.createSession();
            TextMessage msg = session.createTextMessage(texto);
            MessageProducer producer = session.createProducer(topic);
            producer.send(msg);
            session.close();
            connection.close();
        } catch (JMSException ex) {
            Logger.getLogger(ShipmentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String solicitudEnvio(String token, Integer id, Integer idClienteRecibe, Integer idCadete,
            String direccionRetiro, String direccionRecibe, String descripcion, String formaDePago,
            String foto, Double costoEnvio, Double costoComision) {
        String retorno = "";
        try {

            //INSERTO EL SHIPMENT
            ShipmentEntity envio = new ShipmentEntity();
            envio.setDescripcion(descripcion);
            envio.setDireccionDestinatario(direccionRecibe);
            envio.setDireccionEnvia(direccionRetiro);
            envio.setEstado("P");
            envio.setFormaDePago(formaDePago);
            envio.setFoto(foto);
            envio.setIdCadete(idCadete);
            envio.setIdClienteEnvia(id);
            envio.setIdClienteRecibe(idClienteRecibe);
            envio.setCostoEnvio(costoEnvio);
            envio.setComision(costoComision);

            em.persist(envio);

            //Aca llamamos al metodo que expone el modulo notifications notificarEnvioCadete 
            //para notificar al cadete el envio.
            System.out.println("retorno" + retorno);
        } catch (Exception exe) {
            Utils.logError(" ***********AGREGAR*ENVIO************");
            Utils.logError(exe.getMessage());
        }
        return retorno;
    }

    public Double calcularCostoEnvio(String imagen) {
        Double costoEnvio = 0.0;
        try {

            HttpResponse<JsonNode> response = Unirest.post("https://ort-arqsoftort-sizer.herokuapp.com/dimensions")
                    .header("X-Mashape-Key", "<required>")
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .body("{\"image\" : \"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAMAAADXqc3KAAAB+FBMVEUAAAA/mUPidDHiLi5Cn0XkNTPmeUrkdUg/m0Q0pEfcpSbwaVdKskg+lUP4zA/iLi3msSHkOjVAmETdJSjtYFE/lkPnRj3sWUs8kkLeqCVIq0fxvhXqUkbVmSjwa1n1yBLepyX1xxP0xRXqUkboST9KukpHpUbuvRrzrhF/ljbwaljuZFM4jELaoSdLtElJrUj1xxP6zwzfqSU4i0HYnydMtUlIqUfywxb60AxZqEXaoifgMCXptR9MtklHpEY2iUHWnSjvvRr70QujkC+pUC/90glMuEnlOjVMt0j70QriLS1LtEnnRj3qUUXfIidOjsxAhcZFo0bjNDH0xxNLr0dIrUdmntVTkMoyfL8jcLBRuErhJyrgKyb4zA/5zg3tYFBBmUTmQTnhMinruBzvvhnxwxZ/st+Ktt5zp9hqota2vtK6y9FemNBblc9HiMiTtMbFtsM6gcPV2r6dwroseLrMrbQrdLGdyKoobKbo3Zh+ynrgVllZulTsXE3rV0pIqUf42UVUo0JyjEHoS0HmsiHRGR/lmRz/1hjqnxjvpRWfwtOhusaz0LRGf7FEfbDVmqHXlJeW0pbXq5bec3fX0nTnzmuJuWvhoFFhm0FtrziBsjaAaDCYWC+uSi6jQS3FsSfLJiTirCOkuCG1KiG+wSC+GBvgyhTszQ64Z77KAAAARXRSTlMAIQRDLyUgCwsE6ebm5ubg2dLR0byXl4FDQzU1NDEuLSUgC+vr6urq6ubb29vb2tra2tG8vLu7u7uXl5eXgYGBgYGBLiUALabIAAABsElEQVQoz12S9VPjQBxHt8VaOA6HE+AOzv1wd7pJk5I2adpCC7RUcHd3d3fXf5PvLkxheD++z+yb7GSRlwD/+Hj/APQCZWxM5M+goF+RMbHK594v+tPoiN1uHxkt+xzt9+R9wnRTZZQpXQ0T5uP1IQxToyOAZiQu5HEpjeA4SWIoksRxNiGC1tRZJ4LNxgHgnU5nJZBDvuDdl8lzQRBsQ+s9PZt7s7Pz8wsL39/DkIfZ4xlB2Gqsq62ta9oxVlVrNZpihFRpGO9fzQw1ms0NDWZz07iGkJmIFH8xxkc3a/WWlubmFkv9AB2SEpDvKxbjidN2faseaNV3zoHXvv7wMODJdkOHAegweAfFPx4G67KluxzottCU9n8CUqXzcIQdXOytAHqXxomvykhEKN9EFutG22p//0rbNvHVxiJywa8yS2KDfV1dfbu31H8jF1RHiTKtWYeHxUvq3bn0pyjCRaiRU6aDO+gb3aEfEeVNsDgm8zzLy9egPa7Qt8TSJdwhjplk06HH43ZNJ3s91KKCHQ5x4sw1fRGYDZ0n1L4FKb9/BP5JLYxToheoFCVxz57PPS8UhhEpLBVeAAAAAElFTkSuQmCC\"}")
                    .asJson();

            String retorno = response.getBody().toString();
            String[] arrayRetorno = retorno.split(",");
            String length = arrayRetorno[0].split(":")[1];
            String weight = arrayRetorno[1].split(":")[1];
            String height = arrayRetorno[2].split(":")[1];
            costoEnvio = Double.parseDouble(length) * Double.parseDouble(weight);

        } catch (Exception exe) {
            Utils.logError(" ***********CACULAR*COSTO*ENVIO************");
            Utils.logError(exe.getMessage());
        }
        return costoEnvio;
    }

    public String recepcionEnvio(Integer idEnvio) {
        String retorno = "EXITO";
        try {
            ShipmentEntity envio = em.find(ShipmentEntity.class, idEnvio);
            Integer idClienteEnvia = envio.getIdClienteEnvia();
            Integer idClienteRecibe = envio.getIdClienteRecibe();
            //Actualizamos el estado del envio para RECIBIDO
            envio.setEstado("R");
            em.merge(envio);
            //Aqui obtenemos los emails de los clientes mediante el metodo que expone
            //el modulo client ObtenerUnCliente
            //Con ambos emails llamamos al metodo que expone el modulo Notifications
            //notificarRecepcionEnvio
            
        } catch (Exception exe) {
            Utils.logError(" ***********NOTIFICAR*RECEPCION*ENVIO************");
            Utils.logError(exe.getMessage());
        }
        return retorno;
    }

    public boolean validarUsuario(String token, Integer id) {
        boolean ret = false;
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/Client-war/cliente/validarClienteLogueado?token=" + token + "&id=" + id)
                    .asString();

            String retorno = response.getBody().toString();
            if (retorno.equalsIgnoreCase("true")) {
                ret = true;
            }
        } catch (Exception exe) {
            Utils.logError(" ***********VALIDAR*USUARIO************");
            Utils.logError(exe.getMessage());
        }
        return ret;
    }

    public List<ShipmentEntity> consulta() {
        List<ShipmentEntity> listRetorno = new ArrayList();
        try {
            //lista de todos los envios
            listRetorno = em
                    .createQuery("select e from ShipmentPersistence e")
                    .getResultList();

        } catch (Exception exe) {
            Utils.logError(" ***********CONSULTA*ENVIOS*************");
            Utils.logError("Error:" + exe.getMessage());
        }
        return listRetorno;
    }

}
