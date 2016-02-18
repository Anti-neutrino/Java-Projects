package CowsAndBools;

import java.util.Scanner;
public class CowsAndBoolsTest {
	public static void main(String[] args)
	{
		final int DIGIT = 4;
		Number genNumber=new Number(DIGIT);
		Scanner inputScanner=new Scanner(System.in);
		boolean success=false;
		while(!success){
			System.out.println("Enter number: ");
			String userInput=inputScanner.nextLine();
			
			if(userInput==null||userInput.length()!=DIGIT){
				System.out.println("Wrong Input!");
				continue;
			}
			
			int[] userDigits=new int[DIGIT];
			for(int i=0;i<userInput.length();i++){
				userDigits[i]=Integer.parseInt(userInput.substring(i,i+1));
			}
			
			Number guess=new Number(userDigits);		
			if(genNumber.equals(guess)){
				success=true;
				System.out.println("You win!");
			}else{
				Answer answer=NumberMathcher.match(genNumber,guess);
				System.out.println(answer);
			}
		}
		
		inputScanner.close();
	}
}