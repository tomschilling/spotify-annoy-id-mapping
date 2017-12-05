package com.company;

import javax.script.ScriptException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IDMapping {

    private static final String DIR = "/Volumes/My Book/WikipediaDaten Word2vec/";

    public static void main(String[] args) throws ScriptException, IOException {


        // parameter for nearest neighbor search
        String indexpath="/Volumes/My Book/WikipediaDaten Word2vec/wiki.angular.annoy100";
        Integer entityId=794610; //794610 parkinson
        Integer n=10000;
        Integer k=10;


        BufferedReader reader = new BufferedReader(new FileReader(
                String.format("%s/WikiEmbeddingsEntity.csv", DIR)));

        String line = "";
        List<String> entities = new ArrayList<>(); //entities.get(a)
        while ((line = reader.readLine()) != null) {
            entities.add(line);
        }

        String cmd="/Users/Tom/IdeaProjects/utitlities/src/annoy_mapping/";
        String py="getNearest.py";

        // start calculate time
        long startTime = System.currentTimeMillis();
        ProcessBuilder pb = new ProcessBuilder("python", py, indexpath, String.valueOf(entityId), String.valueOf(n), String.valueOf(k));
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
            System.out.println(entities.get(a));
        }
    }
}
