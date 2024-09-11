package pubsub;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Publisher
{
    public static void main(String[] args)
    {

        try(ZContext context = new ZContext())
        {
            ZMQ.Socket socket = context.createSocket(SocketType.PUB);

            socket.bind("tcp://localhost:5002");

            ZMQ.Socket socket1 = context.createSocket(SocketType.SUB);

            socket1.connect("tcp://localhost:5002");

            socket1.subscribe("Update:".getBytes(ZMQ.CHARSET));

            boolean flag = true;
            var counter=0;
            while(flag)
            {
                System.out.println("In Loop");

                socket.send("Update: This is broadcast message"+counter+" ".getBytes(ZMQ.CHARSET),0);

//                byte[] recvMessge = socket1.recv();

                try
                {
                  Thread.sleep(1000);
                    counter++;
                }
                catch (InterruptedException ie)
                {
                    System.out.println(ie);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
