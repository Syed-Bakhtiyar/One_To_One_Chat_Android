package com.example.bakhtiyar.simplechatapplication;

/**
 * Created by Bakhtiyar on 12/19/2016.
 */
public class SentReadMsgs {

    String msgs;

    String download;

    String from;

    boolean flag;

    String user;

    String date;

    String key;

    public SentReadMsgs(String msgs, String from, String user, String date,String key,String download,boolean flag) {
        this.msgs = msgs;
        this.from = from;
        this.user = user;
        this.date = date;
        this.key = key;
        this.flag = flag;
        this.download=download;
    }

    public SentReadMsgs() {
    }

    public String getDownload() {
        return download;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsgs() {
        return msgs;
    }

    public String getFrom() {
        return from;
    }

    public String getUser() {
        return user;
    }

    public String getDate() {
        return date;
    }

    public String getKey() {
        return key;
    }
}
