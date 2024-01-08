package com.gson.javajdk.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileTest {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/～/final.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s = null;
        List<String> list = new ArrayList<>();
        while ((s = br.readLine()) != null){
            if (!list.contains(s)){
                list.add(s);
            }
        }
        FileWriter fileWriter = new FileWriter(new File("output.txt"));
        int batch = 500;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(",");
            if ((i+1)%batch == 0 || (i+1) == list.size()){
                sb.deleteCharAt(sb.length()-1);
                fileWriter.write(sb.toString());
                fileWriter.write("\n");
                sb = new StringBuilder();
            }
        }
        fileWriter.close();
    }
}
