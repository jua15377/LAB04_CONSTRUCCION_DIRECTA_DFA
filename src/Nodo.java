import java.util.HashSet;

public class Nodo {
    private String simobolo;
    private Nodo hijoDerecho;
    private Nodo hijoIzquierdo;
    private Nodo hijo;
    private int id;
    private HashSet<Nodo> firstpos = new HashSet<>();
    private boolean isNullable;
    private HashSet<Nodo> lastpos = new HashSet<>();
    private HashSet<Nodo> followpos = new HashSet<>();




    //constructor para una hoja conun simbolo del alfabeto
    public Nodo(String simobolo, int id){
        this.simobolo = simobolo;
        this.id = id;
    }
    //conmstructoe para operacions con un solo simbolo
    public Nodo (String simobolo, Nodo hijo){
        this.simobolo = simobolo;
        this.hijo = hijo;
    }

    //constructor para las opereaciones que utilizan dos simbolos
    public Nodo(String simbolo, Nodo hIzq, Nodo hDer){
        this.simobolo = simbolo;
        this.hijoIzquierdo = hIzq;
        this.hijoDerecho = hDer;
    }

    public String getSimobolo() {
        return simobolo;
    }

    public void setSimobolo(String simobolo) {
        this.simobolo = simobolo;
    }

    public Nodo getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(Nodo hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public Nodo getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(Nodo hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public Nodo getHijo() {
        return hijo;
    }

    public void setHijo(Nodo hijo) {
        this.hijo = hijo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashSet<Nodo> getFirstpos() {
        return firstpos;
    }

    public void setFirstpos(HashSet<Nodo> firstpos) {
        this.firstpos = firstpos;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean nullable) {
        this.isNullable = nullable;
    }

    public HashSet<Nodo> getLastpos() {
        return lastpos;
    }

    public void setLastpos(HashSet<Nodo> lastpos) {
        this.lastpos = lastpos;
    }

    public HashSet<Nodo> getFollowpos() {
        return followpos;
    }

    public void setFollowpos(HashSet<Nodo> followpos) {
        this.followpos = followpos;
    }

    @Override
    public String toString() {
        return "Nodo{" +
                "s='" + simobolo + '\'' +
                ", hD=" + hijoDerecho +
                ", hI=" + hijoIzquierdo +
                ", h=" + hijo +
                ",   id=" + id +
                '}';
    }
}
