/**
 * @author William Mueller
 * DE CSII
 * TicTacToeHashCode
 * 3/16/2020
 */

import java.lang.reflect.Field;

public class NewString {

	//field holds a string
   private String s;
   
   /**
    * constructor instantiates field s 
    * @param str value for s
    */
   public NewString(String str) {
      s = str;
   }

   @Override 
   /**
    * overrides java's hashcode method
    * @return an int index for the hashcode
    */
   public int hashCode() {
	   	int primeNum = 17;
		int result = 1;
		
		for(int i = 0; i < 9; i++) {
			result = i * result + ((int)s.charAt(i));
		}
		result *= primeNum;
		
		return result;
   }
   
   /**
    * sets the string to str
    * @param str the string to be set
    */
   public void setString(String str) {
      s = str;
   }
   
   /**
    * gets the value of the field
    * @return the value of the field s
    */
   public String getString() {
      return s;
   }
   
   /**
    * returns the value of the object
    * @return the value of the object
    */
   public String toString() {
      return s;
   }
   
   @Override
   /**
    * compares this to an object
    * @param other the object for comparison
    * @return true if this equals other, false otherwise
    */
   public boolean equals(Object other) {
      if (other instanceof NewString) 
         return getString().equals(((NewString) other).getString());
      else
         return false;
   }
}