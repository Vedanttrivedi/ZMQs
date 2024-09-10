package execulsivePair;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class SentPair
{
    public static void main(String[] args)
    {
        try(ZContext context = new ZContext())
        {

            ZMQ.Socket socket = context.createSocket(ZMQ.PAIR);

            System.out.println("Socket Information");

            socket.bind("tcp://*:4500");

            while(true)
            {

                socket.send("Hello Client ".getBytes(ZMQ.CHARSET));

                System.out.println("Blocked");

                var message = socket.recvStr();

                System.out.println("Client : "+message);

            }
        }
    }
}
