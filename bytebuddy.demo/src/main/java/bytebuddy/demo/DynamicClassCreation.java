package bytebuddy.demo;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class DynamicClassCreation {
	
	public static void main(String[] args) {

		DynamicClassCreation demo = new DynamicClassCreation();
		demo.createHelloWorldDemo();

	}

	private void createHelloWorldDemo() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			Class<?> dynamicType = new ByteBuddy()
					  .subclass(Object.class)
					.method(ElementMatchers.named("toString"))
					  .intercept(FixedValue.value("Hello World!"))
					  .make()
					.load(classLoader)
					  .getLoaded();
			System.out.println(dynamicType.newInstance().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
