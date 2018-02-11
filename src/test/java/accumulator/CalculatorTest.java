package accumulator;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CalculatorTest
{
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	@Test
	public void testAddEmptyString()
	{
		Calculator calculator = new Calculator();
		String numbers = "";
		assertEquals( 0, calculator.add( numbers ) );		
	}
	
	@Test
	public void testAddDelimitedNumbers()
	{
		Calculator calculator = new Calculator();
		String numbers = "1,2,3,4,5,6";
		assertEquals( 21, calculator.add( numbers ) );		
	}
	
	@Test
	public void testAddNewLineDelimitedNumbers()
	{
		Calculator calculator = new Calculator();
		String numbers = "1\n2,3,4,5,6";
		assertEquals( 21, calculator.add( numbers ) );	
	}
	
	@Test
	public void testAddGt1000()
	{
		Calculator calculator = new Calculator();
		String numbers = "1\n2,3,4,5,6,1005";
		assertEquals( 21, calculator.add( numbers ) );	
	}
	
	@Test
	public void testAddNegative() throws IllegalArgumentException
	{
		
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("Negatives Not allowed:-2,-3,-5");
		Calculator calculator = new Calculator();
		String numbers = "1\n-2,-3,4,-5";
		calculator.add( numbers );
	}
	
	
	@Test
	public void testAddMultipleDelimiters()
	{
		Calculator calculator = new Calculator();
		String numbers = "//;|,\n1;2,1000";
		assertEquals( 1003, calculator.add( numbers ) );	
	}
	
	@Test
	public void testAddMultipleDelimitersOfAnyLength()
	{
		Calculator calculator = new Calculator();
		String numbers = "//;|%&|,|-\n1;2%&1003,12";
		assertEquals( 15, calculator.add( numbers ) );	
	}

}
