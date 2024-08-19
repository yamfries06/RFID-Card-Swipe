
//JOptionPane.showMessageDialog(null, "info", "info box: ", JOptionPane.INFORMATION_MESSAGE);
//String s = JOptionPane.showInputDialog("product name?");

import com.phidget22.*;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import javax.swing.JOptionPane;


public class RFIDFrame extends javax.swing.JFrame implements RFIDable {
    
    public ArrayList<String> notePad; 
    String currentDirectory = System.getProperty("user.dir");
    String filename="test.txt";
    String filePath = currentDirectory + "/" + filename;
    RFID rfid = null;
    
    SimulateCardReadFrame scrf;
    boolean ListenStatus =true ;

    
    
    ArrayList<String> purchases = new ArrayList<String>();
    int mode=1;   //1 purchase mode,   2 new item mode
    
    
    private javax.swing.JTextField tagField;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField gamesPlayedField;
    
    public RFIDFrame() throws Exception {
        initComponents(); 
        initializeCustomComponents(); 
        this.setLocationRelativeTo(null);
        scrf = new SimulateCardReadFrame(this);
        scrf.setVisible(true);

        loadInfo(); 
       //write a new file
    }
    
    public void loadInfo(){
       notePad = FileHelper.readFile(filePath,true);
       System.out.println(notePad);
       
    }
       
   
    private void initializeCustomComponents() {
    javax.swing.JLabel tagLabel = new javax.swing.JLabel("Tag:");
    javax.swing.JLabel nameLabel = new javax.swing.JLabel("Name:");
    javax.swing.JLabel gamesPlayedLabel = new javax.swing.JLabel("Games Played:");

    tagField = new javax.swing.JTextField(20);
    nameField = new javax.swing.JTextField(20);
    gamesPlayedField = new javax.swing.JTextField(20);

    // Adding new components to the layout
    javax.swing.GroupLayout layout = (javax.swing.GroupLayout) getContentPane().getLayout();
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(30, 30, 30)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(tagLabel)
                .addComponent(nameLabel)
                .addComponent(gamesPlayedLabel))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(tagField)
                .addComponent(nameField)
                .addComponent(gamesPlayedField))
            .addContainerGap(30, Short.MAX_VALUE))
    ); 
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(30, 30, 30)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(tagLabel)
                .addComponent(tagField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(nameLabel)
                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(gamesPlayedLabel)
                .addComponent(gamesPlayedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(30, Short.MAX_VALUE))
    ); 

    setSize(500, 300);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jComboBox1 = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuPhidget = new javax.swing.JMenu();
        menuActivateRFID = new javax.swing.JMenuItem();
        menuDeactivateRFID = new javax.swing.JMenuItem();
        menuPurchaseMode = new javax.swing.JMenuItem();
        menuNewItemMode = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        menuPurchaseMode.setText("Purchase Mode");
        menuPurchaseMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPurchaseModeActionPerformed(evt);
            }
        });
        menuPhidget.add(menuPurchaseMode);

        menuNewItemMode.setText("New Item Mode");
        menuNewItemMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNewItemModeActionPerformed(evt);
            }
        });
        menuPhidget.add(menuNewItemMode);

        jMenuBar1.add(menuPhidget);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 799, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 377, Short.MAX_VALUE)
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

    private void menuPurchaseModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPurchaseModeActionPerformed
        mode=1;
    }//GEN-LAST:event_menuPurchaseModeActionPerformed

    private void menuNewItemModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNewItemModeActionPerformed
        mode=2;
    }//GEN-LAST:event_menuNewItemModeActionPerformed

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
       int k=0; 
       boolean exist = false; 
       for (k=0; k<notePad.size(); k++) {
            String line = notePad.get(k);
            int pos = line.indexOf("*");
            String tag = line.substring(0,pos);
            if (id.equals(tag)) {
                exist=true;
                showStats(notePad.get(k), k); 
            }
       }
       //couldn't find...
       if (exist==false) {
         addNewProduct(id);
       } 
       
       
   } 

   public void showStats(String fullId, int index) {
       String[] splittedId = fullId.split("\\*");
       String tag = splittedId[0];
       String name = splittedId[1]; 
       int gamesPlayed = Integer.parseInt(splittedId[2])+1; 
       String played = String.valueOf(gamesPlayed); 
       String originalLine = notePad.get(index); 
       int indexSecondStar = originalLine.lastIndexOf("*");
       String updatedLine = originalLine.substring(0, indexSecondStar+1) + played; 
       notePad.set(index, updatedLine); 
       
       tagField.setText(tag);
       nameField.setText(name);
       gamesPlayedField.setText(played);
       
       System.out.println("name is: " + name);
       System.out.println("tag: " + tag);
       System.out.println("games played: " + played);
       
      
       
   }
   
    public void handleLoss(String id) {
       System.out.println("handleLoss: " + id );
   } 
    
   
    
   public void addNewProduct(String id){
       String name = JOptionPane.showInputDialog("what's ur name?!?!?!?");
       System.out.println("Your name is " + name);
       String newPlayer = id + "*" + name + "*" + "1";
       notePad.add(newPlayer);
       System.out.println(notePad);
       String currentDirectory = System.getProperty("user.dir");
       String fullFileName = currentDirectory + "/test.txt";
        
        try {
            FileWriter fw = new FileWriter(fullFileName);
            BufferedWriter br = new BufferedWriter(fw);
           
            //loop through array and write numbers
            for( int k=0 ; k<notePad.size(); k++) {
                String temp = "" + notePad.get(k);
                br.write(temp + "\n");
            }
     
            br.close();
        }
        catch(Exception e) {
            System.out.println("Error writing to file");
        }
        
       tagField.setText(id);
       nameField.setText(name);
       gamesPlayedField.setText("1");
     
        
       
       
   }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run()  {
              
                try {
                    System.out.println("Making frame...");
                    new RFIDFrame().setVisible(true);
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
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem menuActivateRFID;
    private javax.swing.JMenuItem menuDeactivateRFID;
    private javax.swing.JMenuItem menuNewItemMode;
    private javax.swing.JMenu menuPhidget;
    private javax.swing.JMenuItem menuPurchaseMode;
    // End of variables declaration//GEN-END:variables

  



}

