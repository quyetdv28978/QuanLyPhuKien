/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DomainModel.ChatLieu;
import Responsitoties.IfResponsitoties;
import Responsitoties.ResChatLieu;
import ViewModel.ChatLieuView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yugip
 */
public class SerChatLieu implements IFService<ChatLieuView> {

    private ResChatLieu resDM = new ResChatLieu();

    @Override
    public List<ChatLieuView> getALl(String dk) {
        List<ChatLieuView> listDMV = new ArrayList<>();
        if (this.resDM.getAll(dk) != null) {
            for (ChatLieu danhMuc : this.resDM.getAll(dk)) {
                listDMV.add(new ChatLieuView(danhMuc.getId(), danhMuc.getMa(), danhMuc.getTen()));
            }
        }
        return listDMV;
    }

    @Override
    public int add(ChatLieuView q) {
        return this.resDM.add((ChatLieu) CD(q));
    }

    @Override
    public int update(ChatLieuView q) {
        return this.resDM.update((ChatLieu) CD(q));
    }

    @Override
    public int delete(String q) {
        return this.resDM.delete(q);
    }

    @Override
    public String timID(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChatLieuView timOB(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object CD(ChatLieuView q) {
        return new ChatLieu(q.getId(), q.getTen(), q.getMa());
    }

    @Override
    public boolean checkTrung(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
