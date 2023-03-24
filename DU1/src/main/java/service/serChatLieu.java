/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import domaimodel.ChatLieu;
import Responshitory.ResChatlieu;
import viewmodel.ChatLieuViewModel;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author ADMIN
 */
public class serChatLieu implements Interface<ChatLieuViewModel>{

    public final ResChatlieu chatLieul;
    
    public serChatLieu() {
        this.chatLieul=new ResChatlieu();
    }
    @Override
    public List<Object[]> getALLJoin(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ChatLieuViewModel> getAll(String dk) {
        List<ChatLieuViewModel> cl=new ArrayList<>();
        for(ChatLieu i: this.chatLieul.getAll(dk)){
            cl.add(new ChatLieuViewModel(i.getId(),i.getMa(),i.getTen()));
        }   
        return cl;
    }
    
    public List<ChatLieuViewModel> getAllLoad() {
        List<ChatLieuViewModel> cl=new ArrayList<>();
        for(ChatLieu i: this.chatLieul.getAllLoad()){
            cl.add(new ChatLieuViewModel(i.getId(),i.getMa(),i.getTen()));
        }   
        return cl;
    }

    @Override
    public int add(ChatLieuViewModel t) {
        return this.chatLieul.add(new ChatLieu(t.getId(), t.getMa(), t.getTenChatLieu()));
    }

    @Override
    public int update(ChatLieuViewModel t) {
        return this.chatLieul.update(new ChatLieu(t.getId(), t.getMa(), t.getTenChatLieu()));
    }

    @Override
    public int delete(String t) {
        return this.chatLieul.delete(t);
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChatLieuViewModel timObject(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object CD(ChatLieuViewModel t) {
        return new ChatLieu(t.getId(),t.getMa(), t.getTenChatLieu());
    }

    @Override
    public boolean checkTrung(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
