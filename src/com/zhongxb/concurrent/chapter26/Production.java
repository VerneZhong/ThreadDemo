package com.zhongxb.concurrent.chapter26;

/**
 * 产品
 * @author Mr.zxb
 * @date 2018-11-01 14:47
 */
public class Production extends InstructionBook {

    /**
     * 产品编号
     */
    private final int prodId;

    public Production(int prodId) {
        this.prodId = prodId;
    }

    @Override
    protected void firstProcess() {
        System.out.println("execute the " + prodId + " first process.");
    }

    @Override
    protected void secondProcess() {
        System.out.println("execute the " + prodId + " second process.");
    }

    @Override
    public String toString() {
        return "Production " + prodId;
    }
}
