/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import domainmodel.ChatLieu;
import java.util.ArrayList;
import java.util.List;
import respon.ChatLieuResponsitories;
import viewmodel.ChatLieuViewModel;

/**
 *
 * @author ADMIN
 */
public class ChatLieuServices implements IServices<ChatLieuViewModel>{

    public final ChatLieuResponsitories chatLieul;
    
    public ChatLieuServices() {
        this.chatLieul=new ChatLieuResponsitories();
    }
   
    
    public List<ChatLieuViewModel> getAllLoad() {
        List<ChatLieuViewModel> cl=new ArrayList<>();
        for(ChatLieu i: this.chatLieul.getAllLoad()){
            cl.add(new ChatLieuViewModel(i.getId(),i.getMa(),i.getTenChatLieu()));
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
    public ChatLieuViewModel timOB(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object CD(ChatLieuViewModel t) {
        return new ChatLieu(t.getMa(), t.getTenChatLieu());
    }

    @Override
    public boolean checkTrung(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ChatLieuViewModel> getALl(String dk) {
        List<ChatLieuViewModel> cl=new ArrayList<>();
        for(ChatLieu i: this.chatLieul.getAll(dk)){
            cl.add(new ChatLieuViewModel(i.getId(),i.getMa(),i.getTenChatLieu()));
        }   
        return cl;
    }

    
}
