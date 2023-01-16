package me.skinnynoonie.mazegenerator;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Random;

public class MazeGenerator {

    public final ArrayList<Vector> radiusVectors = new ArrayList<>();
    public int wallEnclosure;
    public Material wallBlockType;
    public int wallHeight;
    public Random randomNumberGenerator = new Random();
    public Vector[] connectionVectors = getVectors();

    public MazeGenerator() {}

    public void generateMaze(Location centerLocation) {
        for (Vector radiusOffset : radiusVectors) {
            Block radiusOffsetBlock = centerLocation.clone().add(radiusOffset).getBlock();

            if(radiusOffsetBlock.getX() % wallEnclosure != 0) continue;
            if(radiusOffsetBlock.getZ() % wallEnclosure != 0) continue;
            if(radiusOffsetBlock.getType() == Material.AIR) continue;

            setMazeWallBlocks(radiusOffsetBlock);
            radiusOffsetBlock.setType(Material.AIR);
        }
    }

    private void setMazeWallBlocks(Block mainNodeBlock) {
        Vector randomVector = this.connectionVectors[this.randomNumberGenerator.nextInt(4)];
        Location mainNodeLocation = mainNodeBlock.getLocation();
        for(int x = 0; x < this.wallEnclosure; x++) {
            for(int y = 0; y < this.wallHeight; y++) {
                mainNodeLocation.getBlock().setType(this.wallBlockType);
                mainNodeLocation.add(0, 1, 0);
            }
            mainNodeLocation.subtract(0, this.wallHeight, 0);
            mainNodeLocation.add(randomVector);
        }
    }

    public MazeGenerator setHeight(int height) {
        this.wallHeight = height;
        return this;
    }

    public MazeGenerator setRadius(int radius) {
        this.radiusVectors.clear();
        for(int xCoord = -radius; xCoord <= radius; xCoord++)
            for(int zCoord = -radius; zCoord <= radius; zCoord++)
                if(lengthOf(xCoord, zCoord) <= radius)
                    this.radiusVectors.add(new Vector(xCoord, 0, zCoord));
        return this;
    }

    public MazeGenerator setWallEnclosure(int enclosure) {
        this.wallEnclosure = enclosure;
        return this;
    }

    public MazeGenerator setWallBlockType(Material blockType) {
        this.wallBlockType = blockType;
        return this;
    }

    private double lengthOf(int xCoord, int zCoord) {
        return Math.sqrt(xCoord*xCoord + zCoord*zCoord);
    }

    private Vector[] getVectors() {
        return  new Vector[]{
                new Vector(1, 0, 0),
                new Vector(-1, 0, 0),
                new Vector(0, 0, 1),
                new Vector(0, 0, -1)
        };
    }

}
