package com.gson.javajdk.util;

import org.junit.Test;

import java.util.Scanner;

public class ScannerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()){
            System.out.println(scanner.nextInt());
            System.out.println("1");
            System.out.println(scanner.next());
            System.out.println("2");

        }
    }
    @Test
    public void testNext(){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            System.out.println(scanner.nextInt());
        }
    }
}
