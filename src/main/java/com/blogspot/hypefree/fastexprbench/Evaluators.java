package com.blogspot.hypefree.fastexprbench;

import java.util.Collections;

import com.blogspot.hypefree.fastexpr.JaninoFastexpr;
import com.blogspot.hypefree.fastexpr.UnaryDoubleFunction;

public final class Evaluators {
	private static final double X_VALUE = 0.0;

	private parsii.eval.Expression parsiiExpr;
	private net.sourceforge.jeval.Evaluator jevalEvaluator;
	private org.cheffo.jeplite.JEP jep;
	private org.cheffo.jeplite.util.DoubleStack jepStack;
	private org.softwaremonkey.MathEval mathEval;
	private expr.Expr exprEvaluator;

	private org.codehaus.janino.ExpressionEvaluator janinoExpressionEvaluator;

	private UnaryDoubleFunction fastexprFunction;

	public void compileParsii() throws Exception {
		parsii.eval.Scope scope = parsii.eval.Scope.create();
		parsiiExpr = parsii.eval.Parser
				.parse("2 + (7-5) * 3.14159 * x + sin(0)");
		parsii.eval.Variable var = scope.getVariable("x");
		var.setValue(X_VALUE);
	}

	public double evaluateParsii() throws Exception {
		return parsiiExpr.evaluate();
	}

	public void compileJeval() throws Exception {
		jevalEvaluator = new net.sourceforge.jeval.Evaluator();
		jevalEvaluator.setVariables(Collections.singletonMap("x",
				Double.toString(X_VALUE)));
	}

	public double evaluateJeval() throws Exception {
		return Double.parseDouble(jevalEvaluator
				.evaluate("2 + (7-5) * 3.14159 * #{x} + sin(0)"));
	}

	public void compileJeplite() throws Exception {
		jep = new org.cheffo.jeplite.JEP();
		jep.addVariable("x", X_VALUE);
		jep.parseExpression("2 + (7-5) * 3.14159 * x + sin(0)");

		// Throws NPE :-(
		// org.cheffo.jeplite.optimizer.ExpressionOptimizer optimizer = new
		// org.cheffo.jeplite.optimizer.ExpressionOptimizer(jep.getTopNode());
		// org.cheffo.jeplite.SimpleNode optimizedExpression =
		// optimizer.optimize();

		jepStack = new org.cheffo.jeplite.util.DoubleStack();
	}

	public double evaluateJeplite() throws Exception {
		return jep.getValue(jepStack);
	}

	public void compileMathEval() throws Exception {
		mathEval = new org.softwaremonkey.MathEval();
		mathEval.setVariable("x", X_VALUE);
	}

	public double evaluateMathEval() throws Exception {
		return mathEval.evaluate("2 + (7-5) * 3.14159 * x + sin(0)");
	}

	public void compileExpr() throws Exception {
		expr.Variable x = expr.Variable.make("x");
		expr.Parser parser = new expr.Parser();
		parser.allow(x);
		exprEvaluator = parser.parseString("2 + (7-5) * 3.14159 * x + sin(0)");
		x.setValue(X_VALUE);
	}

	public double evaluateExpr() throws Exception {
		return exprEvaluator.value();
	}

	public void compileJanino() throws Exception {
		janinoExpressionEvaluator = new org.codehaus.janino.ExpressionEvaluator(
				"2 + (7-5) * 3.14159 * x + Math.sin(0)", double.class,
				new String[] { "x" }, new Class[] { double.class });
	}

	public double evaluateJanino() throws Exception {
		return (Double) janinoExpressionEvaluator
				.evaluate(new Object[] { new Double(X_VALUE) });
	}

	public void compileJaninoFastexpr() throws Exception {
		fastexprFunction = new JaninoFastexpr().compile("2 + (7-5) * 3.14159 * x + sin(0)");
	}

	public double evaluateJaninoFastexpr() throws Exception {
		return fastexprFunction.evaluate(X_VALUE);
	}
}
