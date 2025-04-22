package TestExecution;

public class SwitchCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int days=2;
		switch(days){
			
			case 1: System.out.print("sunday");
						break;
			case 2: System.out.println("monday");
						break;
			case 3: System.err.println("tuesday");
						break;
			case 4: System.out.println("wednesday");
						break;
			case 5:System.out.println("thursday");
						break;
			case 6: System.out.println("friday");
						break;
			case 7: System.out.println("saturday");
						break;
			default: System.out.println("No case found");
				
		}
	}

	
}
