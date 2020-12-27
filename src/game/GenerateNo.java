package game;

import java.util.Date;
import java.util.Random;

class GenerateNo {

	private int min = 1;

	private int number;

	public boolean equalToOne = false;

	GenerateNo(int number) {
		this.setNumber(number);
	}

	public int roll(int number) {
		Random rand = new Random(new Date().getTime());
		return rand.nextInt((number - min) + 1) + min;
	}

	public Integer getNumber() {
		return number;
	}

	public static void main(String[] args) {
		String assetsPath = System.getProperty("user.dir")+ "\\src\\Assets\\";
		System.out.println(assetsPath);
	}
	
	public void setNumber(Integer number) {
		this.number = number;
		if (number == 1) {
			equalToOne = true;
		}
	}

}
