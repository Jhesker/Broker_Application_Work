
package brokerProgram1;

import java.io.*;
import java.util.Scanner;
import java.util.Arrays;


public class  Broker1
{
    static BrokerLogImpl brokerLogImpl= new BrokerLogImpl();
    static StockTradeLogImpl stockTradeLogImpl = new StockTradeLogImpl();
    static PrintImpl printImpl = new PrintImpl();
    

    /**Main code for running tests
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
      processFile();
      System.out.println(brokerLogImpl.getBrokerLog().toString());
      System.out.println(Arrays.toString(stockTradeLogImpl.getStockTradeArray()));
      createReport(); 
    }
    public static void processFile()
    {
        final String INPUT_ASSN_2 = "input/assn2Input.txt";
        File file = new File(INPUT_ASSN_2);
        
        //uncomment following lines to test input of different 
        
        //final String INPUT_ASSN_2_2 = "input/assn1Input1.txt";
        //File file = new File(INPUT_ASSN_2_2);
        
        // created variables for the loop statment and test values in Array
        String brokerTest = "BROKER";
        String stockTradeTest = "TRADE";
        String nextLine;
        String[] objectAttributes;
        try 
        { 
            Scanner input = new Scanner(file);
            // looping through the file till the end.
            do
            { 
            nextLine = input.nextLine();
            objectAttributes = nextLine.split(",");
            
                if(objectAttributes[0].equalsIgnoreCase(brokerTest))
                { 
                    //second array values == to broker add or delete methods
                    if(objectAttributes[1].equalsIgnoreCase("ADD"))
                        addBroker(objectAttributes);
                    if(objectAttributes[1].equalsIgnoreCase("DEL"))
                        deleteBroker(objectAttributes);
                }
                if(objectAttributes[0].equalsIgnoreCase(stockTradeTest))
                {
                  //second array values == to StockTrade
                    if(objectAttributes[1].equalsIgnoreCase("BUY"))
                        addStockTrade(objectAttributes);
                    if(objectAttributes[1].equalsIgnoreCase("SELL"))
                        deleteStockTrade(objectAttributes);
                }    
            }while(input.hasNext());
        }catch (FileNotFoundException ex) 
            {
                System.out.println("Error" + ex);
                System.exit(1);
            }  
    }
    public static void addBroker(String[] line)
    {
        //Creating the Broker object with line paramaer
        
        Broker brokerObj = new Broker(line);
        //Check to test the validity of Broker Attributes license and dept 
        String dept = brokerObj.getDept();
        String brokerLicense = brokerObj.getBrokerLicense();
        boolean isValidLicense = brokerObj.isValidLicense();
        boolean isValidDept = brokerObj.isValidDept();
        if(isValidLicense != true)
            System.out.println("\nError: Broker with license number\n" +
                    brokerLicense + 
                    "\nIs an invalid entry please check that\n" +
                    brokerLicense + " is valid\n");
        if(isValidDept != true)
            System.out.println("\nError: Broker with license number\n" + 
                    brokerLicense +
                    "\nIs an invalid entry please check that\n" +
                    dept + " is valid\n");
        
        //Checking to ensure the broker license isUnique
        boolean isUnique = 
                brokerLogImpl.isLicenseUnique(brokerLicense);
        if(isUnique == true)
        {
            brokerLogImpl.addBroker(brokerObj);
            System.out.println("\nBroker with license number\n" +
                    brokerLicense + 
                    "\nhas been Added to the log\n");
        }else
            {
                System.out.println("\nError: Broker with license number\n" +
                    brokerLicense + 
                    "\nis already contained in the log and will NOT" +
                    " be added\n");
            }
    }
    /**addStockTrade calls methods to check to make sure that all values
     * entered in to the stock trade object are correct and then 
     * 
     * @param line 
     */
    public static void addStockTrade(String[] line)
    {
        //Creating stockTrade object with line param
        StockTrade stockTradeObj = new StockTrade(line);
        String stockSymbol = stockTradeObj.getStockSymbol();
        double pricePerShare = stockTradeObj.getPricePerShare();
        int wholeShares = stockTradeObj.getWholeShares();
        String brokerLicense = stockTradeObj.getBrokerLicense();
         
        
        //Checking to ensure StockTrade object Attribute are valid
        boolean isValidStockSymbol = stockTradeObj.isValidStockSymbol();
        boolean isValidPrice = stockTradeObj.isValidPrice();
        boolean isValidWholeShares = stockTradeObj.isValidWholeShares();
        if(isValidStockSymbol != true)
            System.out.println("\nError: Stock Trade with stock symbol\n" +
                    stockSymbol + 
                    "\nIs an invalid entry please check that\n" + 
                    stockSymbol + " is valid\n");
        if(isValidPrice != true)
            System.out.println("\nError: Stock Trade with stock symbol\n" +
                    stockSymbol + 
                    "\nIs an invalid entry please check that\n" + 
                    pricePerShare + " is valid\n");
        if(isValidWholeShares != true)
            System.out.println("\nError: Stock Trade with stock symbol\n" +
                    stockSymbol + "\nIs an invalid entry please check that\n" + 
                    wholeShares + " is valid\n");
        //check to see is broker license is not unique 
        //and check to see stock symbol is unique
        boolean isBrokerLicenseUnique = 
                brokerLogImpl.isLicenseUnique(brokerLicense);
        
        boolean isStockUnique = 
                stockTradeLogImpl.isStockSymbolUnique
                (stockSymbol);
        if(isBrokerLicenseUnique == false && isStockUnique == true)
        {
            stockTradeLogImpl.addStockTrade(stockTradeObj);
            System.out.println("\nStock Trade with stock symbol\n" + 
                    stockSymbol + "\nand broker license\n" +
                    brokerLicense + "\nhas been ADDED to the log\n");
        }
        if(isBrokerLicenseUnique == true)
           System.out.println("\nStock Trade with stock symbol\n" + 
                    stockSymbol + "\nand broker license\n" +
                    brokerLicense + 
                    "\nwill NOT be added to the log\n" +
                    brokerLicense + 
                   "\nis not a broker license currently found in the system\n");
        if(isStockUnique == false)
           System.out.println("\nStock Trade with stock symbol\n" + 
                    stockSymbol + "\nand broker license\n" +
                    brokerLicense + 
                    "\nwill NOT be added to the log\n" +
                    stockSymbol + 
                    "\nhas already been added to the system\n"); 
    }
    /**deleteBroker deletes a broker object from the ArrayList and also 
     * removes the StockTrades that are Associated with that broker license
     * number
     * 
     * @param line 
     */
    public static void deleteBroker(String[] line)
    {
        Broker brokerObj = new Broker(line);   
        String brokerLicense = brokerObj.getBrokerLicense();
        boolean isUnique = 
                brokerLogImpl.isLicenseUnique(brokerLicense);
        if(isUnique == false)
        {
            brokerLogImpl.removeBroker(brokerLicense);
            System.out.println("\nBroker with license number\n" +
                    brokerLicense + 
                    "\nhas been REMOVED from the log\n" +
                    "All of the Broker's stocks will also be removed\n");
            stockTradeLogImpl.removeStockTradeByBroker
                    (brokerLicense);
            
        }else
        {
            System.out.println("\nError: Broker with license number\n" +
                    brokerLicense + 
                    "\nWas not found in the system.\n");
        }
        
    }
    /**deleteStockTrade checks to see is the stock symbol exists in the 
     * data structure and then deletes it if it does.
     * 
     * @param line 
     */
    public static void deleteStockTrade(String[] line)
    {
        
        StockTrade stockTradeObj= new StockTrade(line);
        String stockSymbol = stockTradeObj.getStockSymbol();
        String brokerLicense = stockTradeObj.getBrokerLicense();
        boolean isStockUnique = 
                stockTradeLogImpl.isStockSymbolUnique
                (brokerLicense);
        if(isStockUnique == false)
        {
            stockTradeLogImpl.removeStockTrade(stockSymbol);
            System.out.println("\nStock Trade with stock symbol\n" + 
                     stockSymbol +
                    "\nHas been REMOVED from the log\n" );
        }
    }
    /**passes parameters to print impl and then calls the method
     * 
     */
    public static void createReport()
    {
        printImpl.printReport(brokerLogImpl, stockTradeLogImpl);
    }
}
