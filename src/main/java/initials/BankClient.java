package initials;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.time.LocalDateTime;
import java.util.Random;

public class BankClient
{
    public static Long randomLong(long m,long n)
    {
        Random random = new Random();
        return random.nextLong(m,n);

    }
    public static void main(String[] args)
    {
        User user = new User(1,"Vedant",10000);

        try(ZContext context = new ZContext())
        {
            ZMQ.Socket socket = context.createSocket(ZMQ.REQ);

            socket.connect("tcp://localhost:5566");

            while(true)
            {
                socket.send(new Transaction(
                        randomLong(1L,10000L),
                        user.getUsername(),
                        LocalDateTime.now().toString()
                        ).toString().getBytes());

                System.out.println("data sent to server");
                var data = socket.recvStr();
                System.out.println("Server : "+data);
            }
        }
    }
}
