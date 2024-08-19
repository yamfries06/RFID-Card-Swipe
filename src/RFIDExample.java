import com.phidget22.*;
import java.util.Scanner; //Required for Text Input

public class RFIDExample {
	
	static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		
		/***
		* Allocate a new Phidget Channel object
		***/
		RFID ch = new RFID();

		/**
		* Displays info about the attached Phidget channel.
		* Fired when a Phidget channel with onAttachHandler registered attaches
		*/
		ch.addAttachListener(new AttachListener() {
			public void onAttach(AttachEvent ae) {
			
				try {
					//If you are unsure how to use more than one Phidget channel with this event, we recommend going to
					//www.phidgets.com/docs/Using_Multiple_Phidgets for information

					System.out.print("\nAttach Event:");
					
					RFID ph = (RFID) ae.getSource();
					
					/**
					* Get device information and display it.
					**/
					int serialNumber = ph.getDeviceSerialNumber();
					String channelClass = ph.getChannelClassName();
					int channel = ph.getChannel();
					
					DeviceClass deviceClass = ph.getDeviceClass();
					if (deviceClass != DeviceClass.VINT) {
						System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
							  "\n\t-> Channel:  " + channel + "\n");
					} 
					else {			
						int hubPort = ph.getHubPort();
						System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
							  "\n\t-> Hub Port: " + hubPort + "\n\t-> Channel:  " + channel + "\n");
					}
					
				} 
				catch (PhidgetException e) {
					PhidgetHelperFunctions.DisplayError(e, "Getting Channel Informaiton");
				}
			}
		});

		
		/**
		* Displays info about the detached Phidget channel.
		* Fired when a Phidget channel with onDetachHandler registered detaches
		*/
		ch.addDetachListener(new DetachListener() {
			public void onDetach(DetachEvent de) {
				try {
					//If you are unsure how to use more than one Phidget channel with this event, we recommend going to
					//www.phidgets.com/docs/Using_Multiple_Phidgets for information

					System.out.print("\nAttach Event:");
					
					Phidget ph = de.getSource();
					
					/**
					* Get device information and display it.
					**/
					int serialNumber = ph.getDeviceSerialNumber();
					String channelClass = ph.getChannelClassName();
					int channel = ph.getChannel();
					
					DeviceClass deviceClass = ph.getDeviceClass();
					if (deviceClass != DeviceClass.VINT) {
						System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
							  "\n\t-> Channel:  " + channel + "\n");
					} 
					else {			
						int hubPort = ph.getHubPort();
						System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
							  "\n\t-> Hub Port: " + hubPort + "\n\t-> Channel:  " + channel + "\n");
					}
				} 
				catch (PhidgetException e) {
					PhidgetHelperFunctions.DisplayError(e, "Getting Channel Informaiton");
				}
			}
		});

		/**
		* Writes Phidget error info to stderr.
		* Fired when a Phidget channel with onErrorHandler registered encounters an error in the library
		*/
		ch.addErrorListener(new ErrorListener() {
			public void onError(ErrorEvent ee) {
				System.out.println("Error: " + ee.getDescription());
			}
		});

		/**
		* Outputs the RFID's most recently reported tag.
		* Fired when a RFID channel with onTagHandler sees a tag
		*/
		ch.addTagListener(new RFIDTagListener() {
			public void onTag(RFIDTagEvent e) {
				//If you are unsure how to use more than one Phidget channel with this event, we recommend going to
				//www.phidgets.com/docs/Using_Multiple_Phidgets for information

				System.out.println("[Tag Event] -> Tag: " + e.getTag());
			}
		});

		/**
		* Outputs the RFID's most recently lost tag.
		* Fired when a RFID channel with onTagLostHandler loses a tag
		*/
		ch.addTagLostListener(new RFIDTagLostListener() {
			public void onTagLost(RFIDTagLostEvent e) {
				//If you are unsure how to use more than one Phidget channel with this event, we recommend going to
				//www.phidgets.com/docs/Using_Multiple_Phidgets for information

				System.out.println("[Tag Lost Event] -> Tag: " + e.getTag());
			}
		});
		
		try {
			
			/***
			* Set matching parameters to specify which channel to open
			***/
		
			//You may remove these lines and hard-code the addressing parameters to fit your application
			ChannelInfo channelInfo = new ChannelInfo();  //Information from AskForDeviceParameters(). May be removed when hard-coding parameters.
			PhidgetHelperFunctions.AskForDeviceParameters(channelInfo, ch);
			
			ch.setDeviceSerialNumber(channelInfo.deviceSerialNumber);
			ch.setHubPort(channelInfo.hubPort);
			ch.setIsHubPortDevice(channelInfo.isHubPortDevice);
			ch.setChannel(channelInfo.channel);
			
			if(channelInfo.netInfo.isRemote) {
				ch.setIsRemote(channelInfo.netInfo.isRemote);
				if(channelInfo.netInfo.serverDiscovery) {
					try {
						Net.enableServerDiscovery(ServerType.DEVICE_REMOTE);
					}
					catch (PhidgetException e) {
						PhidgetHelperFunctions.PrintEnableServerDiscoveryErrorMessage(e);
						throw new Exception("Program Terminated: EnableServerDiscovery Failed", e);
					}
				}
				else {
					Net.addServer("Server", channelInfo.netInfo.hostname,
						channelInfo.netInfo.port, channelInfo.netInfo.password, 0);
				}
			}
			
			
			//This call may be harmlessly removed
			PrintEventDescriptions();
			
			/***
			* Open the channel with a timeout
			***/
			System.out.print("\nOpening and Waiting for Attachment...");
			
			try {
				ch.open(5000);
			}
			catch (PhidgetException e) {
				PhidgetHelperFunctions.PrintOpenErrorMessage(e, ch);
				throw new Exception ("Program Terminated: Open Failed", e);
			}
			
			/***
			* To find additional functionality not included in this example,
			* be sure to check the API for your device.
			***/
			
			System.out.println("Sampling data for 10 seconds...");

			Thread.sleep(10000);

			/***
			* Perform clean up and exit
			***/			
			System.out.println("\nDone Sampling...");

			System.out.println("Cleaning up...");
			ch.close();
			System.out.print("\nExiting...");
			return;

			
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}
	
	/***
	* Prints descriptions of how events related to this class work
	***/
	public static void PrintEventDescriptions()	{
		System.out.print("\n--------------------\n" +
				"\n  | Tag events will call their associated function every time new tag data is received from the device.\n" +
				"\n  | Tag lost events will call their associated function every time a tag is no longer seen by device.\n" +
				"  | Press ENTER once you have read this message.\n");
		s.nextLine();
		
		System.out.println("\n--------------------");
	}
}
