public class MoreOpen {
  int r;
  int s;
  
  Card (int rank, int suit) {
    r = rank;
    s = suit;
  }
  { // This is the extra open brace
  public int getRank() {
    return r;
  }
  
  public int getSuit() {
    return s;
  }
  
  public String toString() {
    return r + " of " + s;
  }
  
  }