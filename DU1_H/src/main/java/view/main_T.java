/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

//import com.barcodelib.barcode.Linear;
//import com.barcodelib.barcode.Linear;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
//import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 *
 * @author baby
 */
public class main_T extends javax.swing.JFrame {

    /**
     * Creates new form main
     */
    public main_T() {
        initComponents();
        String data = "do van quyet";
        int width = 200;
        int height = 100;

        try {
            // Tạo mã vạch
            testBar barcode = new testBar();
            BufferedImage image = barcode.createBarcode(data, width, height);

            // Lưu ảnh mã vạch
            File file = new File("QRCODE\\barcode.png");
            ImageIO.write(image, "png", file);
            System.out.println(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Linear barcode = new Linear();
    barcode.setType(Linear.CODE128B);
    barcode.setData("CODE39-123456789012");
    barcode.setN(11.0f);
    barcode.renderBarcode("D:\\bcmade.gif");
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        br_data = new javax.swing.JTextField();
        barcode_read = new javax.swing.JLabel();
        br_path = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Tạo mã");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Read Barcode");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        br_data.setText("0");
        br_data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                br_dataActionPerformed(evt);
            }
        });

        barcode_read.setText("0");

        br_path.setText("Enter BArcode path");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(br_path)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(barcode_read, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(br_data, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(273, 273, 273))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(br_data, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(br_path, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barcode_read, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(200, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // write Barcode

//        try {
//            Linear barcode = new Linear();
//            barcode.setType(Linear.CODE128B);
//            barcode.setData(br_data.getText());
//            barcode.setI(11.0f);
//            String fname = br_data.getText();
//            barcode.renderBarcode("C:\\Users\\yugip\\OneDrive\\Pictures\\hkasflkasjf\\" + fname + ".png");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // read bar code

//        try {
//            InputStream barInputStream = new FileInputStream(br_path.getText());
//            BufferedImage barBufferedImage = ImageIO.read(barInputStream);
//            LuminanceSource source = new BufferedImageLuminanceSource(barBufferedImage);
//            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//            Reader reader = new MultiFormatReader();
//            Result result = reader.decode(bitmap);
//            barcode_read.setText(result.getText());
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void br_dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_br_dataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_br_dataActionPerformed

    /*
     InputStream barCodeInputStream = new FileInputStream("D:\\codabar100.gif");  
    BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
    LuminanceSource source = new BufferedImageLuminanceSource(barCodeBufferedImage);  
    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));  
    Reader reader = new MultiFormatReader();  
    Result result = reader.decode(bitmap);
     */
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
            java.util.logging.Logger.getLogger(main_T.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main_T.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main_T.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main_T.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main_T().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel barcode_read;
    private javax.swing.JTextField br_data;
    private javax.swing.JTextField br_path;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    // End of variables declaration//GEN-END:variables
}
