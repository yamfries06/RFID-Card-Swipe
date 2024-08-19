import java.util.ArrayList;

public class FileHelperRunner {
    
    public static void main(String[] args) {
        String currentDirectory = System.getProperty("user.dir");
        String filename="stuff.txt";
        String filePath = currentDirectory + "/" + filename;
        
        
        System.out.println(currentDirectory );
        System.out.println(filePath);
        
        ArrayList<String> lines = FileHelper.readFile(filePath, true);
       
        //print out our list...
        for(int k=0; k<lines.size(); k++)  {
            String temp = lines.get(k);
            System.out.println(temp);
        }
        
        ArrayList<String> things = new ArrayList<String>();
        things.add("truck");
        things.add("ball");
        things.add("car");
        
        FileHelper.listToFile(filePath, things);
        
    }
    
    
   
}