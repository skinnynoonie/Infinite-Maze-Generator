package me.skinnynoonie.commands;

import me.skinnynoonie.listeners.PlayerConnectionListener;
import me.skinnynoonie.mazegenerator.MazeGenerator;
import me.skinnynoonie.miniconfig.Config;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ConfigCommand implements CommandExecutor {

    private final String[] helpMessage = new String[]{
            "/config is missing arguments!",
            "Try these commands:",
            "   /config height <integer>",
            "   /config wallblocktype <material>",
            "   /config radius <integer>"
    };

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("op")) return true;
        if(args.length < 2) { sender.sendMessage(this.helpMessage); return true; }

        try {

            String configKey = args[0];
            if (configKey.equals("height")) {
                int value = Integer.parseInt(args[1]);
                Config.WALL_HEIGHT = value;
                sender.sendMessage("Set WALL_HEIGHT to " + value);
                for(MazeGenerator mazeGenerator : PlayerConnectionListener.mazeGeneratorArranger.values())
                    mazeGenerator.setHeight(Config.WALL_HEIGHT);
            }
            else if (configKey.equals("wallblocktype")) {
                Material value = Material.getMaterial(args[1]);
                if(value == null) return true;
                Config.WALL_BLOCK_TYPE = value;
                sender.sendMessage("Set WALL_BLOCK_TYPE to " + value);
                for(MazeGenerator mazeGenerator : PlayerConnectionListener.mazeGeneratorArranger.values())
                    mazeGenerator.setWallBlockType(Config.WALL_BLOCK_TYPE);
            }
            else if (configKey.equals("radius")) {
                int value = Integer.parseInt(args[1]);
                Config.MAZE_GENERATION_RADIUS = value;
                sender.sendMessage("Set MAZE_GENERATION_RADIUS to " + value);
                for(MazeGenerator mazeGenerator : PlayerConnectionListener.mazeGeneratorArranger.values())
                    mazeGenerator.setRadius(Config.MAZE_GENERATION_RADIUS);
            }
            else sender.sendMessage(this.helpMessage);
            return true;

        }catch (Exception ignored) { sender.sendMessage(this.helpMessage); return true; }

    }


}
