
public class MonthException extends Exception{
	public MonthException() {
		super("Invalid month.");
	}
	
	public MonthException(String msg) {
		super(msg);
	}
}
