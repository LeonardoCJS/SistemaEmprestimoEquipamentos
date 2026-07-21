package emprestimos.repository;

import emprestimos.model.Emprestimo;
import exceptions.EmprestimoNaoEncontradoException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class EmprestimoRepository {
    private Map<Long, Emprestimo> emprestimos = new HashMap<>();
    private Long proximoId = 1L;

    public Long proximoId(){
        return proximoId++;
    }

    public void salvar(Emprestimo emprestimo){
        Objects.requireNonNull(emprestimo, "Emprestimo não pode ser nulo.");
        emprestimos.put(emprestimo.getId(), emprestimo);
    }

    public Emprestimo buscarPorId(Long id){
        Objects.requireNonNull(id, "Id não pode ser nulo.");
        Emprestimo emprestimo = emprestimos.get(id);
        if (emprestimo == null) {
            throw new EmprestimoNaoEncontradoException("Nenhum emprestimo com esse id: " + id);
        }
        return emprestimo;
    }

    public List<Emprestimo> listarTodos(){
        return List.copyOf(emprestimos.values());
    }

    public List<Emprestimo> listarAtivos(){
        return emprestimos.values().stream()
                .filter(Emprestimo::estaAtivo)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Emprestimo> listarAtrasados(){
        return emprestimos.values().stream()
                .filter(Emprestimo::estaAtrasado)
                .collect(Collectors.toUnmodifiableList());
    }
}
