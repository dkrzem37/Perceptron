import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        float waga =  0.1f;
        float próg =  0.1f;

        System.out.println("Podaj ścieżkę do pliku treningowego:");
        Scanner scanner = new Scanner(System.in);
        String sciezkaPlikTreningowy = scanner.nextLine();
        System.out.println("Podaj ścieżkę do pliku testowego:");
        String sciezkaPlikTestowy = scanner.nextLine();
        Map<String, ArrayList<float[]>> mapaPlikTreningowy = OperacjePliki.plikNaMape(sciezkaPlikTreningowy);
        Map<String, ArrayList<float[]>> mapaPlikTestowy = OperacjePliki.plikNaMape(sciezkaPlikTestowy);

        ArrayList<String> listaPlikTestowy = OperacjePliki.mapaNaListe(mapaPlikTestowy);

        //Tworzenie i przypisywanie wag
        Perceptron.wagi = new float[OperacjePliki.podajIleWymiarow(sciezkaPlikTreningowy) + 1];
        for(int i = 0; i< Perceptron.wagi.length; i++){
            if(i == Perceptron.wagi.length - 1){
                Perceptron.wagi[i] = próg;
                continue;
            }
            Perceptron.wagi[i] = waga;
        }
        //
        Perceptron.wartosciNominalne = OperacjePliki.zwróćWartościNominalne(sciezkaPlikTreningowy);

        System.out.println("Podaj stałą uczenia:");
        float stałaUczenia = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Podaj liczbę epok:");
        int liczbaEpok = scanner.nextInt();
        scanner.nextLine();
        float dokladnosc = 0;
        for(int i = 0; i < liczbaEpok; i++){
            Perceptron.trenuj(mapaPlikTreningowy, stałaUczenia);
            dokladnosc = Perceptron.sprawdzDokladnosc(listaPlikTestowy,mapaPlikTestowy);
            System.out.println("Dokladnosc wynosi: " + dokladnosc);
        }
        while(true){
            System.out.println("1. Wprowadz nowe obserwacje do zaklasyfikowania.");
            System.out.println("2. Zakończ program.");
            int wyborMenu = scanner.nextInt();
            scanner.nextLine();
            switch(wyborMenu){
                case 1:
                    System.out.println("Podaj wyniki obserwacji oddzielone spacjami:");
                    String temp1 = scanner.nextLine();
                    String[] temp2 = temp1.split(" ");
                    float[] wynikiObserwacji = new float[temp2.length];
                    for(int i = 0; i< wynikiObserwacji.length; i++){
                        wynikiObserwacji[i] = Float.parseFloat(temp2[i]);
                    }
                    System.out.println(Perceptron.klasyfObserwacji(wynikiObserwacji));

                    break;

                case 2:
                    scanner.close();
                    System.exit(1);
                    break;
                default:

            }

        }

    }
}