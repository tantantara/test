public class Customer
{
    private String custName;
    private String custPhone;

    public Customer()
    {
        custName = " ";
        custPhone = " ";
    }
    
    public Customer(String custName, String custPhone)
    {
        this.custName = custName;
        this.custPhone = custPhone;
    }
    
    public void setCustName(String custName) {this.custName = custName;}
    public void setCustPhone(String custPhone) {this.custPhone = custPhone;}
    
    public String getCustName() {return custName;}
    public String getCustPhone() {return custPhone;}
    
    public String toString()
    {
        return("\nCustomer Name    : "+custName+
               "\nCustomer Number  : "+custPhone);
    }
}
