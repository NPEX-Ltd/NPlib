package np.library.io;

import java.util.Scanner;

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
