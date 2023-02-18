/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodos;
import Pilas.*;
import java.util.ArrayList;

/**
 *
 * @author normaaliciamendoza
 */
public class InfijoAPostfijo {
    
    public static boolean primerSignoMenorASegundo(String signo1, String signo2){
        boolean resp=false;
        if(signo1.equals("*") || signo1.equals("/")){
            switch(signo2){
                case "+":
                    resp=false;
                    break;
                case "-":
                     resp=false;
                    break;
                case "*":
                     resp=true;
                    break;
                case "/":
                     resp=true;
                    break;
                case "elevado":
                     resp=false;
                    break;
                    
        }
            
        }
        else
            if(signo1.equals("+") || signo1.equals("-")){
                switch(signo2){
                case "+":
                    resp=true;
                    break;
                case "-":
                     resp=true;
                    break;
                case "*":
                     resp=true;
                    break;
                case "/":
                     resp=true;
                    break;
                case "elevado":
                     resp=true;
                    break;
            }
    }
        else
             if(signo1.equals("^")){
                 resp=false;
             }
        return resp;
    }
    public static <T> ArrayList<String> APostfijo (String cadena){
        ArrayList<String> postfija=new ArrayList<>();
        PilaA<String> pila1=new PilaA<>();
        String simbolo;
        int i=0;
        
        while(i<cadena.length()&& !cadena.equals(" ")){//checar esta condicion
            simbolo=cadena.substring(i, i+1);
            if(simbolo.equals("(")){
                pila1.push(simbolo);
            }
            else
                if(simbolo.equals(")")){
                    while(!pila1.peek().equals("(")){
                        postfija.add(pila1.pop());
                        
                    }
                    pila1.pop();//solo se quita el parentesis que falta
                }
                else //si no es un operador meter el numero a la cadena de postfijo
               if(!cadena.substring(i, i+1).equals("*") && !cadena.substring(i, i+1).equals("-") && !cadena.substring(i, i+1).equals("/") && !cadena.substring(i, i+1).equals("+") && !cadena.substring(i, i+1).equals("^")){
                  postfija.add(simbolo);
               }
            else
            if(cadena.substring(i, i+1).equals("*") || cadena.substring(i, i+1).equals("-") || cadena.substring(i, i+1).equals("/") || cadena.substring(i, i+1).equals("+") || cadena.substring(i, i+1).equals("^")){
                //Falta paso de: llamar a pila vacia con PILA, TOPE Y BAND
                while(!pila1.isEmpty() && primerSignoMenorASegundo(cadena.substring(i, i+1), pila1.peek()) ){//se verifica que si el operador de la cadena es menor o igual al que esta en el tope de la pila
                    postfija.add(pila1.pop());
                    //Falta paso de: llamar a pila vacia con PILA, TOPE Y BAND
                }
                pila1.push(simbolo);
            }
        }
        //Falta paso de: llamar a pila vacia con PILA, TOPE Y BAND
        while(!pila1.isEmpty()){
            postfija.add(pila1.pop());
            //Falta paso de: llamar a pila vacia con PILA, TOPE Y BAND
        }
        return postfija;
    }
}