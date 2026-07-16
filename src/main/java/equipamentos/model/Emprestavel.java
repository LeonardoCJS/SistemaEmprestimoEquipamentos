package equipamentos.model;

public interface Emprestavel {
    void marcarComoEmprestado();
    void marcarComoDisponivel();
    boolean estaDisponivel();
}
