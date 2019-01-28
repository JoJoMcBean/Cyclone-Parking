package com.example.restapp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("upper")
public class UpperWriter implements TextWriter{


        public String writeText(String s){
            return s.toUpperCase();
        }
    }

