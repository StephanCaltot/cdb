package interfaces;

/**
 * Checking interface for all entities
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public interface ICheck {
	
	
	
	/**
	 * Static method checking "null" for all entities
	 * @param object
	 * @throws NullPointerException
	 */
	public static void isNotNull( Object object) throws NullPointerException{
		if ( object == null) throw new NullPointerException("The name is null");
	}
	
}
