/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domaimodel;

import java.io.Serializable;

/**
 *
 * @author yugip
 */
public class embeddableCTHD implements Serializable{
    private String idhd;
    private String idsp;

    public embeddableCTHD() {
    }

    public embeddableCTHD(String idhd, String idsp) {
        this.idhd = idhd;
        this.idsp = idsp;
    }

    public String getIdhd() {
        return idhd;
    }

    public void setIdhd(String idhd) {
        this.idhd = idhd;
    }

    public String getIdsp() {
        return idsp;
    }

    public void setIdsp(String idsp) {
        this.idsp = idsp;
    }

    @Override
    public String toString() {
        return "embeddableCTHD{" + "idhd=" + idhd + ", idsp=" + idsp + '}';
    }
    
    
}
