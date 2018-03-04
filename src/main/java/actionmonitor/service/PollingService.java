package actionmonitor.service;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PollingService implements IPollingService{
	
	private static Logger logger = LoggerFactory.getLogger(PollingService.class);
	
	private Date lastPoll;
	
	public PollingService(){
		Calendar cal = Calendar.getInstance();
		cal.set(1970, 01, 01);
		this.lastPoll = cal.getTime();
	}

	@Override
	public Date lastPoll(){
		Date toBeReturned = lastPoll;
		this.lastPoll = new Date();
		
		if (logger.isDebugEnabled()){
			logger.debug("Date for checking newly inserted rows is returned with value : {}", toBeReturned.toString());
		}
		
		return toBeReturned;
	}
	
}
