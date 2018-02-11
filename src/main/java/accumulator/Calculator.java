package accumulator;

import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

public class Calculator
{
	public int add( String numbers ) throws IllegalArgumentException
	{	
		//throw exception if negatives in the string
		Stream.of( split( numbers ) ).flatMap( o -> isNegative(o) )
										.reduce((ex1, ex2) -> mergeException(ex1, ex2))
										.ifPresent(ex -> { throw ex; });
		//calculate sum
		return Stream.of( split( numbers ) ).map( this::mapToInteger )
								 			.filter( x -> x <= 1000 )								 
								 			.reduce( (x,y) -> x+y ).get();
	}
	
	private String[] split( String numbers )
	{
		String defaultDelimiter = "\n|,";
		String dynamicDelimiters = StringUtils.substringBetween( numbers, "//", "\n"); 
		String regex = (dynamicDelimiters != null ? dynamicDelimiters : defaultDelimiter);
		
		return dynamicDelimiters != null ? numbers.replace( "//" + dynamicDelimiters + "\n", "" ).split( regex ) : numbers.split(regex);
	}

	private int mapToInteger( String str )
	{
		return StringUtils.isEmpty( str ) ? 0 : Integer.valueOf( str );
	}	
		
	public Stream<IllegalArgumentException> isNegative(String s) 
	{
	    if(s.startsWith("-")) 
	    {
	    	return Stream.of( new IllegalArgumentException( "Negatives Not allowed:" + s ) );
	    }
	    return null;
	}
	
	private IllegalArgumentException mergeException( IllegalArgumentException ex1, IllegalArgumentException ex2 )
	{
		ex1 = new IllegalArgumentException( ex1.getMessage() + "," + ex2.getMessage().replace( "Negatives Not allowed:", "" ) );
		return ex1;
	}
	
}
