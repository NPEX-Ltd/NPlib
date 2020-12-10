package np.library.io;

import java.util.Scanner;

import np.library.annotations.API;
import np.library.annotations.API.Level;
@API(level = Level.ALPHA)
public class SystemIO extends IODevice {

	@Override
	public void WriteString(String message) {
		System.out.println(message);
	}

	@Override
	public String ReadStringOrBlock() {
		Scanner sc = new Scanner(System.in);
		String message = sc.next();
		sc.close();
		return message;
	}

}
