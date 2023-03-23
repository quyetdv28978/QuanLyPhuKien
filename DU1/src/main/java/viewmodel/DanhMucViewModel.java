/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import javax.persistence.Entity;
import javax.persistence.Id;

public class DanhMucViewModel {
    
    private String id,dongSP;

    public DanhMucViewModel() {
    }

    public DanhMucViewModel(String id, String dongSP) {
        this.id = id;
        this.dongSP = dongSP;
    }
    
    public DanhMucViewModel(String dongSP) {
        this.dongSP = dongSP;
    }

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
    
    public Object[] toDataRow() {
        return new Object[]{id,dongSP};
    }
    
    public Object[] toDataRowCBB() {
        return new Object[]{dongSP};
    }
    
//    @Override
//    public String toString() {
//        return dongSP;
//    }

    @Override
    public String toString() {
        return dongSP;
    }
    
    
}
