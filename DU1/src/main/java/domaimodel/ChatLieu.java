/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domaimodel; 
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="chatLieu")
public class ChatLieu implements Serializable{
    @Id
    private String id;
    private String tenChatLieu;

    public ChatLieu() {
    }

    public ChatLieu(String id, String tenChatLieu) {
        this.id = id;
        this.tenChatLieu = tenChatLieu;
    }
    
    
    
    public ChatLieu(String id) {
        this.id = id;
    }
  
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }
    
    
}
