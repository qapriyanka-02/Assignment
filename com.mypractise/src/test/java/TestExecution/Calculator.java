package TestExecution;

public class Calculator {

	public static void main(String[] args) {
		
		Calculator c= new Calculator();
		c.add(2, 9);
		c.sub(5, 2);
		c.mul(2, 5);
		c.div(2, 23);
		
		
		int num=(int) Math.random()*5;
System.err.println("print random" + num);
	}

	public  int  add(int a , int b) {
		 int c= a+b;
		 System.out.println("addition: "+ c);
		 return c;
	}
	
	public int sub(int a, int b) {
		int c= a-b;
		System.out.println("substraction: " + c);
		return c;
	}
	public int mul(int a, int b) {
		int c= a*b;
		System.out.println("multiplication: " + c);
		return c;
	}
	
	public int div(int a, int b) {
		int c= a/b;
		System.out.println("division: " + c);
		return c;
	}
}
