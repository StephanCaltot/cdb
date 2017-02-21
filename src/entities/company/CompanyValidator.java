package entities.company;

import check.StringCheck;
import interfaces.ICheck;
import interfaces.ICompany;

/**
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public interface CompanyValidator {

	
	
	/**
	 * Method checking Company's entity
	 * @param pCompany
	 * @throws Exception
	 */
	public static void check(ICompany pCompany) throws Exception{
		ICheck.isNotNull(pCompany.getName());
		StringCheck.isFormed(pCompany.getName());
	}
}
