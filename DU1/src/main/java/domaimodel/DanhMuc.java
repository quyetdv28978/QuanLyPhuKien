/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domaiModel;
import javax.sql.*;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DanhMuc")
public class DanhMuc implements Serializable{
    @Id
    private String id;
    private String dongSP;

    public DanhMuc() {
    }
    

    public DanhMuc(String id, String dongSP) {
        this.id = id;
        this.dongSP = dongSP;
    }
    
    public DanhMuc(String id) {
        this.id = id;
    }
    
//    public DanhMuc(String dongSP) {
//        this.dongSP = dongSP;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDongSP() {
        return dongSP;
    }

    public void setDongSP(String dongSP) {
        this.dongSP = dongSP;
    }
    
    
}
