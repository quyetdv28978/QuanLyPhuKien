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
@Table(name = "danhmuc")
public class DanhMuc {
    @Id
    private String id;
    private String dongsp, ma;

    public DanhMuc() {
    }

    public DanhMuc(String ten) {
        this.dongsp = ten;
    }

    public DanhMuc(String id, String dongsp, String ma) {
        this.id = id;
        this.dongsp = dongsp;
        this.ma = ma;
    }
    
    

    public DanhMuc(String id, String ten) {
        this.id = id;
        this.dongsp = ten;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return dongsp;
    }

    public void setTen(String ten) {
        this.dongsp = ten;
    }
    
    
}
