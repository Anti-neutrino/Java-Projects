package CowsAndBools;

public class NumberMathcher {
	public static Answer match(Number originalNumbers,Number guess){
		int[] digits=originalNumbers.getDigits();
		int[] guessDigits=guess.getDigits();
		
		int bulls=0;
		for(int i=0;i<digits.length;i++){
			if(digits[i]==guessDigits[i]){
				bulls++;
			}
		}
			
		int cows=0;
		for(int i=0;i<digits.length;i++){
			for(int j=0;j<guessDigits.length;j++){
				if((i!=j)&&(digits[i]==guessDigits[j])){
					cows++;
				}
			}
		}
		
		return new Answer(bulls,cows);
	}		
}
