/**
 * @author William Mueller
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class ExpressionTester {
	
	private final static String outputPath = "outputExpressions.txt";
	private final static File outputFile = new File(outputPath);
	private static PrintWriter output = null;

	public ExpressionTester() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		
		try {
			output = new PrintWriter(outputFile);
		}
		catch(FileNotFoundException x) {
			
		}
		
		Scanner kb = new Scanner(System.in);
		String inputPath;
		if(args.length > 0) {
			inputPath = args[0];
		}
		else {
			inputPath = "postFixExpressions.txt";
		}
		
		File inputFile = new File(inputPath);
		Scanner inputScanner = null;
		
		try {
			inputScanner = new Scanner(inputFile);
		}
		catch(FileNotFoundException ex){
			System.out.print("Enter the input filename: ");
			inputPath = kb.next();
			inputFile = new File(inputPath);
			
			try {
				inputScanner = new Scanner(inputFile);
			}
			catch(FileNotFoundException xception) {
				output.println("Invalid input");
				output.close();
				System.exit(1);
			}
		}
		
		while(inputScanner.hasNextLine()) {
			String line = inputScanner.nextLine();
			String[] vals = line.split(" ");
			
			ExpressionTree eTree = ExpressionTree.buildTree(vals);
			output.println("evalTree: " + eTree.evalTree());
			output.println("Prefix: " + eTree.toPrefixNotation());
			output.println("Infix: " + eTree.toInfixNotation());
			output.println("Postfix: " + eTree.toPostfixNotation());
			output.println("postFixEval: " + eTree.postfixEval(vals));
			
			output.println();
			output.println();
		}
		
		output.close();
	}

}
