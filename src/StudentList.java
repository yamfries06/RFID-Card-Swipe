/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 809669
 */
public class StudentList {
    private String name;
    private String Id ;
    private int Grade; 

    public  StudentList(String Name, String Id, int Grade){
        name = Name;
        this.Id = Id;
        this.Grade = Grade;
    }


    public String  getId(){
        return(Id);
    }
    public String getName(){
        return(name);
    }
     public int getGrade(){
         return(Grade);
     }
    
}
