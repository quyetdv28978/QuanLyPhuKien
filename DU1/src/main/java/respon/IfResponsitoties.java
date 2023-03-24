
package Responshitory;

import java.util.List;

public interface IfResponsitoties<Q> {

    public List<Object[]> getALLJoin(String dk);

    public List<Q> getAll(String dk);

    public int add(Q q);

    public int update(Q q);

    public int delete(String q);

    public String timID(String ma);

    public Q timObject(String dk);
    
}
