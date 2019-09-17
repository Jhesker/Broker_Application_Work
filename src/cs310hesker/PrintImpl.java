/**PrintImp- creates the methods for the output of the report and indicates the 
 * location in which the report was save to.
 * 
 */
package cs310hesker;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PrintImpl 
{
    private final String OUTPUT_FILENAME ="output/assn2report.txt";
    private ArrayList<Broker> brokerLog;
    private StockTrade[] stockTrade;
    private int numStockTrade;
    
    /** printReport for each Broker in the broker ArrayList examines each 
     * StockTrade in StockTrade Array and find StockTrade objects that match the 
     * Broker license of Broker Object //Nested loops// prints out report of
     * StockTrades of each Broker including the individuals totalNum of stock
     * listings and total value of StockTrade listings at the end then last the
     * location the report was then stored in.
     * //for(Object Obj: elements){} for loop the iterates through each element
     * //in the list
     * 
     * @param brokerLogImpl
     * @param stockTradeLogImpl 
     */
    public void printReport(BrokerLogImpl brokerLogImpl,
            StockTradeLogImpl stockTradeLogImpl)
    {
        brokerLog = brokerLogImpl.getBrokerLog();
        stockTrade = stockTradeLogImpl.getStockTradeArray();
        numStockTrade = stockTradeLogImpl.getNumStockTrade();
        try 
        {
            FileWriter fileWriter = new FileWriter(OUTPUT_FILENAME);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            
        
        for(int count1 = 0; count1 < brokerLog.size(); count1++)
        {

            Broker brokerObj = brokerLog.get(count1);
            String brokerLicense = brokerObj.getBrokerLicense();
            String lastName = brokerObj.getLastName();
            String firstName = brokerObj.getFirstName();
            printWriter.println("\n" + brokerLicense + "     " 
                    + lastName + ", " +  firstName + "\n");
            for(int count2 = 0; count2 < numStockTrade - 1
                    ; count2++)
            {
                if(stockTrade[count2].getBrokerLicense().matches(brokerLicense))
                {
                    String printTaxable;
                    if(stockTrade[count2].getTaxable() == true )
                        printTaxable = "YES";
                    else
                        printTaxable = "NO";
                    //write to file using StockTrade.toString method
                    printWriter.printf("%-10s%-15s%-8d%-6s\n", 
                            stockTrade[count2].getStockSymbol(),
                            stockTrade[count2].getPricePerShare(),
                            stockTrade[count2].getWholeShares(), printTaxable );
                } 
            }
            int numOfStockTrade = 
                    stockTradeLogImpl.numberOfBrokerStockTrades(brokerLicense);
            double valueStockTradesListing = 
                    stockTradeLogImpl.totalTradeValue(brokerLicense);
            printWriter.println("\nNumber of Stock Trade Listings for" + 
                        " Broker: " + numOfStockTrade);
            printWriter.println(
                    "Total value of Stock Trade Listings for Broker: $" + 
                    numOfStockTrade + "\n");
        }
        //write the total number of StockTrades using method totalTradeValue
        double totalTradeValue = stockTradeLogImpl.totalTradeValue();
        printWriter.print("Total value of Stock Trade Listings: $" + 
                    totalTradeValue + "\n");
        fileWriter.close();
        printWriter.close();
        fileWriter.close();
        
        } 
        catch (IOException ex) 
        {
           Logger.getLogger(PrintImpl.class.getName()).log(Level.SEVERE,null,ex);
           
        }
        System.out.println("The Report has been saved to " + OUTPUT_FILENAME);
       
    }
        
        
    
    
}
