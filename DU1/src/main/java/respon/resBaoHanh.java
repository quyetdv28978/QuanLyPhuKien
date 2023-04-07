/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import domaimodel.BaoHanh;
import utility.DBConnection;


/**
 *
 * @author Admin
 */
public class resBaoHanh {
    public int add(BaoHanh b){
        return DBConnection.executeQuery(b, "");
    }
}
