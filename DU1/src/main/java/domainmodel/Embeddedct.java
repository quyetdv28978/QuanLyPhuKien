/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodel;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Admin
 */
@Embeddable
public class Embeddedct implements Serializable{
    private String idSP, idKM;
//    private String id;

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    public String getIdKM() {
        return idKM;
    }

    public void setIdKM(String idKM) {
        this.idKM = idKM;
    }

    public Embeddedct(String idSP, String idKM) {
        this.idSP = idSP;
        this.idKM = idKM;
    }

    public Embeddedct() {
    }
    
    
    
}
