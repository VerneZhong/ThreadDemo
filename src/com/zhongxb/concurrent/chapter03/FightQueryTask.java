package com.zhongxb.concurrent.chapter03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class FightQueryTask extends Thread implements FightQuery {

    /**
     * 起点
     */
    private String origin;

    /**
     * 终点
     */
    private String destination;

    private List<String> fightList = new ArrayList<>();

    public FightQueryTask(String airline, String origin, String destination) {
        super("[" +airline + "]");
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public List<String> get() {
        return fightList;
    }

    @Override
    public void run() {
        System.out.printf("%s-Query from %s to %s \n", getName(), origin, destination);
        int randomVal = ThreadLocalRandom.current().nextInt(5);
        try {
            TimeUnit.SECONDS.sleep(randomVal);
            this.fightList.add(getName()+ "-" + randomVal);
            System.out.printf("The fight:%s list query successful\n", getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
