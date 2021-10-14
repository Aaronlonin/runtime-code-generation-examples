package bytebuddy.demo;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;


public class RedefineClassExample {
	
	class Foo {
		  String m() { return "foo"; }
	}
		 
	class Bar {
	  String m() { return "bar"; }
	}
	
	public static void main(String[] args) {

		RedefineClassExample demo = new RedefineClassExample();
		demo.redefineFooToBar();

	}

	// Using Java's HotSwap feature
	private void redefineFooToBar() {
		ByteBuddyAgent.install();
		Foo foo = new Foo();
		System.out.println("Before: " + foo.m());
		new ByteBuddy()
		  .redefine(Bar.class)
		  .name(Foo.class.getName())
		  .make()
		  .load(Foo.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

		// See how foo.m() prints "bar"
		System.out.println("After: " + foo.m());
	}

}
