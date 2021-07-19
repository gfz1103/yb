package com.buit.his.shyb.source.util;

public class CharacterTurnPy {
    public static String convertTo(String str) {
        String tempStr = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 33 && c <= 126) {// 字母和符号原样保留
                tempStr += String.valueOf(c);
            } else {// 累加拼音声母
                tempStr += getPYChar(String.valueOf(c));
            }
        }
        return tempStr;
    }

    private static String getPYChar(String c) {
        byte[] array = new byte[2];
        array = String.valueOf(c).getBytes();
        int i = (short) (array[0] - '\0' + 256) * 256
                + ((short) (array[1] - '\0' + 256));
        if (i < 0xB0A1){
            return "*";
        }
        if (i < 0xB0C5){
            return "A";
        }
        if (i < 0xB2C1){
            return "B";
        }
        if (i < 0xB4EE){
            return "C";}
        if (i < 0xB6EA){
            return "D";}
        if (i < 0xB7A2){
            return "E";}
        if (i < 0xB8C1){
            return "F";}
        if (i < 0xB9FE){
            return "G";}
        if (i < 0xBBF7){
            return "H";}
        if (i < 0xBFA6){
            return "J";}
        if (i < 0xC0AC){
            return "K";}
        if (i < 0xC2E8){
            return "L";}
        if (i < 0xC4C3){
            return "M";}
        if (i < 0xC5B6){
            return "N";}
        if (i < 0xC5BE){
            return "O";}
        if (i < 0xC6DA){
            return "P";}
        if (i < 0xC8BB){
            return "Q";}
        if (i < 0xC8F6){
            return "R";}
        if (i < 0xCBFA){
            return "S";}
        if (i < 0xCDDA){
            return "T";}
        if (i < 0xCEF4){
            return "U";}
        if (i < 0xD1B9){
            return "X";}
        if (i < 0xD4D1){
            return "Y";}
        if (i < 0xD7FA){
            return "Z";}
            else{
        return "*";}
    }
}
