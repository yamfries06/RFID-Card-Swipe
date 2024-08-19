/**
 * This class is intended for use alongside the Phidget examples to allow for additional functionality
 * designed to assist in learning how to use Phidgets, while keeping the example code as streamlined as possible.
 * It mostly consists of console I/O functions to provide insight into getting a Phidget to work for the first time.
 * 
 * You can safely exclude this file from your future Phidgets projects.
 */

import com.phidget22.*;
import java.util.Scanner; //Required for Text Input

public class PhidgetHelperFunctions {

	static Scanner s = new Scanner(System.in);
	
	static boolean ProcessYesNo_Input(int def) throws Exception {
		
		String strvar = s.nextLine();
		
		if (strvar == null)
			throw new Exception("Empty Input String");

		if (strvar.length() == 0) {
			if (def == -1)
				throw new Exception("Empty Input String");
			
			if(def == 0)
				return false;
			else
				return true;
		}

		if (strvar.charAt(0) == 'N' || strvar.charAt(0) == 'n') {
			return false;
		}

		if (strvar.charAt(0) == 'Y' || strvar.charAt(0) == 'y') {
			return true;
		}

		throw new Exception("Invalid Input");
	}

	static void DisplayLocatePhidgetsLink() {
		System.out.println("\n  | In the following example, you will be asked to provide information that specifies which Phidget the program will use. ");
		System.out.println("  | If you are unsure of any of these parameters, be sure to check www.phidgets.com/docs/Finding_The_Addressing_Information ");
		System.out.println("  | Press ENTER once you have read this message.\n");
		s.nextLine();

		System.out.println("\n--------------------\n");
	}

	static void DisplayError(PhidgetException err, String message) {
		
		System.err.println("Runtime Error -> " + message);
		
		System.err.println("Desc: " + err.getDescription());

		if (err.getErrorCode() == com.phidget22.ErrorCode.WRONG_DEVICE) {
			System.err.println("\tThis error commonly occurs when the Phidget function you are calling does not match the class of the channel that called it.");
			System.err.println("\tFor example, you would get this error if you called a PhidgetVoltageInput_* function with a PhidgetDigitalOutput channel.");
		}
		else if (err.getErrorCode() == com.phidget22.ErrorCode.NOT_ATTACHED) {
			System.err.println("\tThis error occurs when you call Phidget functions before a Phidget channel has been opened and attached.\n");
			System.err.println("\tTo prevent this error, ensure you are calling the function after the Phidget has been opened and the program has verified it is attached.\n");
		}
		else if (err.getErrorCode() == com.phidget22.ErrorCode.NOT_CONFIGURED) {
			System.err.println("\tThis error code commonly occurs when you call an Enable-type function before all Must-Set Parameters have been set for the channel.\n");
			System.err.println("\tCheck the API page for your device to see which parameters are labled \"Must be Set\" on the right-hand side of the list.");
		}
	}

	static void InputSerialNumber(ChannelInfo channelInfo) {

		System.out.println("\nFor all questions, enter the value, or press ENTER to select the [Default]");

		System.out.println("\n--------------------------------------");
		System.out.print("\n  | Some Phidgets have a unique serial number, printed on a white label on the device.\n" +
		  "  | For Phidgets and other devices plugged into a VINT Port, use the serial number of the VINT Hub.\n" +
		  "  | Specify the serial number to ensure you are only opening channels from that specific device.\n" +
		  "  | Otherwise, use -1 to open a channel on any device.\n");
		
		int deviceSerialNumber = 0;
		while (true) {
			System.out.print("\nWhat is the Serial Number? [-1] ");
			String strvar = s.nextLine();
			if (strvar == null)
				continue;
			if (strvar.length() == 0) {
				deviceSerialNumber = -1;
				break;
			}
			try{
				deviceSerialNumber = Integer.parseInt(strvar);
			} catch (NumberFormatException e) {
				continue;
			}
			if (deviceSerialNumber >= -1 && deviceSerialNumber != 0)
				break;
		}

		channelInfo.deviceSerialNumber = deviceSerialNumber;
		
		return;
	}

	static void InputIsHubPortDevice(ChannelInfo channelInfo) {
		boolean isHubPortDevice = false;

		while (true) {
			System.out.print("\nIs this a \"HubPortDevice\"? [y/n] ");
			try {
				isHubPortDevice = ProcessYesNo_Input(-1);
				break;
			} catch (Exception ex){
				//Try again
			}
		}

		channelInfo.isHubPortDevice = isHubPortDevice;
	}

	static void InputVINTProperties(ChannelInfo channelInfo, Phidget ch) throws PhidgetException {

		System.out.println("\n--------------------------------------");

		boolean isVINT = false;
		while (true) {
			System.out.print("\nDo you want to specify the hub port that your device is plugged into?\n" +
				"Choose No if your device is not plugged into a VINT Hub. (y/n) ");
			try {
				isVINT = ProcessYesNo_Input(-1);
				break;
			} catch (Exception ex){
				//Try again
			}
		}

		channelInfo.isVINT = isVINT;

		//Don't ask about the HubPort and the HubPortDevice if it's not a VINT device
		if (!isVINT)
			return;

		System.out.println("\n--------------------------------------");
		System.out.print("\n  | VINT Hubs have numbered ports that can be uniquely addressed.\n" +
			"  | The HubPort# is identified by the number above the port it is plugged into.\n" +
			"  | Specify the hub port to ensure you are only opening channels from that specific port.\n" +
			"  | Otherwise, use -1 to open a channel on any port.\n");
		
		int hubPort = -1;
		while (true) {
			System.out.print("\nWhat HubPort is the device plugged into? [-1] ");
			String strvar = s.nextLine();
			if (strvar == null)
				continue;
			if (strvar.length() == 0) {
				hubPort = -1;
				break;
			}
			try{
				hubPort = Integer.parseInt(strvar);
			} catch (NumberFormatException e) {
				continue;
			}
			if (hubPort >= -1 && hubPort <= 5)
				break;
		}

		channelInfo.hubPort = hubPort;

		ChannelClass pcc;
		try {
			pcc = ch.getChannelClass();
		} catch (PhidgetException e){
			DisplayError(e, "Getting Channel Class");
			throw e;
		}


		boolean canBeHubPortDevice = false;		
		switch (pcc) {
		case VOLTAGE_INPUT:
			System.out.print("\n--------------------------------------\n");
			System.out.print("\n  | A VoltageInput HubPortDevice uses the VINT Hub's internal channel to measure the voltage on the white wire.\n" +
			  "  | If the device you are trying to interface returns an analog voltage between 0V-5V, open it as a HubPortDevice.\n");
			canBeHubPortDevice = true;
			break;
		case VOLTAGE_RATIO_INPUT:
			System.out.print("\n--------------------------------------\n");
			System.out.print("\n  | A VoltageRatioInput HubPortDevice uses the VINT Hub's internal channel to measure the voltage ratio on the white wire.\n" +
			  "  | If the device you are trying to interface returns an ratiometric voltage between 0V-5V, open it as a HubPortDevice.\n");
			canBeHubPortDevice = true;
			break;
		case DIGITAL_INPUT:
			System.out.print("\n--------------------------------------\n");
			System.out.print("\n  | A DigitalInput HubPortDevice uses the VINT Hub's internal channel to detect digital changes on the white wire.\n" +
			  "  | If the device you are trying to interface returns a 5V or 3.3V digital signal, open it as a HubPortDevice.\n");
			canBeHubPortDevice = true;
			break;
		case DIGITAL_OUTPUT:
			System.out.print("\n--------------------------------------\n");
			System.out.print("\n  | A DigitalOutput HubPortDevice uses the VINT Hub's internal channel to output a 3.3V digital signal on the white wire.\n" +
			  "  | If the device you are trying to interface accepts a 3.3V digital signal, open it as a HubPortDevice.\n");
			canBeHubPortDevice = true;
			break;
		default:
			break;
		}

		if (canBeHubPortDevice)
			InputIsHubPortDevice(channelInfo);

		return;
	}

	static void InputChannel(ChannelInfo channelInfo) {

		// Hub port devices only have a single channel, so don't ask for the channel
		if (channelInfo.isHubPortDevice)
			return;

		System.out.print("\n--------------------------------------\n");
		System.out.print("\n  | Devices with multiple inputs or outputs of the same type will map them to channels.\n" +
		  "  | The API tab for the device on www.phidgets.com shows the channel breakdown.\n" +
		  "  | For example, a device with 4 DigitalInputs would use channels [0 - 3]\n" +
		  "  | A device with 1 VoltageInput would use channel 0\n");
		
		int channel = 0;
		while (true) {
			System.out.print("\nWhat channel# of the device do you want to open? [0] ");
			String strvar = s.nextLine();
			if (strvar == null)
				continue;
			if (strvar.length() == 0) {
				channel = 0;
				break;
			}
			try{
				channel = Integer.parseInt(strvar);
			} catch (NumberFormatException e) {
				continue;
			}
			if (channel >= 0)
				break;
		}

		channelInfo.channel = channel;

		return;
	}

	static void SetupNetwork(ChannelInfo channelInfo) {

		System.out.print("\n--------------------------------------\n");
		System.out.print("\n  | Devices can either be opened directly, or over the network.\n" +
		  "  | In order to open over the network, the target system must be running a Phidget Server.\n");
		
		boolean isRemote = false;
		while (true) {
			System.out.print("\nIs this device being opened over the network? [y/N] ");
			try {
				isRemote = ProcessYesNo_Input(0);
				break;
			} catch (Exception ex){
				//Try again
			}
		}

		channelInfo.netInfo.isRemote = isRemote;

		// if it's not remote, don't need to ask about the network
		if (!isRemote)
			return;

		System.out.print("\n--------------------------------------\n");
		System.out.print("\n  | Server discovery enables the dynamic discovery of Phidget servers that publish their identity to the network.\n" +
			  "  | This allows you to open devices over the network without specifying the hostname and port of the server.\n");
		
		boolean discovery = false;
		while (true) {
			System.out.print("\nDo you want to enable server discovery? [Y/n] ");
			try {
				discovery = ProcessYesNo_Input(1);
				break;
			} catch (Exception ex){
				//Try again
			}
		}

		channelInfo.netInfo.serverDiscovery = discovery;

		if (discovery)
			return;

		System.out.print("\n--------------------------------------\n");
		System.out.print("\nPlease provide the following information in order to open the device\n");

		while (true) {
			System.out.print("\nWhat is the Hostname (or IP Address) of the server? [localhost] ");
			String strvar = s.nextLine();
			if (strvar == null)
				continue;
			if (strvar.length() == 0) {
				channelInfo.netInfo.hostname = "localhost";
				break;
			}
			// Remove trailing newline
			channelInfo.netInfo.hostname = strvar.split("\n")[0];
			break;
		}

		System.out.print("\n--------------------------------------\n");
		
		int port;
		while (true) {
			System.out.print("\nWhat port is the server on? [5661] ");
			String strvar = s.nextLine();
			if (strvar == null)
				continue;
			if (strvar.length() == 0) {
				port = 5661;
				break;
			}
			try{
				port = Integer.parseInt(strvar);
			} catch (NumberFormatException e) {
				continue;
			}
			if (port <= 65535 && port > 0)
				break;
		}

		channelInfo.netInfo.port = port;

		System.out.print("\n--------------------------------------\n");
		while (true) {
			System.out.print("\nWhat is the password of the server? [] ");
			String strvar = s.nextLine();
			if (strvar == null)
				continue;
			// Remove trailing newline
			channelInfo.netInfo.password = strvar.split("\n")[0];
			break;
		}
		
		System.out.print("\n--------------------------------------\n");

		return;
	}

	static void PrintOpenErrorMessage(PhidgetException e, Phidget ch) throws PhidgetException {
		
		DisplayError(e, "Opening Phidget Channel");
		if (e.getErrorCode() == com.phidget22.ErrorCode.TIMEOUT) {
			System.err.println("\nThis error commonly occurs if your device is not connected as specified, " +
				"or if another program is using the device, such as the Phidget Control Panel.\n\n" +
				"If your Phidget has a plug or terminal block for external power, ensure it is plugged in and powered.\n");
			
			ChannelClass channelClass = ch.getChannelClass();

			if (channelClass != com.phidget22.ChannelClass.VOLTAGE_INPUT
				&& channelClass != com.phidget22.ChannelClass.VOLTAGE_RATIO_INPUT
				&& channelClass != com.phidget22.ChannelClass.DIGITAL_INPUT
				&& channelClass != com.phidget22.ChannelClass.DIGITAL_OUTPUT) {
				System.err.println("\nIf you are trying to connect to an analog sensor, you will need to use the " +
					"corresponding VoltageInput or VoltageRatioInput API with the appropriate SensorType.\n");
			}

			if (ch.getIsRemote() == true)
				System.err.println("\nEnsure the Phidget Network Server is enabled on the machine the Phidget is plugged into.");
		}
		
	}

	static void PrintEnableServerDiscoveryErrorMessage (PhidgetException e) {

		DisplayError(e, "Enable Server Discovery");
		if (e.getErrorCode() == com.phidget22.ErrorCode.UNSUPPORTED) {
			System.err.println("\nThis error commonly occurs if your computer does not have the required mDNS support. " +
				"We recommend using Bonjour Print Services on Windows and Mac, or Avahi on Linux.\n");
		}
	}

	static void AskForDeviceParameters(ChannelInfo channelInfo, Phidget ch) throws PhidgetException {
		DisplayLocatePhidgetsLink();
		InputSerialNumber(channelInfo);
		InputVINTProperties(channelInfo, ch);
		InputChannel(channelInfo);
		SetupNetwork(channelInfo);
	}
}