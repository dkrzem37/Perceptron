import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class OperacjePliki {
    public static int podajIleWymiarow(String path){
        try {
            File plik = new File(path);
            Scanner scanner = new Scanner(plik);
            if(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] dane = line.split(",");

                return dane.length - 1;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
    public static TreeSet<String> zwróćWartościNominalne(String path){
        TreeSet<String> wart = new TreeSet<>();
        try {
            File plik = new File(path);
            Scanner scanner = new Scanner(plik);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] dane = line.split(",");

                if(wart.size()==2){
                    break;
                }else{
                    wart.add(dane[dane.length - 1]);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return wart;
    }
    public static Map<String, ArrayList<float[]>> plikNaMape(String path){
        Map<String, ArrayList<float[]>> mapaPliku = new HashMap<>();
        try {
            File plik = new File(path);
            Scanner scanner = new Scanner(plik);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] dane = line.split(",");

                float[] f = new float[dane.length - 1];
                for(int i = 0; i< f. length; i++){
                    f[i] = Float.parseFloat(dane[i]);
                }

                if(!mapaPliku.containsKey(dane[dane.length - 1])){
                    mapaPliku.put(dane[dane.length - 1], new ArrayList<>());
                    mapaPliku.get(dane[dane.length - 1]).add(f);
                }else{
                    //create a vector
                    mapaPliku.get(dane[dane.length - 1]).add(f);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return mapaPliku;
    }

    public static ArrayList<String> plikNaListe(String path){
        ArrayList<String> temp = new ArrayList<>();
        try {
            File plik = new File(path);
            Scanner scanner = new Scanner(plik);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                temp.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return temp;
    }

    public static ArrayList<String> mapaNaListe(Map<String, ArrayList<float[]>> map){
        ArrayList<String> listaReturn = new ArrayList<>();
        for(Map.Entry<String, ArrayList<float[]>> entry : map.entrySet()){
            for(float[] tab : entry.getValue()){
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i< tab.length; i++){
                    sb.append(tab[i]);
                    sb.append(",");
                }
                sb.append(entry.getKey());
                listaReturn.add(sb.toString());
            }
        }
        return listaReturn;
    }
}
