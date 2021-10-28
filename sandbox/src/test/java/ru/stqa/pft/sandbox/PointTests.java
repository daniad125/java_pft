package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {


    @Test
    public void pointDistance() {
        Point p = new Point(1,1,4,5);
        Assert.assertEquals(p.distance(),5);
        p = new Point(1,1,13,6);
        Assert.assertEquals(p.distance(),13);
        p = new Point(5,5,11,13);
        Assert.assertEquals(p.distance(),10);
    }
}
