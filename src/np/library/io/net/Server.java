package np.library.io.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import np.library.common.Time;
import np.library.exceptions.DeviceOpenException;
import np.library.io.IODevice;
import np.library.io.NetworkIO;

public abstract class Server {
	private ReentrantLock lock = new ReentrantLock();
	private ServerSocket server;
	private int workerCount = 0;
	private int activeConnections = 0;
	private int maxConnections;
	private LinkedBlockingQueue<String> messages = new LinkedBlockingQueue<>();
	private LinkedBlockingQueue<NetworkIO> connections = new LinkedBlockingQueue<>();
	private boolean stopped;
	
	public Server(int port, int maxConnections) {
		try {
			server = new ServerSocket(port);
			stopped = false;
			this.maxConnections = maxConnections;
		} catch (IOException e) {
			stopped = true;
			throw new DeviceOpenException(e);
		}
	}
	
	public void Open() {}
	public void Close() {}
	
	public abstract void OnMessageReceived(String message);
	public abstract void OnConnectionOpened(NetworkIO device);
	public abstract void OnConnectionClosed(NetworkIO device);
	
	public void Start() {
		DispatchThread(() -> {AsyncListenForConnections();});
		DispatchThread(() -> {AsyncProcessMessages();});
	}
	
	public void BroadcastMessage(String message) {
		
		for(IODevice device : connections) {
			System.out.println("Broadcasting To "+device);
			device.WriteString(message);
		}
	}
	
	private void AddMessage(NetworkIO device, String message) {
		if(message != null) {
			messages.add(message);
		} else {
			device.Close();
			connections.remove(device);
			activeConnections--;
			OnConnectionClosed(device);
		}
	}
	
	private String GetMessage() {
		if(messages.isEmpty()) {
			while(messages.isEmpty()) {
				Time.SleepMillis(1);
			}
		} else {
			return messages.poll();
		}
		return null;
		
	}
	
	public String GetNextMessageOrBlock() {
		while(lock.isLocked()) {
			Time.SleepMillis(1);
		}
		
		return GetMessage();
	}
	
	public boolean IsAlive() {
		return !stopped;
	}
	
	public void Stop() {
		stopped = true;
		for(NetworkIO device : connections) {
			device.Disconnect();
		}
		System.out.println("[SERVER]: Stopping Server...");
	};
	

	private void DispatchThread(Runnable job) {
		Thread t = new Thread(job);
		t.setDaemon(true);
		t.setName("Server Worker - "+workerCount++);
		t.start();
	}
	
	
	//===ASYNC WORKERS===========================================================================================
	
	private void AsyncListenForConnections() {
		try {
			for(;;) {
				if(activeConnections < maxConnections) {
					System.out.println("[SERVER]: Waiting For Client...");
					Socket client = server.accept();
					activeConnections++;
					NetworkIO device = new NetworkIO(client);
					DispatchThread(() -> {AsyncReadMessages(device);});
				}
				
				if(IsAlive()) return;
			}
		} catch (IOException ioex) {
			
		}
	}

	private void AsyncReadMessages(NetworkIO device) {
		System.out.println(activeConnections);
		connections.add(device);
		OnConnectionOpened(device);
		while(device.IsOpen()) {
			if(!IsAlive()) return;
			Time.SleepMillis(10);
			System.out.println("["+Thread.currentThread().getName()+"]: Polling Messages...");
			String message = device.ReadStringOrBlock();
			if(message == null) break;
			AddMessage(device, message);
		}
	}
	
	private void AsyncProcessMessages() {
		while(true) {
			Time.SleepMillis(10);
			if(!messages.isEmpty()) { OnMessageReceived(messages.poll()); }
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
