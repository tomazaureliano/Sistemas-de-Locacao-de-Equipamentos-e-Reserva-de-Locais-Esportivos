package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Cliente;
import service.ClienteService;

public class TelaClientes {

    private ClienteService service;
    private TableView<Cliente> tabela;

    public TelaClientes(ClienteService service) {
        this.service = service;
    }

    public VBox getView() {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));

        Label titulo = new Label("Gerenciamento de Clientes");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // --- Tabela ---
        tabela = new TableView<>();
        tabela.setItems(FXCollections.observableArrayList(service.getClientes()));

        TableColumn<Cliente, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colNome.setPrefWidth(200);

        TableColumn<Cliente, String> colDoc = new TableColumn<>("Documento");
        colDoc.setCellValueFactory(new PropertyValueFactory<>("documento"));
        colDoc.setPrefWidth(150);
        
        TableColumn<Cliente, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmail.setPrefWidth(200);

        tabela.getColumns().addAll(colNome, colDoc, colEmail);

        // --- Formulário ---
        HBox form = new HBox(10);
        TextField txtNome = new TextField(); txtNome.setPromptText("Nome");
        TextField txtDoc = new TextField(); txtDoc.setPromptText("CPF/RG");
        TextField txtEmail = new TextField(); txtEmail.setPromptText("Email");
        TextField txtTel = new TextField(); txtTel.setPromptText("Telefone");
        Button btnSalvar = new Button("Adicionar");

        btnSalvar.setOnAction(e -> {
            if(!txtNome.getText().isEmpty() && !txtDoc.getText().isEmpty()){
                Cliente novo = new Cliente(txtNome.getText(), txtDoc.getText(), txtEmail.getText(), txtTel.getText());
                service.adicionar(novo);
                tabela.setItems(FXCollections.observableArrayList(service.getClientes())); // Atualiza tabela
                txtNome.clear(); txtDoc.clear(); txtEmail.clear(); txtTel.clear();
            }
        });

        form.getChildren().addAll(txtNome, txtDoc, txtEmail, txtTel, btnSalvar);

        layout.getChildren().addAll(titulo, form, tabela);
        return layout;
    }
}