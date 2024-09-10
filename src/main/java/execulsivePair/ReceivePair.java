package execulsivePair;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class ReceivePair
{
    public static void main(String[] args)
    {
        try(ZContext context = new ZContext())
        {

            ZMQ.Socket socket = context.createSocket(SocketType.PAIR);

            socket.connect("tcp://*:4500");

            while(true)
            {
                var message = socket.recvStr();

                System.out.println("Client message : "+message);

                socket.send("hey server i am connected!");

            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
