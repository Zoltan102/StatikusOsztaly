import java.sql.SQLOutput;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Veletlen.velEgesz(5, 10));
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(Veletlen.velKarakter('a', 'z'));
        }
        for (int i = 0; i < 10; i++) {
            Random rnd = new Random();
            System.out.println(Veletlen.velTeljesNev(rnd.nextBoolean()));
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(Veletlen.velDatum(2021, 2021));
        }
        for (int i = 0; i < 10; i++) {
            Random rnd = new Random();
            System.out.println(Veletlen.velEmail(Veletlen.velTeljesNev(rnd.nextBoolean())));
        }
        for (int i = 0; i < 10; i++) {
            Random rnd = new Random();
            System.out.println(Veletlen.velMobil());
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(Veletlen.velSportegyesulet());
        }
    }
}