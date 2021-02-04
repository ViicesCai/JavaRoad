package edu.fdzc.impl;

import edu.fdzc.inter.Calculator;
import org.springframework.stereotype.Service;

/**
 * 数学计算器
 *
 * @author CAI
 * @time 2021/1/13
 */
public class MathCalculator implements Calculator {

    @Override
    public int add(int i, int j) {
        return i +j;
    }

    @Override
    public int sub(int i, int j) {
        return i - j;
    }

    @Override
    public int mul(int i, int j) {
        return i * j;
    }

    @Override
    public int div(int i, int j) {
        return i / j;
    }
}
