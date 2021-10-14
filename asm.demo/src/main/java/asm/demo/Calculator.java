package asm.demo;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Calculator {

	public static void main(String[] args) throws SecurityException {
		Calculator calculator = new Calculator();
		int result = calculator.sum(5, 6, 7);
		System.out.println("The result is: " + result);
		
		try {
			Method subMethod = Calculator.class.getDeclaredMethod("sub", int.class, int.class);
			int subResult = (Integer) subMethod.invoke(calculator, 8, 3);
			System.out.println("The sub result is: " + subResult);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	int sum(int... values) {
		return Arrays.stream(values).sum();
	}

//	Wil be
//	generated dynamically

//	int sub(int x, int y) {
//		return x - y;
//	}

}
