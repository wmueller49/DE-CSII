import java.util.*;

public class Brokerage implements Login{
   
   private StockExchange exchange;
   private TreeMap<String, Trader> traderMap;
   private TreeSet<Trader> loggedIn;

   public Brokerage(StockExchange e){
      traderMap = new TreeMap<String, Trader>();
      loggedIn = new TreeSet<Trader>();
      exchange = e;
   }
   
   public int addUser(String n, String p){
      if(n.length() < 4 || n.length() > 10){
         return -1;
      }
      
      if(p.length() < 2 || p.length() > 10){
         return -2;
      }
      if(traderMap.containsKey(n)){
         return -3;
      }
      traderMap.put(n, new Trader(this, n, p));
      return 0;
   }
   
   public int login(String n, String p){
      if(!(traderMap.containsKey(n))){
         return -1;
      }
      if(!(traderMap.get(n).getPassword().equals(p))){
         return -2;
      }
      if(loggedIn.contains(traderMap.get(n))){
         return -3;
      }
      loggedIn.add(traderMap.get(n));
      traderMap.get(n).receiveMessage("Welcome to SafeTrade!");
      traderMap.get(n).openWindow();
      return 0;
   }
   
   public void logout(Trader trader){
      loggedIn.remove(trader);
   }
   
   public void getQuote(String s, Trader t){
      t.receiveMessage(exchange.getQuote(s));
      t.showMessage(exchange.getQuote(s)); //possibly bad code, not sure
   }
   
   public void placeOrder(TradeOrder order){
      exchange.placeOrder(order);
   }
   
}