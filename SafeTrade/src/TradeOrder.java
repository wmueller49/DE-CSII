
public class TradeOrder{
   
   private Trader trader;
   private String symbol;
   private boolean isBuyOrder;
   private boolean isMarketOrder;
   private int numShares;
   private double price;

   public TradeOrder(Trader t, String s, boolean buyOrder, boolean marketOrder, int nShares, double p){
      trader = t;
      symbol = s;
      isBuyOrder = buyOrder;
      isMarketOrder = marketOrder;
      numShares = nShares;
      price = p;
   }
   
   public TradeOrder(TradeOrder t){
      trader = t.getTrader();
      symbol = t.getSymbol();
      isBuyOrder = t.isBuy();
      isMarketOrder = t.isMarket();
      numShares = t.getShares();
      price = t.getPrice();
   }

   public double getPrice(){
      return price;
   }
   
   public int getShares(){
      return numShares;
   }
   
   public String getSymbol(){
      return symbol;
   }
   
   public Trader getTrader(){
      return trader;
   }
   
   public boolean isBuy(){
      return isBuyOrder;
   }
   
   public boolean isSell(){
      return !(isBuyOrder);
   }
   
   public boolean isMarket(){
      return isMarketOrder;
   }
   
   public boolean isLimit(){
      return !(isMarketOrder);
   }
   
   public void subtractShares(int s){
      numShares -= s;
   }

}