package edu.fdzc.factory;

import edu.fdzc.beans.AirPlane;

/**
 * 静态工厂
 *
 * @author CAI
 * @time 2021/1/7
 */
public class AirPlaneStaticFactory {

    public static AirPlane getAirPlane(String captain) {
        System.out.println("静态工厂启动");
        AirPlane airPlane = new AirPlane();
        airPlane.setCaptain(captain);
        airPlane.setEngine("V8");
        airPlane.setSubCaption("Jack");
        airPlane.setCapacity(300);
        airPlane.setWingLen("200.00cm");

        return  airPlane;
    }
}
