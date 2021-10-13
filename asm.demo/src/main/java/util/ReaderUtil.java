package util;

import java.util.Iterator;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class ReaderUtil {

	public static ClassNode readClassNode(ClassReader cr) {
		ClassNode cn = new ClassNode();
		cr.accept(cn, ClassReader.EXPAND_FRAMES | ClassReader.SKIP_FRAMES);
		return cn;
	}

	public static MethodNode findMethod(ClassReader cr, String methodName, String desc) {
		ClassNode cn = readClassNode(cr);
		for (MethodNode m : cn.methods) {
			if (m.name.equals(methodName) && m.desc.equals(desc)) {
				return m;
			}
		}
		return null;
	}

	public static boolean removeMethod(ClassReader cr, String methodName, String desc) {
		ClassNode cn = readClassNode(cr);
		Iterator<MethodNode> iterator = cn.methods.iterator();
		while (iterator.hasNext()) {
			MethodNode m = iterator.next();
			if (m.name.equals(methodName) && m.desc.equals(desc)) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}
}
