package tester;

public class T3 extends Thread
{
    public static int counter=0;
    public  void increment()
    {
        synchronized (T3.class)
        {

            counter++;
        }
    }
    public void run()
    {
        for (int i = 1; i <=1000 ; i++)
        {
            increment();
        }
    }
    public static void main(String[] args) throws Exception
    {
        Thread t1 = new T3();
        Thread t2 = new T3();

        Thread t3 = new T3();
        Thread t4 = new T3();

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("Counter :> "+counter);
    }
}
