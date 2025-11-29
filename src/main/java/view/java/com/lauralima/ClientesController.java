package com.lauralima;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ClientesController {

    @FXML
    private TableColumn<?, ?> NomeCliente;

    @FXML
    private Button OnBtnHistoricoDeReservas;

    @FXML
    private Button OnBtnNovoCliente;

    @FXML
    private Button OnBtnPesquisar;

    @FXML
    private Button OnBtnRemoverClientes;

    @FXML
    private Button OnbtnAtualizar;

    @FXML
    private Button VoltarPAraMenu;

    @FXML
    private TableColumn<?, ?> cpfdoCliente;

    @FXML
    private TableView<?> tabeladeClientes;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNomedoCliente;

    @FXML
    private TextField txtTelefone;

    @FXML
    void AtualizarDados(ActionEvent event) {

    }

    @FXML
    void CadastrarNovoCliente(ActionEvent event) {

    }

    @FXML
    void HistoricodeReservasEqui(ActionEvent event) {

    }

    @FXML
    void PesquisarClientes(ActionEvent event) {

    }

    @FXML
    void RemoverClientes(ActionEvent event) {

    }

    @FXML
    void VoltarParaMenu(ActionEvent event) {

    }

}
