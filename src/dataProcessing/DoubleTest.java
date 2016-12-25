package dataProcessing;

import java.math.BigDecimal;

public class DoubleTest {

	public static void main(String[] args) {
		double f = 111231.5585; 
		double f2 = m1(f);
		System.out.println("结果是——————————"+f2);
	}

	public static Double m1(Double f) {
        BigDecimal bg = new BigDecimal(f);  
        double f1 = bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();  
        return f1;
    }
	
	
	
}
