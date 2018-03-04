package actionmonitor.web.controller;

import java.util.Date;

import actionmonitor.dto.EventType;

public interface IMessageController {

	void sendToClient(String id, Date date, EventType eventType);

}
