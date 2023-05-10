package com.ensah.gs_contact.bo.message;

public class Message {
    private String msg;
    private MessageType type;

    public Message(String msg, MessageType type) {
        this.msg = msg;
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
