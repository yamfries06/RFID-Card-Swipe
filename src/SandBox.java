import java.util.ArrayList;

public class SandBox {
    
    public static void main(String[] args) {
      ArrayList<String> words = FileHelper.readFile("words.txt");
      for(String s: words)
          System.out.println(s);
      
      
      ArrayList<String> stuff = new ArrayList<String>();
      stuff.add("appple");  
      stuff.add("ball");
      stuff.add("car");
      FileHelper.writeFile("test.txt", words);
      
      Product p=new Product("ding dongs", "12a34f5", 2.5);
      System.out.println(p.getFormattedPrice());
    }
}
