package interfaces;

import java.util.Date;

/**
 * Interface implemented by computer in order to made it polymorph
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public interface IComputer {

	public int getId();
	public void setId(int id);
	
	public String getName();
	public void setName(String name);
	
	public Date getDateWichIsIntroduced();	
	public void setDateWichIsIntroduced(Date dateWichIsIntroduced);

	public Date getDateWichIsDiscontinued();	
	public void setDateWichIsDiscontinued(Date dateWichIsDiscontinued);

	public ICompany getManufacturer();	
	public void setManufacturer(ICompany manufacturer);

	
	/**
	 * Display one "Computer" entity
	 */

	public default String display() {
		return "Computer " + getId() + " brand : " + getName()
			+ ", introduced in " + getDateWichIsIntroduced()
			+ " and discontinued in " + getDateWichIsDiscontinued()
			+ ". Manufactured by company number " + getManufacturer().getId();
		}
	
}
