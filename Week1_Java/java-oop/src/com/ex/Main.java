package com.ex;

import com.ex.publications.Book;
import com.ex.publications.Newspaper;
import com.ex.publications.Publication;
import com.ex.publications.ReadablePublication;

public class Main {

    public static void main(String[] args) {
//        Publication pub = new Book(); // compile time polymorphism
//        pub.setTitle("Become a Programming Hero.");
//
//        System.out.println(pub.getTitle()); // run time polymorphism
//        Book b = (Book)pub;
//        b.isFiction();

        ReadablePublication readableBook = new Book();
        ReadablePublication readableNewspaper = new Newspaper();
        readPublication(readableBook);
        readPublication(readableNewspaper);
    }

    private static void readPublication(ReadablePublication readable) {
        System.out.println(readable.getCurrentPage());
    }
}
