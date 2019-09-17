/* Broker - creates object Broker, getters and setters for all values,
 * methods that allow for the checking of values Broker that are commmonly
 * missentered such as brokerLicense and dept.
 * class also overrides object methods eguals() and toString() 
 */
package cs310hesker;

import java.util.Objects;

public class Broker 
{
    
    /**
     * Broker Data Fields
     */
    private String brokerLicense;
    private String firstName;
    private String lastName;
    private String dept;
    private double commissionRate;

    /**Broker Constructor creating Broker Object from String[]
     * 
     * @param line from the main class
     */
    public Broker(String[] line)
    {
        this.brokerLicense = line[2];
        this.firstName = line[3];
        this.lastName = line[4];
        this.dept = line[5]; 
        this.commissionRate = Double.parseDouble(line[6]);
    }
    
    /**Broker constructor
     * 
     * @param brokerLicense
     * @param firstName
     * @param lastName
     * @param dept
     * @param commissionRate 
     */
    public Broker(String brokerLicense, String firstName,
            String lastName, String dept, double commissionRate) 
    {
        this.brokerLicense = brokerLicense;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dept = dept;
        this.commissionRate = commissionRate;
    }
    /**brokerLicense getter
     * 
     * @return brokerLicense
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
    /**firstName getter
     * 
     * @return firstName 
     */
    public String getFirstName() 
    {
        return firstName;
    }
    /**firstName setter
     * 
     * @param firstName 
     */
    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }
    /**lastName getter
     * 
     * @return lastName
     */
    public String getLastName()
    {
        return lastName;
    }
    /**lastName setter
     * 
     * @param lastName 
     */
    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    }
    /**dept getter
     * 
     * @return dept
     */
    public String getDept() 
    {
        return dept;
    }
    /**dept setter
     * 
     * @param dept 
     */
    public void setDept(String dept)
    {
        this.dept = dept;
    }
    /**commissionRate getter
     * 
     * @return commissionRate 
     */
    public double getCommissionRate() 
    {
        return commissionRate;
    }
    /**commissionRate setter
     * 
     * @param commissionRate 
     */
    public void setCommissionRate(double commissionRate)
    {
        this.commissionRate = commissionRate;
        
    }
    
    /**overrides objects equals method
     * 
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj)
    {
       if (obj == this) return true;
       if (obj == null) return false;
       if (this.getClass() == obj.getClass())
       {
            Broker other = (Broker) obj;
            return brokerLicense.equals(other.brokerLicense)&&
                    firstName.equals(other.firstName)&&
                    lastName.equals(other.lastName)&&
                    dept.equals(other.dept)&&
                    commissionRate == other.commissionRate;                    
       }else
       {
            return false;
       } 
    }
    /**overrides objects hashCode
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.brokerLicense);
        hash = 59 * hash + Objects.hashCode(this.firstName);
        hash = 59 * hash + Objects.hashCode(this.lastName);
        hash = 59 * hash + Objects.hashCode(this.dept);
        return hash;
    }
    
    /**Overrides the default toString method
     * 
     * @return String output for Broker Object
     */
    @Override
    public String toString()
    {
        return "\nBroker:" + "{brokerLicense:" + brokerLicense + "firstName="
                + firstName + "lastName: " + lastName +", dept: " + dept 
                +", commissionRate: " + commissionRate + "}";
    }
    
    /**Test the contents of the brokerLicence 3 characters followed by 5 digits.
     * 
     * @return boolean
     */
    public boolean isValidLicense()
    {
        return brokerLicense.matches("[a-zA-Z]{3}[0-9]{5}");
    }
    
    /**Test to see whether the dept is valid must be 7 characters long, contains
     * 3 digits a dash 3 digits, and first three digits must be 1,2,or3. 
     * 
     * @return 
     */
    public boolean isValidDept()
    {
        return dept.matches("[1-3]{3}[-][0-9]{3}");
    }   
     
}
