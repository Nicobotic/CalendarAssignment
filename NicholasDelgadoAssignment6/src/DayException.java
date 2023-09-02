
public class DayException extends Exception{
	public DayException() {
		super("Invalid day.");
	}
	
	public DayException(String msg) {
		super(msg);
	}
}
