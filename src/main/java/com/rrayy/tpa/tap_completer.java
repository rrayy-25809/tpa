package com.rrayy.tpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class tap_completer implements TabCompleter {
    private tpa main;

    public tap_completer(tpa plugin){
        this.main = plugin;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command,
            @NotNull String label, @NotNull String @NotNull [] args) {
        ArrayList<String> suggestions = new ArrayList<>();
        List<String> subcommands = Arrays.asList("accept", "deny");

        Bukkit.getServer().getOnlinePlayers().forEach(pl -> { // 플레이어 이름 중에서 찾기
            String name = pl.getName();

            if (name.toLowerCase().startsWith(args[0].toLowerCase())) {
                suggestions.add(name);
            }
        });

        for (String subc : subcommands) { // 서브커맨드 중에서 찾기
            if (subc.toLowerCase().startsWith(args[0].toLowerCase())) {
                suggestions.add(subc);
            }
        }

        if (subcommands.contains(args[0])) { // 수락 및 거절일 경우 요청자 목록에서 찾기
            ArrayList<Player> request_list = main.list.get_requesters((Player) sender);

            for (Player req : request_list) {
                String name = req.getName();

                if (name.toLowerCase().startsWith(args[1].toLowerCase())) {
                    suggestions.add(name);
                }
            }
        }

        return suggestions;
    }
    
}
