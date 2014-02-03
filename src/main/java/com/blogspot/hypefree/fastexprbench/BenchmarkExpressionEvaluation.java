package com.blogspot.hypefree.fastexprbench;

import com.google.caliper.Benchmark;
import com.google.caliper.runner.CaliperMain;

public final class BenchmarkExpressionEvaluation extends Benchmark {
	private Evaluators evaluators, precompiledEvaluators;

	@Override
	public void setUp() throws Exception {
		evaluators = new Evaluators();
		precompiledEvaluators = new Evaluators();
		precompiledEvaluators.compileParsii();
		precompiledEvaluators.compileJeval();
		precompiledEvaluators.compileJeplite();
		precompiledEvaluators.compileMathEval();
		precompiledEvaluators.compileExpr();
		precompiledEvaluators.compileJanino();
		precompiledEvaluators.compileJaninoFastexpr();
	}

	public double timeParsii(int reps) throws Exception {
		evaluators.compileParsii();

		double result = 0;
		for (int i = 0; i < reps; ++i) {
			result += evaluators.evaluateParsii();
		}
		return result;
	}

//	public double timeJeval(int reps) throws Exception {
//		evaluators.compileJeval();
//
//		double result = 0;
//		for (int i = 0; i < reps; ++i) {
//			result += evaluators.evaluateJeval();
//		}
//		return result;
//	}

	public double timeJeplite(int reps) throws Exception {
		evaluators.compileJeplite();

		double result = 0;
		for (int i = 0; i < reps; ++i) {
			result += evaluators.evaluateJeplite();
		}
		return result;
	}

	public double timeMathEval(int reps) throws Exception {
		evaluators.compileMathEval();

		double result = 0;
		for (int i = 0; i < reps; ++i) {
			result += evaluators.evaluateMathEval();
		}
		return result;
	}

	public double timeExpr(int reps) throws Exception {
		evaluators.compileExpr();

		double result = 0;
		for (int i = 0; i < reps; ++i) {
			result += evaluators.evaluateExpr();
		}
		return result;
	}

	public double timeJanino(int reps) throws Exception {
		evaluators.compileJanino();

		double result = 0;
		for (int i = 0; i < reps; ++i) {
			result += evaluators.evaluateJanino();
		}
		return result;
	}

	public double timeJaninoFastexpr(int reps) throws Exception {
		evaluators.compileJaninoFastexpr();

		double result = 0;
		for (int i = 0; i < reps; ++i) {
			result += evaluators.evaluateJaninoFastexpr();
		}
		return result;
	}

	public double timeParsiiPrecompiled(int reps) throws Exception {
		double result = 0;
		for (int i = 0; i < reps; ++i) {
			result += precompiledEvaluators.evaluateParsii();
		}
		return result;
	}

//	public double timeJevalPrecompiled(int reps) throws Exception {
//		double result = 0;
//		for (int i = 0; i < reps; ++i) {
//			result += precompiledEvaluators.evaluateJeval();
//		}
//		return result;
//	}

	public double timeJeplitePrecompiled(int reps) throws Exception {
		double result = 0;
		for (int i = 0; i < reps; ++i) {
			result += precompiledEvaluators.evaluateJeplite();
		}
		return result;
	}

	public double timeMathEvalPrecompiled(int reps) throws Exception {
		double result = 0;
		for (int i = 0; i < reps; ++i) {
			result += precompiledEvaluators.evaluateMathEval();
		}
		return result;
	}

	public double timeExprPrecompiled(int reps) throws Exception {
		double result = 0;
		for (int i = 0; i < reps; ++i) {
			result += precompiledEvaluators.evaluateExpr();
		}
		return result;
	}

	public double timeJaninoPrecompiled(int reps) throws Exception {
		double result = 0;
		for (int i = 0; i < reps; ++i) {
			result += precompiledEvaluators.evaluateJanino();
		}
		return result;
	}

	public double timeJaninoFastexprPrecompiled(int reps) throws Exception {
		double result = 0;
		for (int i = 0; i < reps; ++i) {
			result += precompiledEvaluators.evaluateJaninoFastexpr();
		}
		return result;
	}

	public static void main(String... args) {
		CaliperMain.main(BenchmarkExpressionEvaluation.class, new String[] {
				"--print-config", "-Cinstrument.micro.options.warmup=30s",
				"--time-limit", "5m", "--instrument", "micro" });
	}
}
