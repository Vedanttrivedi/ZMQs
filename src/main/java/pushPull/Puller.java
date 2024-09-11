package pushPull;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Puller
{
    public static void main(String[] args)
    {

        try(ZContext context = new ZContext())
        {

            ZMQ.Socket socket = context.createSocket(ZMQ.PULL);

            socket.connect("tcp://localhost:6800");

            while(true)
            {
                System.out.println("About to receive message!");

                var message = socket.recvStr();

                System.out.println("Push Server : "+message);
            }
        }
    }
}


