import java.util.Random;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) throws Exception {
        int NumberOfElements = 1000;
        int NumberOfBits = 1000;
        int NumberOfHashes = 3;

        int[]  randomSetA = IntStream.generate(() -> new Random().nextInt()).limit(NumberOfElements).map(flowId -> Math.abs(flowId)).toArray();
        int[]  randomSetB = IntStream.generate(() -> new Random().nextInt()).limit(NumberOfElements).map(flowId -> Math.abs(flowId)).toArray();

        System.out.println("Testing BloomFilter");
        BloomFilter multiHashTable = new BloomFilter(NumberOfBits, NumberOfHashes);
        multiHashTable.ConsumeElements(randomSetA);
        multiHashTable.Output("BloomFilter.txt",randomSetA,randomSetB);

    }
}
