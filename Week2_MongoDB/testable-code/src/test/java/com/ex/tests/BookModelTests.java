package com.ex.tests;

import com.ex.Book;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class BookModelTests {
    @Test
    public void setPubDateShouldBeSet() {
        Book b = new Book();
        Date initial = b.getPubDate();
        Date now = new Date();
        b.setPubDate(now);
        Date after = b.getPubDate();
        Assert.assertNotEquals(initial, after);
        Assert.assertEquals(after, now);
    }
}
