package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
    public static void main(String[] args) {
        String[] langs = {"Java","C#","PHP","Python"};
        for (String l : langs) {
            System.out.println("I want to learn " + l);
        }
        List<String> languages = Arrays.asList("Pascal","PL+");
        for (String l : languages) {
            System.out.println("I want to learn " + l);
        }
    }
}
