package com.gson.algo.dynamic;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 购物单
 * 华为机试
 * https://blog.nowcoder.net/n/5eb528b238db4613870c29011d7c1cec
 */
public class ShopList {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int N = sc.nextInt();
            int m = sc.nextInt();

            //定义m个对象类（初始化，否则不通过）
            Good[] data = new Good[m];
            for(int i=0; i<m; i++){
                data[i] = new Good();
            }
            //将m个对象的属性情况进行赋值
            for(int i=0; i<m; i++){
                int v = sc.nextInt();
                int p = sc.nextInt();
                int q = sc.nextInt();

                data[i].setvpq(v, p, q);
                //如果不是主体
                if(q>0){
                    //找到其对应的主体编号，构建主体和附件的关系
                    if(data[q-1].getA1()==-1){
                        data[q-1].setA1(i);
                    }else{
                        data[q-1].setA2(i);
                    }
                }
            }
            pack(N, m, data);
        }
    }

    public static void pack(int N, int m, Good[] data){
        int[] dp = new int[N+1];

        //dp[i] = i元买到的最大价值
        dp[0] = 0;

        for(int i=0; i<m; i++){
            Good g = data[i];
            //money:费用, value:价值
            ArrayList<Integer> money = new ArrayList();
            ArrayList<Integer> value = new ArrayList();

            //只处理主体的情况
            if(g.q==0){
                /**
                 对于每一件主件都有四种情况：取价值最大
                 1.只放主件
                 2.放主件+附件1
                 3.放主件+附件2
                 4.放主件+附件1+附件2
                 */
                money.add(g.v);
                value.add(g.v*g.p);

                if(g.getA1()!=-1){
                    money.add(money.get(0) + data[g.getA1()].v);
                    value.add(value.get(0) + data[g.getA1()].v * data[g.getA1()].p);
                }
                if(g.getA2()!=-1){
                    money.add(money.get(0) + data[g.getA2()].v);
                    value.add(value.get(0) + data[g.getA2()].v * data[g.getA2()].p);
                }
                if(g.getA1()!=-1 && g.getA2()!=-1){
                    money.add(money.get(0) + data[g.getA1()].v + data[g.getA2()].v);
                    value.add(value.get(0) + (data[g.getA1()].v * data[g.getA1()].p) +(data[g.getA2()].v * data[g.getA2()].p));
                }
            }
            //找在满足不超N元的情况下的最大价值
            for(int k=N; k>=0; k-=10){
                //遍历放主体i的四种情况
                for(int j=0; j<money.size(); j++){
                    //用最少的钱（第k元），就能买到最大的价值
                    if(k-money.get(j)>=0){
                        /*两种方式取最大：
                            1.第j件主体不买（不花这k元的最大价值）
                            2.第j件主体买（从四种情况种选出一种价值最大）
                        */
                        dp[k] = Math.max(dp[k], dp[k-money.get(j)]+value.get(j));
                    }
                }
            }
        }
        System.out.println(dp[N]);
    }


}

class Good{
    public int v;
    public int p;
    public int q;

    //附件id
    private int a1 = -1;
    private int a2 = -1;

    public Good(){

    }
    public Good(int v, int p, int q){
        this.v = v;
        this.p = p;
        this.q = q;
    }
    public void setvpq(int v, int p, int q){
        this.v = v;
        this.p = p;
        this.q = q;
    }
    public void setA1(int a){
        this.a1 = a;
    }
    public void setA2(int a){
        this.a2 = a;
    }

    public int getA1(){
        return this.a1;
    }
    public int getA2(){
        return this.a2;
    }
}


