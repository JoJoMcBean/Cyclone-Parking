package com.example.restapp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fancy")
public class FancyWriter implements TextWriter {
    public String writeText(String s){
        return s + " fancy text";
    }
}
