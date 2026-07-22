package emprestimos.model;

import equipamentos.model.Equipamento;
import equipamentos.model.Notebook;
import exceptions.DevolucaoInvalidaException;
import funcionarios.model.Funcionario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmprestimoTest {

    @Test
    void emprestimoSemDataDevolucaoDeveEstarAtivo(){
        Notebook notebook = new Notebook(1L, "000");
        Funcionario funcionario = new Funcionario(1L, "Teste", "TI");
        Emprestimo emprestimo = new Emprestimo(1L, notebook, funcionario);
        assertTrue(emprestimo.estaAtivo());
    }

    @Test
    void emprestimoComDataDevolucaoNaoDeveEstarAtivo(){
        Notebook notebook = new Notebook(1L, "000");
        Funcionario funcionario = new Funcionario(1L, "Teste", "TI");
        Emprestimo emprestimo = new Emprestimo(1L, notebook, funcionario);
        emprestimo.registrarDevolucao();
        assertFalse(emprestimo.estaAtivo());
    }

    @Test
    void emprestimoDentroDoPrazoNaoDeveEstarAtrasado(){
        Notebook notebook = new Notebook(1L, "000");
        Funcionario funcionario = new Funcionario(1L, "Teste", "TI");
        Emprestimo emprestimo = new Emprestimo(1L, notebook, funcionario);
        assertFalse(emprestimo.estaAtrasado());
    }

    @Test
    void emprestimoForaDoPrazoDeveEstarAtrasado(){
        Funcionario funcionario = new Funcionario(1L, "Teste", "TI");
        Equipamento equipamentoComPrazoNegativo = new Equipamento(1L, "000") {
            @Override
            public int calcularPrazoDevolucao() {
                return -1;
            }
        };
        Emprestimo emprestimo = new Emprestimo(1L, equipamentoComPrazoNegativo, funcionario);

        assertTrue(emprestimo.estaAtrasado());
    }

    @Test
    void emprestimoDevolvidoDuasVezesLancaException(){
        Notebook notebook = new Notebook(1L, "000");
        Funcionario funcionario = new Funcionario(1L, "Teste", "TI");
        Emprestimo emprestimo = new Emprestimo(1L, notebook, funcionario);
        emprestimo.registrarDevolucao();
        DevolucaoInvalidaException exception = assertThrows(DevolucaoInvalidaException.class, emprestimo::registrarDevolucao);
        assertEquals("Esse empréstimo já foi devolvido em: " + emprestimo.getDataDevolucao(), exception.getMessage());
    }
}
