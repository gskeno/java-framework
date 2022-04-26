package com.gson.algo.huawei;

import java.util.*;

/**
 * https://www.nowcoder.com/practice/cf24906056f4488c9ddb132f317e03bc
 *
 * https://blog.nowcoder.net/n/0d2578cbbd5f4119806a6accc64a030c
 */
public class HJ43迷宫问题 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()){
            int lines = scanner.nextInt();
            int rows = scanner.nextInt();
            int[][] table = new int[lines][rows];
            for (int i = 0; i < lines; i++) {
                for (int j = 0; j < rows; j++) {
                    table[i][j] = scanner.nextInt();
                }
            }
            List<Point> pointList = new ArrayList<>();
            helper(0, 0, table, pointList);
            for(Point point : pointList){
                System.out.println("(" + point.x + "," + point.y + ")");
            }
        }
    }

    public static class Point{
        public int x;
        public int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    /**
     * 深度遍历，涉及到回溯，方法定义，方法返回值的含义，递归
     * @param x
     * @param y
     * @param table 元素为0表示该位置可通过，元素为1表示该位置是墙面，不可通过
     * @param pointList
     * @return 能否从x,y点走到表格的右下角
     */
    public static boolean helper(int x, int y, int[][] table, List<Point> pointList){
        pointList.add(new Point(x,y));
        // 1. 该点走过，则置为不可走
        table[x][y] = 1;

        // 已经走到右下角
        if (x == table.length - 1 && y == table[0].length-1){
            return true;
        }

        // 下方节点可走，则尝试朝下走
        if (x+1 < table.length && table[x+1][y] == 0){
            if (helper(x+1, y, table, pointList)){
                return true;
            }
        }

        // 右方节点可走，则尝试朝右走
        if (y + 1 <  table[0].length && table[x][y+1] == 0){
            if (helper(x, y+1, table, pointList)){
                return true;
            }
        }

        // 上方节点可走,则尝试朝上走
        if (x - 1 >=0 && table[x-1][y] == 0){
            if (helper(x-1, y, table, pointList)){
                return true;
            }
        }

        // 左方节点可走，则尝试朝左走
        if (y - 1 >=0 && table[x][y-1] == 0){
            if (helper(x, y-1, table, pointList)){
                return true;
            }
        }

        // 走到这里，说明从当前节点走，朝任何方向都走不到右下角
        // 回溯(因为开始将该节点加入到了list中了，所以这里要移除掉)
        pointList.remove(pointList.size() - 1);
        return false;
    }

    /**
     * 广度优先遍历
     */
    public static void bfs(int[][] map){
        /**
         * 广度优先遍历依赖队列
         */
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0 , null));
        Node finalNode = null;
        while (!queue.isEmpty()){
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;
            // 只有一条路能走到右下角，结束
            if (x == map.length - 1 && y == map[0].length - 1){
                finalNode = node;
                break;
            }

            // 尝试向下走
            if (x + 1 < map.length && map[x+1][y] == 0){
                queue.offer(new Node(x+1, y, node));
                // 走过标记1
                map[x+1][y] = 1;
            }

            // 尝试向右走
            if (y+1 < map[0].length && map[x][y+1] == 0){
                queue.offer(new Node(x, y+1, node));
                map[x][y+1] = 1;
            }

            // 尝试向上走
            if (x-1 >= 0 && map[x-1][y] == 0){
                queue.offer(new Node(x-1, y, node));
                map[x-1][y] = 1;
            }

            // 尝试向左走
            if (y-1 >= 0 && map[x][y-1] == 0){
                queue.offer(new Node(x, y-1, node));
                map[x][y-1] = 1;
            }
        }

        Stack<String> stack = new Stack<>();
        while (finalNode != null){
            stack.push( "(" + finalNode.x + "," + finalNode.y + ")");
            finalNode = finalNode.pre;
        }
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

    public static class Node{
        public int x;
        public int y;
        public Node pre;

        public Node(int x, int y, Node pre) {
            this.x = x;
            this.y = y;
            this.pre = pre;
        }
    }
}
