
package Utilities;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class jframeCheck {

    public boolean checkData(List<Object> checkL, JFrame thisjFrame) {
        for (Object object : checkL) {
            if (object instanceof JTextField) {
                if (((JTextField) object).getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(thisjFrame, ((JTextField) object).getName()
                            + " không được để trống");
                    ((JTextField) object).requestFocus();
                    return false;
                } else if (object instanceof JTextArea) {
                    if (((JTextArea) object).getText().trim().length() == 0) {
                        JOptionPane.showMessageDialog(thisjFrame, ((JTextArea) object).getName()
                                + " không được để trống");
                        ((JTextArea) object).requestFocus();
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int checkDinhDang(List<Object> listOB, String regex[], int dk[], JFrame thisjFrame) {
        int countRG = 0;
        int check;
        for (int i = 0; i < listOB.size(); i++) {
            if (listOB.get(i) instanceof JTextField) {
                if (((JTextField) listOB.get(i)).getName().endsWith("  ")) {
                    if (!((JTextField) listOB.get(i)).getText().matches(regex[countRG])) {
                        JOptionPane.showMessageDialog(thisjFrame, ((JTextField) ((JTextField) listOB.get(i))).getName()
                                + " không đúng định dạng");
                        ((JTextField) listOB.get(i)).requestFocus();
                        countRG++;
                        return 0;
                    }
                } else if (((JTextField) listOB.get(i)).getName().charAt(
                        ((JTextField) listOB.get(i)).getName().length() - 1) == ' ') {
                    try {
                        if (Integer.parseInt(((JTextField) listOB.get(i)).getText().trim()) < dk[0]
                                || Integer.parseInt(((JTextField) listOB.get(i)).getText().trim()) > dk[1]) {
                            JOptionPane.showMessageDialog(thisjFrame, ((JTextField) ((JTextField) listOB.get(i))).getName()
                                    + " phải nằm trong khoản (" + dk[0] + "-" + dk[1] + ")");
                            ((JTextField) listOB.get(i)).requestFocus();
                            return 0;
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(thisjFrame, ((JTextField) listOB.get(i)).getName().trim()
                                + " chưa đúng định dạng");
                        ((JTextField) listOB.get(i)).requestFocus();
                        return 0;
                    }
                }
            }
        }
        return 1;
    }

    public void clearView(List<Object> clearList, JTable table) {
        int check = 0;
        for (Object object : clearList) {
            if (object instanceof JTextField) {
                ((JTextField) object).setText("");
            } else if (object instanceof JTextArea) {
                ((JTextArea) object).setText("");
            } else if (object instanceof JRadioButton && check == 0) {
                ((JRadioButton) object).setSelected(true);
                check = 1;
            }
        }
        if (table != null) {
            table.clearSelection();
        }

    }

    public void clickTable(List<Object> listClick, JTable tableC) {
        for (int i = 0; i < tableC.getColumnCount(); i++) {
            for (Object object : listClick) {
                if (object instanceof JTextField) {
                    if (tableC.getColumnName(i).equalsIgnoreCase(((JTextField) object).getName())) {
                        ((JTextField) object).setText(tableC.getValueAt(tableC.getSelectedRow(), i).toString());
                    }
                } else if (object instanceof JTextArea) {
                    if (tableC.getColumnName(i).equalsIgnoreCase(((JTextArea) object).getName())) {
                        ((JTextArea) object).setText(tableC.getValueAt(tableC.getSelectedRow(), i).toString());
                    }
                } else if (object instanceof JRadioButton) {
                    if (tableC.getValueAt(tableC.getSelectedRow(), i).toString().equalsIgnoreCase(((JRadioButton) object).getText())) {
                        ((JRadioButton) object).setSelected(true);
                    }
                }
            }
        }
    }

    public boolean checkClcick(JTable jtable, JFrame jrame) {
        if (jtable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(jrame, "Vui lòng chọn một dòng trên table");
            return false;
        }
        return true;
    }

    public String randomMA() {
        return new Random().nextLong(100000) + "";
    }

    public UUID createID() {
        return UUID.randomUUID();
    }
}
