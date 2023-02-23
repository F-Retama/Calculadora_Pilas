package Pilas;
/**
 *
 * @author Retama
 * @param <T>
 */
public class PilaA <T> implements PilaADT<T>{
    
    //atributos
    private T[] pila;
    private int tope;
    private final int MAX_INI = 10;
    
    //constructores
    public PilaA (){
        pila = (T[]) new Object[MAX_INI];
        tope = -1;
    }
    public PilaA (int max){
        pila = (T[]) new Object[max];
        tope = -1;
    }
    
    //funcionalidad
    @Override
    public void push(T dato) {
        if (tope == pila.length-1){
            T[] pilaAmpliada = (T[]) new Object[pila.length+MAX_INI];
            for(int i=0; i<=tope; i++)
                pilaAmpliada[i] = pila[i];
            pila = pilaAmpliada;
        }
       tope ++;
       pila[tope] = dato;
    }
    @Override
    public boolean isEmpty() {
        return tope < 0;
    }

    @Override
    public T peek() {
        if (isEmpty())
            throw new ExcepcionColeccionVacia("La pila no tiene elementos");
        return pila[tope];
    }
    @Override
    public T pop() {
        T resp = peek();
        pila[tope] = null;
        tope --;
        return resp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<=tope; i++)
            sb.append("\n").append(pila[i]);
        return sb.toString();
    }
    @Override
    public void multiPop (int n){
        if (n <= tope+1)
            for (int i=0; i<n; i++){
                pila[tope] = null;
                tope --;
            }
    }
    
    public static void main(String[] args) {
        PilaADT pila1 = new PilaA();
        for(int i=0; i<5; i++)
            pila1.push(i);
        
        System.out.println(pila1.toString());
        System.out.println(pila1.equals(pila1));
        System.out.println(pila1.toString());
    }
}
