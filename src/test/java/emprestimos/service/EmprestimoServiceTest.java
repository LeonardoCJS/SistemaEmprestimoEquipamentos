package emprestimos.service;

import emprestimos.model.Emprestimo;
import emprestimos.repository.EmprestimoRepository;
import equipamentos.model.Notebook;
import equipamentos.repository.EquipamentoRepository;
import exceptions.LimiteEmprestimoExcedidoException;
import funcionarios.model.Funcionario;
import funcionarios.repository.FuncionarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class EmprestimoServiceTest {

    @Mock
    private EmprestimoRepository emprestimoRepository;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private EquipamentoRepository equipamentoRepository;

    @InjectMocks
    private EmprestimoService emprestimoService;

    @Test
    void funcionarioSemEmprestimoAtivoConsegueResgistrarNovoEmprestimo(){
        when(funcionarioRepository.buscarPorId(1L)).thenReturn(new Funcionario(1L, "Teste", "TI"));
        when(equipamentoRepository.buscarPorId(1L)).thenReturn(new Notebook(1L, "000"));
        when(emprestimoRepository.listarAtivos()).thenReturn(List.of());

        assertDoesNotThrow(() -> emprestimoService.registrarEmprestimo(1L, 1L));
    }

    @Test
    void funcionarioComTresEmprestimosAtivosNaoConsegueResgistrarNovoEmprestimo(){
        Funcionario funcionario = new Funcionario(1L, "Teste", "TI");
        Notebook equipamento1 = new Notebook(1L, "000");
        Notebook equipamento2 = new Notebook(2L, "111");
        Notebook equipamento3 = new Notebook(3L, "222");
        Emprestimo emprestimo1 = new Emprestimo(1L, equipamento1, funcionario);
        Emprestimo emprestimo2 = new Emprestimo(2L, equipamento2, funcionario);
        Emprestimo emprestimo3 = new Emprestimo(3L, equipamento3, funcionario);
        when(emprestimoRepository.listarAtivos()).thenReturn(List.of(emprestimo1, emprestimo2, emprestimo3));
        when(equipamentoRepository.buscarPorId(4L)).thenReturn(new Notebook(4L, "333"));
        when(funcionarioRepository.buscarPorId(1L)).thenReturn(funcionario);

        LimiteEmprestimoExcedidoException exception = assertThrows(LimiteEmprestimoExcedidoException.class, () -> emprestimoService.registrarEmprestimo(1L, 4L));
        assertEquals("O funcionario ja tem 3 emprestimos ativos!", exception.getMessage());
    }
}
