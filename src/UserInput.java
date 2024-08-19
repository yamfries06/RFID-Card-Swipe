import javax.swing.*;

public class UserInput{

    
    public UserInput(){
         //this space for rent...
    }
    
    public static int getInteger(){
         String s = JOptionPane.showInputDialog(null,"Enter integer");
         if(s ==null)
             return(-1);
         //null check
         int x = Integer.parseInt(s);
         return(x);
    }
    
    public static String getString(){
         String s = JOptionPane.showInputDialog(null,"Enter String");
         return (s);
    }
    
    public static double getDouble(){
         String s = JOptionPane.showInputDialog(null,"Enter double");
         double y = Double.valueOf(s);
         return(y);
    }

    public static int getInteger(String text){
         String s = JOptionPane.showInputDialog(null,text);
         if(s ==null)
             return(-1);
         //null check
         else if(s.isEmpty() == true)
             return(-2);
         int x = Integer.parseInt(s);
         return(x);
    }
    
    public static String getString(String text){
         String s = JOptionPane.showInputDialog(null,text);
         return (s);
    }
    
    public static double getDouble(String text){
         String s = JOptionPane.showInputDialog(null,text);
         double y = Double.valueOf(s);
         return(y);
    
    
    }
    
    
    public static int Confirm (String text) {
       
    
        
    int a = JOptionPane.showConfirmDialog(null,text,null,0);
    int ys = a;
    return (ys);
       
    
    }
//end of class

}