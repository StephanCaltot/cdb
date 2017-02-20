package check;

import java.time.DateTimeException;
import java.util.Date;

import interfaces.ICheck;

/**
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public class DateCheck implements ICheck{
	
	public static void isGood(Date pFirstDate, Date pSecondDate) throws DateTimeException{
		if (pFirstDate.after(pSecondDate)) throw new DateTimeException("The discontinued date is more recent than introducing date \n");
	}
	
}
