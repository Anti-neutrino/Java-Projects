package CowsAndBools;

import java.util.Arrays;
import java.util.Random;
public class Number {
	
	private int[] digits;
	
	public Number(int DIGIT_COUNT){
		this(generateRandomDigits(DIGIT_COUNT));
	}	
	
	public Number(int[] digits){
		this.digits=digits;
	}
	
	private static int[] generateRandomDigits(int digitCount) {
		
		int[] generateDigits=new int[digitCount];
		Random rand=new Random();
		int index=0;
		
		while(index!=digitCount){
			int genDigits=rand.nextInt(10);
			boolean flag=false;
			for(int i=0;i<generateDigits.length;i++){
				if(generateDigits[i]==genDigits){
					flag=true;
					break;
				}
			}
			
			if(!flag){
				generateDigits[index]=genDigits;
				index++;
			}
		}
		
		return generateDigits;
	}
	
	@Override
	public boolean equals(Object arg0) {
		if(!(arg0 instanceof Number)){
			return false;
		}
		
		Number num=(Number) arg0;
		return Arrays.equals(digits, num.digits);
	}
	
	public int[] getDigits(){
		return this.digits;
	}
}