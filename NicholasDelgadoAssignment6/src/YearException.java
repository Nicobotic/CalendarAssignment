
public class YearException extends Exception{
	public YearException() {
		super("Invalid year.");
	}
	
	public YearException(String msg) {
		super(msg);
	}
}
