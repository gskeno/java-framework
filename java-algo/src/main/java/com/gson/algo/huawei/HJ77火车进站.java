package com.gson.algo.huawei;

import java.util.*;

public class HJ77火车进站 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()){
            int n = scanner.nextInt();
            int nums[] = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] =  scanner.nextInt();
            }
            handle(nums, n);
        }
    }

    //result:结果集，
    // temp：临时出车路径，
    // arr:入站序列，
    // n：火车数
    // stack：火车站
    // i: 出栈序列位置
    // j: 入站序列位置
    public static void huisu(List<List<Integer>> result,List<Integer> temp,int[] arr,int n,Stack<Integer> stack,int i,int j){
        //base case:全部出栈入栈完毕，则存入结果集
        if(i==n && j==n){
            result.add(new ArrayList<Integer>(temp));
            return ;
        }
        //选择进站（入栈序列不为空）:入栈序列不为空，就可以选择。选择之后递归，之后再撤销选择
        if(j != n){
            // 选择入栈
            stack.push(arr[j]);
            // 回溯
            huisu(result,temp,arr,n,stack,i,j+1);
            // 撤销
            stack.pop();
        }
        //栈顶的元素出栈：也是可选的(栈不空就可以操作)
        if( !stack.isEmpty()){
            // 选择出栈
            int x=stack.pop();
            temp.add(x);
            // 回溯
            huisu(result,temp,arr,n,stack,i+1,j);
            // 撤销
            temp.remove(temp.size()-1);//再去除最后一个
            stack.push(x);//再压进去
        }
    }

    /**
     * 复杂度较高，因为把所有排列结果都check了一遍是否满足要求
     * @param nums
     * @param n
     */
    public static void handle(int[] nums, int n){
        // 所有的排列结果
        List<List<Integer>> allPath = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        permutate(nums, path, allPath);

        List<List<Integer>> legalList = new ArrayList<>();
        for(List<Integer> single: allPath){
            if (isLegal(nums, single)){
                legalList.add(single);
            }
        }
        legalList.sort(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                StringBuilder sb1 = new StringBuilder();
                for(Integer i : o1){
                    sb1.append(i);
                }
                StringBuilder sb2 = new StringBuilder();
                for(Integer i : o2){
                    sb2.append(i);
                }
                return sb1.toString().compareTo(sb2.toString());
            }
        });
        System.out.println(legalList.size());
        for(List<Integer> single : legalList){
            for (int i = 0; i < single.size(); i++) {
                System.out.print(single.get(i) + " ");
            }
            System.out.println();
        }
    }

    /**
     * 校验出栈顺序 path是否合法。
     * 比如nums为 7，9，6。 path为6，9，7是合法的，全部入栈后依次出来， 9，7，6也是合法的
     * path是 6，7，9是非法的
     * @param nums
     * @param path
     * @return
     */
    public static boolean isLegal(int[] nums, List<Integer> path){
        int j = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            stack.push(nums[i]);
            while (!stack.isEmpty() && stack.peek().equals(path.get(j))){
                j++;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    /**
     *
     * @param nums 比如 1,2,3的排列总共有6种
     * @param singePath
     * @param allPaths
     */
    public static void permutate(int[] nums, List<Integer> singePath, List<List<Integer>> allPaths){
        // 一个排列已经达成
        if (singePath.size() == nums.length){
            allPaths.add(new ArrayList<>(singePath));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //回溯，可以预见到,最外层每次遍历i时，singlePath的元素个数都为0
            if (!singePath.contains(nums[i])){
                singePath.add(nums[i]);
                permutate(nums, singePath, allPaths);
                singePath.remove(singePath.size()-1);
            }
        }
    }


}
