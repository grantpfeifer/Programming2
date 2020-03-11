/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textprocessingdemo;
import java.util.*;

/**
 *
 * @author j-estell
 */
public class Room {
    private int north;
    private int south;
    private int east;
    private int west;
    private boolean visited;
    private String name;
    private String description;
    private ArrayList<Item> items;

    public Room(int north, int south, int east, int west, String title, String description) {
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
        this.name = title;
        this.description = description;
        
        this.visited = false;
        this.items = new ArrayList<Item>();
    }
    
    public void addItem(Item item){
        items.add(item);
    }
    
    public boolean removeItem(Item item){
        for(Item str: items){
            if(str.getName() == item.getName())
                items.remove(str);
            return true;
        }
        return false;
    }

    
    
    public int getRoomToNorth() {
        return north;
    }

    public int getRoomToSouth() {
        return south;
    }

    public int getRoomToEast() {
        return east;
    }

    public int getRoomToWest() {
        return west;
    }

    public boolean hasVisited() {
        return visited;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        visited = true;
        return name + ": " + description;
    }

    public ArrayList<Item> getItemsInRoom() {
        return items;
    }
}
