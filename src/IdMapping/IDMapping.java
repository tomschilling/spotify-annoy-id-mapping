package IdMapping;

import javax.script.ScriptException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IDMapping {

    public static void main(String[] args) throws ScriptException, IOException {

        String path = new File("data").getAbsolutePath();

        // parameter for nearest neighbor search
        String indexpath= path + "/annoy.angular.glove";
        Integer dimensions=300; //dimentions of vectors in index
        String metric = "angular"; //distance for building the index (eg. angular or euclidean)
        Integer entityId=13716; //parkinson
        Integer k=100; //search_k nodes
        Integer n=10; //closest items to return
        String entitiesfile = path + "/glove300dEntities.csv"; //csv file where the entities are stored

        BufferedReader reader = new BufferedReader(new FileReader(entitiesfile));
        String line = "";
        List<String> entities = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            entities.add(line);
        }

        String cmd = new File("src/idmapping/").getAbsolutePath();
        String py="getNearest.py";

        // start calculate time
        long startTime = System.currentTimeMillis();
        ProcessBuilder pb = new ProcessBuilder("python", py, indexpath, String.valueOf(entityId), String.valueOf(k) , String.valueOf(n), String.valueOf(dimensions), metric);
        pb.directory(new File(cmd));
        pb.redirectError();
        Process p = pb.start();

        InputStream is = null;
        String s = "";
        try {
            is = p.getInputStream();
            int in = -1;
            while ((in = is.read()) != -1) {
                s = s + String.valueOf((char)in);
            }
        } finally {
            try {
                is.close();
            } catch (Exception e) {
            }
        }

        s = s.substring(1, s.length()-2);
        int[] result = Arrays.stream(s.split(", ")).mapToInt(Integer::parseInt).toArray();
        long endTime = System.currentTimeMillis();
        // end calculate time
        long duration = (endTime - startTime);
        System.out.println("Execution time: " + String.valueOf(duration) + "ms");
        for (Integer a : result) {
            System.out.println(entities.get(a -1)); //TODO: -1 is a bug fix, actually it should work without
        }
    }
}
