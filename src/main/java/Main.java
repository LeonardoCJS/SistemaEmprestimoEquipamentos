import emprestimos.repository.EmprestimoRepository;
import emprestimos.service.EmprestimoService;
import equipamentos.repository.EquipamentoRepository;
import funcionarios.repository.FuncionarioRepository;
import ui.Menu;

public class Main {
    public static void main(String[] args) {
        FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
        EmprestimoRepository emprestimoRepository = new EmprestimoRepository();
        EquipamentoRepository equipamentoRepository = new EquipamentoRepository();
        EmprestimoService emprestimoService = new EmprestimoService(emprestimoRepository, equipamentoRepository, funcionarioRepository);

        Menu menu = new Menu(funcionarioRepository, equipamentoRepository, emprestimoService);

        menu.rodar();
    }
}
