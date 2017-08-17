import java.util.Stack;

public class Creador {
    private Stack<Nodo> arbol = new Stack<>();
    public Creador(){
    }
    public Stack<Nodo> analizadorExpresion(String regex){
        String regexExtended = "(" + regex + ")#";
        String expresionENPostFix = RegExConverter.infixToPostfix(regexExtended);
        int identificador = 0;
        for (int i = 0; i < expresionENPostFix.length(); i++) {
            char simboloAct = expresionENPostFix.charAt(i);
//            necesita de dos automatas para proceder
            if (simboloAct == '.') {
//              extrae los nodos  a trabajar
                Nodo nodoDercho = arbol.pop();
                Nodo nodoIzquierdo = arbol.pop();
                Nodo nodoNuevoAnd = new Nodo(String.valueOf(simboloAct), nodoIzquierdo, nodoDercho);
                arbol.add(nodoNuevoAnd);
            }
            else if (simboloAct == '|') {
//              extrae los nodos a trabajar
                Nodo nodoDercho = arbol.pop();
                Nodo nodoIzquierdo = arbol.pop();
                Nodo nodoNuevoOr = new Nodo(String.valueOf(simboloAct), nodoIzquierdo, nodoDercho);
                arbol.add(nodoNuevoOr);
            }
//          solo necesita de un nodo
            else if (simboloAct == '*') {
//              extrae el automata a trabajar
                Nodo a = arbol.pop();
                Nodo resultante = new Nodo(String.valueOf(simboloAct), a);
                arbol.add(resultante);
            }
//           no hace ningua operacion solo crea un nodo con simbolo e id
            else {
                Nodo nodo = new Nodo(String.valueOf(simboloAct), identificador);
                arbol.add(nodo);
                identificador++;
            }
        }
        return arbol;
    }
}
