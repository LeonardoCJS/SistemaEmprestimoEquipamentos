package equipamentos.model;

public class Notebook extends Equipamento {
    private static final int PRAZO_DIAS = 30;

    public Notebook(Long id, String patrimonio) {
        super(id, patrimonio);
    }

    @Override
    public int calcularPrazoDevolucao() {
        return PRAZO_DIAS;
    }
}
