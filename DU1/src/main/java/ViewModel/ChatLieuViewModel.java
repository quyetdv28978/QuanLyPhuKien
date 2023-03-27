/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class ChatLieuViewModel implements Serializable {

    private String id;
    private String ma, tenChatLieu;

    public ChatLieuViewModel() {
    }

    public ChatLieuViewModel(String id, String ma, String tenChatLieu) {
        this.id = id;
        this.ma = ma;
        this.tenChatLieu = tenChatLieu;
    }

    public ChatLieuViewModel(String ma, String tenChatLieu) {
        this.ma = ma;
        this.tenChatLieu = tenChatLieu;
    }

    public ChatLieuViewModel(String tenChatLieu) {

        this.tenChatLieu = tenChatLieu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }

    public Object[] toDataRow() {
        return new Object[]{id, ma, tenChatLieu};
    }

    @Override
    public String toString() {
        return tenChatLieu;
    }
    
}
