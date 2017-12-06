package PrepareData;

import java.io.*;

public class seperateEntityAndVector {


    public static void main(String[] args) throws IOException {

        String importFile = new File("/data/EntitiesAndEmbeddings.csv").getAbsolutePath();

        String path = new File("/data/").getAbsolutePath();
        FileWriter vectorWriter = new FileWriter(path + "vector.csv");
        FileWriter entityWriter = new FileWriter(path + "entities.csv");


        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(importFile))) {

            while ((line = br.readLine()) != null) {

                String arr[] = line.split("; ", 2);

                entityWriter.write(arr[0]);
                entityWriter.write(System.lineSeparator());

                vectorWriter.write(arr[1]);
                vectorWriter.write(System.lineSeparator());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        entityWriter.close();
        vectorWriter.close();

    }
}
