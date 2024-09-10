package clientServer;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Client
{
    public static void main(String[] args)
    {
        try(ZContext context = new ZContext())
        {
            ZMQ.Socket socket = context.createSocket(SocketType.REQ);

            socket.connect("tcp://*:5000");

            for(int i=0;i < 10;i++)
            {
                String message = "Hey Server I am connected";

                socket.send(message.getBytes(ZMQ.CHARSET),0);
                //socket.send(message.getBytes(ZMQ.CHARSET),0);

                byte[] response = socket.recv(0);

                if(response!=null)
                    System.out.println("Server : "+new String(response,ZMQ.CHARSET));

            }
        }
        catch (Exception e)
        {
            System.out.println("Error "+e);
        }
    }
}
