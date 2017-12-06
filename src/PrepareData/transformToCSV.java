package PrepareData;

import java.io.*;


public class transformToCSV {

    public static void main(String[] args) throws IOException {

        String path = new File("data").getAbsolutePath();

        FileWriter writer = new FileWriter(path + "/glove300d.csv");
        FileWriter counter = new FileWriter(path + "/RecordCounter.txt");


        String file = path + "/glove.6B.300d.txt";

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
