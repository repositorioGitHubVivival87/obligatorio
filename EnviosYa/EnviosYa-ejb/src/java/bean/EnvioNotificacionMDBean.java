package bean;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/Topic"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
    })

public class EnvioNotificacionMDBean implements MessageListener {
    
    public EnvioNotificacionMDBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            TextMessage msg = (TextMessage) message;
            System.out.println("Notificacion de envio = " + msg.getText());
        } catch (JMSException ex) {
            Logger.getLogger(EnvioNotificacionMDBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
