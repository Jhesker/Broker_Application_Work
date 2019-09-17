/*BrokerLogImp  Creates and manages Broker Array list.
 *  allows for changes to be made to the list 
 *  (add Broker Object to the list via addBroker(),
 *  remove a BrokerObject from the list via removeBroker() ) The Class uses 
 *  to check the brokerLicense is not already in the ArrayList to ensure it is
 *  a new addition. 
 */
package cs310hesker;

import java.util.ArrayList;

public class BrokerLogImpl 
{
    
    private ArrayList<Broker> brokerLog = new ArrayList<>();
    /**returns the ArrayList Attribute
     * 
     * @return ArrayList Attribute
     */
    public ArrayList<Broker> getBrokerLog()
    {
   
        return brokerLog;
    }
    /**addBroker- use compareTo() to run a search in order to find the proper 
     * place according to broker licenseNumer to add the new Broker object 
     * return true if successful 
     * //boolean add(int indes, E element)
     * 
     * @param brokerObj
     * @return boolean
     */
    public boolean addBroker(Broker brokerObj)
    {
        boolean wasSuccessful = false;
        String brokerLicense = brokerObj.getBrokerLicense();
        if (brokerLog.isEmpty())
        {
            brokerLog.add(brokerObj);
            wasSuccessful = true;
        }
        else
        {
            for ( int count = 0; count <= brokerLog.size(); count++ )
            { 
                String brokerObjTestString = 
                        brokerLog.get(count).getBrokerLicense();
                if(brokerLicense.compareTo(brokerObjTestString) < 0)
                {
                    brokerLog.add(count, brokerObj);  
                    count = brokerLog.size() + 1;
                    wasSuccessful = true;
                }
                if(brokerLicense.compareTo(brokerObjTestString) > 0)
                {
                    count++;
                }               
            }     
        }         
     return wasSuccessful;   
    }
    /**removeBroker- remove broker from a Class and return true is successful
     *once again use a binary search in order to find value 
     *  
     * //if statement for int indexOf(object O) for true or false??
     * 
     * @param license
     * @return boolean
     */
    public boolean removeBroker(String license)
    {
        //instantiating array list and declaring variable to be used in method
        boolean wasSuccessful = false;
        int idxRemove = 1;
        int lowIndex = 0 ;
        int mid;
        int highIndex = (brokerLog.size() - 1);
        
        String middleIndexValue;
        
        while (lowIndex <= highIndex)
        {
            mid = ((lowIndex+(highIndex - lowIndex))/2);
            String testBrokerLicense = 
                    brokerLog.get(mid).getBrokerLicense();
            if(license.compareToIgnoreCase(testBrokerLicense) == 0)       
            {
                brokerLog.remove(mid);
                wasSuccessful = true;
                lowIndex = highIndex + 1;   
            }
            if(license.compareToIgnoreCase(testBrokerLicense)> 0)
            {
                highIndex = mid - 1;
                
            }
            if(license.compareToIgnoreCase(testBrokerLicense) < 0)
            {
                lowIndex = mid + 1;
               
            }
            if(license.compareToIgnoreCase(testBrokerLicense) != 0 && 
                    lowIndex == highIndex)
            {
                lowIndex = highIndex + 1;
                wasSuccessful = false;
            }
           
        }
        return wasSuccessful;
    }
    /**isLicenseUnique- Tests if brokerLicense exists in the log and return true
     * if the value is not found boolean
     *
     * @param license
     * @return boolean
     */
    public boolean isLicenseUnique(String license)
    {
        
            
        boolean isUnique = true;
            if (brokerLog.isEmpty())
        {
            isUnique = true;
        }else
        {
            int lowIndex = 0 ;
            int middleIndex;
            int highIndex = (brokerLog.size());
            String middleIndexValue;

            while (lowIndex <= highIndex && highIndex >= 1)
            {
                middleIndex = ((lowIndex + highIndex) / 2);
                String testBrokerLicense = 
                        brokerLog.get(middleIndex).getBrokerLicense();
                if(license.compareToIgnoreCase(testBrokerLicense)> 0)
                {
                    lowIndex = middleIndex + 1;
                
                }
                if(license.compareToIgnoreCase(testBrokerLicense) < 0)
                {
                    highIndex = middleIndex - 1;
               
                }
                if(license.compareToIgnoreCase(testBrokerLicense) == 0)
                    
                {
                    lowIndex = highIndex + 1;
                    isUnique = false;
                }
                if(license.compareToIgnoreCase(testBrokerLicense) != 0 && 
                        lowIndex == highIndex)
                {
                    lowIndex = highIndex + 1;
                    isUnique = true;
                }
    
            }
        }
        return isUnique;
    }
}
