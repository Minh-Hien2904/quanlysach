package com.example.quanlysach;

import com.example.quanlysach.entity.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LombokTest {
    public static void main(String[] args) {
        Book b = new Book();
        b.setTitle("Test Title");
        System.out.println(b.getTitle());
    }
}

