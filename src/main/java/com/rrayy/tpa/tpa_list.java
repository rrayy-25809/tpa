package com.rrayy.tpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class tpa_list {
    private Map<Player,ArrayList<Player>> pls = new HashMap<Player,ArrayList<Player>>();

    public ArrayList<Player> get_requesters(Player acceptor){
        if (pls.containsKey(acceptor)) {
            return this.pls.get(acceptor);
        } else {
            return new ArrayList<Player>();
        }
    }

    public boolean put(Player acceptor, Player requester){
        ArrayList<Player> requesters = this.get_requesters(acceptor);

        boolean work = requesters.add(requester); // 성공 여부를 여기서 받기
        this.pls.put(acceptor, requesters);

        return work;
    }

    public boolean remove(Player acceptor, Player requester){
        ArrayList<Player> requesters = this.get_requesters(acceptor);

        boolean work = requesters.remove(requester); // 성공 여부를 여기서 받기
        this.pls.put(acceptor, requesters);

        return work;
    }
}
//TODO : 플레이어 별 수락 및 거절을 제작하기 위해서 만들 예정이지만 일단 지금은 아님