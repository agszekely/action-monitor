package actionmonitor.web.components;

import actionmonitor.dto.PollResult;

public interface IJmsTemplate {
	void convertAndSend(String address, PollResult result);
}
