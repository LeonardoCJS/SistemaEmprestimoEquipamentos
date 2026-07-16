package equipamentos.model;

import equipamentos.enums.StatusEquipamento;
import exceptions.EquipamentoIndisponivelException;

public abstract class Equipamento implements Emprestavel {
    private final Long id;
    private String patrimonio;
    private StatusEquipamento statusEquipamento;

    public Equipamento(Long id, String patrimonio) {
        this.id = id;
        this.patrimonio = patrimonio;
        this.statusEquipamento = StatusEquipamento.DISPONIVEL;
    }

    public Long getId() {
        return id;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public StatusEquipamento getStatus() {
        return statusEquipamento;
    }

    @Override
    public void marcarComoEmprestado(){
        if(estaDisponivel()){
            this.statusEquipamento = StatusEquipamento.EMPRESTADO;
        }else{
            throw new EquipamentoIndisponivelException("O equipamento esta Indisponivel, statusEquipamento: " + this.statusEquipamento);
        }
    }

    public void marcarComoManutencao(){
        if(estaDisponivel()){
            this.statusEquipamento = StatusEquipamento.MANUTENCAO;
        }else{
            throw new EquipamentoIndisponivelException("O equipamento esta Indisponivel, statusEquipamento: " + this.statusEquipamento);
        }
    }

    @Override
    public void marcarComoDisponivel(){
        if(!estaDisponivel()){
            this.statusEquipamento = StatusEquipamento.DISPONIVEL;
        }else{
            throw new EquipamentoIndisponivelException("O equipamento ja esta Disponivel, statusEquipamento: " + this.statusEquipamento);
        }
    }

    @Override
    public boolean estaDisponivel(){
        return statusEquipamento == StatusEquipamento.DISPONIVEL;
    }

    public abstract int calcularPrazoDevolucao();
}
