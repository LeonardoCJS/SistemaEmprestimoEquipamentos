package equipamentos.model;

public class Headset extends Equipamento{
    private static final int PRAZO_DIAS = 15;

    public Headset(Long id, String patrimonio) {
        super(id, patrimonio);
    }

    @Override
    public int calcularPrazoDevolucao() {
        return PRAZO_DIAS;
    }
}
