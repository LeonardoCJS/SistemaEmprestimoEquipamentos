package equipamentos.model;

public class Monitor extends Equipamento{
    private static final int PRAZO_DIAS = 90;

    public Monitor(Long id, String patrimonio) {
        super(id, patrimonio);
    }

    @Override
    public int calcularPrazoDevolucao() {
        return PRAZO_DIAS;
    }
}
