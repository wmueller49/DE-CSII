/*
 * William Mueller
 * DE CSII
 * 9/15/19
 * 
 * FileReading
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReading {
	private static String outputPath = "output.txt";
	private static File outputFile = new File(outputPath);
	private static PrintWriter output = null;
	private static String replacementWords;
	private static ArrayList<String> wordReplaceList = new ArrayList<String>();
	private static boolean hasReplaceList = false;
	

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		try {
			output = new PrintWriter(outputFile);
		}
		catch(FileNotFoundException ex) {
			
		}
		String path1 = "";
		String path2 = "";
		String path3 = "";
		String answer = "";
		String replaceFile = "";
		
		if(args.length > 0) {
			path1 = args[0];
			
			if(args.length > 1) {
				path2 = args[1];
			}
			if(args.length > 2) {
				path3 = args[2];
			}
			if(args.length > 3) {
				answer = args[3];
			}
			
			if(args.length > 4) {
				replaceFile = args[4];
			}
		}
		else if(args.length <= 0){
			System.out.print("Please enter a file name: ");
			path1 = kb.next();
			
			System.out.print("Please enter a second file name: ");
			path2 = kb.next();
			
			System.out.print("Please enter a story file name: ");
			path3 = kb.next();
			
			System.out.print("Do you have a file for replacement words? (y/n) ");
			answer = kb.next();
			
			if(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
				System.out.print("Please enter the file: ");
				replaceFile = kb.next();
			}
			
		}
		
		bracesBalanced(path1);
		
		identicalFiles(path1, path2);
		
		if(answer.equals("y") || answer.equals("Y")) {
			File replacementFile = new File(replaceFile);
			Scanner inputReplaceFile = null;
			
			try {
				inputReplaceFile = new Scanner(replacementFile);
			}
			catch(FileNotFoundException ex) {
				output.println("Part 3: Unable to Open File");
				output.close();
				System.exit(1);
			}
			
			while(inputReplaceFile.hasNextLine()) {
				wordReplaceList.add(inputReplaceFile.nextLine());
			}
		}
		
		shortStory(path3);
		
	}
	
	
	/*
	 * Takes in a String s which should be a file name
	 * If s is not a file, it writes "Part 1: Unable to Open File" in the output file
	 * Opens file, checks if the file has all braces balanced
	 * If so, an output file is added to with the text "Braces Balanced"
	 * If not, the output file is added with "Braces Not Balanced"
	 */
	public static void bracesBalanced(String f1) {
		int openBrace = 0;
		int closeBrace = 0;
		
		File file1 = new File(f1);
		Scanner input = null;
		
		try {
			input = new Scanner(file1);
		}
		catch(FileNotFoundException ex) {
			output.println("Part 1: Unable to Open File");
			output.close();
			System.exit(1);
		}
		
		while (input.hasNext()) {
			String nextString = input.next();
			if(nextString.equals("{")) {
				openBrace++;
			}
			else if(nextString.equals("}")) {
				closeBrace++;
				if(closeBrace == 1 && openBrace == 0) {
					output.println("Braces Not Balanced");
					output.println();
					return;
				}
				
			}
		}
		
		if(openBrace == closeBrace) {
			output.println("Braces Balanced");
		}
		else if(openBrace != closeBrace) {
			output.println("Braces Not Balanced");
		}
		output.println();
	}
	
	
	/*
	 * Takes in two Strings, f1 and f2, which should be files
	 * If f2 is not a file, it writes "Part 2: Unable to Open File" in the output file
	 * Opens the files and compares them
	 * If they are identical, it writes "Files Identical" in the output file
	 * If the are not, it writes "Files Not Identical" in the output file
	 */
	public static void identicalFiles(String f1, String f2) {
		String file1String = "";
		String file2String = "";
		
		File file1 = new File(f1);
		File file2 = new File(f2);
		Scanner inputFile1 = null;
		Scanner inputFile2 = null;
		
		try {
			inputFile1 = new Scanner(file1);
			inputFile2 = new Scanner(file2);
		}
		catch(FileNotFoundException ex) {
			output.println("Part 2: Unable to Open File");
			output.close();
			System.exit(1);
		}
		if(inputFile1.hasNext() && inputFile2.hasNext()) {
			file1String = inputFile1.nextLine();
			file2String = inputFile2.nextLine();
		}
	
		else {
			output.println("Files Not Identical");
			output.println();
			return;
		}
		while(file1String.equals(file2String) && inputFile1.hasNext() && inputFile2.hasNext()) {
			file1String = inputFile1.nextLine();
			file2String = inputFile2.nextLine();
		}
		if(inputFile1.hasNext() || inputFile2.hasNext()) {
			output.println("Files Not Identical");
			output.println();
		}
		else {
			output.println("Files Identical");
			output.println();
		}
	}
	
	/*
	 * Takes in String f3 which should be a file
	 * If f3 is not a file, it writes "Part 3: Unable to Open File" in the output file
	 * Finds all words with <> around them
	 * If the user has a file with replacement words, those are used
	 * If the replacement file is not a file, it writes "Part 3: Unable to Open File" in the output file
	 * If there is no replacement file, the user is prompted to enter replacement words
	 * 
	 * If the replacement file does not have enough replacement words, the user is prompted to enter 
	 * the rest of the replacement words
	 */
	public static void shortStory(String f3) {
		Scanner kb = new Scanner(System.in);
		File file3 = new File(f3);
		Scanner input = null;
		Scanner inputDuplicate = null;
		Scanner inputForSentences = null;
		Scanner inputForStory = null;
		
		try {
			input = new Scanner(file3);
			inputDuplicate = new Scanner(file3);
			inputForSentences = new Scanner(file3);
			inputForStory = new Scanner(file3);
		}
		catch(FileNotFoundException ex) {
			output.println("Part 3: Unable to Open File");
			output.close();
			System.exit(1);
		}
		
		StringBuffer str = new StringBuffer();
		while(inputForStory.hasNextLine()) {
			str.append(inputForStory.nextLine() + "\n");
		}
		
		String pattern = "<(.+?)>";
		int n = 0;
		int startIndex = 0;
		int stopIndex = 0;
		while(str.indexOf("<") != -1 && str.indexOf(">") != -1) {
			startIndex = str.indexOf("<");
			stopIndex = str.indexOf(">");
			if(wordReplaceList.size() > n) {
				if(str.indexOf("<", str.indexOf("<")) > str.indexOf(">"))
					stopIndex = str.indexOf(">", str.indexOf(">")+1);
				String toBeReplaced;
				toBeReplaced = str.substring(startIndex, stopIndex);
				if(toBeReplaced.contains("\n"))
					str.replace(startIndex, stopIndex+1, "\n" + wordReplaceList.get(n));
				else
					str.replace(startIndex, stopIndex+1, wordReplaceList.get(n));
				n++;
			}
			else if(n >= wordReplaceList.size()) {
				String toBeReplaced;
				if(str.indexOf("<", str.indexOf("<")) > str.indexOf(">"))
					stopIndex = str.indexOf(">", str.indexOf(">") + 1);
				toBeReplaced = str.substring(startIndex +1, stopIndex);
				System.out.println("Please enter a(n) " + toBeReplaced + ": ");
				String word = kb.next();
				if(toBeReplaced.contains("\n"))
					str.replace(startIndex, stopIndex+1, "\n" + word);
				else
					str.replace(startIndex, stopIndex+1, word);
				n++;
			}
		}
		
		output.println(str);

		inputDuplicate.close();
		inputForSentences.close();
		output.close();
	}
}
