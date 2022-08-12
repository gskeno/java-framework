package com.gson.algo.leetcode;

import java.util.*;

/**
 * https://leetcode.cn/problems/shopping-offers/
 * 在 LeetCode 商店中， 有 n 件在售的物品。每件物品都有对应的价格。然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。
 * <p>
 * 给你一个整数数组 price 表示物品价格，其中 price[i] 是第 i 件物品的价格。另有一个整数数组 needs 表示购物清单，其中 needs[i] 是需要购买第 i 件物品的数量。
 * <p>
 * 还有一个数组 special 表示大礼包，special[i] 的长度为 n + 1 ，其中 special[i][j] 表示第 i 个大礼包中内含第 j 件物品的数量，且 special[i][n] （也就是数组中的最后一个整数）为第 i 个大礼包的价格。
 * <p>
 * 返回 确切 满足购物清单所需花费的最低价格，你可以充分利用大礼包的优惠活动。你不能购买超出购物清单指定数量的物品，即使那样会降低整体价格。任意大礼包可无限次购买。
 * <p>
 * 输入：price = [2,3,4], special = [[1,1,0,4],[2,2,1,9]], needs = [1,2,1]
 * 输出：11
 * 解释：A ，B ，C 的价格分别为 ¥2 ，¥3 ，¥4 。
 * 可以用 ¥4 购买 1A 和 1B ，也可以用 ¥9 购买 2A ，2B 和 1C 。
 * 需要买 1A ，2B 和 1C ，所以付 ¥4 买 1A 和 1B（大礼包 1），以及 ¥3 购买 1B ， ¥4 购买 1C 。
 * 不可以购买超出待购清单的物品，尽管购买大礼包 2 更加便宜。
 */
public class 大礼包_638 {

    /**
     * 递归 + 记忆化搜索
     * 每次选择一种购买方式(直接买物品，或者买大礼包), 比较获取到最划算的购买方式
     *
     * @param price
     * @param special
     * @param needs
     * @return
     */
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        // 有些大礼包是无效的，把有效的大礼包找出来。大礼包中无商品，或者大礼包的价格比内部的商品单买更贵，就是无效的大礼包
        List<List<Integer>> validSpecial = new ArrayList<>();

        for (List<Integer> curSpecial : special) {
            int total = 0;
            int totalPrice = 0;
            int size = curSpecial.size();
            for (int i = 0; i < size - 1; i++) {
                total += curSpecial.get(i);
                totalPrice += curSpecial.get(i) * price.get(i);
            }
            if (total > 0 && curSpecial.get(size - 1) < totalPrice) {
                validSpecial.add(curSpecial);
            }
        }

        //递归 + 记忆化搜索
        return dfs(price, validSpecial, needs);
    }

    Map<List<Integer>, Integer> needsToPrices = new HashMap<>();

    private int dfs(List<Integer> prices, List<List<Integer>> specials, List<Integer> needs) {
        if (needsToPrices.get(needs) == null) {
            // 先初始化为不使用大礼包，单独购买每件物品所需要的价格。
            int needPrices = 0;
            for (int i = 0; i < needs.size(); i++) {
                needPrices += needs.get(i) * prices.get(i);
            }

            // 再尝试使用大礼包，假设有3个大礼包，
            // 使用大礼包1所耗费的最低总价格= 大礼包1价格 + 购买剩下物品所需的最低总价格(这里要递归)
            // 使用大礼包2所耗费的最低总价格= 大礼包2价格 + 购买剩下物品所需的最低总价格(这里要递归)
            // 使用大礼包3所耗费的最低总价格= 大礼包3价格 + 购买剩下物品所需的最低总价格(这里要递归)
            // needPrices要取这些决策的最小值
            for (List<Integer> special : specials) {
                int n = special.size() - 1;
                List<Integer> nextNeeds = new ArrayList<>();
                boolean ignoreSpecial = false;
                for (int i = 0; i < n; i++) {
                    if (special.get(i) > needs.get(i)){
                        ignoreSpecial = true;
                        break;
                    }
                    nextNeeds.add(needs.get(i) - special.get(i));
                }
                if (ignoreSpecial){
                    continue;
                }
                int curPrice = special.get(n) + dfs(prices, specials, nextNeeds);
                needPrices = Math.min(needPrices, curPrice);
            }

            needsToPrices.put(needs, needPrices);
        }
        return needsToPrices.get(needs);
    }

    public static void main(String[] args) {
        大礼包_638 solution = new 大礼包_638();
        int ans = solution.shoppingOffers(Arrays.asList(2, 3, 4),
                Arrays.asList(Arrays.asList(1, 1, 0, 4), Arrays.asList(2, 2, 1, 9)),
                Arrays.asList(1, 2, 1));
        System.out.println(ans);
    }

    private void test1() {
        大礼包_638 solution = new 大礼包_638();
        Map<List<Integer>, Integer> map = new HashMap<>();

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        map.put(list1, 7);

        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        Integer integer = map.get(list2);
        System.out.println(integer);
    }
}
