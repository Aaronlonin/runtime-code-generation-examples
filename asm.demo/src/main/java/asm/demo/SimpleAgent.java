package asm.demo;

import java.lang.instrument.Instrumentation;

public class SimpleAgent {

	/**
	 * jvm Start in parameter form, run this method
	 *
	 * @param agentArgs
	 * @param inst
	 */
	public static void premain(String agentArgs, Instrumentation inst) {
		System.out.println("premain");
		inst.addTransformer(new CalculatorTransformer());
	}

	/**
	 * Start in dynamic attach mode, run this method
	 *
	 * @param agentArgs
	 * @param inst
	 */
	public static void agentmain(String agentArgs, Instrumentation inst) {
		System.out.println("agentmain");
	}
}
