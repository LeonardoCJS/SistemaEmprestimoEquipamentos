package funcionarios.model;

public class Funcionario {
    private final Long id;
    private String nome;
    private String departamento;

    public Funcionario(Long id, String nome, String departamento) {
        this.id = id;
        this.nome = nome;
        this.departamento = departamento;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDepartamento() {
        return departamento;
    }

    public Funcionario setDepartamento(String departamento) {
        this.departamento = departamento;
        return this;
    }

    @Override
    public String toString() {
        return "Id: " + id +
                "\nNome: " + nome +
                "\nDepartamento: " + departamento;
    }
}
