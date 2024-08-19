//author: John Janetka 2018
//JOptionPane.showMessageDialog(null, "info", "info box: ", JOptionPane.INFORMATION_MESSAGE);
//String s = JOptionPane.showInputDialog("product name?");

import com.phidget22.*;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JOptionPane;


public class RFID2024Starter extends javax.swing.JFrame implements RFIDable {

    RFID rfid = null;
    
    SimulateCardReadFrame scrf;
    boolean ListenStatus =true ;
    
     
    
    public RFID2024Starter() throws Exception {
       
        initComponents(); 
        this.setLocationRelativeTo(null);
        scrf = new SimulateCardReadFrame(this);
        scrf.setVisible(true);

    }
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        listStuff = new java.awt.List();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuPhidget = new javax.swing.JMenu();
        menuActivateRFID = new javax.swing.JMenuItem();
        menuDeactivateRFID = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        listStuff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listStuffActionPerformed(evt);
            }
        });

        menuPhidget.setText("Phidget RFID Reader");

        menuActivateRFID.setText("Activate");
        menuActivateRFID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuActivateRFIDActionPerformed(evt);
            }
        });
        menuPhidget.add(menuActivateRFID);

        menuDeactivateRFID.setText("Deactivate");
        menuDeactivateRFID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDeactivateRFIDActionPerformed(evt);
            }
        });
        menuPhidget.add(menuDeactivateRFID);

        jMenuBar1.add(menuPhidget);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(listStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(564, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(listStuff, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
     
    private void menuActivateRFIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuActivateRFIDActionPerformed
        activateRFID();
    }//GEN-LAST:event_menuActivateRFIDActionPerformed

    public void activateRFID() {
        System.out.println("setting up RFID");
        try{
            setUpThePhidgetThing();
        }catch(Exception e) {
            System.out.println("Error setting up RFID");
        }
        
        try {
            rfid.open(5000);
            JOptionPane.showMessageDialog(null, "RFID ON!", "notification", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "FAILED TO CONNECT TO RFID!", "notification", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Error connecting to RFID");
        }
    }
    
    private void menuDeactivateRFIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDeactivateRFIDActionPerformed
        System.out.print("closing RFID");
        try{
            rfid.close();} 
        catch(Exception e) { 
            System.out.println("Error trying to close RFID");
        } 
        rfid = null;
    }//GEN-LAST:event_menuDeactivateRFIDActionPerformed

    private void listStuffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listStuffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listStuffActionPerformed

    /**
     * @param args the command line arguments
     */
   public void setUpThePhidgetThing() throws Exception { 
        rfid = new RFID();   
        rfid.addAttachListener(new AttachListener() {
            public void onAttach(AttachEvent ae) {
                    RFID phid = (RFID) ae.getSource();
                    try {
                            if(phid.getDeviceClass() != DeviceClass.VINT){
                                    System.out.println("channel " + phid.getChannel() + " on device " + phid.getDeviceSerialNumber() + " attached");
                            }
                            else{
                                    System.out.println("channel " + phid.getChannel() + " on device " + phid.getDeviceSerialNumber() + " hub port " + phid.getHubPort() + " attached");
                            }
                    } catch (PhidgetException ex) {
                            System.out.println(ex.getDescription());
                    }
            }
        });

        rfid.addDetachListener(new DetachListener() {
            public void onDetach(DetachEvent de) {
                    RFID phid = (RFID) de.getSource();
                    try {
                            if (phid.getDeviceClass() != DeviceClass.VINT) {
                                    System.out.println("channel " + phid.getChannel() + " on device " + phid.getDeviceSerialNumber() + " detached");
                            } else {
                                    System.out.println("channel " + phid.getChannel() + " on device " + phid.getDeviceSerialNumber() + " hub port " + phid.getHubPort() + " detached");
                            }
                    } catch (PhidgetException ex) {
                            System.out.println(ex.getDescription());
                    }
            }
        });

        rfid.addErrorListener(new ErrorListener() {
            public void onError(ErrorEvent ee) {
                    System.out.println("Error: " + ee.getDescription());
            }
        });

        rfid.addTagListener(new RFIDTagListener() {
            public void onTag(RFIDTagEvent e) {
                    System.out.println("Tag read: " + e.getTag());
                    handleRead(e.getTag() );
            }
        });

        rfid.addTagLostListener(new RFIDTagLostListener() {
            public void onTagLost(RFIDTagLostEvent e) {
                    System.out.println("Tag lost: " + e.getTag());
                    handleLoss(e.getTag() );
            }
        });

  
   }

   
    @Override
   public void handleRead(String id){
       System.out.println("handleRead: " + id);    
       listStuff.add(id);
   } 

   
    public void handleLoss(String id){
       System.out.println("handleLoss: " + id );
   } 
    
  
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run()  {
              
                try {
                    System.out.println("Making frame...");
                    new RFID2024Starter().setVisible(true);
                }
                catch(Exception e) {
                    System.out.println("Error Encountered!");
                    System.exit(0);
                }
            }
        }
                        );
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private java.awt.List listStuff;
    private javax.swing.JMenuItem menuActivateRFID;
    private javax.swing.JMenuItem menuDeactivateRFID;
    private javax.swing.JMenu menuPhidget;
    // End of variables declaration//GEN-END:variables



}

