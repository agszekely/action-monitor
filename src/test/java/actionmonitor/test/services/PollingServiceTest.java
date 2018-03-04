package actionmonitor.test.services;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import actionmonitor.service.PollingService;

public class PollingServiceTest {
	
	private PollingService pollingService;
	
	@Before
	public void init(){
		pollingService = new PollingService();
	}
	
	@Test
	public void dateShouldBeUpdated(){
		Date date = pollingService.lastPoll();
		Date afterUpdated = pollingService.lastPoll();
		
		Assert.assertTrue(!date.equals(afterUpdated));
	}
	
	@Test
	public void updatedShouldBeLater(){
		Date date = pollingService.lastPoll();
		Date afterUpdated = pollingService.lastPoll();
		
		Assert.assertTrue(date.before(afterUpdated));
	}
}
