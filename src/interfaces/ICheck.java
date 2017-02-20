package interfaces;

/**
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public interface ICheck {

	public static void isNotNull( Object object) throws NullPointerException{
		if ( object == null) throw new NullPointerException("The name is null");
	}
	
}
