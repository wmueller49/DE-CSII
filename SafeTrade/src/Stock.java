import java.util.PriorityQueue;
import java.text.DecimalFormat;

/**
 * Represents a stock in the SafeTrade project
 */
public class Stock
{
  public static DecimalFormat money = new DecimalFormat("0.00");

  private String stockSymbol;
  private String companyName;
  private double loPrice, hiPrice, lastPrice;
  private int volume;
  private PriorityQueue<TradeOrder> buyOrders, sellOrders;

  /**
   * Constructs a new stock with a given symbol, company name,
   * and starting price. Sets low price, high price,
   * and last price to the same opening price.
   * Sets "day" volume to zero.
   * Initializes a priority qieue for sell orders
   * to an empty <code>PriorityQueue</code> with a <code>PriceComparator</code>
   * configured for comparing orders in ascending order;
   * initializes a priority qieue for buy orders
   * to an empty <code>PriorityQueue</code> with a <code>PriceComparator</code>
   * configured for comparing orders in descending order.
   * @param symbol the stock symbol.
   * @param name full company name.
   * @param price opening price for this stock.
   */
  public Stock(String symbol, String name, double price)
  {
    stockSymbol = symbol;
    companyName = name;
    loPrice = price;
    hiPrice = price;
    lastPrice = price;
    volume = 0;

    buyOrders =  new PriorityQueue<TradeOrder>(1000, new PriceComparator(false));  // "max queue"
    sellOrders = new PriorityQueue<TradeOrder>(1000, new PriceComparator(true));   // "min queue"
  }

  /**
   * Returns a quote string for this stock. The quote includes:
   * the company name for this stock; the stock symbol; last sale price;
   * the lowest and highest day prices; the lowest price in
   * a sell order (or "market") and the number of shares in it
   * (or "none" if there are no sell orders); the highest
   * price in a buy order (or "market") and the number of shares in it
   * (or "none" if there are no buy orders).
   * For example:
   *<pre>
   * Giggle.com (GGGL)
   * Price: 10.00  hi: 10.00  lo: 10.00  vol: 0
   * Ask: 12.75 size: 300  Bid: 12.00 size: 500</pre>
   *
   * Or:
   *
   * Giggle.com (GGGL)
   * Price: 12.00  hi: 14.50  lo: 9.00  vol: 500
   * Ask: none  Bid: 12.50 size: 200
   * @return the quote for this stock.
   */
  public String getQuote()
  {
    String quote = companyName + " (" + stockSymbol + ")\n";
    quote += "Price: " + money.format(lastPrice) + "  hi: " + money.format(hiPrice)
                   + "  lo: " + money.format(loPrice) + "  vol: " + volume + "\n";

    quote += "Ask: ";

    if (!sellOrders.isEmpty())
    {
      TradeOrder order = sellOrders.peek();
      if (order.isLimit())
        quote += money.format(order.getPrice());
      else
        quote += "market";
      quote += " size: " + order.getShares();
    }
    else
    {
      quote += "none";
    }

    quote += "  Bid: ";

    if (!buyOrders.isEmpty())
    {
      TradeOrder order = buyOrders.peek();
      if (order.isLimit())
        quote += money.format(order.getPrice());
      else
        quote += "market";
      quote += " size: " + order.getShares();
    }
    else
    {
      quote += "none";
    }

    return quote;
  }

  /**
   * Places a trading order for this stock.
   * Adds the order to the appropriate priority queue depending
   * on whether this is a buy or sell order.
   * Notifies the trader
   * who placed the order that the order has been placed, by sending a
   * message to that trader. For example:
   * <pre>
   * New order:  Buy GGGL (Giggle.com)
   * 200 shares at $38.00</pre>
   * Or:
   * <pre>
   * New order:  Sell GGGL (Giggle.com)
   * 150 shares at market</pre>
   * Executes pending orders by calling
   * <code>executeOrders</code>.
   * @param order a trading order to be placed.
   */
  public void placeOrder(TradeOrder order)
  {
    String msg = "New order:  ";
    if (order.isSell())
      msg += "Sell ";
    else
      msg += "Buy ";

    msg += stockSymbol + " ";
    if (!"".equals(companyName))
      msg += " (" + companyName + ")";
    msg += "\n";

    msg += order.getShares() + " shares";

    if (order.isMarket())
      msg += " at market ";
    else
      msg += " at  " + money.format(order.getPrice());

    Trader trader = order.getTrader();
    trader.receiveMessage(msg);

    if (order.isSell())
      sellOrders.add(order);
    else
      buyOrders.add(order);

    executeOrders();
  }

  //********************** Private methods *****************

  /**
   * Executes as many pending orders as possible. <br>
   * 1. Examines the top sell order and the top buy order in
   *    the respective priority queues.<br>
   *    i) If both are limit orders and the asking price is less than
   *    or equal to the selling price, executes the order (or a part of it)
   *    at the sell order price.<br>
   *    ii) If one order is limit and the other is market, executes the
   *    order (or a part of it) at the limit order price<br>
   *    iii) If both orders are market, executes the order (or a part of it)
   *    at the last sale price.<br>
   * 2. Figures out how many shares can be traded, which
   *    is the smallest of the numbers of shares in the two orders.<br>
   * 3. Subtracts the traded number of shares from each order;
   *    Removes each of the orders with 0 remaining shares from the respective queue.<br>
   * 4. Updates the day's low price, high price, and volume.<br>
   * 5. Sends a message to each of the two traders involved in the transaction.
   *    For example:
   *    <pre>
   *    You bought: 150 GGGL at 38.00 amt 5700.00</pre>
   * 6. Repeats steps 1-5 for as long as possible, that is as long as
   *    there is any movement in the buy / sell order queues.
   *    (The process gets stuck when the top buy order and sell order
   *    are both limit orders and the ask price is higher than the bid
   *    price.)
   */
  protected void executeOrders()
  {
    while (!buyOrders.isEmpty() && !sellOrders.isEmpty())
    {
      TradeOrder sell = sellOrders.peek();
      TradeOrder buy = buyOrders.peek();
      if (sell.isLimit() && buy.isLimit() && sell.getPrice() > buy.getPrice())
        break;

      double price;

      if (sell.isMarket() && buy.isMarket())
        price = lastPrice;
      else if (sell.isMarket() && buy.isLimit())
        price = buy.getPrice();
      else if (sell.isLimit() && buy.isMarket())
        price = sell.getPrice();
      else // if (sell.isLimit() && buy.isLimit())
        price = sell.getPrice();

      int shares = Math.min(buy.getShares(), sell.getShares());

      sell.subtractShares(shares);
      buy.subtractShares(shares);

      String msg = shares + " " + stockSymbol + " at " + money.format(price)
        + " amt " + money.format(price * shares);
      buy.getTrader().receiveMessage("You bought: " + msg);
      sell.getTrader().receiveMessage("You sold: " + msg);

      if (buy.getShares() == 0)
        buyOrders.remove();

      if (sell.getShares() == 0)
        sellOrders.remove();

      volume += shares;
      if (price < loPrice)
        loPrice = price;
      if (price > hiPrice)
        hiPrice = price;
      lastPrice = price;
    }
  }
}
