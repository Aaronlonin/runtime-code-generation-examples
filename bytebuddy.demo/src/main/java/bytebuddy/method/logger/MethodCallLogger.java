package bytebuddy.method.logger;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.util.Arrays;

import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.asm.Advice.Origin;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.matcher.ElementMatchers;

public class MethodCallLogger {

	public static void main(String[] args) {
//		premain("", ByteBuddyAgent.install());

		Calculator calculator = new Calculator();
		int sum = calculator.sum(10, 15, 25);
		System.out.println("Sum is " + sum);

		int sub = calculator.sub(10, -3);
		System.out.println("Sub is " + sub);
	}

	public static void premain(String arg, Instrumentation inst) {
		new AgentBuilder.Default().with(AgentBuilder.TypeStrategy.Default.REBASE)
				.type(ElementMatchers.any())
				.transform((builder, typeDescription, classLoader, module) -> builder
						.visit(Advice.to(TestAdvice.class).on(ElementMatchers.isAnnotatedWith(Log.class))))
				.installOn(inst);
	}
	
}

class TestAdvice {
	@Advice.OnMethodEnter
	public static void after(@Advice.Origin Method method, @Advice.AllArguments  Object[] args) {
		System.out.println("About to call: " + method.getName());
	}
}

@interface Log {}

class Calculator {

	@Log
	int sum(int... values) {
		return Arrays.stream(values).sum();
	}

	int sub(int a, int b) {
		return a - b;
	}

}
