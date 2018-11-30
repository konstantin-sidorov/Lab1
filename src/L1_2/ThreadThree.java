package L1_2;

public class ThreadThree implements Runnable{
    private Thread thread;
    private Monitor monitor;

    public Thread getThread () {
        return thread;
    }

    public ThreadThree(Monitor monitor) {
        thread = new Thread (this);
        this.monitor=monitor;
    }

    public void run() {
        try {
            //Thread.sleep(1);
            for (int i = 0; i < TicTak.num; i++) {
                synchronized (monitor){
                    while (monitor.x!=3){
                        monitor.wait();
                    }
                    System.out.println(3);
                    monitor.x=1;
                    monitor.notifyAll();
                }
            }
        }
        catch (InterruptedException e) { e.printStackTrace(); }
    }
}
