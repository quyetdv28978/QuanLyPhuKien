/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

/**
 *
 * @author suppe
 */
public class ChucVuViewModel {

    private String idChucVu;
    private String maChucVu;
    private String tenChucVu;

    public ChucVuViewModel() {
    }

    public ChucVuViewModel(String idChucVu, String maChucVu, String tenChucVu) {
        this.idChucVu = idChucVu;
        this.maChucVu = maChucVu;
        this.tenChucVu = tenChucVu;
    }

    public ChucVuViewModel(String idChucVu) {
        this.idChucVu = idChucVu;
    }

    public String getIdChucVu() {
        return idChucVu;
    }

    public void setIdChucVu(String idChucVu) {
        this.idChucVu = idChucVu;
    }

    public String getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }

    @Override
    public String toString() {
        return tenChucVu;
    }

    public Object[] toDataRow() {
        return new Object[]{tenChucVu};
    }

}
