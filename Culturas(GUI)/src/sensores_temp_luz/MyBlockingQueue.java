package sensores_temp_luz;


import java.util.LinkedList;
import java.util.List;

public class MyBlockingQueue<BasicDBObject> {
    List<BasicDBObject> queue = new LinkedList<BasicDBObject>();
    private int limit = 0;

    public MyBlockingQueue() {
        limit=3;
    }

    public synchronized void enqueue(BasicDBObject item) throws InterruptedException {
        while (queue.size() == limit) {
            wait();
        }
        if (queue.size() == 0) {
            notifyAll();
        }
        queue.add(item);
    }

    public synchronized BasicDBObject dequeue() throws InterruptedException {
        while (queue.size() == 0) {
            wait();
        }
        if (queue.size() == limit) {
            notifyAll();
        }
        return queue.remove(0);
    }

}