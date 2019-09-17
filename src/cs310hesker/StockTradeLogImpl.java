/**StockTrade- creates and manages an unordered array of the StockTrade
 * objects.
 * 
 */
package cs310hesker;

public class StockTradeLogImpl
{
    /**Data fields for the StockTradeLogImp Class
     * 
     */
    
    private int numStockTrade = 0;
    private static final int MAX_NUM_STOCK_TRADES = 1000;
    private StockTrade[] stockTrade = new StockTrade[MAX_NUM_STOCK_TRADES];

  
    
    /**getStockTradeArray- returns the array attribute, constructor
     * 
     * @return Array attribute 
     */
    public StockTrade[] getStockTradeArray()
    {
        return stockTrade;
    }
    
    
    /**getNumStockTrade- returns the count attribute
     * 
     * @return number of elements in Array 
     */
    public int getNumStockTrade()
    {
        return numStockTrade;
    }
    
    
    /**addStockTrade- add StockTrade object to log if there is room and 
     * returns if successful
     * 
     * @param tradeObj
     * @return boolean
     */
    public boolean addStockTrade(StockTrade tradeObj)
    {
       boolean wasSuccessful = false;
       if(numStockTrade == 0)
       {
            stockTrade[0] = tradeObj;
            wasSuccessful = true;
            numStockTrade++;
       } else
            {
            if( numStockTrade < MAX_NUM_STOCK_TRADES)
                {
                    stockTrade[numStockTrade] = tradeObj;
                    numStockTrade++;
                    wasSuccessful = true;
                }
            }
       return wasSuccessful;
    }
    
    
    /**removeStockTradeByBroker- Deletes all StockTrade objects with a 
     * given license number from the log and returns true if any objects 
     * were deleted
     * 
     * @param license
     * @return boolean
     */
    public boolean removeStockTradeByBroker(String license)
    {
        boolean wasRemoved = false; 
                    
        //StockTrade stockBrokerLicenseTestObj;
        for ( int count = 0; count < numStockTrade - 1 ; count++ )
        {    
            
            String stockBrokerLicenseTest = 
                    stockTrade[count].getBrokerLicense();
            
            if(license.compareToIgnoreCase(stockBrokerLicenseTest)==0)
            {
                stockTrade[count] = stockTrade[numStockTrade  - 1 ];
                numStockTrade--;
                wasRemoved = true;
                
            }            
        } 
        return wasRemoved;
    }
    
    
    /**removeStockTrade - Removes StockTrade with given stockSymbol from the log
     * 
     * @param stockSymbol
     * @return boolean
     */
    public boolean removeStockTrade(String stockSymbol)
    {
        boolean wasRemoved = true; 
        for ( int count = 0; count < numStockTrade - 1 ; count++ )
        {    
            String stockSymbolTest = stockTrade[count].getStockSymbol();
            
            if(stockSymbol.compareTo(stockSymbolTest)==0)
            {
                wasRemoved = true;
                stockTrade[count]=stockTrade[numStockTrade];
                numStockTrade--;
                count = numStockTrade;
            }            
        } 
        return wasRemoved;
    }
    /**isStockSymbolUnique- tests if StockTrade object with stockSymbol exists 
     * in log and returns true if stockSymbol is not already in the log
     * 
     * @param stockSymbol
     * @return boolean
     */
    public boolean isStockSymbolUnique(String stockSymbol)
    {
        boolean isUnique = true;             
        //StockTrade stockSymbolTestObj;
        for ( int count = 0; count < numStockTrade - 1 ; count++ )
        {    
            String stockSymbolTest=stockTrade[count].getStockSymbol();
             
            
            if(stockSymbol.compareTo(stockSymbolTest)  == 0)
                isUnique = false;
        } 
        return isUnique;
    }
    
    
    /**numberOfBrokerStockTrades return the count of all StockTrade objects 
     * in the log with a given Broker's License 
     * 
     * @param license
     * @return total StockTrade objects
     */
    public int numberOfBrokerStockTrades(String license)
    {
        int numOfBrokerStockTrades = 0;
        for ( int count = 0; count < numStockTrade - 1; count++ )
        {    
            String stockBrokerLicenseTest= stockTrade[count].getBrokerLicense();
            if(license.compareTo(stockBrokerLicenseTest) == 0)
            {
                numOfBrokerStockTrades++;
            }
                
        } 
        return numOfBrokerStockTrades;
    }
    
    
    /**totalTradeValue returns the sum of all stock Holdings in the log
     * uses a loop to collet all [idx] values of number of shares and 
     * pricePerShares and add the together assigning new value after
     * each iteration 
     * 
     * @return sum of Stock holdings
     */
    public double totalTradeValue()
    {
        double pricePerShare;
        double totalTradeValue = 0 ;
        int wholeShares;             
        for ( int count = 0; count < numStockTrade - 1; count++ )
        {   
            pricePerShare = stockTrade[count].getPricePerShare();
            wholeShares = stockTrade[count].getWholeShares();
            totalTradeValue = totalTradeValue + (pricePerShare * wholeShares);    
        } 
        return totalTradeValue;
    }
    
    
    /**totalTradeValue overloaded method returns the sum of all stock holdings
     * for a specific Broker's License
     * 
     * @param license
     * @return Sum of stock holding for Specific broker
     */
    public double totalTradeValue(String license)
    {
        String stockBrokerLicenseTest;
        double pricePerShare;
        double totalTradeValue = 0 ;
        int wholeShares;             
        for ( int count = 0; count < numStockTrade -1; count++ )
        {
            stockBrokerLicenseTest = stockTrade[count].getBrokerLicense();
            if(license.compareToIgnoreCase(stockBrokerLicenseTest) == 0)
            {    
                pricePerShare = stockTrade[count].getPricePerShare();
                wholeShares = stockTrade[count].getWholeShares();
                totalTradeValue = totalTradeValue + (pricePerShare *
                        wholeShares);
            }
                
        } 
        return totalTradeValue;
    }
    
}
