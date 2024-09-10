package initials;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.concurrent.ConcurrentHashMap;

public class BankServer
{
    public static void main(String[] args)
    {
        ConcurrentHashMap<Long,User> users = new ConcurrentHashMap<>();

        users.put(1L,new User(1,"Vedant",10000));

        users.put(2L,new User(1,"Virat",5000));

        try(ZContext context = new ZContext())
        {
            ZMQ.Socket socket  = context.createSocket(SocketType.REP);

            socket.bind("tcp://*:5566");

            System.out.println("Server is bound");

            while(true)
            {
                //check for client request and reply according to that whethere transaction is possible or not
                var message = socket.recv();
                System.out.println("Recevied Transaction "+new String(message,ZMQ.CHARSET));

                socket.send("Okay");
            }
        }
    }
}
