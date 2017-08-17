import java.util.HashSet;

public class EstadoAFDNodo {

    private HashSet<Nodo> nodosQueContiene = new HashSet<>();
    private int numeroEstadoDFA;
    private boolean marcado;
    private boolean isInicial;
    private boolean isFinal;

    public EstadoAFDNodo(HashSet<Nodo> nodosQueContiene) {
        this.nodosQueContiene = nodosQueContiene;
    }

    public HashSet<Nodo> getNodosQueContiene() {
        return nodosQueContiene;
    }

    public void setNodosQueContiene(HashSet<Nodo> nodosQueContiene) {
        this.nodosQueContiene = nodosQueContiene;
    }

    public int getNumeroEstadoDFA() {
        return numeroEstadoDFA;
    }

    public void setNumeroEstadoDFA(int numeroEstadoDFA) {
        this.numeroEstadoDFA = numeroEstadoDFA;
    }

    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }

    public boolean isInicial() {
        return isInicial;
    }

    public void setInicial(boolean inicial) {
        isInicial = inicial;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }
}