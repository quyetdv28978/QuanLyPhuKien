/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respon;

import domaimodel.HoaDon;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utility.DBConnection11;

/**
 *
 * @author HP
 */
public class bieudores implements ibieudo{

    @Override
    public List<HoaDon> getall() {
        List<HoaDon> ar=new ArrayList<>();
        try{
            String sql="select  cast(ngaytt as date) as ngaytt ,sum(tongtien) as tongtien from hoadon group by ngaytt";
            ResultSet rs=DBConnection11.openDbConnection().prepareStatement(sql).executeQuery();
            while(rs.next()){
                ar.add(new HoaDon(rs.getDate("ngaytt"), rs.getFloat("tongtien")));
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return ar;
    }
    
}
