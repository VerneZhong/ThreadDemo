package com.zhongxb.concurrent.chapter18;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 不可变的累加器对象设计，每次处理都是新对象,该类不可继承，避免打破不可变的特性
 * @author Mr.zxb
 * @date 2018-10-30 11:12
 */
public final class IntegerAccumulator {

    private final int init;

    public IntegerAccumulator(int init) {
        this.init = init;
    }

    public IntegerAccumulator(IntegerAccumulator accumulator, int init) {
        this.init = accumulator.getValue() + init;
    }

    public IntegerAccumulator add(int init) {
        return new IntegerAccumulator(this, init);
    }

    public int getValue() {
        return this.init;
    }

    public static void main(String[] args) {

        IntegerAccumulator accumulator = new IntegerAccumulator(0);
        IntStream.range(0, 3).forEach(i -> new Thread(() -> {
            int inc = 0;
            while (true) {
                // 首先获得old value
                int oldValue = accumulator.getValue();
                // 然后调用add方法
                int result = accumulator.add(inc).getValue();
                System.out.println(oldValue + "+" + inc + "=" + result);
                // 经过验证，不合理输出错误信息
                if (oldValue + inc != result) {
                    System.err.println("Error:" + oldValue + "+" + inc + "=" + result);
                }
                inc++;
                slowly();
            }
        }).start());
    }

    public static void slowly() {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
