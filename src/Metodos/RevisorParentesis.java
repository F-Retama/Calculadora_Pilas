/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto;

import Pilas.PilaA;

/**
 *
 * @author normaaliciamendoza
 */
public class RevisorParentesis {
    
    
    public static boolean analizaCadena(String cadena){
       PilaA<String> pila1 = new PilaA();
       boolean encontre=false;
       
        int i=0;
        
        while(i<cadena.length() && !encontre){
            if("(".equals(cadena.substring(i, i+1))){
                pila1.push("(");
            }else
                if(cadena.substring(i, i+1).equals(")")){
                    if(!pila1.isEmpty()){
                        pila1.pop();
                        }
                        else{
                           encontre=true;
                       }      
                }
            
            i++;
        }
        
        return !encontre && pila1.isEmpty();
        
        }
        
    //En el caso de usar no solo parentesis, sino corchetes y llaves
    
//    if(cadena.substring(i)=="(" || cadena.substring(i)=="[" ||cadena.substring(i)=="{"){
//                pila1.push(cadena.substring(i));
//            }
//            else
//                if(cadena.substring(i)==")" || cadena.substring(i)=="]" ||cadena.substring(i)=="}"){
//                    if(cadena.substring(i)==")" && pila1.peek()=="("){
//                        pila1.pop();
//                    }
//                    else
//                        if(cadena.substring(i)=="]" && pila1.peek()=="["){
//                        pila1.pop();
//                    }
//                    else
//                       if(cadena.substring(i)=="}" && pila1.peek()=="{"){
//                        pila1.pop();
//                    }
//                       else{
//                           encontre=true;
//                           resp=false;
//                       }
//                            
//                }
//            i++;
//        }
//        if(pila1.isEmpty()){
//            resp=true;
//        }
//        else
//            resp=false;
//            
//        return resp;
//        }
    public static boolean analizaCadena2(String cadena){
       PilaA<String> pila1 = new PilaA();
       boolean resp=true;
        int i=0;
        while(i<cadena.length() && resp){
            if("(".equals(cadena.substring(i, i+1)) || cadena.substring(i, i+1).equals("[") ||cadena.substring(i, i+1).equals("{")){
                pila1.push(cadena.substring(i, i+1));
            }else
                if(")".equals(cadena.substring(i, i+1)) || cadena.substring(i, i+1).equals("]") ||cadena.substring(i, i+1).equals("}")){
                   if(cadena.substring(i, i+1).equals(")")){
                       if(!pila1.isEmpty() && pila1.peek().equals("(") ){
                         
                            pila1.pop();
                         
                       }else
                       resp=false;
                }
               
                    if(cadena.substring(i, i+1).equals("]")){
                        if(!pila1.isEmpty() && pila1.peek().equals("[") ){
                           
                              pila1.pop();
                           
                        }else
                        resp=false;
                       }
                     
                        if(cadena.substring(i, i+1).equals("}")){
                            if(!pila1.isEmpty() && pila1.peek().equals("{") ){
                               
                                  pila1.pop();
                            }else
                            resp=false;
                        }
                         
                }
            i++;
        }
        
        if(!pila1.isEmpty())
        resp=false;
        
        return resp;
        }
        
        
        
        
    
    public static void main(String[] args){
     System.out.println(analizaCadena("(())"));
     System.out.println(analizaCadena("())"));
     System.out.println(analizaCadena("(()"));
     System.out.println(analizaCadena(""));

System.out.println(analizaCadena2("{{}}"));
System.out.println(analizaCadena2("(())"));
System.out.println(analizaCadena2("[[]]"));
System.out.println(analizaCadena2("[[]]"));
System.out.println(analizaCadena2("{{[[]()[]{}]}}"));
System.out.println(analizaCadena2("{{[[]()[]{}}]}"));
System.out.println(analizaCadena2("}"));
    }
    
}
