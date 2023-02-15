package TempCalc;
import java.util.ArrayList;
/**
 *
 * @author Retama
 */
public class Segmentar {
   
    /*
    public static boolean esOperador(char operador){
        return operador == '+' || operador == '-' || operador == '*' || operador == '/' || operador == '^';
    }
    */
    public static boolean esDigito(char car){
        return car == '1' || car == '2' || car == '3' || car == '4' || car == '5' || car == '6' || 
                car == '7' || car == '8' || car == '9' || car == '0' || car == '.';
    }
    
    public static ArrayList<String> segmenta(String cadena){
        ArrayList <String> resultado = new ArrayList<>();
        String temp = "";
        char item;
        int i=0;
        while (i<cadena.length()){
            item = cadena.charAt(i);
            if (esDigito(item) || item=='-' && temp.equals(""))
                temp += item;   
            else{
                if (!temp.equals(""))
                    resultado.add(temp);
                resultado.add(""+item);
                temp = "";
            }
            i++;
        }
        if (!temp.equals(""))
            resultado.add(temp);
        return resultado;
    }
    
    public static void main(String[] args) {
        
        System.out.println(segmenta("-32.7+34.58-(24*(234))/531").toString());
        System.out.println(segmenta("32+(34-24*-234/-531)").toString());
        System.out.println(segmenta("(32+34-24.89)*234/531").toString());
        
    }
    
    
}
