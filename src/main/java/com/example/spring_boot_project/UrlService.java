package com.example.spring_boot_project;

public class UrlService {
    private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static char[] allowedCharacters = allowedString.toCharArray();
    private static long base = allowedCharacters.length;

    public static String encode(long id){
        StringBuilder stringBuilder = new StringBuilder();
        if(id == 0){
            stringBuilder.append(allowedCharacters[0]);
        }
        while (id > 0){
            stringBuilder.append(allowedCharacters[(int)(id % base)]);
            id /= base;
        }
        return stringBuilder.toString();
    }

    public static long decode(String string){
        char[] array = string.toCharArray();
        int id = 0;
        for(int i = 0; i < array.length; i++){
            id += allowedString.indexOf(array[i]) * Math.pow(base, i);
        }
        return id;
    }

}

