/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Calculadora;
import Pilas.*;
import java.util.ArrayList;

/**
 * @author Fernando Retama
*/
public class Metodos {//métodos estáticos
    
    /**
     * método que determina si un caracter es parte de un operando
     * @author Fenando Retama
     * @param car (caracter)
     * @return boolean 
     */
    public static boolean esDigito(char car){
        //
        return car == '1' || car == '2' || car == '3' || car == '4' || car == '5' || car == '6' || 
                car == '7' || car == '8' || car == '9' || car == '0' || car == '.';
    }
    
    /**
     * método que segmenta la cadena en operadores y operandos
     * @author Fernando Retama
     * @param cadena de texto
     * @return ArrayList con la cadena segmentada
     */
    public static ArrayList<String> segmenta(String cadena){
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
    /**
     * método que valida si una expreión infija está bien escrita
     * @author Fernando Retama, Sofía Imelda
     * @param elementos del String de entrada ya segmentado
     * @return boolean
     */ 
    public static boolean validaString (ArrayList<String> elementos){
        int j, i=0;
        char c;
        boolean dot;
        boolean resp = true;
        PilaADT<String> pilaParen = new PilaA<>();
        PilaADT<String> pilaNums = new PilaA<>();
        try{
            if ("-".equals(elementos.get(0)))
                i++;
            while (i<elementos.size() && resp){
                String item = elementos.get(i);
                if(prioridadOp(item) != -1) // es un operador
                    pilaNums.pop();
                else
                    switch (item) {
                        case "(" -> {
                            if(pilaNums.isEmpty())
                                pilaParen.push(item);
                            else
                                resp = false;
                        }
                        case ")" -> pilaParen.pop();
                        case "." -> resp = false;
                        default -> { //es un número
                            pilaNums.push(item);
                            //ahora se revisa que el número tenga a lo más un punto
                            j=0;
                            dot = false;
                            while (j<item.length() && resp){
                                c = item.charAt(j);
                                if (c=='.'){
                                    if(!dot)
                                        dot = true;
                                    else
                                        resp = false;
                                }
                                j++;
                            }
                        }
                    }
                i++;
            }
            pilaNums.pop();
        }catch(Exception e){ //demasiados operadores o ')'
            resp = false;
        }
        return resp && pilaNums.isEmpty() && pilaParen.isEmpty();
    }
    
    
    /**
     * método que determina la prioridad de los operadores.
     * @author Alexa
     * @param operador (en String)
     * @return entero que represesnta la prioridad de los operadores
     */
    public static int prioridadOp(String operador){
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
    
    /**
     * método que convierte una cadena de infija a postfija.
     * @author Alexa
     * @param infijo váido en ArrayList segmentado
     * @return entero que represesnta la prioridad de los operadores
     */
    public static ArrayList<String> convertirPostfijo(ArrayList<String> infijo){
        ArrayList<String> resultado = new ArrayList();
        PilaADT<String> aux = new PilaA<>();
        int i=0;
        //caso especial: comienza con "-("
        boolean negInicial = "-".equals(infijo.get(0));
        if (negInicial){ 
            resultado.add("-1");
            i++;
        }
        while(i<infijo.size()){
            String item = infijo.get(i);
            if(prioridadOp(item) != -1){//es un operador
                while (!aux.isEmpty() && !"(".equals(aux.peek()) && prioridadOp(item) <= prioridadOp(aux.peek()))
                    //el nuevo operador bota de la pila al resultado a los de mayor o igual prioridad
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
    
    /**
     *"EvaluaPost": Resuelve la expresión en postfija
     * @author Diego Román
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
                if (prioridadOp(aux) == -1) //es un operando
                    pila.push(Double.valueOf(aux));
                else{//es un operador
                    //siempre hay dos números para operar
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
        
//        System.out.println(validaString("-1+212*(-8/-94.56+-9)"));
//        System.out.println(validaString("9*-9"));
//        System.out.println(validaString("(-.9*9)"));
//        System.out.println(validaString("-9(*-9)"));
//        System.out.println(validaString(""));
//        System.out.println(validaString("(()())"));
//        System.out.println(validaString("((5)(9))"));
        
//        System.out.println(convertirPostfijo("2+3*4"));
//        System.out.println(convertirPostfijo("2+3+4+5+6"));
//        System.out.println(convertirPostfijo("2*3/4*5/6"));
//        System.out.println(convertirPostfijo("2+3+4*5+6"));
//        System.out.println(convertirPostfijo("2+3*4+5"));
//        System.out.println(convertirPostfijo("((9)+((-9)))"));
//        System.out.println(convertirPostfijo("(-.9*9)"));
//        System.out.println(convertirPostfijo("-1+212*(-8/-94.56+-9)"));
//        System.out.println(convertirPostfijo("-(1+212)*(-8/-94.56+-9)"));
//        System.out.println(convertirPostfijo("-(1+212)+(-8/-94.56+-9)"));
        
        ArrayList<String> a1 = segmenta("-(1+212)*(-8/-94.56+-9)");
        System.out.println(validaString(a1));
        ArrayList<String> ap1 = convertirPostfijo(a1);
        System.out.println(ap1.toString());
        System.out.println(evaluaPost(ap1));
        
    }
}