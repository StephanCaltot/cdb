package interfaces;

/**
 * Interface implemented by company in order to made it polymorph
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public interface ICompany {

	public int getId();
	public void setId(int id);
	
	public String getName();
	public void setName(String name);
	

	/**
	 * Displays one "Company" entity
	 */
	public default String display() {
		return "Company named" + getName();
	}
	
}
