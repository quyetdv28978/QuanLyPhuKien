/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author yugip
 */
public class jframeCheck {
    public boolean checkData(List<Object> checkL, JFrame thisjFrame){
        for (Object object : checkL) {
            if (object instanceof JTextField) {
                if (((JTextField) object).getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(thisjFrame, ((JTextField) object).getName() + " không được để trống");
                    return false;
                }
                else if (object instanceof JTextArea) {
                    if (((JTextArea) object).getText().trim().length() == 0) {
                        JOptionPane.showMessageDialog(thisjFrame, ((JTextArea) object).getName() + " không được để trống");
                    return false;
                    }
                }
            }
        }
        return true;
    }
    
    public void clearView(List<Object> clearList, JTable table){
        int check = 0;
        for (Object object : clearList) {
            if (object instanceof JTextField) {
                ((JTextField) object).setText("");
            }
            else if (object instanceof JTextArea) {
                ((JTextArea) object).setText("");
            }
           else if (object instanceof JRadioButton && check == 0) {
                ((JRadioButton) object).setSelected(true);
                check = 1;
            }
        }
        if (table != null) {
            table.clearSelection();
        }
       
        }
    
    public void clickTable(List<Object> listClick, JTable tableC){
        for (int i = 0; i < tableC.getColumnCount(); i++) {
            for (Object object : listClick) {
                if (object instanceof JTextField) {
                    if (tableC.getColumnName(i).equalsIgnoreCase(((JTextField) object).getName())) {
                        ((JTextField) object).setText(tableC.getValueAt(tableC.getSelectedRow(), i).toString());
                    }
                }
                else if (object instanceof JTextArea) {
                    if (tableC.getColumnName(i).equalsIgnoreCase(((JTextArea) object).getName())) {
                        ((JTextArea) object).setText(tableC.getValueAt(tableC.getSelectedRow(), i).toString());
                    }
                }
                else if (object instanceof JRadioButton) {
                    if (tableC.getValueAt(tableC.getSelectedRow(), i).toString().equalsIgnoreCase(((JRadioButton) object).getText())) {
                        ((JRadioButton) object).setSelected(true);
                    }
                }
            }
        }
    }
    
    public boolean checkClcick(JTable jtable, JFrame jrame){
        System.out.println(jtable.getSelectedRow());
        if (jtable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(jrame, "Vui lòng chọn một dòng trên table");
            return false;
        }
        return true;
    }
    
        public String randomMA() {
        return new Random().nextLong(100000) + "";
    }
}
