
import java.lang.Comparable;
import java.util.*;

public class Trader implements java.lang.Comparable<Trader>{

   private LinkedList<String> mailbox = new LinkedList<String>();
   private Brokerage brokerage;
   private String name;
   private String password;
   private TraderWindow myWindow;

   public Trader(Brokerage b, String n, String p){
      brokerage = b;
      name = n;
      password = p;
   }
   
   public int compareTo(Trader other){
      return getName().compareTo(other.getName());
   }

   public String getName(){
      return name;
   }
   
   public String getPassword(){
      return password;
   }

   public void getQuote(String s){
      brokerage.getQuote(s, this);
   }
   
   public void openWindow(){
      myWindow = new TraderWindow(this);
      if(hasMessages()){
         for(String s: mailbox){
            myWindow.showMessage(s);
         }
      }
   }
   
   //possibly a bad method, not sure
   public void showMessage(String s){
      myWindow.showMessage(s);
   }
   
   public boolean hasMessages(){
      return !(mailbox.isEmpty());
   }
   
   public void receiveMessage(String msg){
        mailbox.add(msg);
   }
   
   public void placeOrder(TradeOrder order){
      brokerage.placeOrder(order);
   }
   
   public void quit(){
      brokerage.logout(this);
   }

}