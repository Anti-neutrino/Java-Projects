package CowsAndBools;

public class Answer {
	private Integer bulls;
	private Integer cows;
	
	public Answer(Integer bulls,Integer cows){
		this.setBulls(bulls);
		this.setCows(cows);
	}

	public Integer getBulls() {
		return bulls;
	}

	public void setBulls(Integer bulls) {
		this.bulls = bulls;
	}

	public Integer getCows() {
		return cows;
	}

	public void setCows(Integer cows) {
		this.cows = cows;
	}
	
	@Override
	public String toString() {
		return "Bulls: "+bulls+" Cows: "+cows;
	}
}
