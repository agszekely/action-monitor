package actionmonitor.web.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import actionmonitor.dto.PollResult;

@Component
public class JmsTemplateImplementation implements IJmsTemplate{
	
	private final JmsTemplate jmsTemplate;
	
	@Autowired
	public JmsTemplateImplementation(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	public void convertAndSend(String address, PollResult result) {
		jmsTemplate.convertAndSend(address, result);
	}
}
