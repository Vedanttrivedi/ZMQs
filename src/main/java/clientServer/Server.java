package clientServer;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Server
{
    static {
        System.out.println("Class loaded ");
    }
    public static void main(String[] args)
    {
        System.out.println("Mainer");
        try(ZContext context = new ZContext())
        {
            ZMQ.Socket socket = context.createSocket(SocketType.REP);

            System.out.println("main program");

            socket.bind("tcp://*:5678");

            System.out.println("Server is binded to port 5000");

            for(int i=0;i < 10;i++)
            {
                byte[] recv = socket.recv(0);

                if(recv!=null)
                    System.out.println("received from client "+ new String(recv,ZMQ.CHARSET));

                String message = "Hey you are connected ";

                socket.send(message.getBytes(ZMQ.CHARSET),0);

            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
}
