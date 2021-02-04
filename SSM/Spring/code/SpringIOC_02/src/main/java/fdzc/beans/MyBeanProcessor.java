package fdzc.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 后置处理器类
 * 配置过程：
 *  1.编写后置处理器的实现类
 *  2.将后置处理器注册在配置文件中
 *
 * @author CAI
 * @time 2021/1/7
 */
public class MyBeanProcessor implements BeanPostProcessor {

    /**
     * 初始化之前调用
     *
     * @param bean 将要初始化的Bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[" + beanName +"] bean将要调用初始化方法，这个bean是这样：[" + bean + "]");

        return bean;
    }

    /**
     * 初始化方法之后调用
     *
     * @param bean
     * @param beanName 在XML配置的bean id
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("bean初始化方法调用结束");

        // 初始化之后返回的bean；返回的是什么容器中保存的就是什么
        return bean;
    }
}
