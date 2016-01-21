import edu.duke.*;
import java.lang.*;
import java.util.*;
/**
 * Write a description of CodonParser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CodonParser {
    
    private HashMap<String, Integer> map;
    
    
    public CodonParser(){
        map = new HashMap<String, Integer>();
}
    
    public void buildCodonMap(int start, String dna){
        map.clear();
        ArrayList<String> codons = new ArrayList<String>();
        int currentPosition = start;
        while (currentPosition < dna.length()-2){
            String t = dna.substring(currentPosition, currentPosition+3);
            codons.add(t);
            currentPosition = currentPosition+3;
        }
        for (String c : codons){
            if (!map.containsKey(c)){
                map.put(c,1);
            }
            else {
                map.put(c,map.get(c)+1);
            }
            
        }
        System.out.println("Reading frame starting with " + start + " results in " + map.size() +" unique codons");
}

    public String getMostCommonCodon(){
        int count = 0;
        String mostCommonCodon = null;
        for (String c : map.keySet()){
            if (map.get(c) > count){
                count = map.get(c);
                mostCommonCodon = c;
            }
        }
        System.out.println("and most common codon is "+mostCommonCodon + " with count " + map.get(mostCommonCodon));
        return mostCommonCodon;
}
    public void printCodonCounts(int start, int end){
        System.out.println("Counts of codons between "+start+ " and " + end + " inclusive are: ");
        for (String c : map.keySet()){
            if (map.get(c) >= start && map.get(c) <= end){
                System.out.println(c + "\t" + map.get(c));
            }
        }
    
    }
    
    public void tester(){
        FileResource resource = new FileResource();
        String dna = resource.asString();
        dna = dna.toUpperCase();
        for (int k=0; k < 3; k++){
            buildCodonMap(k,dna); //builds a HashMap of codons + prints the total number of unique codons
            getMostCommonCodon(); // in the reading frame
            printCodonCounts(1,5);
            
        }
    }
}