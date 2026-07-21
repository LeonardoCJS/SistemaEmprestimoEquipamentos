package funcionarios.repository;

import exceptions.FuncionarioNaoEncontradoException;
import funcionarios.model.Funcionario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FuncionarioRepository {
    private Map<Long, Funcionario> funcionarios = new HashMap<>();
    private Long proximoId = 1L;

    public Long proximoId(){
        return proximoId++;
    }

    public void salvar(Funcionario funcionario){
        Objects.requireNonNull(funcionario, "Funcionario não pode ser nulo.");
        funcionarios.put(funcionario.getId(), funcionario);
    }

    public Funcionario buscarPorId(Long id){
        Objects.requireNonNull(id, "Id não pode ser nulo.");
        Funcionario funcionario = funcionarios.get(id);
        if (funcionario == null){
            throw new FuncionarioNaoEncontradoException("Nenhum Funcionario com esse id: " + id);
        }
        return funcionario;
    }

    public List<Funcionario> listarTodos(){
        return List.copyOf(funcionarios.values());
    }
}
