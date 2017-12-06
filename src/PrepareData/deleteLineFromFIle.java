package PrepareData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class deleteLineFromFIle {

    public static void main(String[] arg) throws IOException {


        String inputfile = "/Volumes/My Book/wikiner/WikiEmbeddingsVector.csv";

        String line1 = "";
        try (BufferedReader br = new BufferedReader(new FileReader(inputfile))) {

            line1 = br.readLine();

            System.out.println(line1);


        } catch (IOException e) {
            e.printStackTrace();
        }

        //delete first line
        RandomAccessFile raf = new RandomAccessFile("/Volumes/My Book/wikiner/WikiEmbeddingsVector.csv", "rw");
        //Initial write position
        long writePosition = raf.getFilePointer();
        raf.readLine();
        // Shift the next lines upwards.
        long readPosition = raf.getFilePointer();

        byte[] buff = new byte[1024];
        int n;
        while (-1 != (n = raf.read(buff))) {
            raf.seek(writePosition);
            raf.write(buff, 0, n);
            readPosition += n;
            writePosition += n;
            raf.seek(readPosition);
        }
        raf.setLength(writePosition);
        raf.close();


        String line2 = "";
        try (BufferedReader br = new BufferedReader(new FileReader(inputfile))) {

            line2 = br.readLine();

            System.out.println(line2);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
