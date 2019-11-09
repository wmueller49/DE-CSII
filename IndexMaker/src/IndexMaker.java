/**
 * William Mueller
 * DE CSII
 * IndexMaker
 * 11/9/19
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class IndexMaker {

   private File inputFile;
   private File outputFile;
   private PrintWriter output;
   private Scanner input;
   private int line = 1;
   private DocumentIndex index;
	
   
   /*
    * creates an indexMaker with the input file i and output file o
    */
   public IndexMaker(String i, String o) {
      inputFile = new File(i);
      outputFile = new File(o);
   	
      try {
         output = new PrintWriter(outputFile);
      }
      catch(FileNotFoundException ex) {
         System.out.println("Unable to create output file");
         System.exit(1);
      }
      
      try {
         input = new Scanner(inputFile);
      }
      catch(FileNotFoundException ex) {
         output.println("Unable to Open File");
         output.close();
         System.exit(1);
      }
      
      index = new DocumentIndex();
   }
	
   /*
    * Creates an indexMaker, asks the user for the input and output file names
    */
   public IndexMaker() {
      Scanner kb = new Scanner(System.in);
      System.out.print("Please enter the input file: ");
      inputFile = new File(kb.next());
   	
      System.out.print("Please enter the output file: ");
      outputFile = new File(kb.next());
   	
      try {
         output = new PrintWriter(outputFile);
      }
      catch(FileNotFoundException ex) {
         System.out.println("Unable to create output file");
         System.exit(1);
      }
      
      try {
         input = new Scanner(inputFile);
      }
      catch(FileNotFoundException ex) {
         output.println("Unable to Open File");
         output.close();
         System.exit(1);
      }
      
      index = new DocumentIndex();
   }
	
   /*
    * Returns whether the file input has another line
    */
   public boolean hasNextLine(){
      return input.hasNextLine();
   }
   
   public static void main(String[] args) {
	  /*
	   * sets the inputFile and outputFile names to null, unless args has values
	   * if args has values, sets outputFile and inputFile names to those values
	   */
      String inputFileName = null;
      String outputFileName = null;
   
      if(args.length > 0){
         if(args.length >= 1){
            inputFileName = args[0];
         }
         if(args.length >= 2){
            outputFileName = args[1];
         }
      }
   
      IndexMaker i = null;
      
      if(inputFileName == null && outputFileName == null){
         i = new IndexMaker();
      }
      else if(inputFileName != null && outputFileName == null){
         if(inputFileName.contains(".")){
            String beginning = inputFileName.substring(0, inputFileName.indexOf(".")) + "index";
            outputFileName = beginning + inputFileName.substring(inputFileName.indexOf("."));
            i = new IndexMaker(inputFileName, outputFileName);
         }
         else{
            outputFileName = inputFileName + "index";
            i = new IndexMaker(inputFileName, outputFileName);
         }
      }
      else if(inputFileName != null && outputFileName != null){
         i = new IndexMaker(inputFileName, outputFileName);
      }
      
      while(i.hasNextLine()) {
         i.index.addAllWords(i.input.nextLine(), i.line);
         i.line++;
      }
      ArrayList<IndexEntry> entries = i.index.getIndex();
      for(IndexEntry entry : entries){
         i.output.println(entry);
      }
      i.output.close();
   }

}
