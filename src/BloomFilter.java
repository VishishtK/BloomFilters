import java.io.BufferedWriter;
import java.io.FileWriter;

public class BloomFilter {
    Integer[] bitmap;
    int numberOfBits;
    int numberOfHashes;
    HashFunctions hashFunctions;
    String outputFileName = "BloomFilter.txt";

    public BloomFilter(int numberOfBits, int numberOfHashes){
        this.numberOfBits = numberOfBits;
        this.hashFunctions = new HashFunctions(numberOfHashes, numberOfBits);
        this.numberOfHashes = numberOfHashes;
        bitmap = new Integer[numberOfBits];
    }

    public void encode(int element){
        for(int i=0;i<numberOfHashes;i++){
            bitmap[hashFunctions.Hash(element, i)] = 1;
        }
    }

    public boolean lookUp(int element){
        for(int i=0;i<numberOfHashes;i++){
            if((bitmap[hashFunctions.Hash(element, i)] == null) || bitmap[hashFunctions.Hash(element, i)] < 1){
                return false;
            }
        }
        return true;
    }

    public void ConsumeElements(int[] set){
        for (int i = 0; i < set.length; i++){
            encode(set[i]);
        }
    }

    public void Output(String outputFileName, int[] randomSetA, int[] randomSetB) {
        String output = "";
        output += "1) ";
        int elementsFoundOfSetA = 0;
        for(int i=0;i<randomSetA.length;i++){
            if(lookUp(randomSetA[i])){
                elementsFoundOfSetA++;
            }
        }
        output += "Number of elements of set A found in filter: " + elementsFoundOfSetA + "\n";

        output += "2) ";
        int elementsFoundOfSetB = 0;
        for(int i=0;i<randomSetB.length;i++){
            if(lookUp(randomSetB[i])){
                elementsFoundOfSetB++;
            }
        }
        output += "Number of elements of set B found in filter: " + elementsFoundOfSetB + "\n";

        try{
            System.out.println(output);
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
            writer.write(output);
            writer.close();
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

}
