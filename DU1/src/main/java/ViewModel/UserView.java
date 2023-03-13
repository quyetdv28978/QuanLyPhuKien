/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

/**
 *
 * @author yugip
 */
public class UserView {
    private String id, ten, mk;

    public UserView(String ten, String mk) {
        this.ten = ten;
        this.mk = mk;
    }

    public UserView(String id, String ten, String mk) {
        this.id = id;
        this.ten = ten;
        this.mk = mk;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }
    
    
}
