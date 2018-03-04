package actionmonitor.dto;

import java.util.Date;

public class Message {
	
	private StringBuilder sb;
	
	public Message(String message, Date date, String event){
		sb = new StringBuilder();
		sb.append("timestamp=");
		sb.append(date.getTime());
		sb.append(" :: a row with ID=");
		sb.append(message);
		sb.append(" was ");
		sb.append(event);
	}

	public String getContent() {
		return sb.toString();
	}
	
	
	
}
