/*+
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodos;
import java.util.ArrayList;
import Pilas.*;

/**
 *
 * @author Alexa
 */

public class InfijoAPostfijo {    
    //métodos estáticos
    public static ArrayList<String> convertirPostfijo(String infija){
        //método que convierte una cadena de infija a postfija.
        ArrayList<String> resultado=new ArrayList();
        ArrayList<String> cadenaSegmentada= segmenta(infija);
        PilaADT<String> elementos=new PilaA<>();
        int i=1, j=0;
            elementos.push(cadenaSegmentada.get(0));
        while(i<cadenaSegmentada.size()){
            if(determinarOperador(cadenaSegmentada.get(i))){
                while(!elementos.isEmpty() && determinarPrioridad(cadenaSegmentada.get(i)) <= Integer.parseInt(elementos.peek()))
                    resultado.add(elementos.pop());
                elementos.push(cadenaSegmentada.get(i));
            } else {
            }
            if(cadenaSegmentada.get(i) == "(")
                elementos.push(cadenaSegmentada.get(i));
            if(cadenaSegmentada.get(i) == ")"){
                while(!"(".equals(elementos.peek()))
                    resultado.add(elementos.pop());
                elementos.pop();
            }
            i++;
        }
        while(!elementos.isEmpty()){
            resultado.add(cadenaSegmentada.get(j));
            j++;
        }
        
        return resultado;
    }
    
    public static int determinarPrioridad(String operador){
        //método que determina la prioridad de los operadores.
        int resp=-1;
        switch(operador){
            case "^" -> resp=1;
            case "*" -> resp=2;
            case "/" -> resp=2;
            case "+" -> resp=3;
            case "-" -> resp=3;
        }
        return resp;
    }
    public static boolean determinarOperador(String operador){
        //método que determina si es un operador.
        boolean resp=false;
        if(operador == "+" || operador == "-" || operador == "*" || operador == "/" || operador == "^")
            resp=true;
        return resp;
    }
     public static boolean esDigito(char car){
         //método que determina si es un dígito.
        return car == '1' || car == '2' || car == '3' || car == '4' || car == '5' || car == '6' || 
                car == '7' || car == '8' || car == '9' || car == '0' || car == '.';
    }
    
    public static ArrayList<String> segmenta(String cadena){
        //método que segmenta la cadena
        ArrayList <String> resultado = new ArrayList<>();
        String temp = "";
        char item;
        int i=0;
        while (i<cadena.length()){
            item = cadena.charAt(i);
            if (esDigito(item) || item=='-' && temp.isEmpty())
                temp += item;   
            else{
                if (!temp.isBlank())
                    resultado.add(temp);
                resultado.add(""+item);
                temp = "";
            }
            i++;
        }
        if (!temp.isEmpty())
            resultado.add(temp);
        return resultado;
    }

    public static void main(String[] args) {
        String prueba = "2+3*4";
        
        System.out.println(convertirPostfijo(prueba));
    }
}//fin clase
