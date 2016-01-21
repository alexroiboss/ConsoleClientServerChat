import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;

    public Client() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter IP address for connecting to server: ");

        String ip = sc.nextLine();

        try {
            socket = new Socket(ip, Server.PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Enter your nickname: ");
            out.println(sc.nextLine());

            Resender resend = new Resender();
            resend.start();

            String str = "";
            while(!str.equals("exit")) {
                str = sc.nextLine();
                out.println(str);
            }
            resend.setStop();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch(Exception e) {
            System.err.println("Threads have not been closed.");
        }
    }

    private class Resender extends Thread {
        private boolean stopped = false;

        public void setStop() {
            stopped = true;
        }

        @Override
        public void run() {
            try {
                while(!stopped) {
                    String str = in.readLine();
                    System.out.println(str);
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
