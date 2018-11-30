package L1_2;

public class ThreadOne extends Thread {
  private Monitor monitor;
    public ThreadOne(Monitor monitor) {
        this.monitor=monitor;
    }


    public void run () {
        try
        {
            for (int i = 0; i < TicTak.num; i++) {
                synchronized (monitor){
                    while (monitor.x!=1){
                        monitor.wait();
                    }
                    System.out.print(1+" - ");
                    monitor.x++;
                    monitor.notifyAll();
                }
            }
        }
        catch (InterruptedException e) { e.printStackTrace(); }
    }
}
