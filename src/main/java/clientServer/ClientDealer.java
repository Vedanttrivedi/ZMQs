package clientServer;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.SocketType;

public class ClientDealer
{
    public static void main(String[] args)
    {
        try (ZContext context = new ZContext())
        {
            ZMQ.Socket socket = context.createSocket(SocketType.DEALER);
            socket.connect("tcp://localhost:5555"); // Connect to the broker

            for (int requestNumber = 0; requestNumber < 5; requestNumber++) {
                String request = "Request " + requestNumber;
                System.out.println("Sending: " + request);
                socket.send(request);  // Send request to the broker

                // Can send multiple requests without waiting for replies
            }

            for (int requestNumber = 0; requestNumber < 5; requestNumber++)
            {
                String reply = socket.recvStr(); // Receive reply asynchronously
                System.out.println("Received: " + reply);
            }
        }
    }
}

