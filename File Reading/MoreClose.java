public class MoreClose {
  int r;
  int s;
  
  Card (int rank, int suit) {
    r = rank;
    s = suit;
    } // THIS IS THE EXTRA CLOSING BRACE
  }
  
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