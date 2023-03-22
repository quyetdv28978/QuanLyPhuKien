/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Responsitoties;

import DomainModel.ChatLieu;
import Utilities.DBConnection;
import java.util.List;

/**
 *
 * @author yugip
 */
public class ResChatLieu implements IfResponsitoties<ChatLieu>{

   @Override
    public List<Object[]> getALLJoin(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ChatLieu> getAll(String dk) {
        List<ChatLieu> listDM = DBConnection.selectQueRy("from ChatLieu");
        if (listDM!=null) {
            return listDM;
        }
        return null;
    }

    @Override
    public int add(ChatLieu q) {
        return DBConnection.executeQuery(q, null);
    }

    @Override
    public int update(ChatLieu q) {
        return DBConnection.executeQuery(q, "sdf");
    }

    @Override
    public int delete(String q) {
        return DBConnection.delete(q, ChatLieu.class);
    }
    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChatLieu timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
