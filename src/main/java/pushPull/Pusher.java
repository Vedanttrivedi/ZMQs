package pushPull;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Pusher
{
    public static void main(String[] args)
    {
        try(ZContext context = new ZContext())
        {

            ZMQ.Socket socket = context.createSocket(ZMQ.PUSH);

            socket.bind("tcp://*:5678");

            int messageNumber = 0;

            while(true)
            {
                try
                {
                    var message = "Message "+messageNumber;

                    socket.send(message.getBytes(ZMQ.CHARSET));

                    System.out.println("Message Sent!");

                    messageNumber++;

                    Thread.sleep(1000);

                }
                catch (Exception e)
                {

                    System.out.println(e);

                }
            }
        }
    }
}
