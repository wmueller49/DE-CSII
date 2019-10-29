import java.util.*;

public class Stock{

   public static java.text.DecimalFormat money;
   private String symbol;
   private String name;
   private double price;
   private double lowPrice;
   private double highPrice;
   
   private PriorityQueue<TradeOrder> sellOrders;
   private PriorityQueue<TradeOrder> buyOrders;
   
   public Stock(String s, String n, double p){
      symbol = s;
      name = n;
      price = p;
   }

   public String getQuote(){
      return "Hello";
   }
   
   public void placeOrder(TradeOrder order){
      if(order.isBuy()){
         buyOrders.add(order);
         order.getTrader().receiveMessage("New order:  Buy " + order.getSymbol() + "\n" + order.getShares() + " shares at " + order.getPrice());
      }
      else{
         sellOrders.add(order);
         order.getTrader().receiveMessage("New order:  Sell " + order.getSymbol() + "\n" + order.getShares() + " shares at market");
      }
      
   }


}