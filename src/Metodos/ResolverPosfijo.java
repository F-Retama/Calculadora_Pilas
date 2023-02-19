/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodos;
import Pilas.*;
import java.util.ArrayList;

/**
 * @author Diego Román
*/
public class ResolverPosfijo {
    
    /**
     *"EvaluaPost": Evalua la expresión en postfija
     * @param exp
     * @return el resultado de las operaciones resultantes.
     * <ul>
     * <li> Se recibe un ArrayList ("exp") con los elementos de la operación en
     * postfija, con signos y números por separado</li>
     * <li> Se utilizan 1 pila auxiliar para efectuar las operaciones. </li>
     * </ul>
     *
       Explicación:  
       * -El método se ejecuta dependiendo de la cantidad de elementos en la expresión.
       * -Se analiza el ArrayList casilla por casilla en busca de operadores. (Siempre detrás de cada operador hay por lo menos dos números)
       * -Si la casilla que se esta analizando es un número, entonces se introduce a la pila auxiliar (pila1)
       * -Cada vez que se encuentra un operador se busca qué operación se va a ejecutar y posteriormente se obtienen los dos elementos más 
       * próximos al operador (respetando su orden).
       * -Se calcula el resultado y se agrega nuevamente en la pila.
       * -Si ya se recorrió todo el ArrayList, todas las operaciones correspondientes se realizaron y se devuelve el resultado final (resp).
     */
    
    public static String evaluaPost(ArrayList<String> exp) {
        String resp = "";
        if (exp != null && !exp.isEmpty()) {
            int i = 0;
            PilaA<Double> pila = new PilaA<>();
            String aux;
            double a,b;
            while (i < exp.size()) {
                aux = exp.get(i);
                if (!aux.equals("+") && !aux.equals("-") && !aux.equals("/") && !aux.equals("*") && !aux.equals("^"))
                    pila.push(Double.valueOf(aux));
                else{
                    //Se asume que en la expresión ya es válida, se validó anteriorimente (siempre hay dos números para operar)
                    b = pila.pop();
                    a = pila.pop();
                    //Dependiendo del signo se realiza una operación determinada
                    switch (aux) {
                        case "+" -> pila.push(a + b);
                        case "-" -> pila.push(a - b);
                        case "/" -> {
                            // checar que no divida en 0
                            if (b != 0)
                                pila.push(a / b);
                            else 
                                throw new RuntimeException("No se puede dividir entre 0");
                        }
                        case "*" -> pila.push(b * a);
                        case "^" -> pila.push( Math.pow(a, b));
                    }
                }
                i++;
            }
            resp = String.valueOf(pila.pop());
        }
        return resp;
    }

    public static void main(String[] args) {
        ArrayList<String> p1 = new ArrayList<>();
        System.out.println(evaluaPost(p1));
        
        p1.add("5");
        p1.add("6");
        p1.add("1");
        p1.add("-3");
        p1.add("+");
        p1.add("2");
        p1.add("^");
        p1.add("*");
        p1.add("2.5");
        p1.add("/");
        p1.add("+");
        System.out.println(evaluaPost(p1));
    }
}