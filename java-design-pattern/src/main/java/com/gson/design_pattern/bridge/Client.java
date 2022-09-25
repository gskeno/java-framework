package com.gsonkeno.pattern.bridge;

/**
 * 桥梁模式
 * Created by gaosong on 2018-01-18
 */
public class Client {

    public static void main(String[] args) {
        HouseProduct houseProduct = new HouseProduct();
        HouseCrop houseCrop = new HouseCrop(houseProduct);
        houseCrop.makeMoney();

        IpodProduct ipodProduct = new IpodProduct();
        ShanzhaiCrop shanzhaiCrop = new ShanzhaiCrop(ipodProduct);
        shanzhaiCrop.makeMoney();

        IPhoneProduct iPhoneProduct = new IPhoneProduct();
        ShanzhaiCrop shanzhaiCrop1 = new ShanzhaiCrop(iPhoneProduct);
        shanzhaiCrop1.makeMoney();
    }
}
