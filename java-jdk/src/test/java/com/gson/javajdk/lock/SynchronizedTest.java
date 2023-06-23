package com.gson.javajdk.lock;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SynchronizedTest {
    // 每new一个LearnHandler,且start,则表示一个Follower与Leader建立了连接
    class LearnHandler extends Thread{
        Leader leader;
        String learnerId;
        public LearnHandler(Leader leader, String learnerId){
            this.leader = leader;
            this.learnerId = learnerId;
        }
        @Override
        public void run() {
            leader.waitHalfConnect(learnerId);
        }
    }
    class Leader{
        List<String> participants = new ArrayList<>();
        int half = 3;
        boolean overHalf = false;
        public void waitHalfConnect(String id){
            synchronized (participants){
                if (overHalf){
                    return;
                }
                participants.add(id);
                System.out.println("participants size " + participants.size());
                if (participants.size() >= half){
                    participants.notifyAll();
                    overHalf = true;
                    return;
                }else {
                    try {
                        System.out.println(id + "开始wait");
                        participants.wait(10000);
                        System.out.println(id + "结束wait");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    if (overHalf){
                        return;
                    }
                    throw new RuntimeException("过了10秒了，还没有过半参与者进来");
                }
            }
        }


        public void wait0(){
            synchronized (participants){
                try {
                    System.out.println("开始wait 0");
                    participants.wait(-5);
                    System.out.println("结束wait 0");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    @Test
    public void test1() throws InterruptedException {
        Leader leader = new Leader();
        LearnHandler handler1 = new LearnHandler(leader, "1");
        handler1.start();
        LearnHandler handler2 = new LearnHandler(leader, "2");
        handler2.start();

        LearnHandler handler3 = new LearnHandler(leader, "3");
        handler3.start();


        LearnHandler handler4 = new LearnHandler(leader, "4");
        handler4.start();
        handler4.join();
    }

    @Test
    public void test2(){
        Leader leader = new Leader();
        leader.wait0();
    }

}
