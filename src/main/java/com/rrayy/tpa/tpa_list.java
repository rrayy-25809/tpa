package com.rrayy.tpa;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class tpa_list {
    private tpa main;
    private ArrayList<Player> requesters = new ArrayList<>();
    private ArrayList<Player> acceptors = new ArrayList<>();

    public tpa_list(tpa plugin){
        this.main = plugin;
    }

    public Player get_requester(Player acceptor){
        ArrayList<Integer> indexs = new ArrayList<>();
        indexs.add(acceptors.indexOf(acceptor));
        if (indexs.get(0) == -1) return null;
        for (int i = indexs.get(0)+1; i < acceptors.size(); i++) {
            if (acceptors.get(i) == acceptor){

            }
        }
        return acceptor;
    }
}
//TODO : 플레이어 별 수락 및 거절을 제작하기 위해서 만들 예정이지만 일단 지금은 아님