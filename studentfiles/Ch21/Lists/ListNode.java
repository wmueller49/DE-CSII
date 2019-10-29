// Represents a node of a singly-linked list.

public class ListNode
{
   private Object value;
   private ListNode next;

   public ListNode(Object v)
   {
      value = v;
      next = null;
   }

   public ListNode(Object v, ListNode nx)
   {
      value = v;
      next = nx;
   }

   public Object getValue() { 
      return value; }
   public ListNode getNext() { 
      return next; }
      
   public String toString(){
      String result = "";
      for(ListNode node = this; node != null; node = node.getNext()){
         result += node.getValue().toString();
         result += " ";
      }
      return result;
   }
   
   public void setValue(Object v) { value = v; }
   public void setNext(ListNode nx) { next = nx; }
  
   public ListNode reverseList(ListNode head){
      ListNode newHead = null;
   
      for(ListNode node = head; node != null; node = node.getNext()){
         ListNode newNode = new ListNode(node.getValue(), newHead);
         newHead = newNode;
      }
   
      return newHead;
   }
   
   private static ListNode getRandomList(){
      Integer n;
      int numNodes = (int) (Math.random() * 10) + 11;
      ListNode myList = null;
      
      for(int i = 0; i < numNodes; i++){
         n = new Integer((int) (Math.random() * 200) - 100);
         ListNode node = new ListNode(n, myList);  
         myList = node;
      }
      
      return myList;
   }
  
   public ListNode middleNode(){
      ListNode toMiddle = this, toEnd = this;
   
      while(toEnd != null && toEnd.getNext() != null){
         toMiddle = toMiddle.getNext();
         toEnd = toEnd.getNext().getNext();
      }
   
      return toMiddle;
   }
   
   public ListNode moveToBack(ListNode head, String pattern){
      
   
   }
   
   public static void main(String[] args){
      ListNode rand = getRandomList();
      System.out.println(rand.toString());
      System.out.println(rand.middleNode());
   
   
   }
   
}


