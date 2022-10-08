public class CountingBloomFilter extends BloomFilter{

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
        System.out.println("Number of elements of set A found in filter: " + elementsFoundOfSetA);
    }
    
}
