package asm.demo;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class CalculatorTransformer implements ClassFileTransformer {

    public byte[] transform(ClassLoader loader, String className,

			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,

	byte[] classfileBuffer) throws IllegalClassFormatException {

    	ClassReader cr = new ClassReader(classfileBuffer);

		ClassWriter classWriter = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);

		if (className.endsWith("Calculator")) {
			addSubMethod(classWriter);
		}

		cr.accept(classWriter, 0);

        return classWriter.toByteArray();

    }

	private void addSubMethod(ClassWriter classWriter) {
		{
			MethodVisitor methodVisitor = classWriter.visitMethod(0, "sub", "(II)I", null, null);
			methodVisitor.visitCode();
			Label label0 = new Label();
			methodVisitor.visitLabel(label0);
			methodVisitor.visitLineNumber(28, label0);
			methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
			methodVisitor.visitVarInsn(Opcodes.ILOAD, 2);
			methodVisitor.visitInsn(Opcodes.ISUB);
			methodVisitor.visitInsn(Opcodes.IRETURN);
			Label label1 = new Label();
			methodVisitor.visitLabel(label1);
			methodVisitor.visitLocalVariable("this", "Lasm/demo/Calculator;", null, label0, label1, 0);
			methodVisitor.visitLocalVariable("x", "I", null, label0, label1, 1);
			methodVisitor.visitLocalVariable("y", "I", null, label0, label1, 2);
			methodVisitor.visitMaxs(2, 3);
			methodVisitor.visitEnd();
		}
	}

}
