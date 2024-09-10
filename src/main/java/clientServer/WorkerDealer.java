package clientServer;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.SocketType;

public class WorkerDealer
{
    public static void main(String[] args)
    {
        try (ZContext context = new ZContext())
        {
            ZMQ.Socket socket = context.createSocket(SocketType.DEALER);
            socket.connect("tcp://localhost:5556"); // Connect to the broker

            while (!Thread.currentThread().isInterrupted()) {
                String message = socket.recvStr();  // Receive message from broker
                System.out.println("Processing: " + message);

                // Simulate processing work
                String response = "Processed " + message;
                socket.send(response);  // Send processed response back to broker
            }
        }
    }
}
