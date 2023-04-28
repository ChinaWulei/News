package com.example;

import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.concurrent.locks.ReentrantLock;

public class Son {

    private static Thread thread1;
    private static Thread thread2;
    static ReentrantLock reentrantLock = new ReentrantLock();

    public  static  void testMeth(String name) throws InterruptedException {
        reentrantLock.lock();
        for (int i = 1; i < 20; i++) {
            System.out.println(name + i);
        }
        reentrantLock.unlock();
    }



    public  static void main(String[] args) throws InterruptedException {
        /*Son son  =   new Son();
        thread1 = new Thread(new Runnable() {

                                      @Override
                                      public void run() {
                                          try {
                                              testMeth("Irving");
                                          } catch (InterruptedException e) {
                                              throw new RuntimeException(e);
                                          }
                                      }
                                  }

        );
        thread1.setName("Irving");
        thread2 = new Thread(new Runnable() {

                                      @Override
                                      public void run() {
                                          try {
                                              testMeth("James");
                                          } catch (InterruptedException e) {
                                              throw new RuntimeException(e);
                                          }
                                      }
                                  }

        );
        thread1.start();
        thread2.start();
*/

        System.out.println("asdasddas");




    }
}
