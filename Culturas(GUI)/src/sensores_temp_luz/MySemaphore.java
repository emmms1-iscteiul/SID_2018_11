package sensores_temp_luz;

import java.util.concurrent.Semaphore;

public class MySemaphore {

	 
    private Semaphore semaphore;
 
 
    public MySemaphore() {
        semaphore = new Semaphore(1);
    }
 
    boolean acquire() {
        return semaphore.tryAcquire();
    }
 
    void release() {
        semaphore.release();
    }
 
    int availableSlots() {
        return semaphore.availablePermits();
    }
 
}