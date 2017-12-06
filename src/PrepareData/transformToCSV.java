package PrepareData;

import java.io.*;


public class transformToCSV {

    public static void main(String[] args) throws IOException {

        String path = new File("/data/").getAbsolutePath();

        FileWriter writer = new FileWriter(path + "EntitiesAndEmbeddings.csv");
        FileWriter counter = new FileWriter(path + "RecordCounter.txt");


        String file = "/Volumes/My Book/wikiner/Word2VecEncoder_en_150_Wiki+minlc_20170309.txt";

            Integer i = 0;
            String line = "";
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {

                //while (i < 10) {
                while ((line = br.readLine()) != null) {

                //line = br.readLine();
                String arr[] = line.split(" ", 2);

                arr[1] = arr[1].replaceAll("([^ ]+) ", "$1, ");

                writer.write(arr[0] + "; " + arr[1]);
                writer.write(System.lineSeparator());

                i++;

                }
            } catch (IOException e) {
            e.printStackTrace();
            }

            writer.close();

            counter.write(String.valueOf(i));
            counter.close();
        }

}
