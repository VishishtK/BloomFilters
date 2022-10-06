public class BloomFilter {
    Integer[] bitmap;
    int numberOfBits;
    int numberOfHashes;
    HashFunctions hashFunctions;

    public BloomFilter(int numberOfBits, int numberOfHashes){
        this.numberOfBits = numberOfBits;
        this.hashFunctions = new HashFunctions(numberOfHashes, numberOfBits);
        this.numberOfHashes = numberOfHashes;
        bitmap = new Integer[numberOfBits];
    }

    void encode(int element){
        for(int i=0;i<numberOfHashes;i++){
            bitmap[hashFunctions.Hash(element, i)] = 1;
        }
    }

    boolean lookUp(int element){
        for(int i=0;i<numberOfHashes;i++){
            if((bitmap[hashFunctions.Hash(element, i)] == null) || bitmap[hashFunctions.Hash(element, i)] != 1){
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
        System.out.println("A)");
        int elementsFoundOfSetA = 0;
        for(int i=0;i<randomSetA.length;i++){
            if(lookUp(randomSetA[i])){
                elementsFoundOfSetA++;
            }
        }
        System.out.println("Number of elements of set A found in filter: " + elementsFoundOfSetA);

        System.out.println("B)");
        int elementsFoundOfSetB = 0;
        for(int i=0;i<randomSetB.length;i++){
            if(lookUp(randomSetB[i])){
                elementsFoundOfSetB++;
            }
        }
        System.out.println("Number of elements of set B found in filter: " + elementsFoundOfSetB);
    }

}
