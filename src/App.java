import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) throws Exception {
        int NumberOfElements = 1000;
        int NumberOfBits = 1000;
        int NumberOfHashes = 3;

        int[] randomSetA = IntStream.generate(() -> new Random().nextInt()).limit(NumberOfElements).map(element -> Math.abs(element)).toArray();
        int[] randomSetB = IntStream.generate(() -> new Random().nextInt()).limit(NumberOfElements).map(element -> Math.abs(element)).toArray();

        System.out.println("Testing BloomFilter");
        BloomFilter bloomFilter = new BloomFilter(NumberOfBits, NumberOfHashes);
        bloomFilter.ConsumeElements(randomSetA);
        bloomFilter.Output("BloomFilter.txt",randomSetA,randomSetB);


        int NumberOfCounters = 10000;
        int NumberOfElementsToDelete = 500;
        int NumberOfElementsToAdd = 500;
        int[] setToAdd = IntStream.generate(() -> new Random().nextInt()).limit(NumberOfElementsToAdd).map(element -> Math.abs(element)).toArray();
        System.out.println("\nTesting CountingBloomFilter");
        CountingBloomFilter countingBloomFilter = new CountingBloomFilter(NumberOfCounters, NumberOfHashes);
        countingBloomFilter.ConsumeElements(randomSetA);
        countingBloomFilter.Remove(Arrays.stream(randomSetA).limit(NumberOfElementsToDelete).toArray());
        countingBloomFilter.ConsumeElements(setToAdd);
        countingBloomFilter.Output("CountingBloomFilter.txt", randomSetA);
    }
}
