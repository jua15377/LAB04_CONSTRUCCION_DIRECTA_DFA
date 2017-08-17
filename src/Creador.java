import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class Creador {
    private Stack<Nodo> arbol = new Stack<>();
    public Creador(){
    }
    public Stack<Nodo> analizadorExpresion(String regex){
        String regexExtended = "(" + regex + ")#";
        String expresionENPostFix = RegExConverter.infixToPostfix(regexExtended);
        int identificador = 1;
        for (int i = 0; i < expresionENPostFix.length(); i++) {
            char simboloAct = expresionENPostFix.charAt(i);
//            necesita de dos automatas para proceder
            if (simboloAct == '.') {
//              extrae los nodos  a trabajar
                Nodo nodoDercho = arbol.pop();
                Nodo nodoIzquierdo = arbol.pop();
                Nodo nodoNuevoAnd = new Nodo(String.valueOf(simboloAct), nodoIzquierdo, nodoDercho);

                //Calculando las operaciones nullable, fistpos, lastpos y followpos
                nodoNuevoAnd.setNullable(nullable(nodoNuevoAnd));
                nodoNuevoAnd.setFirstpos(firstpos(nodoNuevoAnd));
                nodoNuevoAnd.setLastpos(lastpos(nodoNuevoAnd));
                followpos(nodoNuevoAnd);
                arbol.add(nodoNuevoAnd);
            }
            else if (simboloAct == '|') {
//              extrae los nodos a trabajar
                Nodo nodoDercho = arbol.pop();
                Nodo nodoIzquierdo = arbol.pop();
                Nodo nodoNuevoOr = new Nodo(String.valueOf(simboloAct), nodoIzquierdo, nodoDercho);

                //Calculando las operaciones nullable, fistpos, lastpos y followpos
                nodoNuevoOr.setNullable(nullable(nodoNuevoOr));
                nodoNuevoOr.setFirstpos(firstpos(nodoNuevoOr));
                nodoNuevoOr.setLastpos(lastpos(nodoNuevoOr));
                followpos(nodoNuevoOr);

                arbol.add(nodoNuevoOr);
            }
//          solo necesita de un nodo
            else if (simboloAct == '*') {
//              extrae el automata a trabajar
                Nodo a = arbol.pop();
                Nodo resultante = new Nodo(String.valueOf(simboloAct), a);

                resultante.setNullable(nullable(resultante));
                resultante.setFirstpos(firstpos(resultante));
                resultante.setLastpos(lastpos(resultante));
                followpos(resultante);
                arbol.add(resultante);
            }
//           no hace ningua operacion solo crea un nodo con simbolo e id
            else {
                Nodo nodo = new Nodo(String.valueOf(simboloAct), identificador);
                nodo.setNullable(nullableSingelNode(nodo));
                nodo.setFirstpos(firstposSingleNode(nodo));
                nodo.setLastpos(lastposSingleNode(nodo));
                arbol.add(nodo);
                identificador++;
            }
        }
        return arbol;
    }

    public boolean nullableSingelNode(Nodo nodo){
        boolean nullable;
        if(nodo.getSimobolo().equals("@")){
            nullable = true;
        }else{
            nullable = false;
        }
        return nullable;
    }

    public boolean nullable(Nodo nodo){
        boolean nullable=false;
        String simbolo = nodo.getSimobolo();
        Nodo c1 = nodo.getHijoIzquierdo();
        Nodo c2 = nodo.getHijoDerecho();
        if(simbolo.equals("|")){
            nullable = c1.isNullable() || c2.isNullable();
        }else if(simbolo.equals(".")){
            nullable = c1.isNullable() && c2.isNullable();
        }else if(simbolo.equals("*")){
            nullable = true;
        }
        return nullable;
    }

    public HashSet<Nodo> firstposSingleNode(Nodo nodo){
        HashSet<Nodo> firstpos = new HashSet<>();
        if(nodo.getSimobolo().equals("@")){
            firstpos = firstpos;
        }else{
            firstpos.add(nodo);
        }
        return firstpos;
    }

    public HashSet<Nodo> firstpos(Nodo nodo){
        HashSet<Nodo> firstpos = new HashSet<>();

        String simbolo = nodo.getSimobolo();
        Nodo c1 = nodo.getHijoIzquierdo();
        Nodo c2 = nodo.getHijoDerecho();
        Nodo c = nodo.getHijo();

        if(simbolo.equals("|")){
            firstpos.addAll(c1.getFirstpos());
            firstpos.addAll(c2.getFirstpos());
        }else if(simbolo.equals(".")){
            if(c1.isNullable()){
                firstpos.addAll(c1.getFirstpos());
                firstpos.addAll(c2.getFirstpos());
            }else{
                firstpos.addAll(c1.getFirstpos());
            }
        }else if(simbolo.equals("*")){
            firstpos.addAll(c.getFirstpos());
        }
        return firstpos;
    }

    public HashSet<Nodo> lastposSingleNode(Nodo nodo){
        HashSet<Nodo>lastpos = new HashSet<>();
        if(nodo.getSimobolo().equals("@")){
            lastpos = lastpos;
        }else{
            lastpos.add(nodo);
        }
        return lastpos;
    }

    public HashSet<Nodo> lastpos(Nodo nodo){
        HashSet<Nodo> lastpos = new HashSet<>();

        String simbolo = nodo.getSimobolo();
        Nodo c1 = nodo.getHijoIzquierdo();
        Nodo c2 = nodo.getHijoDerecho();
        Nodo c = nodo.getHijo();

        if (simbolo.equals("|")) {
            for (Nodo n : c1.getLastpos()) {
                lastpos.add(n);
            }
            for (Nodo n : c2.getLastpos()) {
                lastpos.add(n);
            }

        } else if (simbolo.equals(".")) {
            if (c2.isNullable()) {
                for (Nodo n : c1.getLastpos()) {
                    lastpos.add(n);
                }
                for (Nodo n : c2.getLastpos()) {
                    lastpos.add(n);
                }
            } else {
                lastpos = c2.getLastpos();
            }

        } else if (simbolo.equals("*")) {
            lastpos = c.getLastpos();

        }
        return lastpos;
    }

    public void followpos(Nodo nodo){
        String simbolo = nodo.getSimobolo();
        Nodo c1 = nodo.getHijoIzquierdo();
        Nodo c2 = nodo.getHijoDerecho();

        if(simbolo.equals(".")){
            for (Nodo n: c1.getLastpos()) {
                n.getFollowpos().addAll(c2.getFirstpos());
                //i.setFollowpos(c2.getFirstpos());
            }
        }else if(simbolo.equals("*")){
            for (Nodo n: nodo.getLastpos()) {
                n.getFollowpos().addAll(nodo.getFirstpos());
            }
        }

    }
    public void construccionDirecta(AutomataAFD automata,Nodo n, ArrayList<String> alfabeto){

        //Creando una nueva instancia del objeto EstadoAFDNodo  que contiene como atributo el firstpos de la hoja n.
        EstadoAFDNodo elEstado = new EstadoAFDNodo(n.getFirstpos());

        //Inicializando Dstates con el EstadoAFDHOja sin marcar
       

}
