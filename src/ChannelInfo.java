/**
 * This class is intended for use alongside PhidgetHelperFunctions.java to store the information collected by those
 * functions for later use. If you are not using PhidgetHelperFunctions.java in your project, it is likely you do not
 * need this class in your project either.
 */

public class ChannelInfo {
	
	public class NetInfo {
		boolean isRemote;
		boolean serverDiscovery;
		String hostname;
		int port;
		String password;
		
		public NetInfo() {
			isRemote = false;
			serverDiscovery = false;
			hostname = "";
			port = 0;
			password = "";
		}
	};	
	
	int deviceSerialNumber;
	int hubPort;
	int channel;
	boolean isHubPortDevice;
	boolean isVINT;
	NetInfo netInfo;
	
	public ChannelInfo() {
		deviceSerialNumber = 0;
		hubPort = -1;
		channel = -1;
		isHubPortDevice = false;
		isVINT = false;
		netInfo = new NetInfo();
	}
};