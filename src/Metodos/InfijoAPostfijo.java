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
    //atributos
    private String cadena;
    
    //constructores
    public InfijoAPostfijo(){
    }

    public InfijoAPostfijo(String cadena) {
        this.cadena = cadena;
    }
    
    //funcionalidad propia de la clase
    public ArrayList<Character> convertirPostfijo(String infija){
        //método un convierte una cadena de infija a postfija.
        ArrayList<Character> resultado=new ArrayList();
        PilaADT<Character> elementos=new PilaA<>();
        int i=0, j=0;
        while(i<infija.length()){
            elementos.push(infija.charAt(i));
            if(determinarOperador(infija.charAt(i))){
                while(!elementos.isEmpty() && determinarPrioridad(infija.charAt(i))<= elementos.peek())
                    resultado.add(elementos.pop());
                elementos.push(infija.charAt(i));
            }
            if(infija.charAt(i) == '(')
                elementos.push(infija.charAt(i));
            if(infija.charAt(i) == ')'){
                while(elementos.peek() != '(')
                    resultado.add(elementos.pop());
                elementos.pop();
            }
            i++;
        }
        while(!elementos.isEmpty()){
            resultado.add(infija.charAt(j));
            j++;
        }
        
        return resultado;
    }
    
    //métodos estáticos
    public static int determinarPrioridad(char operador){
        //método que determina la prioridad de los operadores.
        int resp=-1;
        switch(operador){
            case '^' -> resp=1;
            case '*' -> resp=2;
            case '/' -> resp=2;
            case '+' -> resp=3;
            case '-' -> resp=3;
        }
        return resp;
    }
    public static boolean determinarOperador(char operador){
        boolean resp=false;
        if(operador == '+' || operador == '-' || operador == '*' || operador == '/' || operador == '^')
            resp=true;
        return resp;
    }
}//fin clase
