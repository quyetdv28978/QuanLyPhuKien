/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package respository;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface Interface<T> {
    public List<Object[]> getALLJoin(String dk);

    public List<T> getAll(String dk);

    public int add(T t);

    public int update(T t);

    public int delete(String t);

    public String timID(String ma);

    public T timObject(String dk);
}
