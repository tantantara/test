public class CookieOrder extends Dessert
{
    private char addOn;
    private String addType;
    
    public CookieOrder(Customer cust, String type, String flavor, int quantity, char addOn, String addType)
    {
        super(cust, type, flavor, quantity);
        this.addOn = addOn;
        this.addType=addType;
    }
    
    public void setCookieOrder()
    {
        this.flavor = flavor;
        this.quantity = quantity;
        this.addOn = addOn;
        this.addType=addType;
    }
    
    public char getAddOn() {return addOn;}
    public String getAddType() {return addType;}
    
    public void setAddOn(char addOn){this.addOn = addOn;}
    public void setAddType(String addType){this.addType = addType;}
    
    
    public double calcTotal()
    {
        double flavorPrice = 0.0;
        double addOnPrice=0.0;
        if(getFlavor().equalsIgnoreCase("chocolate") )
        {
            flavorPrice = 5.90;
        }
        else if(getFlavor().equalsIgnoreCase("oatmeal"))
        {
            flavorPrice = 6.90;
        }
        else if(getFlavor().equalsIgnoreCase("matcha"))
        {
            flavorPrice = 7.90;
        }
        else
        {
            flavorPrice=0.00;
        }
        if(getAddOn()=='Y' || getAddOn()=='y')
        {
            if(getAddType().equalsIgnoreCase("chocolate chip"))
            {
                addOnPrice=0.50;
            }
            else if (getAddType().equalsIgnoreCase("walnuts"))
            {
                addOnPrice=0.50;
            }
            else if(getAddType().equalsIgnoreCase("almonds"))
            {
                addOnPrice=0.50;
            }
        }
        else if(getAddOn()=='N' || getAddOn()=='n')
        {
            addOnPrice=0.0;
        }
        double totalPrice = flavorPrice*getQty()+(addOnPrice * getQty());
        return totalPrice;
    }
    
    public String toString()
    {
        return(super.toString()+
               "\nAdd on           : "+addOn+
               "\nTopping          : "+getAddType());
    }
}
