package ui;

import emprestimos.model.Emprestimo;
import emprestimos.service.EmprestimoService;
import equipamentos.enums.StatusEquipamento;
import equipamentos.model.Equipamento;
import equipamentos.model.Headset;
import equipamentos.model.Monitor;
import equipamentos.model.Notebook;
import equipamentos.repository.EquipamentoRepository;
import funcionarios.model.Funcionario;
import funcionarios.repository.FuncionarioRepository;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private final Scanner sc = new Scanner(System.in);
    private final FuncionarioRepository funcionarioRepository;
    private final EquipamentoRepository equipamentoRepository;
    private final EmprestimoService emprestimoService;

    public Menu(FuncionarioRepository funcionarioRepository, EquipamentoRepository equipamentoRepository, EmprestimoService emprestimoService) {
        this.funcionarioRepository = funcionarioRepository;
        this.equipamentoRepository = equipamentoRepository;
        this.emprestimoService = emprestimoService;
    }

    public void rodar(){
        boolean sair = false;

        do {
            mostrarMenu();
            String opcao = sc.nextLine().trim();
            switch (opcao) {
                case "1":
                    registrarFuncionario();
                    break;
                case "2":
                    registrarEquipamento();
                    break;
                case "3":
                    registrarEmprestimo();
                    break;
                case "4":
                    registrarDevolucao();
                    break;
                case "5":
                    listarAtrasados();
                    break;
                case "6":
                    listarEquipamentosPorStatus();
                    break;
                case "0":
                    sair = true;
                    break;
                default:
                    System.out.println("Opção Inválida!");
            }
        }while(!sair);
    }

    private void mostrarMenu(){
        System.out.println("=== Sistema de Empréstimo de Equipamentos ===");
        System.out.println("1 - Registrar funcionario");
        System.out.println("2 - Registrar equipamento");
        System.out.println("3 - Registrar empréstimo");
        System.out.println("4 - Registrar devolução");
        System.out.println("5 - Listar equipamentos atrasados");
        System.out.println("6 - Relatório de equipamentos por status");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void registrarFuncionario(){
        try{
            System.out.println("Digite o nome do funcionario: ");
            String nome = sc.nextLine();
            System.out.println("Digite o departamento do funcionario: ");
            String departamento = sc.nextLine();
            funcionarioRepository.salvar(new Funcionario(funcionarioRepository.proximoId(), nome, departamento));
            System.out.println("Funcionario registrado com sucesso!");
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void registrarEquipamento(){
        try{
            tipoEquipamento();
            String tipoEquipamento = sc.nextLine().trim();
            switch (tipoEquipamento) {
                case "1":
                    System.out.println("Digite o patrimonio do equipamento: ");
                    String patrimonio = sc.nextLine();
                    equipamentoRepository.salvar(new Notebook(equipamentoRepository.proximoId(), patrimonio));
                    System.out.println("Notebook salvo com sucesso!");
                    break;
                case "2":
                    System.out.println("Digite o patrimonio do equipamento: ");
                    patrimonio = sc.nextLine();
                    equipamentoRepository.salvar(new Monitor(equipamentoRepository.proximoId(), patrimonio));
                    System.out.println("Monitor salvo com sucesso!");
                    break;
                case "3":
                    System.out.println("Digite o patrimonio do equipamento: ");
                    patrimonio = sc.nextLine();
                    equipamentoRepository.salvar(new Headset(equipamentoRepository.proximoId(), patrimonio));
                    System.out.println("Headset salvo com sucesso!");
                    break;
                default:
                    System.out.println("Opção Invalida!");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void registrarEmprestimo(){
        try{
            System.out.println("Digite o id do Funcionario: ");
            Long idFuncionario = Long.valueOf(sc.nextLine());
            System.out.println("Digite o id do equipamento: ");
            Long idEquipamento = Long.valueOf(sc.nextLine());
            emprestimoService.registrarEmprestimo(idFuncionario, idEquipamento);
            System.out.println("Emprestimo registrado com sucesso!");
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void registrarDevolucao(){
        try{
            System.out.println("Digite o id do emprestimo que deseja realizar a devolução: ");
            Long idEmprestimo = Long.valueOf(sc.nextLine());
            emprestimoService.registrarDevolucao(idEmprestimo);
            System.out.println("Devolução registrada com sucesso!");
        }catch(Exception e){
            System.out.println("Erro:  " + e.getMessage());
        }
    }

    private void listarAtrasados(){
        List<Emprestimo> emprestimosAtrasados = emprestimoService.listarAtrasados();
        if(emprestimosAtrasados.isEmpty()){
            System.out.println("Nenhum emprestimo atrasado encontrado!");
            return;
        }
        System.out.println("====== Lista de Emprestimos Atrasados ======");
        for (Emprestimo emprestimo : emprestimosAtrasados) {
            System.out.println(emprestimo);
        }
    }

    private void listarEquipamentosPorStatus(){
        Map<StatusEquipamento, List<Equipamento>> equipamentosPorStatus = emprestimoService.listarEquipamentosPorStatus();
        if(equipamentosPorStatus.isEmpty()){
            System.out.println("Nenhum equipamento encontrado!");
            return;
        }
        System.out.println("====== Lista de Emprestimos Por Status ======");

        for (Map.Entry<StatusEquipamento, List<Equipamento>> entry : equipamentosPorStatus.entrySet()) {
            System.out.println("Status: " + entry.getKey() + " | Equipamento: " + entry.getValue());
        }
    }

    private void tipoEquipamento(){
        System.out.println("=== Equipamentos ===");
        System.out.println("1 - Notebook");
        System.out.println("2 - Monitor");
        System.out.println("3 - Headset");
        System.out.print("Qual equipamento deseja resistrar: ");
    }
}
