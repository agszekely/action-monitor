package actionmonitor.test.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import actionmonitor.dto.EventType;
import actionmonitor.service.EventReceiver;
import actionmonitor.service.IEventReceiver;
import actionmonitor.test.model.JmsTemplateTestImplementation;

public class EventReceiverTest {
	private IEventReceiver eventReceiver;
	private ITestJmsTemplate jsmTemplate;
	
	private static final String ID = "ID";
	private static final String MODIFIED = "MODIFIED";
	private static final String CREATED = "CREATED";
	private static final Date DATE = new Date();
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	private static final String ID_VALUE = "IdValue";
	private static final String ID_VALUE_UPDATED = "IdValueUpdated";
	
	@Before
	public void init(){
		this.jsmTemplate = new JmsTemplateTestImplementation();
		this.eventReceiver = new EventReceiver(jsmTemplate);
	}
	
	@Test
	public void shouldNotSendAnyMessage(){
		List<Map<String, String>> received= new ArrayList<>();
		eventReceiver.setKeys(received);
		
		//verifying that no message has been sent
		Assert.assertTrue(jsmTemplate.getResults().isEmpty());
	}
	
	@Test
	public void shouldSendOneCreatedMessage(){
		List<Map<String, String>> received= new ArrayList<>();

		Map<String, String> created = new HashMap<>();
		created.put(ID, ID_VALUE);
		created.put(MODIFIED, null);
		created.put(CREATED, DATE_FORMAT.format(DATE));
		
		received.add(created);
		
		eventReceiver.setKeys(received);
		
		//verifying that no message has been sent
		Assert.assertTrue(jsmTemplate.getResults().size() == 1);
		Assert.assertTrue(jsmTemplate.getResults().get(0).getEventType().equals(EventType.INSERTED));
		Assert.assertTrue(jsmTemplate.getResults().get(0).getId().equals(ID_VALUE));
		Assert.assertTrue(jsmTemplate.getResults().get(0).getDate().equals(DATE));
	}
	
	@Test
	public void shouldSendOneUpdatedMessage(){
		List<Map<String, String>> received= new ArrayList<>();

		Map<String, String> updated = new HashMap<>();
		updated.put(ID, ID_VALUE);
		updated.put(MODIFIED, DATE_FORMAT.format(DATE));
		updated.put(CREATED, DATE_FORMAT.format(DATE));
		
		received.add(updated);
		
		eventReceiver.setKeys(received);
		
		//verifying that no message has been sent
		Assert.assertTrue(jsmTemplate.getResults().size() == 1);
		Assert.assertTrue(jsmTemplate.getResults().get(0).getEventType().equals(EventType.UPDATED));
		Assert.assertTrue(jsmTemplate.getResults().get(0).getId().equals(ID_VALUE));
		Assert.assertTrue(jsmTemplate.getResults().get(0).getDate().equals(DATE));
	}
	
	@Test
	public void shouldSendNMessage(){
		List<Map<String, String>> received= new ArrayList<>();
		
		int N = 10;

		for (int i = 0; i < N; ++i){
			Map<String, String> created = new HashMap<>();
			created.put(ID, ID_VALUE + "N_" + i);
			created.put(MODIFIED, null);
			created.put(CREATED, DATE_FORMAT.format(DATE));
			
			received.add(created);
			
		}
		
		
		eventReceiver.setKeys(received);
		
		//verifying that no message has been sent
		Assert.assertTrue(jsmTemplate.getResults().size() == N);
	}
	
	@Test
	public void shouldSendMoreCreatedOneUpdatedMessage(){
		List<Map<String, String>> received= new ArrayList<>();
		
		
		Map<String, String> created = new HashMap<>();
		created.put(ID, ID_VALUE);
		created.put(MODIFIED, null);
		created.put(CREATED, DATE_FORMAT.format(DATE));
		
		received.add(created);
		
		Map<String, String> created2 = new HashMap<>();
		created2.put(ID, ID_VALUE);
		created2.put(MODIFIED, null);
		created2.put(CREATED, DATE_FORMAT.format(DATE));
		
		received.add(created2);
	
		Map<String, String> updated = new HashMap<>();
		updated.put(ID, ID_VALUE_UPDATED);
		updated.put(MODIFIED, DATE_FORMAT.format(DATE));
		updated.put(CREATED, DATE_FORMAT.format(DATE));
		
		received.add(created);
		
		eventReceiver.setKeys(received);
		
		//verifying that no message has been sent
		Assert.assertTrue(jsmTemplate.getResults().size() == 3);
		
		for (int i = 0; i < 3; ++i){
			if (jsmTemplate.getResults().get(i).getId().equals(ID_VALUE)){
				Assert.assertTrue(jsmTemplate.getResults().get(i).getEventType().equals(EventType.INSERTED));
			}else{
				Assert.assertTrue(jsmTemplate.getResults().get(i).getEventType().equals(EventType.UPDATED));
			}
		}
		
		
	}
}
