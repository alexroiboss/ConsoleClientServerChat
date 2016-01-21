import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Run this program as a [S]erver or a [C]lient? ");
        while (true) {
            char answer = Character.toLowerCase(sc.nextLine().charAt(0));
            if (answer == 's') {
                new Server();
                break;
            } else if (answer == 'c') {
                new Client();
                break;
            } else {
                System.out.println("Invalid input");
            }
        }

        sc.close();
    }
}
