public class application {

	public static void main(String[] args) throws Exception {
		
		PortScanner portscanner = new PortScanner("meclabs.com");
		portscanner.findOpenPorts(1, 100);
	}

}
