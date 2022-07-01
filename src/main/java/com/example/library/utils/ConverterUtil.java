package com.example.library.utils;

public class ConverterUtil {

    public static final String TYPE_BOOK = "book";
    public static final String TYPE_NEWSPAPER = "newspaper";
    public static final String TYPE_DOCUMENTARY = "documentary";

    public static int TypeToNumber(String type){
        switch(type){
            case TYPE_BOOK:
                return 0;
            case TYPE_NEWSPAPER:
                return 1;
            case TYPE_DOCUMENTARY:
                return 2;
        }
        return -1;
    }
    
}
