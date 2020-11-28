package np.library.io;

import java.io.*;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;


public abstract class FileWatcher {
	private WatchService watcher = FileSystems.getDefault().newWatchService();
	public FileWatcher() throws IOException {}
	
	protected abstract void OnFileCreated(File file);
	protected abstract void OnFileModified(File file);
	protected abstract void OnFileDeleted(File file);
	
	public boolean continueFlag;
	
	@SuppressWarnings("unused")
	public void RegisterDir(File dir) {
		try {
			if(!dir.isDirectory()) return;
			WatchKey key = Path.of(dir.getPath()).register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public Thread StartPollingForEvents() {
		Thread t = new Thread(() -> { AsyncPollEvents(); });
		t.setDaemon(true);
		t.setName("FileWatcher");
		t.start();
		return t;
	}
	
	public void StopPolling() {
		continueFlag = false;
	}
	
	@SuppressWarnings("unchecked")
	private void AsyncPollEvents() {
		continueFlag = true;
		for (;;) {
		    // wait for key to be signaled
		    WatchKey key = null;
		    try {
		        key = watcher.take();
		    } catch (InterruptedException x) {
		    	System.out.println(x);
		    }

		    for (WatchEvent<?> event: key.pollEvents()) {
		        WatchEvent.Kind<?> kind = event.kind();
		        

		        // This key is registered only
		        // for ENTRY_CREATE events,
		        // but an OVERFLOW event can
		        // occur regardless if events
		        // are lost or discarded.
		        if (kind == OVERFLOW) {
		            continue;
		        }

		        // The filename is the
		        // context of the event.
		        WatchEvent<Path> ev = (WatchEvent<Path>)event;
		        Path filename = ev.context();
		        File file = filename.toFile();
		        
		        if (kind == ENTRY_CREATE) {
		        	OnFileCreated(file);
		        }
		        
		        if (kind == ENTRY_MODIFY) {
		        	OnFileModified(file);
		        }
		        
		        if (kind == ENTRY_DELETE) {
		        	OnFileDeleted(file);
		        }
		    }

		    // Reset the key -- this step is critical if you want to
		    // receive further watch events.  If the key is no longer valid,
		    // the directory is inaccessible so exit the loop.
		    boolean valid = key.reset();
		    if (!valid && continueFlag) {
		        break;
		    }
		}
	}
}
