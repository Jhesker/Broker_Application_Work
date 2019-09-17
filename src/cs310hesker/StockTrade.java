/*StockTrade - creates object StockTrade, getters and setters for all values,
 * methods that allow for the checking of values StockTrade that are commmonly
 * missentered such as stockSymbol, procePerShare, and wholeShares.
 * class also overrides object methods eguals() and toString()
 */
package cs310hesker;

import java.util.Objects;


public class StockTrade 
{
    
    /**
     * Stock Trade Data Fields
     */
    private String stockSymbol;
    private double pricePerShare;
    private int wholeShares;
    private String brokerLicense;
    private boolean taxable;
    
    
    /**Constructor creating StockTrade Object from String[]
     * 
     * @param line from file read in main class
     */ 
    public StockTrade(String[] line) 
    {
        this.stockSymbol = line[2];
        this.pricePerShare = Double.parseDouble(line[3]);
        this.wholeShares = Integer.parseInt(line[4]);
        this.brokerLicense = line[5];
        this.taxable = Boolean.parseBoolean(line[6]);
        
    }
    /**StockTrade constructor
    *
    *@param stockSymbol
    *@param pricePerShare
    *@param wholeShares
    *@param brokerLicense
    *@param taxable 
    */
    public StockTrade(String stockSymbol, double pricePerShare, int wholeShares,
            String brokerLicense, boolean taxable) 
    {
        
        this.stockSymbol = stockSymbol;
        this.pricePerShare = pricePerShare;
        this.wholeShares = wholeShares;
        this.brokerLicense = brokerLicense;
        this.taxable = taxable;
        
    }
    /**stockSymbol getter
     * 
     *@return stockSymbol
     */
    public String getStockSymbol() 
    {
        return stockSymbol;
    }
    /**stockSymbol setter
     * 
     * @param stockSymbol 
     */
    public void setStockSymbol(String stockSymbol) 
    {
        this.stockSymbol = stockSymbol;
    }
    /** pricePerShare getter
     * 
     * @return pricePerShare
     */
    public double getPricePerShare() 
    {
        return pricePerShare;
    }
    /**pricePerShare setter
     * 
     * @param pricePerShare 
     */
    public void setPricePerShare(double pricePerShare)
    {
        this.pricePerShare = pricePerShare;
    }
    /**wholeShares setter
     * 
     * @return 
     */
    public int getWholeShares()
    {
        return wholeShares;
    }
    /**wholeShares setter
     * 
     * @param wholeShares 
     */
    public void setWholeShares(int wholeShares)
    {
        this.wholeShares = wholeShares;
    }
    /**brokerLicense getter
     * 
     * @return 
     */
    public String getBrokerLicense()
    {
        return brokerLicense;
    }
    /**brokerLicense setter
     * 
     * @param brokerLicense 
     */
    public void setBrokerLicense(String brokerLicense)
    {
        this.brokerLicense = brokerLicense;
    }
    /**taxable getter
     * 
     * @return taxable
     */
    public boolean getTaxable()
    {
        return taxable;
    }
    /**taxable setter
     * 
     * @param taxable 
     */
    public void setTaxable(boolean taxable) 
    {
        this.taxable = taxable;
    }
    
    /**Override the default equals()method for the object
     * 
     * @return boolean
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) return true;
        if (obj == null) return false;
        if (this.getClass() == obj.getClass())
        {
            StockTrade other = (StockTrade) obj;
             return stockSymbol.equals(other.stockSymbol) &&
                    pricePerShare == other.pricePerShare &&
                    wholeShares == other.wholeShares &&
                    brokerLicense.equals(other.brokerLicense) &&
                    taxable == other.taxable;                    
        }else
        {
            return false;
        }
        
    }

    @Override
    public int hashCode() 
    {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.stockSymbol);
        hash = 37 * hash + Objects.hashCode(this.brokerLicense);
        return hash;
    }
     
    /**Override the object.toString() in order to rewrite to encompass 
     *the printing of the values of the current object.
     *
     * @return String Output 
     */
    @Override
    public String toString()
    {
       return "\nStockTrade{" + "stockSymbol: " + stockSymbol 
               + ", pricePerShare: " + pricePerShare + ", wholeShares: " 
               + wholeShares + ", brokerLicense:" + brokerLicense 
               + ", taxable: " + taxable + "}"; 
    }
    
    /**Checking too see if stockSymbol is valid. 3-4 uppercased characters long.
     *
     * @return boolean 
     */ 
    public boolean isValidStockSymbol ()
    {
        return stockSymbol.length() >= 3 && stockSymbol.length()<= 4 && 
                stockSymbol.matches("[A-Z]+");
    }
    /**Check to test is the pricePerShare is valid. Must not be over $1,000 per
     *share. 
     *
     * @return boolean
     */
    public boolean isValidPrice()
    {
        final double PRICE_PER_SHARE = this.pricePerShare;
        return PRICE_PER_SHARE<= 1000;
                    
    }
    /**Check to test whether the number of shares is valid and is not over 
     *100,000.
     *
     * @return boolean
     */
    public boolean isValidWholeShares()
    {
        final int WHOLE_SHARES = this.wholeShares;
        return WHOLE_SHARES <= 100000;
        
    }
    
}
