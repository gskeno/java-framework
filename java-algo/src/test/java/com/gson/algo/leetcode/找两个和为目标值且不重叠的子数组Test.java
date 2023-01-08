package com.gson.algo.leetcode;

import com.gson.algo.leetcode.slidingwindow.找两个和为目标值且不重叠的子数组;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;

public class 找两个和为目标值且不重叠的子数组Test {

    @Test
    public void test1() throws IOException, URISyntaxException {
        找两个和为目标值且不重叠的子数组 solution = new 找两个和为目标值且不重叠的子数组();
        URL resource = getClass().getResource("/找两个和为目标值且不重叠的子数组case.txt");
        File file = new File(resource.toURI());
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        String[] strs = line.split(",");
        int[] nums = Arrays.stream(strs).mapToInt(s -> Integer.valueOf(s)).toArray();
        System.out.println(nums.length);
        System.out.println("最大值" + Arrays.stream(nums).max().getAsInt());
        System.out.println("最小值" + Arrays.stream(nums).min().getAsInt());
        int ans = solution.minSumOfLengths(nums, 78748);
        // 正确答案是 3032
        System.out.println(ans);
    }
}
