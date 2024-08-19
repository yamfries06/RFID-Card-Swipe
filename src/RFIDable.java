//author John Janeka 2018

/*this interface allows a class to receive a 'fake' RFID  read */

public interface RFIDable {
   
    public void handleRead(String id);
    public void handleLoss(String id);
    
}
