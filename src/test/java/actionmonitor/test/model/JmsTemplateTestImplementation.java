package actionmonitor.test.model;

import java.util.ArrayList;
import java.util.List;

import actionmonitor.dto.PollResult;
import actionmonitor.test.services.ITestJmsTemplate;

public class JmsTemplateTestImplementation implements ITestJmsTemplate{

	private List<PollResult> received = new ArrayList<>();
	
	@Override
	public void convertAndSend(String address, PollResult result) {
		//only verifying that the message arrived
		received.add(result);
	}

	@Override
	public List<PollResult> getResults() {
		return received;
	}

}
