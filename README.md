# Calculadora_Pilas
Calculadora programada en Java que utiliza pilas para pasar Infijo a Posfijo
package Proyecto;
import Pilas.*;
import java.util.ArrayList;

/**Faltan varias cosas para este código, me arroja errores por el uso de los signos más, etc.
La lógica es la siguiente: Este método tiene que recibir un arreglo o ArrayList (por eso tantos comentarios en este método) para posteroirmente formar una cadena del resultado y ese mismo presentarlo al usuario. Para esto el código analiza este arreglo casilla por casilla para checar si lo que se va a colocar en mi pila auxiliar es un signo o un número. Cabe mencionar que para este convertidor se debe siempre de entregar un arreglo correcto, pues no corrige errores que los demás códigos deben de verificar (más signos, corchetes, etc.).
Cómo funciona: se recorre el arreglo, si es número se introduce a la pila siempre cuidando la condición de que para que se pueda realizar una operación por lo menos debe de haber 2 números anteriores a un signo. Cuando el algoritmo encuentra un operador se detiene y busca la operación que se debe de hacer con los 2 numeros que se encuentran en la pila (en comentario está la opción de usar un switch o también desarrolé la forma en if and else anidados.. Estos números son Strings y se castean a doubles para posteriormente hacer la operación y ese resultado ponerlo de nuevo en la pila. Cuando ya se haya cuando ya se haya recorrido todo el arreglo de Strings, el algoritmo castea el resultado a un String y es el mismo que regresa el método, es decir, hace un último pop a la cadena (el número que quedó como resultado) y lo muestra en el String.

Posteriormente se genera
 *
 * 
 */
 
 
public class Convertidor {
    public static String evaluaPost(String[] exp){
        // Se regresará un String, pues se presentará en el JText como un String 
        //De esta forma no se castea más veces
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

