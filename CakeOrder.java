public class CakeOrder extends Dessert
{
    private int candle;
    private String lettering;
    
    public CakeOrder( Customer cust, String type, String flavor, int quantity, int candle, String lettering)
    {
        super(cust, type, flavor, quantity);
        this.candle = candle;
        this.lettering = lettering;
    }
    
    public void setCakeOrder()
    {
        this.flavor = flavor;
        this.quantity = quantity;
        this.candle = candle;
        this.lettering = lettering;
    }
    
    public int getCandle() {return candle;}
    public String getLettering() {return lettering;}
    
    public void setCandle(int candle) {this.candle=candle;}
    public void setLettering(String lettering) {this.lettering=lettering;}
    
    public double calcTotal()
    {
        double price = 0.0;
        if(getFlavor().equalsIgnoreCase("red velvet") )
        {
            price = 55.00;
        }
        else if(getFlavor().equalsIgnoreCase("fresh cream"))
        {
            price = 70.00;
        }
        else if(getFlavor().equalsIgnoreCase("chocolate"))
        {
            price = 60.00;
        }
        else
        {
            price=0.00;
        }
        double totalPrice = price*getQty();
        return totalPrice;
    }
    
    public String toString()
    {
        return(super.toString()+
               "\nAmount of candle : "+candle+
               "\nLettering        : "+lettering);
    }
}
