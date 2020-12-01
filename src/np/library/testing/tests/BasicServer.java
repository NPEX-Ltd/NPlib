package np.library.testing.tests;

import np.library.common.Time;
import np.library.io.NetworkIO;
import np.library.io.net.Server;

public class BasicServer extends Server {

	public static void main(String[] args) {
		Server server = new BasicServer();
		while(server.IsAlive()) {
			Time.SleepMillis(500);
			System.out.println(server.IsAlive());
		}
	}
	
	public BasicServer() {
		super(5000, 1);
		Start();
	}

	

	@Override
	public void OnMessageReceived(String message) {
		System.out.println("Message:"+message);
		BroadcastMessage(message);
		Stop();
	}

	@Override
	public void OnConnectionOpened(NetworkIO device) {
		System.out.println(device+" Connected...");
	}

	@Override
	public void OnConnectionClosed(NetworkIO device) {
		System.out.println(device+" Disconnected...");
	}

}
