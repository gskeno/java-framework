package com.gson.algo.leetcode.count;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/baby-names-lcci/
 */
public class 婴儿名字 {

    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        // 每个名字出现的次数
        Map<String,Integer> nameCountMap = new HashMap<>();
        for(String name : names){
            int i = name.indexOf("(");
            int j = name.indexOf(")");
            nameCountMap.put(name.substring(0,i), Integer.valueOf(name.substring(i+1, j)));
        }
        // 子父关系
        Map<String,String> childParentMap = new HashMap<>();
        for(String synonym : synonyms){
            int i = synonym.indexOf("(");
            int j = synonym.indexOf(",");
            int k = synonym.indexOf(")");
            String name1 = synonym.substring(i + 1, j);
            String name2 = synonym.substring(j + 1, k);

            // 一直找到祖宗
            while (childParentMap.get(name1) != null){
                name1 = childParentMap.get(name1);
            }
            while (childParentMap.get(name2) != null){
                name2 = childParentMap.get(name2);
            }
            // 祖宗不同要合并；祖宗相同，说明这两个名字的权值已经加到祖宗上了，不需要处理
            if (!name1.equals(name2)){
                int totalFrequency = nameCountMap.getOrDefault(name1, 0 ) + nameCountMap.getOrDefault(name2, 0);
                // name1和name2，这2者字典序小的作为父亲
                String parent = name1.compareTo(name2) < 0 ? name1 : name2;
                String child = name1.compareTo(name2) < 0 ? name2 : name1;
                // 构建父子关系
                childParentMap.put(child, parent);
                // 将孩子的权值转移到祖先上
                nameCountMap.put(parent, totalFrequency);
                // 同名只保留字典序最小的名字,后续会遍历其输出结果
                nameCountMap.remove(child);
            }
        }
        String[] res = new String[nameCountMap.size()];
        int index = 0;
        for (String name : nameCountMap.keySet()) {
            StringBuilder sb = new StringBuilder(name);
            sb.append('(');
            sb.append(nameCountMap.get(name));
            sb.append(')');
            res[index++] = sb.toString();
        }
        return res;
    }

    public static void main(String[] args) {
        婴儿名字 solution = new 婴儿名字();
        String[] ans = solution.trulyMostPopular(new String[]{"John(15)", "Jon(12)", "Chris(13)", "Kris(4)", "Christopher(19)"},
                new String[]{"(Jon,John)", "(John,Johnny)", "(Chris,Kris)", "(Chris,Christopher)"});
        System.out.println(Arrays.toString(ans));
    }
}
