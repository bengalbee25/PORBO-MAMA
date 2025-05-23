/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PORBO_MAMA;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 *
 * @author USER
 */
public class viewbooks1 extends javax.swing.JFrame {

    /**
     * Creates new form viewbooks1
     */
    Connection con;
    ResultSet rs;
    PreparedStatement pst;
    private int userid;
    private int recordCount;
    public String enteredPassword;
    public viewbooks1(int userid,String enteredPassword) {
        initComponents();
        getContentPane().setBackground(Color.white);
        this.userid = userid;
        this.enteredPassword = enteredPassword;
        
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(30);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/porbo_mama","root","25march2002@");
            
            pst = con.prepareStatement("SELECT COUNT(*) FROM user_books where user_id=?");
            pst.setInt(1,userid);
            ResultSet rs1 = pst.executeQuery();
            
            if (rs1.next()) {
                recordCount = rs1.getInt(1);
                jLabel2.setText("Total Books: " + recordCount);
            }
            displayBooks();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(viewbooks1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(viewbooks1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   private void displayBooks() {
    try {
        pst = con.prepareStatement("SELECT * FROM user_books WHERE user_id = ?");
        pst.setInt(1, userid);
        rs = pst.executeQuery();

        jPanel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.insets = new Insets(10, 10, 10, 10);
                
        while (rs.next()) { 
            byte[] imageData = rs.getBytes("image_data");
            String title = rs.getString("title");
            String selectedPDFPath = rs.getString("filepath");

            if (imageData != null && imageData.length > 0) {
                ImageIcon imageIcon = new ImageIcon(imageData);
                Image image = imageIcon.getImage().getScaledInstance(420, 530, java.awt.Image.SCALE_SMOOTH);
                ImageIcon scaledImageIcon = new ImageIcon(image);
                JLabel imageLabel = new JLabel(scaledImageIcon);
                jPanel1.add(imageLabel, gbc);
            } else {
               JLabel noImageLabel = new JLabel("No Image", SwingConstants.CENTER); 
               noImageLabel.setVerticalAlignment(SwingConstants.CENTER); 
               noImageLabel.setFont(new Font("Arial", Font.PLAIN, 48));
               noImageLabel.setPreferredSize(new Dimension(420, 530));
               noImageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
               noImageLabel.setBackground(new java.awt.Color(204, 204, 204)); 
               noImageLabel.setOpaque(true); 
               jPanel1.add(noImageLabel, gbc);
              
            }

            gbc.gridy++;

            JButton titleButton = new JButton(title);
            titleButton.setBackground(new java.awt.Color(255, 153, 51));
            titleButton.setFont(new Font("Arial", Font.PLAIN, 24));
            titleButton.setPreferredSize(new Dimension(650, 70));
            jPanel1.add(titleButton, gbc);
            titleButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                   new viewbook(userid, title, enteredPassword).setVisible(true);
                   dispose();
                }
            });
            gbc.gridy++;
            
            JButton pdfButton = new JButton();
            pdfButton.setText("View PDF");
            pdfButton.setBackground(new java.awt.Color(255, 153, 51));
            pdfButton.setFont(new Font("Arial", Font.PLAIN, 24));
            pdfButton.setPreferredSize(new Dimension(420, 70)); 
            jPanel1.add(pdfButton, gbc);
            pdfButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                   if (selectedPDFPath != null && !selectedPDFPath.isEmpty()) {
                    try {
                        JOptionPane.showMessageDialog(viewbooks1.this, "PDF opened successfully");
                        Desktop.getDesktop().open(new File(selectedPDFPath)); 
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(viewbooks1.this, "Error opening PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(viewbooks1.this, "No PDF added", "Error", JOptionPane.ERROR_MESSAGE);
                }
                }
            });
            gbc.gridy++;
        }
    } catch (SQLException ex) {
        Logger.getLogger(viewbooks1.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Error fetching image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    private viewbooks1() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("View Books");

        jLabel2.setBackground(new java.awt.Color(255, 153, 51));
        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setOpaque(true);

        jButton2.setBackground(new java.awt.Color(51, 204, 255));
        jButton2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButton2.setText("Delete Book");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(51, 204, 255));
        jButton3.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButton3.setText("Randomize");
        jButton3.setPreferredSize(new java.awt.Dimension(163, 37));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 51));
        jLabel1.setText("View Books");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PORBO_MAMA/logo2.png"))); // NOI18N

        jButton1.setBackground(new java.awt.Color(51, 204, 255));
        jButton1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(153, 255, 255));

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1170, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 489, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        jMenuBar2.setBackground(new java.awt.Color(153, 255, 255));
        jMenuBar2.setBorder(null);
        jMenuBar2.setForeground(new java.awt.Color(255, 153, 51));
        jMenuBar2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jMenuBar2.setName("newpage"); // NOI18N
        jMenuBar2.setOpaque(false);

        jMenu4.setText("New");
        jMenu4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jMenu4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jMenuItem1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jMenuItem1.setForeground(new java.awt.Color(255, 153, 51));
        jMenuItem1.setText("Go to start menu");
        jMenuItem1.setOpaque(true);
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuBar2.add(jMenu4);

        jMenu2.setText("About");
        jMenu2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jMenu2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jMenuItem2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jMenuItem2.setForeground(new java.awt.Color(255, 153, 51));
        jMenuItem2.setText("About PORBO MAMA");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar2.add(jMenu2);

        jMenu1.setText("Delete");
        jMenu1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jMenuItem4.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jMenuItem4.setForeground(new java.awt.Color(255, 153, 51));
        jMenuItem4.setText("Delete library");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar2.add(jMenu1);

        jMenu3.setText("Exit");
        jMenu3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jMenu3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jMenuItem3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jMenuItem3.setForeground(new java.awt.Color(255, 153, 51));
        jMenuItem3.setText("Exit program");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar2.add(jMenu3);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14)))))
                .addGap(57, 57, 57))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        new loginsignup().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        new about().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        if(recordCount == 0)
        {
            JOptionPane.showMessageDialog(this, "There are no books available to delete at the moment", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int choice = JOptionPane.showConfirmDialog(viewbooks1.this,
            "Are you sure you want to delete all books from your library?", "Confirm library Deletion",
            JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION){
            try {
                pst = con.prepareStatement("DELETE FROM user_books WHERE user_id=?");
                pst.setInt(1, userid);
                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "All books deleted successfully");
                    new viewbooks1(userid, enteredPassword).setVisible(true);
                    this.dispose();
                }
            } catch (SQLException ex) {
                Logger.getLogger(viewbooks1.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error deleting books: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else {
            JOptionPane.showMessageDialog(this, "Library deletion canceled");
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(recordCount == 0)
        {
            JOptionPane.showMessageDialog(this, "There are no books available to delete at the moment", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

            String deletebook = JOptionPane.showInputDialog(this,"Enter name of the book to delete");

            if (deletebook == null || deletebook.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter title of the book", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                //            pst = con.prepareStatement("delete from user_books where title=? and user_id=? ");
                //            pst.setString(1, deletebook.trim());
                deletebook = deletebook.trim().replaceAll("\\s+", " ");
                pst = con.prepareStatement("delete from user_books where REPLACE(title, ' ', '') = REPLACE(?, ' ', '') and user_id = ?");
                pst.setString(1, deletebook);
                pst.setInt(2, userid);
                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this,"Book with the title " + deletebook.trim() + " deleted successfully");
                    new viewbooks1(userid,enteredPassword).setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "No book found with the title " + deletebook.trim(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                Logger.getLogger(viewbooks1.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error deleting book: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            pst = con.prepareStatement("SELECT * FROM user_books WHERE user_id = ? ORDER BY RAND() LIMIT 1");
            pst.setInt(1, userid);
            rs = pst.executeQuery();

            if (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                String year = rs.getString("year");
                String genre = rs.getString("genre");
                String position = rs.getString("position_in_bookshelf");
                String status = rs.getString("reading_status");
                String time = rs.getString("total_read_time");
                String remarks = rs.getString("remarks");

                JOptionPane.showMessageDialog(this,
                    "Title: " + title + "\n" +
                    "Author: " + author + "\n" +
                    "Publisher: " + publisher + "\n" +
                    "Year: " + year + "\n" +
                    "Genre: " + genre + "\n" +
                    "Position in Bookshelf: " + position + "\n" +
                    "Reading Status: " + status + "\n" +
                    "Total Read Time: " + time + "\n" +
                    "Remarks: " + remarks,
                    "Random Book Details", JOptionPane.INFORMATION_MESSAGE);

            } else {

                JOptionPane.showMessageDialog(this, "No books found for the user", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(viewbooks1.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error fetching random book: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new mainmenu(userid,enteredPassword).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatLightLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewbooks1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
