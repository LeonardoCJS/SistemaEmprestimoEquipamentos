package equipamentos.model;


import equipamentos.enums.StatusEquipamento;
import exceptions.EquipamentoIndisponivelException;
import exceptions.EquipamentoJaDisponivelException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class EquipamentoTest {
    @Test
    void notebookRecemCriadoDeveEstarDisponivel(){
        Notebook notebook = new Notebook(1L, "000");
        assertEquals(StatusEquipamento.DISPONIVEL, notebook.getStatus());
    }

    @Test
    void monitorRecemCriadoDeveEstarDisponivel(){
        Monitor monitor = new Monitor(1L, "000");
        assertEquals(StatusEquipamento.DISPONIVEL, monitor.getStatus());
    }

    @Test
    void headsetRecemCriadoDeveEstarDisponivel(){
        Headset headset = new Headset(1L, "000");
        assertEquals(StatusEquipamento.DISPONIVEL, headset.getStatus());
    }

    @Test
    void marcarComoEmprestadoDeveLancarExcecaoQuandoJaEstaEmprestado(){
        Notebook notebook = new Notebook(1L, "000");
        notebook.marcarComoEmprestado();
        EquipamentoIndisponivelException exception = assertThrows(EquipamentoIndisponivelException.class, notebook::marcarComoEmprestado);
        assertEquals("O equipamento esta Indisponivel, statusEquipamento: " + notebook.getStatus(), exception.getMessage());
    }

    @Test
    void marcarComoEmprestadoDeveLancarExcecaoQuandoEstaEmManutencao(){
        Notebook notebook = new Notebook(1L, "000");
        notebook.marcarComoManutencao();
        EquipamentoIndisponivelException exception = assertThrows(EquipamentoIndisponivelException.class, notebook::marcarComoEmprestado);
        assertEquals("O equipamento esta Indisponivel, statusEquipamento: " + notebook.getStatus(), exception.getMessage());
    }

    @Test
    void marcarComoManutencaoDeveLancarExcecaoQuandoJaEstaEmManutencao(){
        Notebook notebook = new Notebook(1L, "000");
        notebook.marcarComoManutencao();
        EquipamentoIndisponivelException exception = assertThrows(EquipamentoIndisponivelException.class, notebook::marcarComoManutencao);
        assertEquals("O equipamento esta Indisponivel, statusEquipamento: " + notebook.getStatus(), exception.getMessage());
    }

    @Test
    void marcarComoManutencaoDeveLancarExcecaoQuandoEstaEmprestado(){
        Notebook notebook = new Notebook(1L, "000");
        notebook.marcarComoEmprestado();
        EquipamentoIndisponivelException exception = assertThrows(EquipamentoIndisponivelException.class, notebook::marcarComoManutencao);
        assertEquals("O equipamento esta Indisponivel, statusEquipamento: " + notebook.getStatus(), exception.getMessage());
    }

    @Test
    void marcarComoDisponivelDeveLancarExcecaoQuandoJaEstaDisponivel(){
        Notebook notebook = new Notebook(1L, "000");
        EquipamentoJaDisponivelException exception = assertThrows(EquipamentoJaDisponivelException.class, notebook::marcarComoDisponivel);
        assertEquals("O equipamento ja esta Disponivel, statusEquipamento: " + notebook.getStatus(), exception.getMessage());
    }
}
