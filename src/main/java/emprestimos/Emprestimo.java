package emprestimos;

import equipamentos.model.Equipamento;
import exceptions.DevolucaoInvalidaException;
import funcionarios.model.Funcionario;
import java.time.LocalDate;

public class Emprestimo {
    private final Long id;
    private final Equipamento equipamento;
    private final Funcionario funcionario;
    private final LocalDate dataEmprestimo;
    private final LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;

    public Emprestimo(Long id, Equipamento equipamento, Funcionario funcionario) {
        this.id = id;
        this.equipamento = equipamento;
        this.funcionario = funcionario;
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevistaDevolucao = dataEmprestimo.plusDays(equipamento.calcularPrazoDevolucao());
        equipamento.marcarComoEmprestado();
    }

    public Long getId() {
        return id;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean estaAtrasado(){
        return LocalDate.now().isAfter(dataPrevistaDevolucao) && this.dataDevolucao == null;
    }

    public void registrarDevolucao(){
        if(this.dataDevolucao == null){
            this.dataDevolucao = LocalDate.now();
            this.equipamento.marcarComoDisponivel();
        }else{
            throw new DevolucaoInvalidaException("Esse empréstimo já foi devolvido em: " + this.dataDevolucao);
        }
    }
}
