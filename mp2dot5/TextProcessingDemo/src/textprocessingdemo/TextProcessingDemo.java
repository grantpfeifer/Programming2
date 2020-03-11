package textprocessingdemo;

import java.util.*;
import java.io.*;

/**
 * TextProcessingDemo.java - this JFrame is used to communicate between the game 
 * and the user.
 * 10 March 2020
 * @author g-pfeifer
 * @version 1.0
 */
public class TextProcessingDemo extends javax.swing.JFrame {

    private int roomIndex = 0;
    private Room currentRoom = null;
    private ArrayList<Room> rooms;
    private ArrayList<Item> items;
    private ArrayList<Item> inventory;
    
    
    /**
     * Creates new form TextProcessingDemo
     */
    public TextProcessingDemo() {
        initComponents();
        rooms = new ArrayList<Room>();
        items = new ArrayList<Item>();
        inventory = new ArrayList<Item>();
        
        // Read in the room file
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader( this.getClass().getResourceAsStream("/text/rooms.csv"));
        } catch( Exception e ) { System.exit(1); }
        
        Scanner scr = new Scanner( isr );
            // read in the file - assume it has correct format for each room of:
            // 1. Initial header line designating column data (read and ignore)
            // 2. Rows of comma-separated fields containing:
            //      a. room number (can ignore)
            //      b. room to the north (-1 = no connection)
            //      c. room to the south (-1 = no connection)
            //      d. room to the east (-1 = no connection)
            //      e. room to the west (-1 = no connection)
            //      f. room description
           
            String headers = scr.nextLine();
            while ( scr.hasNext() ) {
                Scanner roomData = new Scanner( scr.nextLine() ); // read in entire line
                roomData.useDelimiter(",");
                int room = roomData.nextInt();
                int n = roomData.nextInt();
                int s = roomData.nextInt();
                int e = roomData.nextInt();
                int w = roomData.nextInt();
                String title = roomData.next();

 // note that there could be embedded commas in the description
 // first order solution: read in next comma, then rest of line

                roomData.skip(",");  
                StringBuffer description = new StringBuffer( roomData.nextLine() );
                // remove extraneous quotation marks from the descriptive text.
                // At a minimum, CSV cells with embedded commas and/or quotes 
                // are encapsulated within quotation marks. Quotation marks within
                // the text are doubled: e.g. "foo" becomes ""foo"".
                // Routine below will just delete any double quote it sees; in the
                // case of the two successive quote characters, when the first one is
                // deleted, the second moves into the i-th character position, but is
                // NOT examined, so it's preserved as we go to the next iteration...
                for (int i = 0; i < description.length(); i++)
                    if (description.charAt(i) == '"') {
                            description.deleteCharAt(i);
                    }
                Room newRoom = new Room( n, s, e, w, title, description.toString() );
                rooms.add( newRoom );
            }
            scr.close();
            
            // Read in the items file
            InputStreamReader isrTwo = null;
        try {
            isrTwo = new InputStreamReader( this.getClass().getResourceAsStream("/text/items.csv"));
        } catch( Exception e ) { System.exit(1); }
        
        Scanner scrTwo = new Scanner( isrTwo );
            // read in the file - assume it has correct format for each room of:
            // 1. Initial header line designating column data (read and ignore)
            // 2. Rows of comma-separated fields containing:
            //      a. room number (can ignore)
            //      b. room to the north (-1 = no connection)
            //      c. room to the south (-1 = no connection)
            //      d. room to the east (-1 = no connection)
            //      e. room to the west (-1 = no connection)
            //      f. room description
           
            String headersTwo = scrTwo.nextLine();
            while ( scrTwo.hasNext() ) {
                Scanner itemData = new Scanner( scrTwo.nextLine() ); // read in entire line
                itemData.useDelimiter(",");
                int room = itemData.nextInt();
                String title = itemData.next();                
                Item newItem = new Item(title, room);
                items.add( newItem );
                
            }
            scrTwo.close();
            
            // Add the items to the room
            for(int i = 0; i < items.size(); i++){
                rooms.get(items.get(i).getRoomNum()).addItem(items.get(i));
            }
            
            // Set up the game for initial gameplay
            currentRoom = rooms.get(roomIndex);
            displayTextArea.setText("Welcome!\n");
            displayTextArea.append(currentRoom.getDescription() + "\n");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        inputTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        displayTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("From Adventure to Zork...");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("The a-MAIZE-ing Game");

        inputTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inputTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputTextFieldActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Your Command: ");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        displayTextArea.setEditable(false);
        displayTextArea.setBackground(java.awt.Color.black);
        displayTextArea.setColumns(20);
        displayTextArea.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        displayTextArea.setForeground(new java.awt.Color(0, 255, 0));
        displayTextArea.setLineWrap(true);
        displayTextArea.setRows(5);
        displayTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(displayTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputTextFieldActionPerformed
        String input = inputTextField.getText();
        displayTextArea.append("> " + input + "\n");
        processCommand( input );
        inputTextField.setText("");
    }//GEN-LAST:event_inputTextFieldActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        inputTextField.requestFocusInWindow(); 
    }//GEN-LAST:event_formComponentShown

    
    private void processCommand( String command ) {
        String noun = "";
        String verb = "";
        String modifier = "";
        Scanner parse = new Scanner(command);
        if (!parse.hasNext()) return;
        verb = parse.next();
        if (parse.hasNext()) noun = parse.next();
        while (parse.hasNext()) noun = noun + " " + parse.next();
        int newRoomIndex = -1;
        if (parse.hasNext()) modifier = parse.nextLine();
        
        switch (verb) {
            case "move":
            case "go":
                switch (noun) {
                    case "north": newRoomIndex = currentRoom.getRoomToNorth(); break;
                    case "south": newRoomIndex = currentRoom.getRoomToSouth(); break;
                    case "east":  newRoomIndex = currentRoom.getRoomToEast(); break;
                    case "west":  newRoomIndex = currentRoom.getRoomToWest(); break;
                    default:
                        unknownNoun("direction");
                        return;
                }
                
                if (newRoomIndex == -1) {
                    displayTextArea.append("You cannot go in that direction.\n");
                    return;
                } 
                
                roomIndex = newRoomIndex;
                currentRoom = rooms.get(roomIndex);
                displayRoomInfo();
                break;
                
            case "take":
                Item itemRef = findItemInRoom(noun);
                if(itemRef != null){
                    if(currentRoom.removeItem(itemRef)){
                    inventory.add(itemRef);
                    displayTextArea.append(noun + " added to inventory!\n");
                    }
                }
                else
                    displayTextArea.append(noun + " not found!\n");
                break;
                
            case "drop":
                boolean inInventory = false;
                Item itemRefTwo = findItemInventory(noun);
                if(itemRefTwo != null){
                    inventory.remove(itemRefTwo);
                    currentRoom.addItem(itemRefTwo);
                    displayTextArea.append(noun + " dropped!\n");
                }

//                }
                else displayTextArea.append(noun + " not in inventory!\n");
                break;
            case "look":
                    displayTextArea.append(currentRoom.getDescription() + "\n" + "Current items in room: " + displayItems(currentRoom.getItemsInRoom())+ "\n");
                break;
            case "inventory":
                    displayTextArea.append("Current Inventory: " + displayItems(inventory) + "\n");
                break;
            case "quit":
                    System.exit(0);
                break;
                    
                           
            default:
                displayTextArea.append("I don't understand\n");
        }
    }
    
    private Item findItemInRoom(String name){
        for(Item it : currentRoom.getItemsInRoom()){
            if(it.getName().equalsIgnoreCase(name)){
                return it;
            }
                
        }return null;
            
    }
    
    private Item findItemInventory(String name){
        for(Item it : inventory){
            if(it.getName().equalsIgnoreCase(name)){
                return it;
            }
                
        }return null;
    }
    
    private String displayItems(ArrayList<Item> itemsToDisplay){
        String temp = "";
        if(itemsToDisplay.size() > 0){
            int i = 0;
            if(itemsToDisplay.size() > 1){
                for(i = 0; i < itemsToDisplay.size() - 1; i++){
                    temp = temp + itemsToDisplay.get(i).getName() + ", ";
                }
                temp = temp + itemsToDisplay.get(i).getName();
                return temp;
            }
        temp = temp + itemsToDisplay.get(i).getName();
        return temp;
        }
        else return "No items";
    }
    
    
    private void unknownNoun( String phrase ) {
        displayTextArea.append( "I don't recognize that " + phrase + "\n");
    }
    
    private void displayRoomInfo() {
        if (!currentRoom.hasVisited())
            displayTextArea.append( currentRoom.getDescription() + "\n");
        else
            displayTextArea.append( currentRoom.getName() + "\n");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TextProcessingDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TextProcessingDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TextProcessingDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TextProcessingDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TextProcessingDemo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea displayTextArea;
    private javax.swing.JTextField inputTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
