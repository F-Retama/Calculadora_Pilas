/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto;
import Pilas.*;
import java.util.ArrayList;

/**
 *
 * @author normaaliciamendoza
 */
public class Convertidor {
    public static String evaluaPost(String[] exp){
        // Se regresará un String, pues se presentará en el JText como un String 
        //De este forma no se castea más veces
        PilaA pila1= new PilaA();
        double a,b, result;
        String signo, datoF;
        int cuantos;
        cuantos = exp.length;
         //String[] aux=new String[cuantos];
         //aux=(String[]) exp.toArray();
        //while(!exp.isEmpty()){
        int i=0;
        while(i<cuantos){
            pila1.push(exp[i]);
            i++;
            
            if(!pila1.peek().equals("+") && !pila1.peek().equals("-")&& !pila1.peek().equals("/")&& !pila1.peek().equals("*")&& !pila1.peek().equals("^")){
                pila1.push(exp[i]);
                
            }
            else{
                if(pila1.peek().equals("+")|| pila1.peek().equals("-") || pila1.peek().equals("/") ||pila1.peek().equals("*") ||pila1.peek().equals("^")){
                signo=(String)pila1.pop();
                b=(Double.parseDouble((String)pila1.pop()));
                a=(Double.parseDouble((String)pila1.pop()));
                //Dependiendo del signo se realiza una operación determinada
                if(signo.equals("+")){
                    result=(b+a);
                        pila1.push(result);
                }
                else
                    if(signo.equals("-")){
                        result=(a-b);
                        pila1.push(result);
                    }
                    else 
                        if(signo.equals("/")){
                            result=(a/b);
                            pila1.push(result);
                        }
                        else 
                            if(signo.equals("*")){
                                result=(b*a);
                                pila1.push(result);
                            }
                            else
                                 if(signo.equals("^")){
                                     result = Math. pow(a, b);
                                     pila1.push(result);
                                 }
//                switch(signo){
//                    case "+" -> {
//                        result=(b+a);
//                        pila1.push(result);
//                        }
//                    case "-" -> {
//                        result=(a-b);
//                        pila1.push(result);
//                        }
//                    case "*" -> {
//                        result=(b*a);
//                        pila1.push(result);
//                        }
//                    case "/" -> {
//                        result=(a/b);
//                        pila1.push(result);
//                        }
//                    case "^" -> {
//                        result = Math. pow(a, b);
//                        pila1.push(result);
//                        }
//                        
//                }
                }
                
            }
            
                       
            }
        datoF=(String)pila1.pop();
        return datoF;
        }
    
    public static void main(String[] args){
        //ArrayList<String> p=new ArrayList<>();
        String[] p= new String[5];
        p[0]=("10");
        p[1]=("2");
        p[2]=("+");
        p[3]=("5");
        p[4]=("*");
        System.out.println(evaluaPost(p));
    }
    }

