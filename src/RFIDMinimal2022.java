import com.phidget22.*;
import javax.swing.JOptionPane;


public class RFIDMinimal2022 extends javax.swing.JFrame {

    RFID rfid = null;
    
    
    public RFIDMinimal2022() {
        initComponents();
    }
    
    
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

   
       
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonActivate = new javax.swing.JButton();
        buttonDeactivate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonActivate.setText("Activate");
        buttonActivate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActivateActionPerformed(evt);
            }
        });

        buttonDeactivate.setText("Deactivate");
        buttonDeactivate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeactivateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonActivate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonDeactivate)
                .addContainerGap(222, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonActivate)
                    .addComponent(buttonDeactivate))
                .addContainerGap(266, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonDeactivateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeactivateActionPerformed
        System.out.print("closing RFID");
        try{
            rfid.close();} 
        catch(Exception e) { 
            System.out.println("Error trying to close RFID");
        } 
        rfid = null;
    }//GEN-LAST:event_buttonDeactivateActionPerformed

    private void buttonActivateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActivateActionPerformed
        activateRFID();
    }//GEN-LAST:event_buttonActivateActionPerformed

    
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
    
    
  public void handleRead(String id){
       System.out.println("handleRead: " + id);   
   } 

   
    public void handleLoss(String id){
       System.out.println("handleLoss: " + id );
   }        
       
       
   
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RFIDMinimal2022.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RFIDMinimal2022.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RFIDMinimal2022.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RFIDMinimal2022.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RFIDMinimal2022().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonActivate;
    private javax.swing.JButton buttonDeactivate;
    // End of variables declaration//GEN-END:variables
}
