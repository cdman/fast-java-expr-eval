package com.blogspot.hypefree.fastexpr;

import java.io.ByteArrayInputStream;
import java.security.Permissions;
import java.security.ProtectionDomain;
import java.security.SecureClassLoader;
import java.util.concurrent.atomic.AtomicLong;

import org.codehaus.janino.ClassLoaderIClassLoader;
import org.codehaus.janino.Parser;
import org.codehaus.janino.Scanner;
import org.codehaus.janino.UnitCompiler;
import org.codehaus.janino.util.ClassFile;

public final class JaninoFastexpr {
	private final static AtomicLong COMPILED_CLASS_INDEX = new AtomicLong();

	private final static class JaninoRestrictedClassLoader extends
			SecureClassLoader {
		Class<?> defineClass(String name, byte[] b) {
			return defineClass(name, b, 0, b.length, new ProtectionDomain(null,
					new Permissions(), this, null));
		}
	}

	public UnaryDoubleFunction compile(String expression) throws Exception {
		if (!java.util.regex.Pattern.matches(
				"^[a-zA-Z0-9+\\-()/\\* \t^%\\.\\?]+$", expression)) {
			throw new SecurityException();
		}

		String classPackage = getClass().getPackage().getName() + ".compiled";
		String className = "JaninoCompiledFastexpr"
				+ COMPILED_CLASS_INDEX.incrementAndGet();
		String source = "package " + classPackage + ";\n"
				+ "import static java.lang.Math.*;\n" + "public final class "
				+ className + " implements "
				+ UnaryDoubleFunction.class.getCanonicalName() + " {\n"
				+ "public double evaluate(double x) {\n"
				+ "return (" + expression + ");\n" + "}\n" + "}";
		Scanner scanner = new Scanner(null, new ByteArrayInputStream(
				source.getBytes("UTF-8")), "UTF-8");

		JaninoRestrictedClassLoader cl = new JaninoRestrictedClassLoader();
		UnitCompiler unitCompiler = new UnitCompiler(
				new Parser(scanner).parseCompilationUnit(),
				new ClassLoaderIClassLoader(cl));
		ClassFile[] classFiles = unitCompiler.compileUnit(true, true, true);
		Class<?> clazz = cl.defineClass(classPackage + "." + className,
				classFiles[0].toByteArray());

		return (UnaryDoubleFunction) clazz.newInstance();
	}
}
