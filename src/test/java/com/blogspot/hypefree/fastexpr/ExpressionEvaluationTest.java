package com.blogspot.hypefree.fastexpr;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.blogspot.hypefree.fastexprbench.Evaluators;

public final class ExpressionEvaluationTest {
	private static final double EXPECTED_RESULT = 2.0;
	private static final double EXPECTED_PRECISION = 1e-10;
	private Evaluators evaluators;

	@Test
	public void testParsii() throws Exception {
		evaluators.compileParsii();
		assertEquals(EXPECTED_RESULT, evaluators.evaluateParsii(),
				EXPECTED_PRECISION);
	}

	@Test
	public void testJeval() throws Exception {
		evaluators.compileJeval();
		assertEquals(EXPECTED_RESULT, evaluators.evaluateJeval(),
				EXPECTED_PRECISION);
	}

	@Test
	public void testJeplite() throws Exception {
		evaluators.compileJeplite();
		assertEquals(EXPECTED_RESULT, evaluators.evaluateJeplite(),
				EXPECTED_PRECISION);
	}

	@Test
	public void testMathEval() throws Exception {
		evaluators.compileMVEL();
		assertEquals(EXPECTED_RESULT, evaluators.evaluateMVEL(),
				EXPECTED_PRECISION);
	}

	@Test
	public void testExpr() throws Exception {
		evaluators.compileExpr();
		assertEquals(EXPECTED_RESULT, evaluators.evaluateExpr(),
				EXPECTED_PRECISION);
	}

	@Test
	public void testJanino() throws Exception {
		evaluators.compileJanino();
		assertEquals(EXPECTED_RESULT, evaluators.evaluateJanino(),
				EXPECTED_PRECISION);
	}

	@Test
	public void testJaninoFastexpr() throws Exception {
		evaluators.compileJaninoFastexpr();
		assertEquals(EXPECTED_RESULT, evaluators.evaluateJaninoFastexpr(), EXPECTED_PRECISION);
	}

	@Test
	public void testMVEL() throws Exception {
		evaluators.compileMVEL();
		assertEquals(EXPECTED_RESULT, evaluators.evaluateMVEL(),
				EXPECTED_PRECISION);
	}

	@Test
	public void testMVELConstant() throws Exception {
		evaluators.compileMVELConstant();
		assertEquals(EXPECTED_RESULT, evaluators.evaluateMVELConstant(),
				EXPECTED_PRECISION);
	}

	@Before
	public void setUp() {
		evaluators = new Evaluators();
	}
}
