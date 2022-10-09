import java.io.BufferedWriter;
import java.io.FileWriter;

public class CodedBloomFilter{
    int numberOfFilters;
    int numberOfBits;
    int numberOfHashes;
    BloomFilter[] bloomFilters;
    String outputFileName = "CodedBloomFilter.txt";

    public CodedBloomFilter(int numberOfFilters, int numberOfBits, int numberOfHashes){
        this.numberOfBits = numberOfBits;
        this.numberOfHashes = numberOfHashes;
        this.numberOfFilters = numberOfFilters;
        this.bloomFilters = new BloomFilter[numberOfFilters];

        for(int i=0;i<numberOfFilters;i++){
            bloomFilters[i] = new BloomFilter(numberOfBits, numberOfHashes);
        }
    }

    void encode(int set, int element){
        for(int i=0;i<numberOfFilters;i++){
            if((set & (1<<i)) !=0 ){
                bloomFilters[i].encode(element);
            }
        }
    }

    int lookup(int element){
        int code = 0;
        for(int i=0;i<numberOfFilters;i++){
            if(bloomFilters[i].lookUp(element)){
                code += Math.pow(2, i);
            }
        }
        return code;
    }

    public void ConsumeElements(int[][] sets){
        for (int i = 0; i < sets.length; i++){
            for(int j = 0 ;j< sets[i].length; j++){
                encode(i, sets[i][j]);
            }
        }
    }

    public void Output(int[][] sets){
        int correctResults = 0;

        for(int i=0;i<sets.length;i++){
            for(int j=0;j<sets[i].length;j++){
                if(i==lookup(sets[i][j])){
                    correctResults++;
                }
            }
        }

        String output = "Correct Lookups: " + correctResults;
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