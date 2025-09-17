package com.rrayy.tpa;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class tpa_list {
    private tpa main;
    private Map<Player,ArrayList<Player>> pls = new HashMap<Player,ArrayList<Player>>();
    // 맵의 벨류에 ArrayList를 하나 더 넣는다면??

    public tpa_list(tpa plugin){
        this.main = plugin;
    }

    public ArrayList<Player> get_requesters(Player acceptor){
        return this.pls.get(acceptor);
    }

    public void put(Player acceptor, Player requester){
        if (pls.is_wtf(acceptor)) { // 맵에 플레이어가 존재한다면
            ArrayList<Player> requesters = this.get_requesters(acceptor);
        } else {
            ArrayList<Player> requesters = new ArrayList<Player>();
        }

        requesters.add(requester);
        this.pls.put(acceptor, requesters);
    }
}
//TODO : 플레이어 별 수락 및 거절을 제작하기 위해서 만들 예정이지만 일단 지금은 아님