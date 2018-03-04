package actionmonitor.web.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import actionmonitor.dto.PollResult;
import actionmonitor.util.Constants;
import actionmonitor.web.controller.IMessageController;

@Component
public class JmsListenerComponent {
	
	private static Logger logger = LoggerFactory.getLogger(JmsListenerComponent.class);
	
	private final IMessageController controller;
	
	@Autowired
	public JmsListenerComponent(IMessageController greetingController) {
		this.controller = greetingController;
	}
	
	@JmsListener(destination = Constants.JMS_ADDRESS, containerFactory = "myFactory")
    public void receiveMessage(PollResult result) {
		if (logger.isDebugEnabled()){
			logger.debug("Received JMS message with DTO : {}, sending to controller to notify the clients", result.toString());
		}
		controller.sendToClient(result.getId(), result.getDate(), result.getEventType());
    }
}
