import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.time.Year.isLeap;

public final class Veletlen {
    private Veletlen() {
    }

    private static final Random rnd = new Random();
    private static final List<String> vezNevek = feltolt("files/veznev.txt");
    private static final List<String> ferfiKerNevek = feltolt("files/ferfikernev.txt");
    private static final List<String> noiKerNevek = feltolt("files/noikernev.txt");

    private static List<String> feltolt(String fileName) {
        List<String> lista = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(fileName));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                lista.add(line);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.err);
        }
        return lista;
    }

    public static int velEgesz(int min, int max) {
        return rnd.nextInt(max - min + 1) + min;
    }

    public static char velKarakter(char min, char max) {
        return (char) velEgesz(min, max);
    }

    public static String velVezetekNev() {
        return vezNevek.get(rnd.nextInt(vezNevek.size()));
    }

    /**
     * Véletlen magyar keresztnév generálása
     *
     * @param nem A generált név neme. Férfi: true, Nő: false.
     * @return A generált keresztnév
     */
    public static String velKeresztNev(boolean nem) {
        String keresztNev;
        if (nem) {
            keresztNev = velFerfiKeresztNev();
        } else {
            keresztNev = velNoiKeresztNev();
        }
        return keresztNev;
    }

    private static String velFerfiKeresztNev() {
        return ferfiKerNevek.get(rnd.nextInt(ferfiKerNevek.size()));
    }

    private static String velNoiKeresztNev() {
        return noiKerNevek.get(rnd.nextInt(noiKerNevek.size()));
    }

    /**
     * Véletlen magyar nevek generálása
     *
     * @param nem A generált név neme. Férfi: true, Nő: false.
     * @return A generált név
     */
    public static String velTeljesNev(boolean nem) {
        return velVezetekNev() + " " + velKeresztNev(nem);
    }

    public static String velDatum(int ev1, int ev2) {
        int year = velEv(ev1, ev2);
        int month = velHonap();
        int day = velNap(year, month);
        return year + "-" + month + "-" + day;
    }

    private static int velEv(int min, int max) {
        return velEgesz(min, max);
    }

    private static int velHonap() {
        return velEgesz(1, 12);
    }

    private static int velNap(int year, int month) {
        int day;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = velEgesz(1, 31);
                break;
            case 2:
                if (isLeap(year)) {
                    day = velEgesz(1, 29);
                } else {
                    day = velEgesz(1, 28);
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:

                day = velEgesz(1, 30);
                break;
            default:
                throw new RuntimeException("Nem egy valós hónap lett megadva!");
        }
        return day;
    }

    public static String velEmail(String nev) {
        String email;
        email = removeAccent(normalize(nev)).replaceAll("\\s+", "") + velEgesz(1, 100) + "@gmail.com";
        return email;
    }

    public static String normalize(String s) {
        return s == null ? null : Normalizer.normalize(s, Normalizer.Form.NFKD);
    }

    public static String removeAccent(String s) {
        return normalize(s).replaceAll("\\p{M}", "").toLowerCase();
    }


    public static String velMobil() {
        int szolgaltato;
        int random = velEgesz(0, 3);
        if (random == 0) {
            szolgaltato = 20;
        } else if (random == 1) {
            szolgaltato = 30;
        } else if (random == 2) {
            szolgaltato = 31;
        } else {
            szolgaltato = 70;
        }
        return String.format("+36 (%d) %d%d%d-%d%d-%d%d",
                szolgaltato, velEgesz(0, 9), velEgesz(0, 9), velEgesz(0, 9), velEgesz(0, 9), velEgesz(0, 9), velEgesz(0, 9), velEgesz(0, 9));
    }
}
