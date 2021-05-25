import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

import javax.cache.Cache;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;


public class Main {
    final private static Random r = new Random(System.currentTimeMillis());
    public static void main(String[] args) throws IOException, ParseException {

        Ignite ignite = Ignition.start();
        IgniteCache<Long, Klient> klientMap = ignite.getOrCreateCache("klientMap");
        IgniteCache<Long, Podroz> podrozMap = ignite.getOrCreateCache("podrozMap");



        while(true){
            System.out.println("\nWybierz opcje:");
            System.out.println("1. Dodawanie.");
            System.out.println("2. Edycja.");
            System.out.println("3. Usuwanie.");
            System.out.println("4. Wyświetlanie wszystkich elementów.");
            System.out.println("5. Wyświetlanie po ID.");

            Scanner scan = new Scanner(System.in);
            int s = scan.nextInt();

            switch(s){
                case 1:
                    int n = wyborTabeli();
                    if(n==1){
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Podaj imie:");
                        String imie = scanner.next();

                        System.out.println("Podaj nazwisko:");
                        String nazwisko = scanner.next();

                        System.out.println("Podaj pesel:");
                        String pesel = scanner.next();

                        Long key1 = (long) Math.abs(r.nextInt());
                        Klient klient = Klient.builder().imie(imie).nazwisko(nazwisko).pesel(pesel).build();
                        klientMap.put(key1, klient);
                        break;
                    }
                    if(n==2){
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Podaj kraj:");
                        String kraj = scanner.next();
                        Long key2 = (long) Math.abs(r.nextInt());
                        System.out.println("Podaj id klienta:");
                        Long klientId = scanner.nextLong();
                        Klient klient = null;
                        klient = klientMap.get(klientId);
                        Podroz podroz = Podroz.builder().kraj(kraj).klient(klient).build();
                        podrozMap.put(key2, podroz);
                        break;
                    }
                    break;
                case 2:
                    int q = wyborTabeli();
                    if(q==1){
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Podaj ID:");
                        Long idK = scanner.nextLong();


                        System.out.println("Podaj imie:");
                        String imie = scanner.next();

                        System.out.println("Podaj nazwisko:");
                        String nazwisko = scanner.next();

                        System.out.println("Podaj pesel:");
                        String pesel = scanner.next();

                        Klient klient = Klient.builder().imie(imie).nazwisko(nazwisko).pesel(pesel).build();
                        klientMap.put(idK, klient);
                    }

                    if(q==2){
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Podaj ID:");
                        Long idP = scanner.nextLong();


                        System.out.println("Podaj kraj:");
                        String kraj = scanner.next();

                        System.out.println("Podaj id klienta:");
                        Long idK = scanner.nextLong();

                        Klient klient = null;
                        klient = klientMap.get(idK);

                        Podroz podroz = Podroz.builder().kraj(kraj).klient(klient).build();
                        podrozMap.put(idP, podroz);
                    }
                    break;

                case 3:

                    int l = wyborTabeli();
                    if(l==1){
                        System.out.println("Podaj ID:");
                        Long klientID = scan.nextLong();

                        klientMap.remove(klientID);

                        break;
                    }
                    if(l==2){
                        System.out.println("Podaj ID:");
                        Long podrozID = scan.nextLong();

                        podrozMap.remove(podrozID);

                        break;
                    }


                    break;

                case 4:
                    int k = wyborTabeli();
                    if(k==1){
                        for(Cache.Entry<Long, Klient> e : klientMap){
                            System.out.println(e.getKey() + ", " + e.getValue() + ", ");
                        }
                        break;
                    }
                    if(k==2){
                        for(Cache.Entry<Long, Podroz> e : podrozMap){
                            System.out.println(e.getKey() + ", " + e.getValue() + ", ");
                        }
                        break;
                    }
                    break;

                case 5:
                    int u = wyborTabeli();
                    if(u==1){
                        System.out.println("Podaj ID:");
                        Scanner scanner = new Scanner(System.in);
                        Long klientID = scanner.nextLong();

                        Klient klient = klientMap.get(klientID);
                        System.out.println(klientID + ", " + klient.toString());

                        break;
                    }
                    if(u==2){
                        System.out.println("Podaj ID:");
                        Scanner scanner = new Scanner(System.in);
                        Long podrozID = scanner.nextLong();

                        Podroz podroz = podrozMap.get(podrozID);
                        System.out.println(podrozID + ", " + podroz.toString());
                        break;
                    }

                    break;
            }
        }
    }
    public static Integer wyborTabeli() {
        System.out.println("\nWybierz tabele:");
        System.out.println("1. Klient");
        System.out.println("2. Podroz");
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }
}
