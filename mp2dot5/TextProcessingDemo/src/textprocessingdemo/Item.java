/*
 * Insert Header Here
 */
package textprocessingdemo;

/**
 *
 * @author m-lendon
 */
public class Item {
    private String name;
    private int roomNum;

    public Item(String name, int roomNum){
        this.name = name;
        this.roomNum = roomNum;
    }

    public String getName(){
        return name;
    }

    public int getRoomNum(){
        return roomNum;
    }
}
