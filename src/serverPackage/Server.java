
package serverPackage;


import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        InetAddress ip = InetAddress.getByName("192.168.1.4");
        InetSocketAddress adresse = new InetSocketAddress(ip, 1234);

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(adresse);

        System.out.println("Je suis un serveur en attente");

        Socket socket = serverSocket.accept();

        System.out.println("Un client est connecté");

        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        InputStream is=socket.getInputStream();
        DataInputStream dis=new DataInputStream(is);
        while(true) {

           int ch =dis.readInt();
            if(ch==0)
            {
                System.out.println("le client a mis 0 donc on va perdu la comminication");
                break;
            }
            double a=dis.readDouble();
            double b=dis.readDouble();
            double r=0;
            boolean v=true;
            switch (ch) {
                case 1:
                    r = a + b;
                    break;
                case 2:
                    r = a - b;
                    break;
                case 3:
                    r = a * b;
                    break;
                case 4:
                    if (b == 0) {
                        v = false;
                    } else {
                        r = a / b;
                    }
                    break;
                default:
                    v = false;
                    break;
            }
                System.out.println("Choix reçu : "+ch);
                System.out.println("Nombres reçus : " + a + " et " + b);
            dos.writeBoolean(v);

            if (v) {
                dos.writeDouble(r);
                    System.out.println("Résultat envoyé : " + r);
                } else {
                    System.out.println("Opération invalide.");
                }

            }
        dis.close();
        dos.close();
        socket.close();
        serverSocket.close();
    }

}
