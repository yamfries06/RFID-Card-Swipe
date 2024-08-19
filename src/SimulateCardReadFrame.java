
import java.io.FileWriter;
import java.io.IOException;

// author John Janetka 2018

public class SimulateCardReadFrame extends javax.swing.JFrame {

    RFIDable owner;
    String log="log...";
    String[] tags={"abc123", "xyz789", "bob23", "rat83", "tank98"};
    
    
    public SimulateCardReadFrame(RFIDable owner) {
        if (owner instanceof RFIDable)
            this.owner = owner;
        else{
            System.out.println("SimulateCardReadFrame class requires an owner");
            System.out.println("that implements RFIDable interface");
            System.exit(0);
        }
        
        initComponents();
        this.setLocationRelativeTo(null);
        updateLog();
        fillTagList();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        buttonSimulateGain = new javax.swing.JButton();
        textSimulate = new javax.swing.JTextField();
        buttonSimulateTagLoss = new javax.swing.JButton();
        textArea1 = new java.awt.TextArea();
        buttonClearLog = new javax.swing.JButton();
        listTags = new java.awt.List();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(200, 500));

        jLabel1.setText("Type a card ID in the text box");

        buttonSimulateGain.setText("Simulate Tag Gain");
        buttonSimulateGain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSimulateGainActionPerformed(evt);
            }
        });

        buttonSimulateTagLoss.setText("Simulate Tag Loss");
        buttonSimulateTagLoss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSimulateTagLossActionPerformed(evt);
            }
        });

        textArea1.setMaximumSize(new java.awt.Dimension(180, 32767));

        buttonClearLog.setText("clear log");
        buttonClearLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClearLogActionPerformed(evt);
            }
        });

        listTags.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listTagsMouseClicked(evt);
            }
        });

        jLabel2.setText("Tag Log:");

        jLabel3.setText("Quick Tags (DBL-CLK reads)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(textSimulate)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonSimulateGain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonSimulateTagLoss, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonClearLog, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(listTags, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textSimulate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonSimulateGain)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSimulateTagLoss)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listTags, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonClearLog)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public void updateLog(){
        textArea1.setText(log);
    }
    
    public void fillTagList() {
        listTags.removeAll();
        for(String s: tags)
            listTags.add(s);
    }
    
    private void buttonSimulateGainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSimulateGainActionPerformed
        //read textbox 
        String id = textSimulate.getText();
        if (id.length() == 0) 
            return;
        //send to owner (should implement 
        owner.handleRead(id);
        
        log = id + "\n" + log;
        updateLog();
        textSimulate.setText("");
    }//GEN-LAST:event_buttonSimulateGainActionPerformed

    
    private void buttonSimulateTagLossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSimulateTagLossActionPerformed
        //read textbox 
        String id = textSimulate.getText();
        if (id.length() == 0) 
            return;
        //send to owner (should implement 
        owner.handleLoss(id);
        
        log = id + "\n" + log;
        updateLog();
        textSimulate.setText("");
    }//GEN-LAST:event_buttonSimulateTagLossActionPerformed

    private void buttonClearLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClearLogActionPerformed
        log="";
        updateLog();
    }//GEN-LAST:event_buttonClearLogActionPerformed

    private void listTagsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listTagsMouseClicked
        if (evt.getClickCount()==1) {
            int pos = listTags.getSelectedIndex();
            if (pos<0)
                return;
            String s = listTags.getItem(pos);
            textSimulate.setText(s);
            return;
        }
        if (evt.getClickCount()==2) {
            int pos = listTags.getSelectedIndex();
            if (pos<0)
                return;
            String s= listTags.getItem(pos);
            textSimulate.setText(s);
            buttonSimulateGainActionPerformed(null);  //no mouse event passed in, not important
        }
    }//GEN-LAST:event_listTagsMouseClicked

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClearLog;
    private javax.swing.JButton buttonSimulateGain;
    private javax.swing.JButton buttonSimulateTagLoss;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private java.awt.List listTags;
    private java.awt.TextArea textArea1;
    private javax.swing.JTextField textSimulate;
    // End of variables declaration//GEN-END:variables

   
}