package com.zhongxb.concurrent.chapter26;

/**
 * 在流水线上需要被加工的产品，create作为一个模板方法，提供了加工产品的说明书
 * @author Mr.zxb
 * @date 2018-11-01 14:43
 */
public abstract class InstructionBook {

    /**
     * 固定加工方法
     */
    public final void create() {
        this.firstProcess();
        this.secondProcess();
    }

    /**
     * 步骤一
     */
    protected abstract void firstProcess();

    /**
     * 步骤二
     */
    protected abstract void secondProcess();
}
