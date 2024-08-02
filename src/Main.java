import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        generateRandomNumbers("Source.txt", 10000); // we created 10000 integer random
        generateRandomNumbers1("Search.txt", 10000);

        LinkedList<Integer> list = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("Source.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\s+");
                for (String value : values) {
                    Integer val = Integer.valueOf(value);
                    list.insertToEnd(val);
                }
            }
        } catch (IOException e) {
            System.out.println("File could not be read ...");
        }
        
        
        // Measure time for the original search method
        long startTimeOriginal = System.nanoTime();
        int avgMemoryAccesses = list.searchAll("Search.txt");
        long endTimeOriginal = System.nanoTime();
        long elapsedTimeOriginal = (endTimeOriginal - startTimeOriginal) / 1000000;
        
        System.out.println("Search 1 : ");
        System.out.println("Average Memory Accesses (Before Moving to Front): " + avgMemoryAccesses);
        System.out.println("Time taken by Original Search Method : " + elapsedTimeOriginal + " ms");
        System.out.println("Total Memory Accesses (Before Moving to Front): " + avgMemoryAccesses * list.count());
        System.out.println("List 1 : " + list.count());
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||| ");
        
        
        LinkedList<Integer> list2 = new LinkedList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("Source.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\s+");
                for (String value : values) {
                    Integer val = Integer.valueOf(value);
                    
                    list2.insertToEnd(val);
                }
            }
        } catch (IOException e) {
            System.out.println("File could not be read ...");
        }

        // Measure time for the modified search method
        long startTimeModified = System.nanoTime();
        try (BufferedReader br = new BufferedReader(new FileReader("Search.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Integer val = Integer.valueOf(line);
                list2.moveToFront(val);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int avgMemoryAccesses2 = list2.searchAll("Search.txt");
        long endTimeModified = System.nanoTime();
        long elapsedTimeModified = (endTimeModified - startTimeModified) / 1000000;
        
        System.out.println("Search 2 : ");
        System.out.println("Average Memory Accesses (After Moving to Front): " + avgMemoryAccesses2);
        System.out.println("Time taken by Modified Search Method: " + elapsedTimeModified + " ms");
        System.out.println("Total Memory Accesses (After Moving to Front): " + avgMemoryAccesses2 * list2.count());
        System.out.println("List 2 : " + list.count());
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||| ");
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time taken: " + totalTime + " milliseconds");

    }

    private static void generateRandomNumbers(String fileName, int count) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            Random random = new Random();

            for (int i = 0; i < count; i++) {
                int randomNumber;
                boolean isDuplicate;

                do {
                    isDuplicate = false;
                    randomNumber = random.nextInt(10000);

                    // Daha önce eklenen sayılarla kontrol et
                    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            int existingNumber = Integer.parseInt(line);
                            if (existingNumber == randomNumber) {
                                isDuplicate = true;
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } while (isDuplicate);

                bw.write(Integer.toString(randomNumber));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error while writing file");
        }
    }
        private static void generateRandomNumbers1(String fileName, int count) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            Random random = new Random();
            for (int i = 0; i < count; i++) {
                int randomNumber = random.nextInt(1000);
                bw.write(Integer.toString(randomNumber));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

// TOTAL 3700 - 3900 Milliseconds, ALL IN PROGRESS