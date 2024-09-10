package pubsub;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class XSubscriber
{
    public static void main(String[] args)
    {
        try (ZContext context = new ZContext())
        {

            ZMQ.Socket socket = context.createSocket(SocketType.XSUB);

            socket.connect("tcp://localhost:4567");

            socket.send("MH:".getBytes(ZMQ.CHARSET));

            System.out.println("Messge Sent!");

            while(true)
            {

                String message = socket.recvStr();

                System.out.println("Message recied from xpub "+message);

            }
        }
    }
}
