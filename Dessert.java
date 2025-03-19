public abstract class Dessert
{
    protected Customer cust;
    protected CakeOrder cake;
    protected CookieOrder cookie;
    protected String type;
    protected String flavor;
    protected int quantity;
    
    public Dessert()
    {
        cust = new Customer();
        type="";
        flavor = "";
        quantity = 0;
    }
    
    public Dessert(Customer cust,String type, String flavor, int quantity)
    {
        this.cust = cust;
        this.type=type;
        this.flavor = flavor;
        this.quantity = quantity;
    }

    public void setCustName(String custName){cust.setCustName(custName);}
    public void setCustPhone(String custPhone){cust.setCustPhone(custPhone);}
    public void setCandle(int candle){cake.setCandle(candle);}
    public void setLettering(String lettering){cake.setLettering(lettering);}
    public void setAddOn(char addOn){cookie.setAddOn(addOn);}
    public void setAddType(String addType){cookie.setAddType(addType);}
    public void setType(String type) {this.type=type;}
    public void setFlavor(String flavor) {this.flavor = flavor;}
    public void setQty(int quantity) {this.quantity = quantity;}
    
    public Customer getCustomer(){return cust;}
    public String getCustName() {return cust.getCustName();}
    public String getCustPhone() {return cust.getCustPhone();}
    public int getCandle() {return cake.getCandle();}
    public String getLettering() {return cake.getLettering();}
    public char getAddOn(){return cookie.getAddOn();}
    public String getAddType(){return cookie.getAddType();}
    public String getType() {return type;}
    public String getFlavor() {return flavor;}
    public int getQty() {return quantity;}
    
    public abstract double calcTotal();
    
    public String toString()
    {
        return("\nCustomer details   "+cust+         
               "\nType             : "+type+        
               "\nFlavor           : "+flavor+       
               "\nQuantity         : "+quantity);
    }
}
