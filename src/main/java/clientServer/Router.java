package clientServer;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.SocketType;

public class Router
{
    public static void main(String[] args)
    {
        try (ZContext context = new ZContext())
        {
            // Create a ROUTER socket to handle incoming client requests
            ZMQ.Socket frontend = context.createSocket(SocketType.ROUTER);
            frontend.bind("tcp://*:4444");

            // Create a DEALER socket to handle worker connections
            ZMQ.Socket backend = context.createSocket(SocketType.DEALER);
            backend.bind("tcp://*:4446");

            // Proxy all messages between frontend (clients) and backend (workers)
         //   ZMQ.proxy(frontend, backend, null);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
