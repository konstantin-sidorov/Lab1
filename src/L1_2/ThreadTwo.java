package L1_2;

public class ThreadTwo implements Runnable
{
    private Thread thread;
    private Monitor monitor;

    public Thread getThread () {
        return thread;
    }

    public ThreadTwo(Monitor monitor) {
        thread = new Thread (this);
        this.monitor=monitor;
    }
    
    public void run() {
        try {
            //Thread.sleep(1);
            for (int i = 0; i < TicTak.num; i++) {
                synchronized (monitor){
                    while (monitor.x!=2){
                        monitor.wait();
                    }
                    System.out.print(2+" - ");
                    monitor.x++;
                    monitor.notifyAll();
                }
            }
        }
        catch (InterruptedException e) { e.printStackTrace(); }
    }
}
