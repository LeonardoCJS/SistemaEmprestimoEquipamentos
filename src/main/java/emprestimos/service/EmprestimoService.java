package emprestimos.service;

import emprestimos.model.Emprestimo;
import emprestimos.repository.EmprestimoRepository;
import equipamentos.model.Equipamento;
import equipamentos.repository.EquipamentoRepository;
import exceptions.LimiteEmprestimoExcedidoException;
import funcionarios.model.Funcionario;
import funcionarios.repository.FuncionarioRepository;


public class EmprestimoService {
    private final EmprestimoRepository emprestimoRepository;
    private final EquipamentoRepository equipamentoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository,  EquipamentoRepository equipamentoRepository, FuncionarioRepository funcionarioRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.equipamentoRepository = equipamentoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public String registrarEmprestimo(Long idFuncionario, Long idEquipamento) {
        Funcionario funcionario = funcionarioRepository.buscarPorId(idFuncionario);
        Equipamento equipamento = equipamentoRepository.buscarPorId(idEquipamento);
        long emprestimosAtivos = emprestimoRepository.listarAtivos().stream()
                .filter(empres -> empres.getFuncionario().getId().equals(funcionario.getId()))
                .count();
        if (emprestimosAtivos >= 3) {
            throw new LimiteEmprestimoExcedidoException("O funcionario ja tem 3 emprestimos ativos!");
        }
        emprestimoRepository.salvar(new Emprestimo(emprestimoRepository.proximoId(), equipamento, funcionario));
        return "Emprestimo salvo com sucesso!";
    }

    public String registrarDevolucao(Long idEmprestimo) {
        Emprestimo emprestimo = emprestimoRepository.buscarPorId(idEmprestimo);
        emprestimo.registrarDevolucao();
        return "Devolução registrada com sucesso!";
    }

}
