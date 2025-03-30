package org.example.ThreadCommunicationExample;


class Message{
    String msg;
    public Message(String message){
        this.msg = message;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

class Waiter implements Runnable{
    private Message message;
    public Waiter(Message message){
        this.message = message;
    }

    @Override
    public void run(){
        synchronized (message){
            System.out.println("waiting to be notifed at: "+System.currentTimeMillis());
            try {
                message.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("notified with "+ message.msg + "at "+System.currentTimeMillis());
        }
    }
}

class Notifier implements Runnable{
    private Message message;

    public Notifier(Message message){
        this.message = message;
    }

    @Override
    public void run(){
        synchronized (message){
            System.out.println("notifying waiter at "+System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            message.setMsg("sample notification");
            message.notify();
        }
    }
}



public class CommunicationExample {
    public static void main(String[] args) {
        Message message = new Message("This is a sample message");
        Thread t1 = new Thread(new Waiter(message));
        Thread t2 = new Thread(new Notifier(message));
        t1.start();
        t2.start();
    }
}
