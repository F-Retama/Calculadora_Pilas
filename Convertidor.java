/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto;
import Pilas.*;
import java.util.ArrayList;

/**
 *
 * @author normaaliciamendoz
 */
public class Convertidor {
    
    /**
     * Evalua la expresión en postfija   
     * @return el resultado de las opercaiones resultantes.
     * <ul>
     * <li> Se recibe un ArrayList con los elementos de la operación en postfija, con signos y numeros por separado</li>
     * <li> Se utilizan 1 pila auxiliar para efectuar las operaciones. </li>
     * </ul>
     */
    @SuppressWarnings("empty-statement")
    public static String evaluaPost(ArrayList<String> exp){
        // Se regresará un String, pues se presentará en el JText como un String 
        //De este forma no se castea más veces
        PilaA<String> pila1= new PilaA();
        double a,b, result;
        String signo, datoF="nada";
        int cuantos;
        cuantos = exp.size();
         //String[] aux=new String[cuantos];
         //aux=(String[]) exp.toArray();
        //while(!exp.isEmpty()){
        
        
        
        int i=0;
        while(i<=cuantos ){
           if(!exp.isEmpty()){
            pila1.push(exp.get(0));
            exp.remove(0);
            i++;
           }
           
            
             
            if(!pila1.peek().equals("+") && !pila1.peek().equals("-") && !pila1.peek().equals("/") && !pila1.peek().equals("*") && !pila1.peek().equals("^")){
                pila1.push(exp.get(0));
                exp.remove(0);
                i++;
            }
            else{
                if(pila1.peek().equals("+") || pila1.peek().equals("-") || pila1.peek().equals("/") ||pila1.peek().equals("*") ||pila1.peek().equals("^")){
                signo=(pila1.pop());
                b=((Double.parseDouble(pila1.pop())));;
                a=((Double.parseDouble(pila1.pop())));
                //Dependiendo del signo se realiza una operación determinada
                if(signo.equals("+")){
                    result=b+a;
                        pila1.push(String.valueOf(result));
                }
                else
                    if(signo.equals("-")){
                        result=(a-b);
                        pila1.push(String.valueOf(result));
                    }
                    else 
                        if(signo.equals("/")){
                            result=(a/b);
                            pila1.push(String.valueOf(result));
                        }
                        else 
                            if(signo.equals("*")){
                                result=(b*a);
                                pila1.push(String.valueOf(result));
                            }
                            else
                                 if(signo.equals("^")){
                                     result = Math. pow(a, b);
                                     pila1.push(String.valueOf(result));
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
        if(i==cuantos){
                datoF=(String)pila1.pop();
                
            }
        return datoF;
    }
    
    public static void main(String[] args){
        //ArrayList<String> p=new ArrayList<>();
        String[] p= new String[5];
        ArrayList<String> p1=new ArrayList<String>();
        p1.add("10");
        p1.add("2");
        p1.add("+");
        p1.add("5");
        p1.add("*");
//        p[0]=("10");
//        p[1]=("2");
//        p[2]=("+");
//        p[3]=("5");
//        p[4]=("*");
        System.out.println(evaluaPost(p1));
    }
    }

