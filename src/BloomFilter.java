public class BloomFilter {
    int[] bitmap;
    int numberOfBits;
    HashFunctions hashFunctions;

    public BloomFilter(int numberOfBits, int numberOfHashes){
        this.numberOfBits = numberOfBits;
        this.hashFunctions = new HashFunctions(numberOfHashes, numberOfBits);
    }

    public void encode(){

    }

    public int lookUp(){
        return 0;
    }


}
