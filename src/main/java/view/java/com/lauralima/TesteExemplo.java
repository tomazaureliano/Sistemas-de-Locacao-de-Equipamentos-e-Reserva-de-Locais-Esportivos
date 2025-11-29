package com.lauralima;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class TesteExemplo {

    @FXML
    private ChoiceBox<?> CBChoiceBoxEquipamentos;

    @FXML
    private ChoiceBox<?> CBChoiceBoxEquipamentos1;

    @FXML
    private ChoiceBox<?> CBChoiceBoxLocais;

    @FXML
    private Label LbSituacaodosLocais;

    @FXML
    private Button OnBtnPEsquisarCliente;

    @FXML
    private Button OnBtnPEsquisarCliente1;

    @FXML
    private Button OnBtnPEsquisarCliente11;

    @FXML
    private Button OnBtnPEsquisarCliente12;

    @FXML
    private Button OnBtnPEsquisarCliente121;

    @FXML
    private TabPane mainTabPane;

    @FXML
    private Tab tabGerenciarClientes;

    @FXML
    private Tab tabGerenciarEquipamentos;

    @FXML
    private Tab tabGerenciarLocais;

    @FXML
    private Tab tabNovaReserva;

    @FXML
    private Tab tabRelatorios;

    @FXML
    private TextField tfPesquisarCliente;

    @FXML
    private TextField tfPesquisarCliente1;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtUsuario;

    @FXML
    private ImageView img1;

    @FXML
    public void initialize() {
        // Desabilita todas as abas, exceto a primeira (Tab Home) no início
        setTabsDisabled(true);
    }

    @FXML
    void handleLogin(ActionEvent event) {
    String usuario = txtUsuario.getText();
    String senha = txtSenha.getText();

    // Lógica de Validação de Credenciais
    if (isValidCredentials(usuario, senha)) {
        // Credenciais válidas: Habilita as abas
        setTabsDisabled(false);

        // Opcional: Leva o usuário para a primeira aba de trabalho
        // Oculta a área de login se for uma tela separada, ou limpa os campos
        
        mainTabPane.getSelectionModel().select(tabNovaReserva);
        
        // Opcional: Mostra um alert de sucesso
        showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Login efetuado com sucesso!", "Bem-vindo ao Sportelli!");
        
    } else {
        // Credenciais inválidas: MOSTRAR ALERTA VISUAL
        
        // 1. Mostrar Alert de Erro
        showAlert(Alert.AlertType.ERROR, "Erro de Login", "Credenciais Inválidas", "O usuário ou a senha digitados não estão corretos. Tente novamente.");

        // 2. Opcional: Limpar os campos para forçar nova digitação
        txtSenha.clear();
        txtUsuario.requestFocus(); // Coloca o foco no campo do usuário

        // A linha abaixo (sout) não será mais necessária!
        // System.out.println("Usuário ou senha inválidos.");
    }
}

/**
 * Função auxiliar para exibir o alerta de forma centralizada.
 * * @param type O tipo de alerta (ERROR, WARNING, INFORMATION, CONFIRMATION).
 * @param title O título da janela de alerta.
 * @param header O cabeçalho da mensagem (acima do conteúdo).
 * @param content O conteúdo principal da mensagem.
 */
    private void showAlert(Alert.AlertType type, String title, String header, String content) {
    Alert alert = new Alert(type);
    
    // Define o Stage (janela) proprietário para garantir que o Alert apareça na frente
    // Mas para isso, precisaríamos injetar um Parent do FXML.
    // Por simplicidade, vou omitir o Stage proprietário aqui.

    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait(); // Pausa a aplicação até que o usuário feche o alerta
    }

    private boolean isValidCredentials(String user, String pass) {
        // Exemplo: Aceita apenas 'admin' e '12345'
        return "admin".equals(user) && "12345".equals(pass);
    }

    private void setTabsDisabled(boolean disabled) {
        // A Tab 'Home' (índice 0) deve permanecer sempre habilitada.
        // Começamos a desabilitar/habilitar a partir da segunda tab.

        if (tabNovaReserva != null) {
            tabNovaReserva.setDisable(disabled);
        }
        if (tabGerenciarClientes != null) {
            tabGerenciarClientes.setDisable(disabled);
        }
        if (tabGerenciarEquipamentos != null) {
            tabGerenciarEquipamentos.setDisable(disabled);
        }
        if (tabGerenciarLocais != null) {
            tabGerenciarLocais.setDisable(disabled);
        }
        if (tabRelatorios != null) {
            tabRelatorios.setDisable(disabled);
        }
        
        // Se alguma tab não foi injetada (null), ela será ignorada.
    }

}



