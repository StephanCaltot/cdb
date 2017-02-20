package check;

import interfaces.ICompany;

/**
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public interface CompanyValidator {

	public static void check(ICompany pCompany) throws Exception{
		ICheck.isNotNull(pCompany.getName());
		StringCheck.isFormed(pCompany.getName());
	}
}
