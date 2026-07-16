package Equipamentos.Model;

public interface Emprestavel {
    void marcarComoEmprestado();
    void marcarComoDisponivel();
    boolean estaDisponivel();
}
