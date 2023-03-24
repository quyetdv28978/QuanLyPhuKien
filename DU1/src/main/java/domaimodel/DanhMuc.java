
package domaimodel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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



