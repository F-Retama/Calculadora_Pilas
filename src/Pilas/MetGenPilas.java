package Pilas;
import java.util.ArrayList;
/**
 * Problemas 14 a 17.
 * @author Retama
 */
public class MetGenPilas {
    
    public static void vaciaPila(PilaADT aVaciar, PilaADT recipiente){
        if (aVaciar != null && recipiente != null)
            while (!aVaciar.isEmpty())
                recipiente.push(aVaciar.pop());
    }
    
    //problema 14
    public static <T> int midePila(PilaADT <T> pila){
        if (pila == null)
            throw new NullPointerException("Parámetro nulo");
        int resp = 0;
        PilaADT <T> slinky = new PilaA();
        while (!pila.isEmpty()){
            slinky.push(pila.pop());
            resp ++;
        }
        for (int i=0; i<resp; i++)
            pila.push(slinky.pop());
        return resp;
    }
    
    //problema 15
    public static <T> boolean contencionPilas(PilaADT <T> contenida, PilaADT <T> contenedora){
        if (contenida == null || contenedora == null)
            throw new NullPointerException("Parámetro nulo");
        PilaADT <T> slinky = new PilaA();
        ArrayList <T> aux = new ArrayList();
        while (!contenedora.isEmpty())
            aux.add(contenedora.pop());
        for (int i=aux.size()-1; i>=0; i--)
            contenedora.push(aux.get(i));
        while (!contenida.isEmpty() && aux.contains(contenida.peek()))
            slinky.push(contenida.pop());
        boolean resp = contenida.isEmpty();
        vaciaPila(slinky, contenida);
        return resp;
    }
    
    //problema 16
    public static <T> void inviertePila(PilaADT <T> pila){
        if (pila == null)
            throw new NullPointerException("Parámetro nulo");
        ArrayList <T> aux = new ArrayList();
        while (!pila.isEmpty())
            aux.add(pila.pop());
        for (int i=0; i<aux.size(); i++)
           pila.push(aux.get(i));
    }
    
    //problema 17
    public static <T> void eliminaRepetidosConsec (PilaADT <T> pila){
        if (pila == null)
            throw new NullPointerException("Parámetro nulo");
        PilaADT <T> slinky = new PilaA();
        while (!pila.isEmpty()){
            slinky.push(pila.pop());
            while (!pila.isEmpty() && pila.peek().equals(slinky.peek()))
                pila.pop();
        }
        vaciaPila(slinky, pila);
    }
    
    public static <T> boolean multiPop (PilaADT pila, int n){
        PilaADT <T> aux = new PilaA();
        while (!pila.isEmpty() && n>0){
            aux.push((T) pila.pop());
            n--;
        }
        if (n!=0)
            vaciaPila(aux, pila);
        return n==0;
    }
    
    public static <T> boolean pilasEspejo(PilaADT p1, PilaADT p2){
        if (p1 == null || p2==null)
            throw new NullPointerException("Parámetro nulo");
        PilaADT <T> aux = new PilaA<>();
        vaciaPila(p2, aux);
        boolean resp = p1.equals(aux);
        vaciaPila(aux, p2);
        return resp;
    }
    
    public static <T> boolean cantIguales(PilaADT p1, PilaADT p2, int n){
        if (p1 == null || p2==null)
            throw new NullPointerException("Parámetro nulo");
        PilaADT <T> aux1 = new PilaA();
        PilaADT <T> aux2 = new PilaA();
        int cont = 0;
        while(!p1.isEmpty() && !p2.isEmpty() && cont<n){
            if (p1.peek().equals(p2.peek()))
                cont ++;
            aux1.push((T) p1.pop());
            aux2.push((T) p2.pop());
        }
        vaciaPila(aux1, p1);
        vaciaPila(aux2, p2);
        return cont == n;
    }

    
    public static void main(String[] args) {
        
        PilaA pila1 = new PilaA();
        PilaA pila2 = new PilaA();
        for (int i=2; i<5; i++)
            pila1.push(i);
        for (int i=0; i<7; i++)
            pila2.push(i);
        /*
        System.out.println(contencionPilas(pila1, pila2));
        System.out.println(contencionPilas(pila2, pila1));
        PilaA pilaVacia = new PilaA();
        System.out.println(contencionPilas(pila2, pilaVacia));
        System.out.println(contencionPilas(pilaVacia, pila2));
        
        for (int i=0; i<4; i++)
            pila1.push(1);
        for (int i=4; i<7; i++)
            pila1.push(i);
        pila1.push(6);
        System.out.println(pila1.toString());
        eliminaRepetidosConsec(pila1);
        System.out.println(pila1.toString());
        */
        System.out.println(pila2.toString());
        System.out.println(multiPop(pila2, 15));
        System.out.println(pila2.toString());
        System.out.println(multiPop(pila2, 4));
        System.out.println(pila2.toString());
        System.out.println(multiPop(pila2, 6));
        
    }
    
}
