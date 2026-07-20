package equipamentos.repository;

import equipamentos.enums.StatusEquipamento;
import equipamentos.model.Equipamento;
import exceptions.EquipamentoNaoEncontradoException;
import exceptions.PatrimonioExistenteException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class EquipamentoRepository {
    private Map<Long, Equipamento> equipamentos = new HashMap<Long, Equipamento>();
    private Long proximoId = 1L;

    public Long proximoId(){
        return proximoId++;
    }


    public void salvar(Equipamento equipamento){
        Objects.requireNonNull(equipamento, "Equipamento não pode ser nulo.");
        if(equipamentos.values().stream().anyMatch(equip -> equip.getPatrimonio().equals(equipamento.getPatrimonio()))){
            throw new PatrimonioExistenteException("Ja existe um equipamento com esse patrimonio!");
        }
        equipamentos.put(equipamento.getId(), equipamento);
    }

    public Equipamento buscarPorId(Long id){
        Objects.requireNonNull(id, "Id não pode ser nulo.");
        Equipamento equipamento = equipamentos.get(id);
        if (equipamento == null) {
            throw new EquipamentoNaoEncontradoException("Nenhum equipamento com esse id!");
        }
        return equipamento;
    }

    public List<Equipamento> listarTodos(){
        return List.copyOf(equipamentos.values());
    }

    public List<Equipamento> listarPorStatus(StatusEquipamento status){
        return equipamentos.values().stream()
                .filter(equip -> equip.getStatus() == status)
                .collect(Collectors.toUnmodifiableList());
    }
}
