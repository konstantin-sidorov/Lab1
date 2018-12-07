package L1_3;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class RW {

    public static void main(String[] args) throws Exception {
        Data d = new Data();

        ExecutorService es = Executors.newFixedThreadPool(5);
        for(int i=0; i<5; i++)
            es.submit(new WorkWData(d));

        TimeUnit.SECONDS.sleep(3);
        es.shutdown();
        es.awaitTermination(10000, TimeUnit.MILLISECONDS);
    }
}

class WorkWData implements Runnable {
    Data obj;
    WorkWData(Data d) {
        obj=d;
    }
    public void run() {
        int n;
        n=obj.read();
        System.out.println("First read"+" "+Thread.currentThread().getName()+" "+new Integer(n).toString());
        obj.write();
        System.out.println("Write ... "+" "+Thread.currentThread().getName()+" "+new Integer(n).toString());
        n=obj.read();
        System.out.println("Second read"+" "+Thread.currentThread().getName()+" "+new Integer(n).toString());
    }
}

class Data {
    private int count=0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock rl =   lock.readLock();
    private Lock wl =   lock.writeLock();
    int read(){
        rl.lock();
        try {
            int n = count;
            TimeUnit.MILLISECONDS.sleep(400);
            count=n;
        }
        catch (InterruptedException ex) { }
        finally {
            rl.unlock();
        }

        return count;
    }
    void write(){
        wl.lock();
        try {
            int n = count;
            TimeUnit.MILLISECONDS.sleep(400);
            n++;
            count=n;
        }
        catch (InterruptedException ex) { }
        finally {
            wl.unlock();
        }
    }
}

