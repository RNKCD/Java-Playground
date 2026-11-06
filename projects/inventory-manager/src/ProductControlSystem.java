/**
   Date: 12/2/2024
   Course: CSCI2073-44355- Data Structures - Fall 2024
   Description: This program helps to manage inventory operations like aadd new item, delete, buy, sell, and provides
            report with total cost and total profit details.
   On my honor, I have neither given nor received unauthorized help while completing
   this assignment.
   */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProductControlSystem{
    private double report_t_cost;       //Total cost on report
    private double report_loss;         //Total loss on report
    private double report_profit;       //Total profit on report
    Map<String, itemDetails> itemMap;   //Mao that hold itemDetails object

    /**
     * Constructs ProductControlSystem class that initializes itemMap and report_t_cost, report_loss, report_profit variable
     */
    public ProductControlSystem(){

        itemMap = new HashMap<>();
        report_t_cost = 0;
        report_loss = 0;
        report_profit = 0;

    }

    /**
     *  Process commands from a file and performs operations accordingly
     *  @param dataFile file containing transaction data
     *  @return String containing the result of all operation
     */
    public String processTransactions(String dataFile){
      String result = "";

       try{

        //loading the file
        File file = new File(dataFile);
        Scanner infile = new Scanner(file);
        
        //reading line from line
        while(infile.hasNextLine()){

            String line = infile.nextLine();
            
            Scanner scan_line = new Scanner(line);

            //process operation in the line
            while(scan_line.hasNext()){
                String task = scan_line.next();

                if(task.equals("new")){
                    String item_id = scan_line.next();
                    double item_cost = scan_line.nextDouble();
                    double item_sell = scan_line.nextDouble();

                    result += _new(item_id , item_cost , item_sell) + "\n";
                    

                }
                else if(task.equals("delete")){
                    String item_id = scan_line.next();

                    result += delete(item_id) + "\n";
                }
                else if(task.equals("buy")){
                    String item_id = scan_line.next();
                    int quantity = scan_line.nextInt();

                    result += buy(item_id, quantity) + "\n"; 
                }
                else if(task.equals("sell")){
                    String item_id = scan_line.next();
                    int quantity = scan_line.nextInt();

                    result += sell(item_id, quantity) + "\n";
                }
                else if(task.equals("report")){

                    result += report() + "\n";

                }

            }


            scan_line.close();
        }
            infile.close();
        }
        catch(FileNotFoundException e){
            return "ERROR! File Not Found.";
        }

        return result;

    }

    /**
     *  Adds new item in the inventory
     * @param item_id ID of the item
     * @param item_cost cost price of the item
     * @param item_sell selling price of the item
     * @return A String telling that item is added or Error message if operation can't be performed
     */
    public String _new(String item_id, double item_cost, double item_sell){
        String text = "";

        //Checks if the item_id key is already present in the HashMap
        if(item_id.isEmpty() || !Double.isFinite(item_cost) || !Double.isFinite(item_sell) || item_cost < 0 || item_sell < 0){
            return "ERROR: Product ID must be nonempty and prices must be nonnegative";
        }
        if(itemMap.containsKey(item_id)){
            text = "ERROR: " + item_id + " already in inventory";
        }
        else{
            text = item_id + " added to inventory";
            itemDetails item = new itemDetails(item_cost, item_sell);
            itemMap.put(item_id, item);
        }
        return text;
    }

    /**
     *  Deletes the item details from the inventory
     * @param item_id ID of the item
     * @return A String indicating the loss of removing item or Error message if operation can't be performed
     */
    public String delete(String item_id){
        String text = "";

        //Checks if the item_id key is already present in the HashMap
        if(itemMap.containsKey(item_id)){
            itemDetails curr_item = itemMap.get(item_id);
            text = String.format("%s removed from inventory for a total loss of $%.2f", item_id ,(curr_item.get_item_cost()* curr_item.rem_quantity()));

            //Updates report total loss and report total cost
            report_loss  += (curr_item.get_item_cost()* curr_item.rem_quantity());
            report_t_cost -= (curr_item.get_item_cost()* curr_item.rem_quantity());
            itemMap.remove(item_id);
        }
        else{
            text = "ERROR: "+ item_id + " not in inventory";
        }
        return text;

    }

    /**
     * Increases the quantity of an item in inventory
     * @param item_id ID of the item
     * @param quantity number of item
     * @return  A String with the total cost of adding item or Error message if operation can't be performed
     */
    public String buy(String item_id, int quantity){
        if (quantity <= 0) return "ERROR: Quantity must be positive";
        String text = "";

        //Checks if the item_id key is already present in the HashMap
        if(itemMap.containsKey(item_id)){
            itemDetails curr_item = itemMap.get(item_id);
            text = String.format("%d units of %s added to inventory at a total cost of $%.2f" , quantity , item_id , (curr_item.get_item_cost()* quantity));
            
            //updating the quanity  of item bought and report total cost
            curr_item.t_quantity_bought(quantity);
            report_t_cost += (curr_item.get_item_cost()* quantity);

            itemMap.put(item_id, curr_item );
        }
        else{
            text = "ERROR: "+ item_id + " not in inventory";
        }
        return text;
    }

    /**
     * Decrese the quantity of an item in inventory
     * @param item_id Id of the item
     * @param quantity number of item
     * @return A string indicating the price and profit of the item sold  or  Error messege if the operation can't be performed
     */
    public String sell(String item_id, int quantity){
        if (quantity <= 0) return "ERROR: Quantity must be positive";
        String text = "";
        
        //Checks if the item_id key is already present in the HashMap
        if(itemMap.containsKey(item_id)){
            itemDetails curr_item = itemMap.get(item_id);
            
            //Return Error message if requested quantity is greater than available quantity of item
            if(curr_item.rem_quantity() < quantity){
                text = "ERROR: "+ quantity + " exceeds units of " + item_id + " in inventory";
                return text;
            }

            //Updates report profit and report total cost
            double s_p = quantity * curr_item.get_item_sell();
            double c_p = quantity * curr_item.get_item_cost();
            report_profit += (s_p-c_p);
            report_t_cost -= c_p;
            curr_item.t_quantity_sold(quantity);

            text += String.format("%d units of %s sold at a total price of $%.2f for a profit of $%.2f",quantity, item_id, s_p ,(s_p - c_p));

            

        }
        else{

            text = "ERROR: "+ item_id + " not in inventory";
        }

        return text;

    }

    /**
     * Generate a report of total cost and total profit
     * @return A String containing the total cost and profit
     */
    public String report(){
        String text ="";

        text = String.format("Total cost: $%.2f, Total profit: $%.2f", report_t_cost , (report_profit - report_loss));
        return text;
    }
    




}