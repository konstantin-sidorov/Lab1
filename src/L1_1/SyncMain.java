package L1_1;

class Data
{
    int count =0;
    static int countSt =0;
}

class MyThread implements Runnable {
    private Data obj;

    MyThread(Data d){
        obj = d;
        new Thread(this).start();
    }

    void Add(){

            try {
                synchronized (obj) {
                    //System.out.println("Start Add of thread:"+Thread.currentThread().getName());
                    Thread.sleep(5);
                    int n = obj.count;
                    n++;
                    Thread.sleep(5);
                    obj.count = n;
                    //System.out.println("End Add "+obj.count+" thread:"+Thread.currentThread().getName());
                }
            } catch (InterruptedException ex) {
            }
    }

    static void AddStatic(Class clazz) {
        synchronized (clazz) {
            //System.out.println("*** Start AddStatic thread:" + Thread.currentThread().getName());
            try {
                Thread.sleep(5);
                int n = Data.countSt;
                n++;
                Thread.sleep(5);
                Data.countSt = n;
            } catch (InterruptedException ex) {
            }
            //System.out.println("*** End AddStatic " + Data.countSt + " thread:" + Thread.currentThread().getName());
        }
    }
    @Override
    public void run() {
        for(int i=0; i<10; i++) Add();
        for(int i=0; i<10; i++) AddStatic(obj.getClass());
    }
}

public class SyncMain {

    public static void main(String[] args) throws Exception {
        Data d=new Data();
        MyThread t1=new MyThread(d);
        MyThread t2=new MyThread(d);

        Thread.sleep(3000);
        System.out.println(d.count);
        System.out.println(Data.countSt);
    }
}
