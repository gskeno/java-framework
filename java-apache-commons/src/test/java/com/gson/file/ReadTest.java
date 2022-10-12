package com.gson.file;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.util.*;

public class ReadTest {
    private String readFilePath = System.getProperty("user.home") + "/downloaded_data.txt";
    @Test
    public void test() throws IOException {
        FileReader reader = new FileReader(readFilePath);
        List<String> lines = IOUtils.readLines(reader);
        IOUtils.closeQuietly(reader);

        List<String> rows = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            JSONObject jo = JSON.parseObject(line);
            String args = jo.getString("args");

            String row = "[" + args + "]";
            rows.add(row);
        }
        FileWriter fileWriter = new FileWriter(new File("out.txt"));
        IOUtils.writeLines(rows, null, fileWriter);
        IOUtils.closeQuietly(fileWriter);

    }

    private String localFile = "slow_membership_id.txt";
    @Test
    public void testReadCuDir() throws IOException {
        FileReader reader = new FileReader(localFile);
        List<String> lines = IOUtils.readLines(reader);
        HashSet<String> set = new HashSet<>(lines);
        Map<Integer,Integer> map = new HashMap<>();
        Long min = Long.MAX_VALUE;
        Long max = Long.MIN_VALUE;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            min = Math.min(min, Long.valueOf(line));
            max = Math.max(max, Long.valueOf(line));
            int idx = Math.abs(line.hashCode() % 1024) / 64;
            map.put(idx, map.getOrDefault(idx, 0) + 1);
        }
        System.out.println(lines.size());
        System.out.println(map);
        System.out.println(min);
        System.out.println(max);
        System.out.println("-------");
        map.clear();

        for(String row : set){
            int idx = Math.abs(row.hashCode() % 1024) / 64;
            map.put(idx, map.getOrDefault(idx, 0) + 1);
        }
        System.out.println(map);

    }

    @Test
    public void testHashCode() throws IOException {
        FileReader reader = new FileReader(localFile);
        List<String> lines = IOUtils.readLines(reader);
        Map<String,Integer> map = new HashMap<>();
        for(String line : lines){
            map.put(line, map.getOrDefault(line, 0) + 1);
        }
        PriorityQueue<Object[]> queue = new PriorityQueue<>(new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                return (int)o2[1] - (int)o1[1];
            }
        });
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            queue.offer(new Object[]{entry.getKey(), entry.getValue()});
        }
        System.out.println(Arrays.toString(queue.poll()));
        System.out.println(Arrays.toString(queue.poll()));
    }

    @Test
    public void testHash(){
        System.out.println(Math.abs("3542775664".hashCode() % 1024) / 64);
    }

    private String inFile = "in.txt";
    private String inFile1 =  System.getProperty("user.home") + "/工作/listPointParam_.txt";
    private String inFile2 =  System.getProperty("user.home") + "/工作/ultraman/summary.txt";
    private String outFile2 =  System.getProperty("user.home") + "/工作/ultraman/out_summary.txt";

    private String inFile3 =  System.getProperty("user.home") + "/工作/ultraman/summary.txt";
    private String outFile3 =  System.getProperty("user.home") + "/工作/ultraman/out_summary.txt";

    @Test
    public void processInFile() throws IOException {
        FileReader reader = new FileReader(inFile2);
        List<String> lines = IOUtils.readLines(reader);
        IOUtils.closeQuietly(reader);

        int error = 0;
        List<String> rows = new ArrayList<>();
        for(String line : lines){
            try {
                String newLine = line.replace("\\", "");
                JSON.parseArray(newLine);
                rows.add(newLine);
            }catch (Exception e){
                error++;
                System.out.println(line);
            }
        }
        FileWriter fileWriter = new FileWriter(new File(outFile2));
        IOUtils.writeLines(rows, null, fileWriter);
        IOUtils.closeQuietly(fileWriter);
        System.out.println("error lines total =" + error);
    }
}
