package com.gson.design;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 停车场管理系统
 * 一个入口，一个出口，入口完成记时，出口完成结账及车位释放
 * 停车位有 货车和小车
 * 货车每小时10元，每天累计120元
 * 小车每小时5元，每天累计60元
 *  设计一个管理系统，(1)车辆进入 (2)车辆离开 (3)计算当日停车场缴费金额
 */
public class ParklotManager {
    /**
     * 停车场的货车总车位数
     */
    private int totalTruckParkingSpace;

    /**
     * 停车场的小车总车位数
     */
    private int totalCarParkingSpace;

    /**
     * 小车计数器
     */
    private AtomicInteger carCounter;

    /**
     * 大车计数器
     */
    private AtomicInteger truckCounter;

    /**
     * 停车车牌-->停车时间
     */
    private Map<String, Long> carToParkTime = new ConcurrentHashMap<>();

    /**
     * 总金额
     */
    private AtomicInteger totalFee = new AtomicInteger(0);

    public ParklotManager(int totalTruckParkingSpace, int totalCarParkingSpace){
        this.totalCarParkingSpace = totalCarParkingSpace;
        this.totalTruckParkingSpace = totalTruckParkingSpace;
        truckCounter = new AtomicInteger(totalTruckParkingSpace);
        carCounter = new AtomicInteger(totalCarParkingSpace);
    }

    /**
     * 1 货车
     * 2 小车
     * @param vehicleType
     * @return 停车是否成功
     */
    public boolean park(int vehicleType, String vehicleNo){
        if (vehicleType == 1 ){
            int truckLeft = truckCounter.get();
            if (truckLeft >=0 && truckCounter.compareAndSet(truckLeft, truckLeft-1)){
                carToParkTime.put(vehicleNo, System.currentTimeMillis());
                return true;
            }
        }else if (vehicleType == 2){
            int carLeft = carCounter.get();
            if (carLeft >=0 && carCounter.compareAndSet(carLeft, carLeft-1)){
                carToParkTime.put(vehicleNo, System.currentTimeMillis());
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param vehicleType
     * @param vehicleNo
     */
    public boolean leave(int vehicleType, String vehicleNo){
        Long parkTime = carToParkTime.get(vehicleNo);
        if (parkTime == null){
            return false;
        }
        int hours = (int)Math.ceil((double)(System.currentTimeMillis() - parkTime) / (60 * 60 * 1000));
        if (vehicleType == 1 ){
            int fee = hours * 10;
            if (fee > 120){
                fee = 120;
            }
            totalFee.addAndGet(fee);
            // 空停车位+1
            truckCounter.addAndGet(1);
            carToParkTime.remove(vehicleNo);
        }else if (vehicleType == 2){
            int fee = hours * 5;
            if (fee > 60){
                fee = 60;
            }
            totalFee.addAndGet(fee);
            // 空停车位+1
            carCounter.addAndGet(1);
            carToParkTime.remove(vehicleNo);
        }
        return true;
    }

    public int getTotalFee(){
        return totalFee.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ParklotManager parklotManager = new ParklotManager(10, 10);
        parklotManager.park(1, "浙A12345");
        parklotManager.park(2, "浙C12345");

        Thread.sleep(1000);
        parklotManager.leave(1, "浙A12345");
        parklotManager.leave(2, "浙C12345");

        int totalFee = parklotManager.getTotalFee();
        System.out.println(totalFee);
    }
}
