package actionmonitor.web.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import actionmonitor.dto.EventType;
import actionmonitor.dto.Message;

@Controller
public class MessageController implements IMessageController{
	
	private static Logger logger = LoggerFactory.getLogger(IMessageController.class);
	
	private static final String WEBSOCKET_ADDRESS = "/message/dbevent";
	
	private SimpMessagingTemplate webSocket;
	
	@Autowired
	public MessageController(SimpMessagingTemplate webSocket){
		this.webSocket = webSocket;
	}

	@Override
    public void sendToClient(String id, Date date, EventType eventType){
		if (logger.isDebugEnabled()){
			logger.debug("Sending extratced message to client with values ID : {}, DATE : {}, EventMessage : {}", id, date, eventType.getMsg());
		}
		webSocket.convertAndSend(WEBSOCKET_ADDRESS, new Message(id, date, eventType.getMsg()));
    }

}