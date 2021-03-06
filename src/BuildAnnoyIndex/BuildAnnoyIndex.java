package BuildAnnoyIndex;

import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class BuildAnnoyIndex {

    public static void main(String[] args) throws ScriptException, IOException {

        String path = new File("data").getAbsolutePath();

        //Please set paremeter for building an AnnoyIndex
        String inputfile = path + "/glove300dVector.csv";
        String annoyIndex = path + "/annoy.angular.glove";
        Integer dimensions = 300; //dimentions of vectors in inputfile
        String metric = "angular"; //distance for building the index (eg. angular or euclidean)
        Integer trees = 100; //trees for building the index


        String cmd = new File("src/buildannoyindex/").getAbsolutePath();
        String py = "build.py";

        ProcessBuilder pb = new ProcessBuilder("python", py, inputfile, String.valueOf(dimensions), metric, String.valueOf(trees), annoyIndex);
        pb.directory(new File(cmd));
        pb.redirectError();
        Process p = pb.start();
        //pb.start();

        InputStream is = null;
        String s = "";
        try {
            is = p.getInputStream();
            int in = -1;
            while ((in = is.read()) != -1) {
                System.out.print((char)in);
            }
        } finally {
            try {
                is.close();
            } catch (Exception e) {
            }
        }

    }
}
