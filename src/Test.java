import entities.Computer;
import interfaces.IComputer;

/**
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		
		IComputer computer = new Computer.ComputerBuilder(null).build();
		System.out.println(computer.display());
		
	}

}
 