/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author yugip
 */
@Entity
@Table(name = "chatlieu")
public class ChatLieu {

    @Id
    private String id;
    private String tenchatlieu, ma;

    public ChatLieu() {
    }

    public ChatLieu(String ten) {
        this.tenchatlieu = ten;
    }
    
    

    public ChatLieu(String id, String ten, String ma) {
        this.id = id;
        this.tenchatlieu = ten;
        this.ma = ma;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return tenchatlieu;
    }

    public void setTen(String ten) {
        this.tenchatlieu = ten;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

}