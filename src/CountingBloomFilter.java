import java.io.BufferedWriter;
import java.io.FileWriter;

public class CountingBloomFilter extends BloomFilter{

    String outputFileName = "CountingBloomFilter.txt";

    public CountingBloomFilter(int numberOfBits, int numberOfHashes) {
        super(numberOfBits, numberOfHashes);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void encode(int element){
        for(int i=0;i<numberOfHashes;i++){
            if(bitmap[hashFunctions.Hash(element, i)]==null){
                bitmap[hashFunctions.Hash(element, i)] = 1;
            }else{
                bitmap[hashFunctions.Hash(element, i)]++;
            }
        }
    }

    void remove(int element){
        for(int i=0;i<numberOfHashes;i++){
            bitmap[hashFunctions.Hash(element, i)]--;
        }
    }

    public void Remove(int[] set){
        for (int i = 0; i < set.length; i++){
            remove(set[i]);
        }
    }

    public void Output(String outputFileName, int[] randomSetA) {
        int elementsFoundOfSetA = 0;
        for(int i=0;i<randomSetA.length;i++){
            if(lookUp(randomSetA[i])){
                elementsFoundOfSetA++;
            }
        }
        String output = "Number of elements of set A found in filter: " + elementsFoundOfSetA;
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
