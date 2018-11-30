package L1_2;

public class TicTak {
    static int num=20;

    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        ThreadOne   thr1 = new ThreadOne (monitor);
        ThreadTwo   thr2 = new ThreadTwo (monitor);
        ThreadThree thr3 = new ThreadThree(monitor);

        thr1.start();
        thr2.getThread().start();
        thr3.getThread().start();

        try {
            thr1.join();
            thr2.getThread().join();
            thr3.getThread().join();
        }
        catch (InterruptedException e) { e.printStackTrace();}
    }
}
class Monitor{
    int x=1;
}