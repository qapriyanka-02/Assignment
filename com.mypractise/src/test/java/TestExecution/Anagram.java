package TestExecution;

import java.util.Arrays;

public class Anagram {

	public static void main(String[] args) {
		
		
		String input1="silent";
		String input2="listen";
		
		if(anagram(input1, input2))
		{
			System.out.println("Anagram:    " + input1 + "and" +  input2 );
		}
		else
		{
			System.out.println("Not Anagram:   " + input1 + "and" +  input2 );
		}
	}
	
	public static Boolean anagram(String str1, String str2) {
		
		
		//remove spaces and convert to lowercase	
		str1=str1.replaceAll("//s", " ").toLowerCase();
		str2= str2.replaceAll("//s", " ").toLowerCase();
		
		// check if length match
		
		if(str1.length() != str2.length()) {
			return false;
		}
		
		//convert to character array
		
		char[] Array1= str1.toCharArray();
		char[] Array2=str2.toCharArray();
		
		Arrays.sort(Array1);
		Arrays.sort(Array2);
		
		//compare arrays
		return Arrays.equals(Array1, Array2);
	}

}
