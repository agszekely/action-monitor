package actionmonitor.test.services;

import java.util.List;

import actionmonitor.dto.PollResult;
import actionmonitor.web.components.IJmsTemplate;

public interface ITestJmsTemplate extends IJmsTemplate{
	List<PollResult> getResults();
}
