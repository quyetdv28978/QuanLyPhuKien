/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import java.util.List;

/**
 *
 * @author yugip
 */
public interface IfResponsitoties<Q> {

    public List<Object[]> getALLJoin(String dk);

    public List<Q> getAll(String dk);

    public int add(Q q);

    public int update(Q q);

    public int delete(String q);

    public String timID(String ma);

    public Q timObject(String dk);
    
}
