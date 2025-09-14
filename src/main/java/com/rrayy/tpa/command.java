package com.rrayy.tpa;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent.Action;

public class command implements CommandExecutor {
    private tpa main;
    private final Map<Player,Player> pls = new HashMap<Player,Player>();

    public command(tpa plugin){
        this.main = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(sender instanceof Player) {
            if (args.length <= 0) return false; // 아무 인자도 없으면 false
            if (args[0].equals("accept")) {
                Player acceptor = (Player) sender;
                Player requester = pls.get(acceptor);

                requester.sendMessage(ChatColor.BOLD+acceptor.getDisplayName()+ChatColor.GREEN+" 님께서 tpa 요청을 "+ChatColor.DARK_GREEN+"[수락]"+ChatColor.GREEN+" 하셨습니다.");

                Location accLocation = acceptor.getLocation();
                requester.teleport(accLocation);
                pls.remove(acceptor, requester);
                return true;
            } else if (args[0].equals("deny")) {
                Player acceptor = (Player) sender;
                Player requester = pls.get(acceptor);

                requester.sendMessage(ChatColor.BOLD+acceptor.getDisplayName()+ChatColor.GREEN+" 님께서 tpa 요청을 "+ChatColor.DARK_RED+"[거절]"+ChatColor.GREEN+" 하셨습니다.");
                pls.remove(acceptor, requester);
                return true;
            } else {
                Player acceptor = Bukkit.getPlayer(args[0]);
                Player requester = (Player) sender;

                if (acceptor == null) return false;
                if (acceptor == requester) return false; // 요청자와 수락자의 아이디가 같으면 취소
                if (acceptor.getWorld() != requester.getWorld()){
                    sender.sendMessage(ChatColor.RED + "다른 월드의 해당 플레이어에게는 tpa 요청을 보낼 수 없습니다.");
                    return true;
                }

                sendtpa(requester, acceptor);
                sender.sendMessage(main.PREFIX+ChatColor.BOLD+acceptor.getDisplayName()+ChatColor.GREEN+" 님께 tpa 요청을 보냈습니다, 수락 시 이동됩니다.");
                return true;
            }
        } else {
            sender.sendMessage(main.PREFIX+ChatColor.GREEN+"플레이어가 아니면 명령어를 사용할 수 없습니다.");
            return true;
        }
    }

    public void sendtpa(Player requester, Player acceptor) {
        pls.put(acceptor, requester);
        TextComponent accept = new TextComponent(ChatColor.DARK_GREEN+"[수락]");
        TextComponent deny = new TextComponent(ChatColor.DARK_RED +"[거절]");

        accept.setBold(true);
        deny.setBold(true);
        accept.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/tpa accept"));
        deny.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/tpa deny"));

        acceptor.sendMessage(ChatColor.GREEN+requester.getName()+ChatColor.AQUA+ "님께서 tpa 요청을 보냈습니다, 수락 시 이동됩니다.");
        acceptor.spigot().sendMessage(accept, deny);
    }
}