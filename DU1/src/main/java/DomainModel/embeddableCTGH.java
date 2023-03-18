/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

import java.io.Serializable;

/**
 *
 * @author yugip
 */
public class embeddableCTGH implements Serializable{
    private String idSP, idgh;

    public embeddableCTGH(String idSP, String idgh) {
        this.idSP = idSP;
        this.idgh = idgh;
    }

    public embeddableCTGH() {
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    public String getIdgh() {
        return idgh;
    }

    public void setIdgh(String idgh) {
        this.idgh = idgh;
    }
    
    
}
