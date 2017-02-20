package check;

import interfaces.ICheck;
import interfaces.IComputer;

/**
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public interface ComputerValidator{
	
	public static void check(IComputer pComputer) throws Exception{
		ICheck.isNotNull(pComputer.getName());
		StringCheck.isFormed(pComputer.getName());
		if (pComputer.getDateWichIsIntroduced() != null){
			DateCheck.isGood(pComputer.getDateWichIsIntroduced(), pComputer.getDateWichIsDiscontinued());	
		}
	}
}

