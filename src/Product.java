
import java.text.DecimalFormat;

public class Product {
    
    private String name;
    private String id;
    private double price;
    DecimalFormat df=new DecimalFormat("#.00");
    
    public Product(String name, String id, double price) {
        this.name=name;
        this.id=id;
        this.price=price;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String new_name) {
        name=new_name;
    }
    
    public String getId() {
        return id;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getFormattedPrice() {
        return "$"+df.format(price);
    }
    
    public void setPrice(double new_price) {
        price=new_price;
    }
    
    public String toString() {
        return( name + "@ " + getFormattedPrice() );
    }
}

