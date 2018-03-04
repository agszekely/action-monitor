package actionmonitor.dto;

public enum EventType {
	INSERTED("inserted"), UPDATED("updated");
	
	private String msg;
	
	private EventType(String msg){
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
	
	
}
