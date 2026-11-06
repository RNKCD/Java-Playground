/**
   Date: 10/30/2024
   Course: CSCI2073-44355- Data Structures - Fall 2024
   Description: This program contain the codes for the robot arms that manipulates blocks that are arranged
                in two piles. Blocks are moved between piles, discarded, or grabbed based on specific commands
                 from an input file.
   On my honor, I have neither given nor received unauthorized help while completing
   this assignment.
   */


import java.util.*;
import java.io.*;


public class  RobotWorld{
    private StackInt<String> block1;    //Stack representing the 1st pile of block
    private StackInt<String> block2;    //Stcak representing the 2nd pile of block

    /**
     * Constructs the RobotWorld with two stacks of blocks from a comma-separated string.
     * 
     * @param block A comma-separated string containing blocks for pile 1 and pile 2.
     */
    public RobotWorld(String block){

        Scanner cmd = new Scanner(block);
        cmd.useDelimiter(",");

        // Splitting input string into two parts for each pile
        String block1_str = cmd.next();
        String block2_str = cmd.next();

        // Creating stacks from block strings
        block1 = blockmaker(block1_str);
        block2 = blockmaker(block2_str);

        cmd.close();
    }

    /**
     * Converts a string into a stack of blocks.
     * 
     * @param block_str A string representing a sequence of blocks in a pile.
     * @return A StackInt containing the blocks in order.
     */
    private StackInt<String> blockmaker(String block_str){
        StackInt<String> block = new LinkedStack<String>();

        for(int i= 0 ; i < block_str.length(); i= i+2){
            String temp = block_str.substring(i, i+2);
            block.push(temp);
        }
        return block;
    }

    /** Shows both piles from top to bottom without modifying the stacks. */
    public String displayPiles() {
        return "PILE 1 (top first): " + block1 + "\nPILE 2 (top first): " + block2;
    }

    /**
     * Processes commands from a file and updates the state of the piles accordingly.
     * 
     * @param filename File containing commands for the robot.
     * @return A string representing the final state of the piles after executing all commands.
     */

    public String commands(String filename) {
        String output = "";
        
        try {
            File file_name = new File(filename);
            Scanner infile = new Scanner(file_name);
    
            while (infile.hasNextLine()) {
                String cmd_line = infile.nextLine();
                Scanner cmd_scnr = new Scanner(cmd_line);
                
                //separating command and block/color name
                String command = cmd_scnr.next();
                String data = cmd_scnr.next();
                
                // Perform the specified command and update output string
                if (command.equals("move")) {
                    move(data);
                    String move_str = String.format("PILE 1: " + block1.toString().replace(",", "") + " PILE 2: "+ block2.toString().replace(",", "") + "\n");
                    output = output + move_str;
                } else if (command.equals("discard")) {
                    discard(data);
                    String discard_str = String.format("PILE 1: " + block1.toString().replace(",", "") + " PILE 2: "+ block2.toString().replace(",", "") + "\n");
                    output = output + discard_str;
                    
                } else if (command.equals("grab-color")) {
                    grab_color(data);
                    String grab_str = String.format("PILE 1: " + block1.toString().replace(",", "") + " PILE 2: "+ block2.toString().replace(",", "") + "\n");
                    output = output + grab_str;
                }

                cmd_scnr.close();
            }

            infile.close();
    
        } catch (FileNotFoundException e) {
            return "File Not Found.";
        }

    
        return output;
    }

    /**
     * Moves a block from one pile to the other if it matches the block name.
     * 
     * @param block_name The name of the block to move.
     */
    public void move(String block_name){
        if(moveHelper(block1, block2, block_name) == false){
            moveHelper(block2, block1, block_name);
        }

    }

    /**
     * Helper method that moves a block from one stack to another based on the block name.
     * 
     * @param in_Stack The stack to search and remove the block from.
     * @param to_Stack The stack to which the block shoud be added.
     * @param block_name The name of the block to move.
     * @return True if the block was moved successfully, false otherwise.
     */
    private boolean moveHelper(StackInt<String> in_Stack, StackInt<String> to_Stack, String block_name) {
        StackInt<String> temp1 = new LinkedStack<>();
        boolean String_moved = false;
    
        while (!in_Stack.empty()) {
            String chosen_element = in_Stack.pop();
            String chosen_letter = String.valueOf(chosen_element.charAt(0));
    
            if (chosen_letter.equals(block_name) && !String_moved) {
                to_Stack.push(chosen_element);
                String_moved = true;
            } else {
                temp1.push(chosen_element);
            }
        }
    
        while (!temp1.empty()) {
            in_Stack.push(temp1.pop());
        }

        return String_moved;
    }

    /**
     * Remove block that match the specified name from both piles.
     * 
     * @param block_name The name of the block to remove.
     */
    public void discard(String block_name){
        discardHelper(block1, block_name);
        discardHelper(block2, block_name);

    }
    
    /**
     * Helper method that removes block matching the name from a pile.
     * 
     * @param in_Stack The stack to remove the block from.
     * @param block_name The name of the block to discard.
     */
    private void discardHelper(StackInt<String> in_Stack, String block_name){

        StackInt<String> temp1 = new LinkedStack<String>();

        while(!in_Stack.empty()){
            String chosen_element = in_Stack.pop();
            String chosen_letter = String.valueOf(chosen_element.charAt(0));

            if(!chosen_letter.equals(block_name)) {
                temp1.push(chosen_element);

            }
        }

        while(!temp1.empty()){
            in_Stack.push(temp1.pop());

        }
    }

    /**
     * Removes the block of specific color from each pile, if present.
     * 
     * @param color The color of the block to remove.
     */
    public void grab_color(String color){
        grab_colorHelper(block1, color);
        grab_colorHelper(block2, color);
    }


    /**
     * Helper method that removes the topmost block of a specified color from a pile.
     * 
     * @param in_Stack The stack to search for the block.
     * @param color The color of the block to remove.
     */
    private void grab_colorHelper(StackInt<String> in_Stack, String color){
        StackInt<String> temp1 = new LinkedStack<String>();
        int count = 1;

        while(!in_Stack.empty()){
            String chosen_element = in_Stack.pop();
            String chosen_letter = String.valueOf(chosen_element.charAt(1));

            if(count == 1 && color.equals(chosen_letter)){
                count=2;
    
            }
            else{
                temp1.push(chosen_element);
                
            }
        }

        while(!temp1.empty()){
            in_Stack.push(temp1.pop());

        }
    }

}