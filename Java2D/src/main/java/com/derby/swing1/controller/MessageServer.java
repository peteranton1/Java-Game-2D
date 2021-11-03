package com.derby.swing1.controller;

import com.derby.swing1.model.Message;

import java.util.*;

public class MessageServer implements Iterable<Message>{

    private Map<Integer, List<Message>> messages;
    private List<Message> selected;

    public int getMessageCount(){
        return selected.size();
    }

    public List<Message> getMessages(){
        return selected;
    }

    public MessageServer() {
        selected = new ArrayList<>();
        messages = new TreeMap<>();
        for(int i=0;i<5;i++) {
            makeMessages(i);
        }
    }

    private void makeMessages(int channel) {
        List<Message> list = new ArrayList<>();
        list.add(Message.builder()
            .title("Hello-1-"+channel)
            .content("Hello-1 message channel "+channel)
            .build());
        list.add(Message.builder()
            .title("Hello-2-"+channel)
            .content("Hello-2 message channel "+channel)
            .build());
        messages.put(channel,list);
    }

    public void setSelectedServers(Set<Integer> servers){
        selected.clear();

        for(Integer id: servers) {
            if(messages.containsKey(id)){
                selected.addAll(messages.get(id));
            }
        }
    }

    @Override
    public Iterator<Message> iterator() {
        return new MessageIterator(selected);
    }
}

class MessageIterator implements Iterator<Message> {

    private Iterator<Message> iterator;

    public MessageIterator(List<Message> messages) {
        iterator = messages.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Message next() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // Do nothing
        }
        return iterator.next();
    }

    @Override
    public void remove() {
        iterator.remove();
    }
}