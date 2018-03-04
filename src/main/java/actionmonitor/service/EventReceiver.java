package actionmonitor.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;

import actionmonitor.dto.EventType;
import actionmonitor.dto.PollResult;
import actionmonitor.util.Constants;
import actionmonitor.web.components.IJmsTemplate;

public class EventReceiver implements IEventReceiver{
	
	private static Logger logger = LoggerFactory.getLogger(EventReceiver.class);
	
	private static final String ID = "ID";
	private static final String MODIFIED = "MODIFIED";
	private static final String CREATED = "CREATED";
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	private final IJmsTemplate jmsTemplate;
	
	@Autowired
	public EventReceiver(IJmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	@ServiceActivator
	@Override
	public void setKeys(List<Map<String, String>> values) {
		if (logger.isDebugEnabled()){
			logger.debug("Service activator invoked with values : {}", values);
		}
		
		for (Map<String, String> value : values){
			try {
				if (value.get(MODIFIED) == null){
					//record just been inserted
					PollResult result = new PollResult(value.get(ID), DATE_FORMAT.parse(value.get(CREATED)), EventType.INSERTED);
					if (logger.isDebugEnabled()){
						logger.debug("Sending pollResult to JMS queue for address : {}, with dto : {}", Constants.JMS_ADDRESS, result.toString());
					}
					jmsTemplate.convertAndSend(Constants.JMS_ADDRESS, result);
				}else{
					//record was updated
					PollResult result = new PollResult(value.get(ID), DATE_FORMAT.parse(value.get(MODIFIED)), EventType.UPDATED);
					if (logger.isDebugEnabled()){
						logger.debug("Sending pollResult to JMS queue for address : {}, with dto : {}", Constants.JMS_ADDRESS, result.toString());
					}
					jmsTemplate.convertAndSend(Constants.JMS_ADDRESS, result);
				}

			} catch (ParseException e) {
				logger.error("Parsing message was not successful! Exception message : {}", e.getMessage());
			}
		}
		
	}
		
}
