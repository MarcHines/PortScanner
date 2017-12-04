import java.util.*;
import java.io.IOException;
import java.net.*;

public class PortScanner {
    private List<Integer> openPorts;
    private String remoteTarget;
    
    public PortScanner(String remoteTarget) {
    	this.openPorts = new ArrayList<>();
    	this.remoteTarget = remoteTarget;
    }
    
    public void findOpenPorts(int startingFrom, int endingAt) {
    	if(startingFrom > endingAt){
    		System.out.println("Starting value must be less than ending value");
    		return;
    	}
    	
    	if(this.remoteTarget == null || this.remoteTarget.isEmpty()) {
    		System.out.println("Remote target can not be null or empty");
    		return;
    	}
    	
    	int port = startingFrom;
    	System.out.println("Scanning ports for " + this.remoteTarget);
    	
    	while(port < endingAt) {
    		
    		try {
    			Socket socket = new Socket(this.remoteTarget, port);
    			System.out.println("Port " + port + " is listening");
    			this.openPorts.add(port);
    			socket.close();
    		
    		//IOException means port is refusing to connect. It's not listening
    		}catch(IOException e) {
    			System.out.println("Port " + port + " is not open");
    		}
    		port++;
    	}
    	
    	System.out.println("Port scanning complete!");
    }
    
    public boolean isPortOpen(int port) {
    	return this.openPorts.contains(port);
    }
    
    public List<Integer> getOpenPorts(){
    	return this.openPorts;
    }

    public void setRemoteTarget(String targetUrl) throws UnknownHostException {
    	if(targetUrl == null || targetUrl.isEmpty()) {
    		return;
    	}
    	
    	InetAddress inetaddress = InetAddress.getByName(targetUrl);
    	this.remoteTarget = inetaddress.getHostAddress();
    }
}
