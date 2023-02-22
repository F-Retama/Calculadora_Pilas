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
    public static int prioridadOp(String operador){
        //método que determina la prioridad de los operadores.
        int resp=-1;
        switch(operador){
            case "+" -> resp = 1;
            case "-" -> resp = 1;
            case "*" -> resp = 2;
            case "/" -> resp = 2;
            case "^" -> resp = 3;
        }
        return resp;
    }
//    public static boolean esOperador(String operador){
//        //método que determina si es un operador.
//        boolean resp=false;
//        if(operador == "+" || operador == "-" || operador == "*" || operador == "/" || operador == "^" )
//            resp=true;
//        return resp;
//    }
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
            if (esDigito(item) || (item=='-' && temp.isEmpty()))
                temp += item;   
            else{
                if (!temp.isEmpty())
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
    public static ArrayList<String> convertirPostfijo(String infija){
        //método que convierte una cadena de infija a postfija.
        ArrayList<String> resultado = new ArrayList();
        ArrayList<String> cadenaSegmentada= segmenta(infija);
        PilaADT<String> aux = new PilaA<>();
        int i=0;
        boolean negInicial = "-".equals(cadenaSegmentada.get(0));
        if (negInicial){ //caso especial: comienza con "-("
            resultado.add("-1");
            i++;
        }
        while(i<cadenaSegmentada.size()){
            String item = cadenaSegmentada.get(i);
            if(prioridadOp(item) != -1){//es un operador
                while (!aux.isEmpty() && !"(".equals(aux.peek()) && prioridadOp(item) <= prioridadOp(aux.peek()))
                    resultado.add(aux.pop());
                aux.push(item);
            } 
            else{
                switch (item) {
                    case "(" -> aux.push(item);
                    case ")" -> {
                        //vacío los operadores hasta encontrar el paréntesis que abría
                        while(!"(".equals(aux.peek()))
                            resultado.add(aux.pop());
                        aux.pop();
                        if (negInicial){
                            resultado.add("*");
                            negInicial = false;
                        }
                    }
                    default -> resultado.add(item); //es un operando
                }
            }
            i++;
        }
        while(!aux.isEmpty()){//vaciar operadores restantes
            resultado.add(aux.pop());
        }
        return resultado;
    }
    

    public static void main(String[] args) {
        
        System.out.println(convertirPostfijo("2+3*4"));
        System.out.println(convertirPostfijo("2+3+4+5+6"));
        System.out.println(convertirPostfijo("2*3/4*5/6"));
        System.out.println(convertirPostfijo("2+3+4*5+6"));
        System.out.println(convertirPostfijo("2+3*4+5"));
        System.out.println(convertirPostfijo("((9)+((-9)))"));
        System.out.println(convertirPostfijo("(-.9*9)"));
        System.out.println(convertirPostfijo("-1+212*(-8/-94.56+-9)"));
        System.out.println(convertirPostfijo("-(1+212)*(-8/-94.56+-9)"));
        System.out.println(convertirPostfijo("-(1+212)+(-8/-94.56+-9)"));
        
    }
}//fin clase
