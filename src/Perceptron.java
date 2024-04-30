import java.util.*;

public class Perceptron {
    public static float[] wagi;
    public static TreeSet<String> wartosciNominalne;


    public static void trenuj(Map<String, ArrayList<float[]>> mapaPlikTreningowy, float stałaUczenia){

        for(Map.Entry<String, ArrayList<float[]>> entry : mapaPlikTreningowy.entrySet()){
            for(float[] tab : entry.getValue()){
                int d;
                //Dodanie -1 do konca macierzy X
                float[] temp = new float[tab.length + 1];
                for(int i = 0; i < temp.length; i++){
                    if(i == temp.length - 1){
                        temp[i] = -1;
                    }else{
                        temp[i] = tab[i];
                    }
                }
                String zaklas = klasyfObserwacji(tab);
                int y = zwróćWartośćY(tab);

                if(entry.getKey().equals(wartosciNominalne.last())){
                    d = 1;
                }else{
                    d = 0;
                }
                for(int i = 0; i< wagi.length; i++){
                    wagi[i] += (d - y) * stałaUczenia *temp[i];
                }
            }
        }
    }

    public static String klasyfObserwacji(float[] wynikiObserwacji){
        float wartoscY = 0;
        //Dodanie -1 do konca macierzy X
        float[] wynikiObsD = new float[wynikiObserwacji.length + 1];
        for(int i = 0; i < wynikiObsD.length; i++){
            if(i == wynikiObsD.length - 1){
                wynikiObsD[i] = -1;
            }else{
                wynikiObsD[i] = wynikiObserwacji[i];
            }
        }

        for(int i = 0; i < wynikiObsD.length; i++){
            wartoscY += wynikiObsD[i] * wagi[i];
        }
        if(wartoscY >= 0){
            return wartosciNominalne.last();
        }else{
            return wartosciNominalne.first();
        }
    }

    public static int zwróćWartośćY(float[] wynikiObserwacji){
        int wartoscY = 0;
        //Dodanie -1 do konca macierzy X
        float[] wynikiObsD = new float[wynikiObserwacji.length + 1];
        for(int i = 0; i < wynikiObsD.length; i++){
            if(i == wynikiObsD.length - 1){
                wynikiObsD[i] = -1;
            }else{
                wynikiObsD[i] = wynikiObserwacji[i];
            }
        }

        for(int i = 0; i < wynikiObsD.length; i++){
            wartoscY += wynikiObsD[i] * wagi[i];
        }
        if(wartoscY >= 0){
            return 1;
        }else{
            return 0;
        }
    }

    public static float sprawdzDokladnosc(ArrayList<String> listaPlikTestowy, Map<String, ArrayList<float[]>> mapaPlikTestowy){
        ArrayList<String> zaklasyfikowane = new ArrayList<>();
        for(Map.Entry<String, ArrayList<float[]>> entry : mapaPlikTestowy.entrySet()){
            for(float[] tab : entry.getValue()){
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i< tab.length; i++){
                    sb.append(tab[i]);
                    sb.append(",");
                }
                sb.append(klasyfObserwacji(tab));
                zaklasyfikowane.add(sb.toString());
            }
        }

        int prawidlowo = 0;
        int wszystkie = 0;
        for(String s: zaklasyfikowane){
            if(listaPlikTestowy.contains(s)){
                prawidlowo++;
            }
            wszystkie++;
        }
        return ((float)prawidlowo/(float)wszystkie) * 100;
    }
}
