package check;

/**
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public class StringCheck implements ICheck{

	public static void isFormed(String element) throws Exception{
		if (element.matches("")) throw new IllegalArgumentException("the string content forbidden characters");
	};
	
}
