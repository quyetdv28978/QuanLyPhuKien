/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package responsitories;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface Iresponsitories<Q> {
     public List<Object[]> getALLJoin(String dk);

    public List<Q> getAll(String dk);

    public int add(Q q);

    public int update(Q q);

    public int delete(String q);

    public String timID(String ma);

    public Q timObject(String dk);
}
