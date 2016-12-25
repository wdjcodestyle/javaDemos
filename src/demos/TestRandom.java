package demos;

import java.util.Random;

public class TestRandom {

	public static void main(String[] args) {
		 int i = (int)(Math.random()*100);
		 int i2 = new Random().nextInt(100)+1;
		 System.out.println(i);
		 System.out.println(i2);
	}
	
}
