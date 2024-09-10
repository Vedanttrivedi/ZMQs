package pubsub;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Subscriber
{
    public static void main(String[] args)
    {
        try(ZContext context = new ZContext())
        {
            ZMQ.Socket socket = context.createSocket(SocketType.SUB);

            socket.connect("tcp://localhost:5002");

            socket.subscribe("Update:");

            while (true)
            {
                System.out.println("Hello");
                byte[] message = socket.recv(0);

                System.out.println("Publisher : "+ new String(message,ZMQ.CHARSET));
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
