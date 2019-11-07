/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Account;
import Model.Room;

import Model.Session;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Dau Manh Tuan
 */
public class RoomList extends javax.swing.JFrame {

    /**
     * Creates new form RoomList
     */
    private void updateTop5(){
        JSONArray arr = Account.getTop5();
         DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        int count = model.getRowCount();
        for (int i=0;i<count;i++){
            model.removeRow(0);
        }
        
        for (int i=0;i<arr.length();i++){
            model.addRow(new Object[]{
                arr.getJSONObject(i).get("user"),arr.getJSONObject(i).get("point")
            });
        }
    }
    private void updateRoom(){
        JSONArray arr = Model.Room.getAllRomm();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int count = model.getRowCount();
        for (int i=0;i<count;i++){
            model.removeRow(0);
        }
        for (int i=0;i<arr.length();i++){
            String x;
            if (arr.getJSONObject(i).getBoolean("state")==true){
                x="1/2";
            }else x = "2/2";
            model.addRow(new Object[]{
                
                arr.getJSONObject(i).get("room"),arr.getJSONObject(i).get("name"),x
            });
        }
    }
    private void updateAccount(){
        JSONObject x = Account.getAccount(Resource.ServerResource.user);
        
        jLabel2.setText("user "+x.getString("user"));
        String s = Integer.toString(x.getInt("point"));
        jLabel1.setText("Score "+s);
    }
    public RoomList() {
        initComponents();
        updateTop5();
        updateAccount();
        updateRoom();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("My score:");

        jLabel3.setText("Room");

        jLabel4.setText("Top5");

        jButton1.setText("Logout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},

            },
            new String [] {
                "Room", "name", "state"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "user", "score"
            }
        ));
        jScrollPane1.setViewportView(jTable2);

        jLabel2.setText("User:");

        jButton3.setText("Join Room");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Create Room");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setText("Reset Room");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(32, 32, 32)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Session.logOut();
        this.setVisible(false);
        
        LoginForm x= new LoginForm();
        x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (jTable1.getSelectedRow()==-1) JOptionPane.showMessageDialog(null,"Hay chon phong");
        String room = jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0).toString();
        joinRoom(room);
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        JSONObject x = Room.createRoom();
        System.out.println("View.RoomList.jButton2ActionPerformed()");
        if (x.getBoolean("accept")){
            this.setVisible(false);
           joinRoom(x.getString("room"));
           this.dispose();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        updateTop5();
        updateAccount();
        updateRoom();
    }//GEN-LAST:event_jButton2ActionPerformed
    
    private boolean joinRoom(String room){
        JSONObject x = Room.joinRoom(room);
        if (x.getBoolean("accept")){
            String user1= "";
            String user2= "";
            String score1= "";
            String score2= "";
            
            if (!x.isNull("player1")){
                JSONObject y = Account.getAccount(x.getString("player1"));
                user1 = x.getString("player1");
                score2 = Integer.toString(y.getInt("point"));
            } 
            if (!x.isNull("player2")){
                JSONObject y = Account.getAccount(x.getString("player2"));
                user2 = x.getString("player2");
                score2 = Integer.toString(y.getInt("point"));
            } 
            this.setVisible(false);
           
           RoomCaro roomCaro = new RoomCaro(room,"chuc mung nam moi",user1,user2,score1,score2);
           roomCaro.setVisible(true);
            this.dispose();
        }
        else {
            JOptionPane.showMessageDialog(null,"Phong khong ton tai hoac da bi xoa");
            updateRoom();
            updateTop5();
            return false;
        }
        return true;
    }
    
    /**
     * @param args the command line arguments
     */
 
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
