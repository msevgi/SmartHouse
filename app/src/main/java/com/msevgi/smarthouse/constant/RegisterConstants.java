package com.msevgi.smarthouse.constant;

public final class RegisterConstants {

    public static String sToken;
    public static String sMSISDN;
    public static int sID;

    public static String getToken() {
        return sToken;
    }

    public static void setToken(String token) {
        RegisterConstants.sToken = token;
    }

    public static String getMSISDN() {
        return sMSISDN;
    }

    public static void setMSISDN(String msisdn) {
        RegisterConstants.sMSISDN = msisdn;
    }

    public static int getID() {
        return sID;
    }

    public static void setID(int id) {
        RegisterConstants.sID = id;
    }
}
