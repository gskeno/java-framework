package com.gson.algo.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.cn/problems/possible-bipartition/
 * 
 * 给定一组n人（编号为1, 2, ..., n），我们想把每个人分进任意大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。
 *
 * 给定整数 n和数组 dislikes，其中dislikes[i] = [ai, bi]，表示不允许将编号为 ai和bi的人归入同一组。
 * 当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。
 */
public class 可能的二分法 {

    // 节点key着的色value
    Map<Integer, Integer> colorMap = new HashMap<>();
    // 与某人互斥的所有人
    Map<Integer, Set<Integer>> dislikesMap = new HashMap<>();

    /**
     * 二分着色
     * @param n
     * @param dislikes
     * @return
     */
    public boolean possibleBipartition(int n, int[][] dislikes){
        for(int[] dislike : dislikes){
            dislikesMap.computeIfAbsent(dislike[0], (key)->new HashSet<>()).add(dislike[1]);
            dislikesMap.computeIfAbsent(dislike[1], (key)->new HashSet<>()).add(dislike[0]);
        }
        // 对n个人进行着色。
        // 着色策略: 如果A没有着色，将其着红色(值为0)，所有与A互斥的人(记为B) 都着黑色, 所有与B互斥的人(记为C)再着红色,
        // 如果C中已经有人着过色且不是红色，则发生冲突。
        for (int i = 0; i < n; i++) {
            // 如果节点还没着色，则尝试着红色，如果着色失败，则发生冲突
            if (!colorMap.containsKey(i) && !setColor(i, 0)){
                return false;
            }
        }
        return true;
    }


    /**
     * 是否可以将i节点染成color色(0 表示红， 1表示黑)
     * @param i
     * @param color
     * @return
     */
    private boolean setColor(int i, int color){
        // 如果节点i已经着色，则判断已着色是否与目标色相同
        if (colorMap.get(i) != null){
            return colorMap.get(i) == color;
        }
        // 着色
        colorMap.put(i, color);

        // 互斥的其他节点着互斥色
        Set<Integer> dislikePersons = dislikesMap.get(i);
        if (dislikePersons != null && !dislikePersons.isEmpty()){
            for(int dislikeId : dislikePersons){
                // 任意一个互斥节点没有成功着色，从当前节点发起的着色行为就算失败。
                if (!setColor(dislikeId, color ^ 1)){
                    return false;
                }
            }
        }

        return true;

    }
    /**
     * 一个人不喜欢的所有人
     */

    private Map<Integer, Set<Integer>> disMap = new HashMap<>();

    /**
     *
     * @param n
     * @param dislikes
     * @return
     */
    public boolean possibleBipartition1(int n, int[][] dislikes) {
        if (dislikes.length == 0){
            return true;
        }
        for(int[] dislike : dislikes){
            int person0 = dislike[0];
            int person1 = dislike[1];
            if (disMap.get(person0) == null){
                disMap.put(person0, new HashSet<>());
                disMap.get(person0).add(person1);
            }
            if (disMap.get(person1) == null){
                disMap.put(person1, new HashSet<>());
                disMap.get(person1).add(person0);
            }
        }
        Set<Integer> team1 = new HashSet<>();
        Set<Integer> team2 = new HashSet<>();
        team1.add(dislikes[0][0]);
        team2.add(dislikes[0][1]);
        return canPart(team1, team2, dislikes, 2);
    }

    private void check(Set<Integer> set1, Set<Integer> set2, int[][] dislikes){
        for(int[] dislike : dislikes){
            int i = dislike[0];
            int j = dislike[1];
            if (set1.contains(i) && set1.contains(j)){
                throw new RuntimeException( i + "," + j);
            }
            if (set2.contains(i) && set2.contains(j)){
                throw new RuntimeException( i + "," + j);
            }
        }
    }

    /**
     * 从dislikes的第begin(0开始)对不喜欢的人开始进行分队，分到已有队员的team1和team2中，看是否可以分队
     * @param team1
     * @param team2
     * @param dislikes
     * @param begin
     * @return
     */
    public boolean canPart(Set<Integer> team1, Set<Integer> team2, int[][] dislikes, int begin){
        if (begin > dislikes.length){
            System.out.println(team1);
            System.out.println(team2);
            check(team1, team2, dislikes);
            return true;
        }
        int[] dislike = dislikes[begin-1];
        int person1 = dislike[0];
        int person2 = dislike[1];


        boolean can = false;
        // 1已进1队，2可进2队
        if (team1.contains(person1) && canJoinTeam(team2, person2)){
            Set<Integer> newTeam1 = new HashSet<>(team1);
            Set<Integer> newTeam2 = new HashSet<>(team2);
            newTeam2.add(person2);
            can = canPart(newTeam1, newTeam2, dislikes, begin + 1);
        }
        if (can){
            return true;
        }

        // 1进1队，2进2队
        if (canJoinTeam(team1, person1) && canJoinTeam(team2, person2)){
            Set<Integer> newTeam1 = new HashSet<>(team1);
            Set<Integer> newTeam2 = new HashSet<>(team2);

            newTeam1.add(person1);
            newTeam2.add(person2);
            can = canPart(newTeam1, newTeam2, dislikes, begin + 1);
        }
        if (can){
            return true;
        }
        // 1进2队，2进1队
        if (canJoinTeam(team1, person2) && canJoinTeam(team2, person1)){
            Set<Integer> newTeam1 = new HashSet<>(team1);
            Set<Integer> newTeam2 = new HashSet<>(team2);
            newTeam1.add(person2);
            newTeam2.add(person1);
            can =  canPart(newTeam1, newTeam2, dislikes, begin + 1);
        }
        if (can){
            return true;
        }

        // 1已进2队，2可进1队
        if (team2.contains(person1) && canJoinTeam(team1, person2)){
            Set<Integer> newTeam1 = new HashSet<>(team1);
            Set<Integer> newTeam2 = new HashSet<>(team2);
            newTeam1.add(person2);
            can =  canPart(newTeam1, newTeam2, dislikes, begin + 1);
        }
        if (can){
            return true;
        }

        // 2已进1队，1可进2队
        if (team1.contains(person2) && canJoinTeam(team2, person1)){
            Set<Integer> newTeam1 = new HashSet<>(team1);
            Set<Integer> newTeam2 = new HashSet<>(team2);
            newTeam2.add(person1);
            return canPart(newTeam1, newTeam2, dislikes, begin + 1);
        }
        if (can){
            return true;
        }

        // 2已进2队，1可进1队
        if (team2.contains(person2) && canJoinTeam(team1, person1)){
            Set<Integer> newTeam1 = new HashSet<>(team1);
            Set<Integer> newTeam2 = new HashSet<>(team2);
            newTeam1.add(person1);
            return canPart(newTeam1, newTeam2, dislikes, begin + 1);
        }
        if (can){
            return true;
        }

        // 1已进1队,2已进2队
        if (team1.contains(person1) && team2.contains(person2)){
            return canPart(team1, team2, dislikes, begin + 1);
        }
        // 1已进2队,2已进1队
        if (team1.contains(person2) && team2.contains(person1)){
            return canPart(team1, team2, dislikes, begin + 1);
        }

        return false;
    }

    /**
     * 一个人能否放进一个队伍里
     * @param team
     * @param person
     * @return
     */
    public boolean canJoinTeam(Set<Integer> team, int person){
        if (team.contains(person)){
            return false;
        }

        // 不喜欢的人在队伍里，也不能进队
        Set<Integer> dislikes = disMap.get(person);
        for(Integer dislike : dislikes){
            if (team.contains(dislike)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        可能的二分法 solution = new 可能的二分法();
//        System.out.println(solution.possibleBipartition(4, new int[][]{
//                {1,2},
//                {1,3},
//                {2,4}
//        }));
//
//        System.out.println(solution.possibleBipartition(3, new int[][]{
//                {1,2},
//                {1,3},
//                {2,3}
//        }));
//
//        System.out.println(solution.possibleBipartition(5, new int[][]{
//                {1,2},
//                {2,3},
//                {3,4},
//                {4,5},
//                {1,5}
//        }));

//        System.out.println(solution.possibleBipartition(10, new int[][]{
//                {1,2},
//                {3,4},
//                {5,6},
//                {6,7},
//                {8,9},
//                {7,8}
//
//        }));

//        System.out.println(solution.possibleBipartition(10, new int[][]{
//                {6,9},{1,3},{4,8},{5,6},{2,8},{4,7},{8,9},{2,5},{5,8},{1,2},{6,7},{3,10},{8,10},{1,5},
//                {3,6},{1,10},{7,9}
//        }));

                System.out.println(solution.possibleBipartition(100, getInput1()));
    }


    public static int[][] getInput1(){
        String s = "[26,47],[20,25],[23,60],[6,28],[30,41],[34,52],[5,57],[76,100],[39,92],[65,84],[28,88],[64,88],[31,80],[26,98],[67,80],[2,27],[68,92],[15,34],[39,97],[85,87],[53,62],[27,97],[36,67],[57,85],[8,38],[59,61],[14,34],[7,44],[35,52],[53,73],[54,73],[31,89],[44,55],[10,66],[5,93],[22,88],[83,90],[1,15],[30,51],[39,43],[29,89],[61,100],[68,97],[6,14],[82,87],[45,97],[61,94],[62,84],[16,29],[73,92],[35,61],[38,90],[43,45],[24,28],[40,86],[10,19],[39,57],[4,11],[35,79],[22,74],[14,95],[70,72],[43,83],[3,60],[36,37],[65,81],[29,75],[40,93],[84,89],[39,54],[13,63],[11,19],[12,20],[30,99],[72,90],[58,68],[21,48],[5,29],[59,64],[86,88],[2,82],[8,33],[32,46],[17,90],[54,82],[34,44],[2,4],[31,82],[7,70],[47,95],[1,18],[2,25],[50,63],[45,76],[2,51],[56,60],[23,55],[32,37],[23,45],[6,47],[37,42],[44,49],[9,17],[45,52],[43,71],[46,80],[87,88],[7,9],[20,68],[27,84],[33,57],[40,44],[39,76],[11,32],[35,63],[42,46],[16,53],[10,55],[18,55],[14,17],[10,17],[25,31],[56,91],[41,92],[16,90],[64,100],[70,78],[69,100],[25,43],[39,69],[15,73],[16,44],[14,24],[38,53],[3,85],[10,24],[67,73],[33,96],[18,65],[21,84],[56,72],[44,91],[32,84],[14,36],[66,97],[15,33],[14,78],[82,97],[54,62],[63,72],[55,63],[59,69],[78,90],[29,66],[42,58],[13,54],[45,57],[4,87],[90,100],[25,53],[33,63],[70,82],[6,11],[8,42],[31,36],[64,94],[25,47],[28,83],[75,81],[90,91],[92,100],[17,61],[64,83],[44,51],[55,67],[17,87],[11,25],[33,84],[57,94],[67,99],[14,88],[38,98],[5,23],[24,84],[20,36],[15,66],[41,47],[63,68],[18,27],[53,94],[36,58],[50,92],[31,66],[9,24],[42,54],[4,90],[17,98],[89,98],[2,83],[96,99],[86,99],[52,55],[74,86],[61,83],[62,67],[27,47],[28,40],[31,45],[27,69],[23,27],[66,98],[77,98],[65,79],[7,69],[57,82],[80,93],[43,99],[32,76],[20,39],[6,22],[54,60],[47,49],[13,64],[11,80],[9,32],[16,37],[25,96],[90,94],[54,72],[8,17],[30,68],[8,73],[22,91],[2,21],[46,77],[68,90],[91,98],[27,61],[23,51],[61,95],[23,41],[52,73],[15,71],[79,83],[38,63],[67,95],[4,29],[46,62],[23,72],[9,82],[74,96],[52,80],[31,38],[5,63],[11,36],[10,51],[55,90],[3,80],[64,66],[44,66],[34,97],[46,65],[23,65],[4,31],[37,83],[14,75],[9,91],[24,93],[78,87],[18,42],[89,93],[3,89],[67,77],[39,44],[76,89],[19,64],[7,93],[68,93],[10,72],[22,49],[70,95],[75,96],[6,20],[36,76],[34,76],[29,36],[12,92],[29,94],[75,92],[36,48],[31,88],[38,58],[44,78],[14,51],[9,99],[45,70],[19,47],[45,64],[28,74],[23,77],[82,84],[45,96],[2,39],[15,91],[33,58],[30,34],[17,31],[48,91],[11,33],[31,65],[77,87],[22,80],[41,93],[69,91],[66,79],[13,31],[19,52],[56,59],[26,57],[28,55],[97,100],[15,78],[54,88],[31,78],[29,74],[55,84],[12,63],[30,94],[12,81],[91,92],[33,97],[29,41],[8,77],[22,77],[21,63],[35,93],[37,51],[59,67],[52,89],[48,73],[61,73],[26,93],[33,46],[51,79],[2,77],[18,40],[12,54],[39,46],[65,67],[76,91],[8,94],[34,84],[43,65],[42,97],[46,78],[3,7],[36,96],[22,55],[14,80],[20,35],[70,89],[28,35],[29,34],[2,35],[4,52],[31,60],[28,72],[22,95],[14,49],[47,77],[8,65],[9,75],[63,74],[19,23],[93,100],[35,81],[11,77],[29,38],[52,85],[79,95],[42,47],[25,44],[79,99],[46,88],[19,54],[33,64],[46,66],[69,99],[34,92],[6,30],[15,72],[7,31],[61,78],[75,76],[40,58],[4,64],[7,53],[5,76],[12,57],[10,25],[67,72],[20,73],[46,99],[7,96],[51,58],[9,95],[8,36],[61,71],[36,47],[53,75],[36,53],[8,82],[39,52],[47,88],[3,99],[21,92],[56,80],[13,96],[70,83],[29,32],[25,64],[81,99],[4,54],[1,92],[9,12],[51,67],[37,49],[51,98],[39,63],[7,86],[42,86],[5,98],[27,29],[69,94],[57,71],[9,19],[34,67],[7,10],[30,38],[50,67],[63,71],[29,83],[78,79],[24,97],[6,81],[66,81],[64,65],[5,52],[19,56],[36,44],[4,30]";
        s = s.replace("[","");
        s = s.replace("]","");

        String[] strs = s.split(",");

        int[][] nums = new int[strs.length/2][2];
        for (int i = 0; i < strs.length/2; i++) {
            nums[i] = new int[]{Integer.valueOf(strs[i*2]), Integer.valueOf(strs[i*2 + 1])};
        }
        return nums;
    }
}
