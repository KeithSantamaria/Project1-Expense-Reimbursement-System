package com.ex.tests;

import com.ex.Rectangle;
import org.junit.Assert;
import org.junit.Test;

public class RectangleTests {
    
    @Test
    public void newRectAreaShouldBeZero() {
        Rectangle r = new Rectangle();
        int area = r.area();
        Assert.assertEquals(0, area);
    }
    
    @Test
    public void rectWithHeight2ShouldHaveAreaOf0() {
        Rectangle r = new Rectangle();
        r.setHeight(2);
        int area = r.area();
        Assert.assertEquals(0, area);
    }
}
