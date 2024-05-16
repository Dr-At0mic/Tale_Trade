package com.taleTrade.Authentication.systemUtils;

import java.util.UUID;

public class UuidGenerator {
    public static String TempName (){
        return UUID.randomUUID().toString().substring(0,8);
    }
}
