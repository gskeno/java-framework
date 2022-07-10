package com.gson.algo.huawei;

import java.util.*;
public class 机试_1 {
    public static void main(String[] args) {
        Set<String> allEle = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        int line1 = sc.nextInt();
        // String[] eleOnLine1 = new String[line1];
        for (int i = 0; i < line1; i++) {
            String ele = sc.next();
            //eleOnLine1[i] = ele;
            allEle.add(ele);
        }
        int line2 = sc.nextInt();
        //String[] eleOnLine2 = new String[line2];
        for (int i = 0; i < line2; i++) {
            String ele = sc.next();
            // eleOnLine2[i] = ele;
            allEle.add(ele);
        }

        List<String> eleList = new ArrayList<>(allEle);
        //System.out.println(Arrays.toString(eleOnLine1));
        //System.out.println(Arrays.toString(eleOnLine2));
        Collections.sort(eleList);
        for (int i = 0; i < eleList.size(); i++) {
            if(i == 0){
                System.out.print("[");
            }
            System.out.print(eleList.get(i));
            if (i != eleList.size() - 1){
                System.out.print(" ");
            }

            if(i == eleList.size() -1){
                System.out.print("]");
            }
        }

        if (eleList.isEmpty()){
            System.out.println("[]");
        }
    }
}
