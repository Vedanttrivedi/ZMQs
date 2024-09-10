package pubsub;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class XPublisher
{
    public static void main(String[] args)
    {
        try(ZContext context = new ZContext())
        {

            ZMQ.Socket socket = context.createSocket(SocketType.XPUB);

            socket.bind("tcp://*:4567");

            while (true)
            {
                // Listen for subscription updates from subscribers
                byte[] message = socket.recv(0);

                if (message != null)
                {
                    String subscription = new String(message, ZMQ.CHARSET);

                    if (subscription.startsWith("MH:"))
                    {
                        System.out.println("Subscriber subscribed to: " + subscription);

                        socket.send("Hey buddy we are connected".getBytes(ZMQ.CHARSET));

                        System.out.println("Message Sent!!!");
                    }

                    else
                    {
                        System.out.println("Subscriber unsubscribed from: " + subscription.substring(1));
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
