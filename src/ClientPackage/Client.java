package ClientPackage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
    static void main(String[] args) throws IOException {
        System.out.println("je suis un client pas encore connecte");

        Socket socket = new Socket("192.168.1.4", 1234);

        System.out.println("je suis un client  connecte");

        Scanner sca = new Scanner(System.in);

        OutputStream os = socket.getOutputStream();

        DataOutputStream dos = new DataOutputStream(os);

        InputStream is = socket.getInputStream();

        DataInputStream dis = new DataInputStream(is);
        int ch;
        do {
            System.out.println("\n CALCULATRICE TCP ");
            System.out.println("1. Addition");
            System.out.println("2. Soustraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            ch = sca.nextInt();
            dos.writeInt(ch);
            System.out.println("le chois envoyer au serveur ");
            if (ch == 0) {
                System.out.println("Fin de la comminication serveur et client");
                break;
            }
            if (ch < 1 || ch > 4) {
                System.out.println("Choix invalide.");
            }

            System.out.print("Saisir le premier nombre : ");
            double a = sca.nextDouble();

            System.out.print("Saisir le deuxième nombre : ");
            double b = sca.nextDouble();

            // Envoyer les nombres au serveur
            dos.writeDouble(a);
            dos.writeDouble(b);
            System.out.println("les 2 nombres envoyer au serveur ");
            boolean valide = dis.readBoolean();
            if (valide) {
                double resultat = dis.readDouble();
                System.out.println("Résultat du serveur : " + resultat);
            } else {
                System.out.println(
                        "Erreur : opération invalide ou division par zéro.");
            }
        } while (ch != 0);

        System.out.println("la comminication de la serveur et client se terminer ");
        dos.close();
        dis.close();
        sca.close();
        socket.close();
    }
}
