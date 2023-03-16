/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

/**
 *
 * @author Admin
 */
public class DanhMucViewModel {
    private String id;
    private String ma;
    private String dongSP;

    public DanhMucViewModel() {
    }

    public DanhMucViewModel(String id, String ma, String dongSP) {
        this.id = id;
        this.ma = ma;
        this.dongSP = dongSP;
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

    public String getDongSP() {
        return dongSP;
    }

    public void setDongSP(String dongSP) {
        this.dongSP = dongSP;
    }
  public Object[] toRow(){
      return new Object[]{id,ma,dongSP};
  }
}
