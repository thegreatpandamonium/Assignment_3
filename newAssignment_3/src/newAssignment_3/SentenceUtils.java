package newAssignment_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Histogram{
    
    private HashMap<String, Integer> mHistogram;  
    
    public Histogram(){
        this.mHistogram = new HashMap<>();
    }
    
    public void generateHistogram(ArrayList<String> letterGroups) {
        mHistogram.clear();
        //For each loop to iterate over Array list of Letter Groups
        for (String group : letterGroups) {
        	//Insert letter group key, if letter group doesn't exist return 0
        	//then add 1 if it does exist add 1 to existing count
            mHistogram.put(group, mHistogram.getOrDefault(group, 0) + 1);
        }
    }
    
    public void printHistogram() {
    	//Loop through each letter group entry in the mHistogram map	
        for (Map.Entry<String, Integer> entry : mHistogram.entrySet()) {
            //Get letter group name from current entry
        	String group = entry.getKey();
        	//Get letter group count from current entry
            int count = entry.getValue();
            System.out.print(group + ": ");
            for (int i = 0; i < count; i++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

public class SentenceUtils {
	 private ArrayList<String> mLetterGroups;
	 private int mLetterGroupLen;
	    
	public SentenceUtils(int letterGroupLen){
		this.mLetterGroups = new ArrayList<>();
	    this.mLetterGroupLen = letterGroupLen;
	    }
	public void addWords(String line){
		String[] tokens = this.getTokens(line);
		this.splitTokensToLetterGroups(tokens);
	    }
	public ArrayList<String> getLetterGroups(){
		return (this.mLetterGroups);
	    }   
	private String[] getTokens(String line) {
        return line.split(" ");
    }
	
	private void splitTokensToLetterGroups(String[] tokens) {
        for (String token : tokens) {
            for (int j = 0; j < token.length(); j++) {
                if (j + mLetterGroupLen <= token.length()) {
                    mLetterGroups.add(token.substring(j, j + mLetterGroupLen));
                }
            }
        }
    }

	public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the file path: ");
        String filePath = in.nextLine();

        Scanner scan;
        File file = new File(filePath);
        scan = new Scanner(file);
            
        if(file.exists()) {
        	Scanner input = new Scanner(System.in);
	        System.out.println("Enter the Length of Letter Group: ");
	        int letterGroup = input.nextInt();
	
	        SentenceUtils sentenceUtils = new SentenceUtils(letterGroup);
	        ArrayList<String> letterGroups = new ArrayList<>();
	
	        while (scan.hasNextLine()) {
	        	String line = scan.nextLine();
	            letterGroups.add(line);
	            }
	
	        for (String group : letterGroups) {
	        	sentenceUtils.addWords(group);
	            }
	        
	        letterGroups = sentenceUtils.getLetterGroups();
	        Histogram letterGroupCount = new Histogram();
	        letterGroupCount.generateHistogram(letterGroups);
	        letterGroupCount.printHistogram();    
	
	        } 
            else  {
            	System.out.println(file + "does not exist!" );
            }
    }
}
