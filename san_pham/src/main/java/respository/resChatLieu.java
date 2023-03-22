/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respository;

import domainModel.ChatLieu;
import java.util.List;
import utility.DBConnection;

/**
 *
 * @author ADMIN
 */
public class resChatLieu implements Interface<ChatLieu> {

    @Override
    public List<Object[]> getALLJoin(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ChatLieu> getAll(String dk) {
        return DBConnection.selectQueRy("from ChatLieu");
    }

    public List<ChatLieu> getAllLoad() {
        return DBConnection.selectQueRy("from ChatLieu");
    }

    @Override
    public int add(ChatLieu t) {
        return DBConnection.executeQuery(t, null);
    }

    @Override
    public int update(ChatLieu t) {
        return DBConnection.executeQuery(t, "update");
    }

    @Override
    public int delete(String t) {
        return DBConnection.delete(t, ChatLieu.class);
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
