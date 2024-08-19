public class Purchase implements Comparable<Purchase> {
    
    String id;
    int count;
    
    public Purchase(String id){
        this.id=id;
    }
    
    public int compareTo(Purchase P) {
        return(id.compareTo(P.id));
    }
    
    public void add(int num) {
        count+=num;
    }
    
}
