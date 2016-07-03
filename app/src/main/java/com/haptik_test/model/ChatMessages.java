package com.haptik_test.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sameer on 7/2/2016.
 */
public class ChatMessages {

    private Integer count;
    private List<Message> messages = new ArrayList<>();

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

}
