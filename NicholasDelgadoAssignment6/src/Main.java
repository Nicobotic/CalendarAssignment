//import java.util.StringTokenizer;
import java.util.Scanner;
import java.util.Calendar;

public class Main {
	public static void main(String[] args) {
		//gets the initial input of the user
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter date to parse (MM/DD/YYYY format)");
		String input = sc.nextLine();
		
		//not using StringTokenizer since in the documentation it recommended me to use split instead and split help me solve an edge case easier
		//StringTokenizer st = new StringTokenizer(input, "/");
		String[] list = input.split("/");
		
		//checks to make sure that it is in the correct format and no other algebraic or symbol inputs other than /
		boolean isDone = false;
		while(!isDone) {
			if(input.matches(".*[a-zA-Z].*") || input.contains(" ") || input.matches(".*[\"'':;`^.,!@#$%&*()_+=|<>?{}\\\\[\\\\]~-].*")) {
				System.out.println("Invalid input. No letters, special characters, or spaces. Reenter a valid input (MM/DD/YYYY format):");
				input = sc.nextLine();
				list = input.split("/");
			}
			else if(list.length != 3) {
				System.out.println("Invalid format. Reenter a valid input (MM/DD/YYYY format):");
				input = sc.nextLine();
				list = input.split("/");
			}
			else if(list[0].length()>2 || list[1].length()>2|| list[2].length()>4) {
				System.out.println("Input(s) for month, day, and/or year have too many digits. Reenter a valid input (MM/DD/YYYY format):");
				input = sc.nextLine();
				list = input.split("/");
			}
			else {
				isDone = true;
			}
		}
		//divide the string based on the format by the / into three variables
		int month = Integer.parseInt(list[0]);
		int day = Integer.parseInt(list[1]);
		int year = Integer.parseInt(list[2]);
		boolean done = false;
		
		//loop that checks the month, year, and day for validity. If wrong will ask the user to replace with an appropriate value
		while(!done)	{
			try {
				//checks month value
				getMonth(month);
				
				//checks day value
				if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
					if(day<1 || day>31) {
						throw new DayException	("Invalid day. Reenter a valid day:\n");
					}
				}
				if (month == 4 || month == 6 || month == 9 || month == 11) {
					if (day<1 || day>30) {
						throw new DayException("Invalid day. Reenter a valid day:\n");
					}
				}
				if(month == 2 && isLeapYear(year)) {
					if(day<1 || day>29) {
						throw new DayException("Invalid day. Reenter a valid day:\n");
					}
				}
				if(month == 2 && !isLeapYear(year)) {
					if(day<1 || day>28) {
						throw new DayException("Invalid day. Reenter a valid day:");
					}
				}
				
				//checks year value
				if(year< 1000 || year>3000) {
					throw new YearException("Invalid year. Reenter a valid year:");
				}
				
				//prints out the date in month day year format
				System.out.println("Parsed date: " + getMonth(month) + " " + day + ", " + year);
				done = true;
			}
			catch(MonthException e) {
				System.out.println(e.getMessage());
				try {
					month = sc.nextInt();
				}
				catch(Exception f) {
					sc.next();
					continue;
				}
			}
			catch(DayException e) {
				System.out.println(e.getMessage());
				try {
					day = sc.nextInt();
				}
				catch(Exception f) {
					sc.next();
					continue;
				}
			}
			catch(YearException e) {
				System.out.println(e.getMessage());
				try {
					year = sc.nextInt();
				}
				catch(Exception f) {
					sc.next();
					continue;
				}
			}
		}
	}
	
	//turns the month into the word version based on the int value given. If lower than 1 or higher than 12 throw MonthException
	public static String getMonth(int month) throws MonthException {
		String[] months = {"January", "February", "March", "April", "May", "June", "July", 
				"August", "September", "October", "November", "December"};
		if(month<1 || month>12) {
			throw new MonthException("Invalid month. Reenter a valid month:");
		}
		return months[month-1];
	}
	
	/*checks if the year inputted is a leap year using the Calendar class. Could've used % easier to understand with calendar.
	added a method in comments for example with modulus. Both work
	public static boolean isLeapYear(int year) {
		if (year % 4 != 0) {
			return false;
		}
	  	else if (year % 400 == 0) {
    		return true;
		} 
    	else if (year % 100 == 0) {
    		return false;
    	} 
    	else {
    		return true;
  		}
	}
	*/
	public static boolean isLeapYear(int year) {
		  Calendar cal = Calendar.getInstance();
		  cal.set(Calendar.YEAR, year);
		  return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
	}
}
