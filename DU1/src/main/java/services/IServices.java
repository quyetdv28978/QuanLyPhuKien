/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface IServices<Q> {
    public List<Q> getALl(String dk);

    public int add(Q q);

    public int update(Q q);

    public int delete(String q);

    public String timID(String ma);

    public Q timOB(String id);

    public Object CD(Q q);
    
    public boolean checkTrung(String ma);
}
