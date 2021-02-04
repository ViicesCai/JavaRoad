package edu.fdzc.test;

import edu.fdzc.impl.MathCalculator;
import edu.fdzc.inter.Calculator;
import edu.fdzc.proxy.CalculatorProxy;
import org.junit.Test;

/**
 * @author CAI
 * @time 2021/1/13
 */
public class AOPTester {

    @Test
    public void test() {
        Calculator proxy = CalculatorProxy.getProxy(new MathCalculator());
        proxy.add(1, 2);
        proxy.mul(2, 5);
    }

}
