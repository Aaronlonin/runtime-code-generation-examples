package asm.demo;

import java.io.IOException;

import org.objectweb.asm.util.ASMifier;

public class ASMGenerator {

	public static void main(String[] args) throws IOException {
		ASMifier.main(new String[] { Calculator.class.getCanonicalName() });
	}

}
