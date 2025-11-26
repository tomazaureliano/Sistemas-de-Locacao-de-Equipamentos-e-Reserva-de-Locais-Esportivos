package view;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import model.Cliente;
import model.Equipamento;
import model.Funcionario;
import model.Locacao;
import service.ClienteService;
import service.LocacaoService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TelaLocacaoEquipamentos {

    private final LocacaoService locacaoService;
    private final ClienteService clienteService;
    private final Funcionario funcionarioLogado;

    // Dados da tela
    private ComboBox<Cliente> cbCliente;
    private DatePicker dpData;
    private Spinner<Integer> spHora, spMinuto, spDuracao;
    private Label lblTotal;

    // Controle do "Carrinho" de Equipamentos
    private ComboBox<Equipamento> cbEquipamento;
    private Spinner<Integer> spQuantidadeItem;
    private TableView<ItemCarrinho> tabelaCarrinho;
    private ObservableList<ItemCarrinho> itensCarrinho;

    public TelaLocacaoEquipamentos(LocacaoService locacaoService, ClienteService clienteService, Funcionario funcionario) {
        this.locacaoService = locacaoService;
        this.clienteService = clienteService;
        this.funcionarioLogado = funcionario;
        this.itensCarrinho = FXCollections.observableArrayList();
    }

    public VBox getView() {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #f0f2f5;");

        Label titulo = new Label("⚽ Locação de Equipamentos");
        titulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // --- PARTE 1: Dados Gerais (Cliente e Data) ---
        GridPane gridGeral = new GridPane();
        gridGeral.setHgap(10);
        gridGeral.setVgap(10);

        cbCliente = new ComboBox<>();
        cbCliente.setItems(FXCollections.observableArrayList(clienteService.getClientes()));
        cbCliente.setPromptText("Selecione o Cliente");
        cbCliente.setPrefWidth(250);
        cbCliente.setConverter(new StringConverter<Cliente>() {
            @Override public String toString(Cliente c) { return c == null ? "" : c.getNome(); }
            @Override public Cliente fromString(String s) { return null; }
        });

        dpData = new DatePicker(LocalDate.now());
        
        spHora = new Spinner<>(0, 23, 10); spHora.setPrefWidth(70);
        spMinuto = new Spinner<>(0, 59, 0, 15); spMinuto.setPrefWidth(70);
        HBox boxHora = new HBox(5, spHora, new Label(":"), spMinuto);
        boxHora.setAlignment(Pos.CENTER_LEFT);

        spDuracao = new Spinner<>(1, 24, 1);
        spDuracao.setPrefWidth(80);
        spDuracao.valueProperty().addListener((obs, o, n) -> atualizarTotal());

        gridGeral.add(new Label("Cliente:"), 0, 0); gridGeral.add(cbCliente, 1, 0);
        gridGeral.add(new Label("Data:"), 0, 1);    gridGeral.add(dpData, 1, 1);
        gridGeral.add(new Label("Horário:"), 2, 1); gridGeral.add(boxHora, 3, 1);
        gridGeral.add(new Label("Duração (h):"), 0, 2); gridGeral.add(spDuracao, 1, 2);

        // --- PARTE 2: Seleção de Itens (Adicionar ao Carrinho) ---
        VBox boxItens = new VBox(10);
        boxItens.setStyle("-fx-border-color: #ccc; -fx-border-radius: 5; -fx-padding: 10; -fx-background-color: white;");
        Label lblItens = new Label("Adicionar Itens ao Carrinho");
        lblItens.setStyle("-fx-font-weight: bold;");

        HBox boxSelecaoItem = new HBox(10);
        boxSelecaoItem.setAlignment(Pos.CENTER_LEFT);

        cbEquipamento = new ComboBox<>();
        // Mock de equipamentos (Na vida real viria de estoqueService.listarDisponiveis())
        cbEquipamento.setItems(FXCollections.observableArrayList(getMockEquipamentos()));
        cbEquipamento.setPromptText("Equipamento...");
        cbEquipamento.setPrefWidth(200);
        cbEquipamento.setConverter(new StringConverter<Equipamento>() {
            @Override public String toString(Equipamento e) { 
                return e == null ? "" : e.getNome() + " (Disp: " + e.getQuantidadeDisponivel() + ") - R$" + e.getValor(); 
            }
            @Override public Equipamento fromString(String s) { return null; }
        });

        spQuantidadeItem = new Spinner<>(1, 100, 1);
        spQuantidadeItem.setPrefWidth(80);

        Button btnAdicionar = new Button("➕ Adicionar");
        btnAdicionar.setOnAction(e -> adicionarItemAoCarrinho());

        boxSelecaoItem.getChildren().addAll(cbEquipamento, new Label("Qtd:"), spQuantidadeItem, btnAdicionar);

        // Tabela do Carrinho
        tabelaCarrinho = new TableView<>();
        tabelaCarrinho.setItems(itensCarrinho);
        tabelaCarrinho.setPrefHeight(150);
        tabelaCarrinho.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<ItemCarrinho, String> colNome = new TableColumn<>("Equipamento");
        colNome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().equipamento.getNome()));
        
        TableColumn<ItemCarrinho, Integer> colQtd = new TableColumn<>("Qtd");
        colQtd.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().quantidade).asObject());

        TableColumn<ItemCarrinho, Double> colUnit = new TableColumn<>("Valor Un.");
        colUnit.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().equipamento.getValor()).asObject());

        tabelaCarrinho.getColumns().addAll(colNome, colQtd, colUnit);

        Button btnRemoverItem = new Button("Remover Selecionado");
        btnRemoverItem.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
        btnRemoverItem.setOnAction(e -> {
            ItemCarrinho selecionado = tabelaCarrinho.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                itensCarrinho.remove(selecionado);
                atualizarTotal();
            }
        });

        boxItens.getChildren().addAll(lblItens, boxSelecaoItem, tabelaCarrinho, btnRemoverItem);

        // --- PARTE 3: Rodapé (Total e Confirmar) ---
        HBox footer = new HBox(20);
        footer.setAlignment(Pos.CENTER_RIGHT);
        
        lblTotal = new Label("Total: R$ 0,00");
        lblTotal.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button btnFinalizar = new Button("✅ Finalizar Locação");
        btnFinalizar.setPrefSize(150, 40);
        btnFinalizar.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold;");
        btnFinalizar.setOnAction(e -> finalizarLocacao());

        footer.getChildren().addAll(lblTotal, btnFinalizar);

        layout.getChildren().addAll(titulo, gridGeral, boxItens, footer);
        VBox.setVgrow(boxItens, Priority.ALWAYS); // Ocupa o espaço vertical disponível

        return layout;
    }

    // --- LÓGICA ---

    private void adicionarItemAoCarrinho() {
        Equipamento equip = cbEquipamento.getValue();
        int qtd = spQuantidadeItem.getValue();

        if (equip == null) {
            alerta(Alert.AlertType.WARNING, "Selecione um equipamento.");
            return;
        }
        
        // Verifica estoque localmente (apenas UI)
        if (qtd > equip.getQuantidadeDisponivel()) {
            alerta(Alert.AlertType.WARNING, "Estoque insuficiente! Disponível: " + equip.getQuantidadeDisponivel());
            return;
        }

        // Verifica se já está no carrinho e soma
        for (ItemCarrinho item : itensCarrinho) {
            if (item.equipamento.getNome().equals(equip.getNome())) {
                if (item.quantidade + qtd > equip.getQuantidadeDisponivel()) {
                    alerta(Alert.AlertType.WARNING, "Soma das quantidades excede o estoque.");
                    return;
                }
                item.quantidade += qtd;
                tabelaCarrinho.refresh();
                atualizarTotal();
                return;
            }
        }

        itensCarrinho.add(new ItemCarrinho(equip, qtd));
        atualizarTotal();
    }

    private void atualizarTotal() {
        double subtotalItens = itensCarrinho.stream()
                .mapToDouble(i -> i.equipamento.getValor() * i.quantidade)
                .sum();
        
        long duracao = spDuracao.getValue();
        double totalFinal = subtotalItens * duracao;
        
        lblTotal.setText(String.format("Total: R$ %.2f", totalFinal));
    }

    private void finalizarLocacao() {
        try {
            if (cbCliente.getValue() == null || itensCarrinho.isEmpty() || dpData.getValue() == null) {
                alerta(Alert.AlertType.WARNING, "Preencha o cliente, a data e adicione itens.");
                return;
            }

            LocalDateTime inicio = LocalDateTime.of(dpData.getValue(), LocalTime.of(spHora.getValue(), spMinuto.getValue()));
            LocalDateTime fim = inicio.plusHours(spDuracao.getValue());

            // Converter a lista do carrinho para o Map exigido pelo model
            Map<Equipamento, Integer> mapEquipamentos = new HashMap<>();
            for (ItemCarrinho item : itensCarrinho) {
                mapEquipamentos.put(item.equipamento, item.quantidade);
            }

            Locacao novaLocacao = new Locacao(
                    cbCliente.getValue(),
                    inicio,
                    fim,
                    funcionarioLogado,
                    mapEquipamentos,
                    Locacao.StatusLocacao.ATIVO // Adicionei um status padrão
            );

            // O service vai validar o estoque real e lançar exceção se falhar
            locacaoService.criarLocacao(novaLocacao);

            alerta(Alert.AlertType.INFORMATION, "Locação realizada com sucesso!");
            
            // Limpa a tela
            itensCarrinho.clear();
            atualizarTotal();

        } catch (Exception e) {
            alerta(Alert.AlertType.ERROR, "Erro ao locar: " + e.getMessage());
        }
    }

    private void alerta(Alert.AlertType tipo, String msg) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    // --- MOCKS E CLASSES AUXILIARES ---

    private List<Equipamento> getMockEquipamentos() {
        // Idealmente viria de um EstoqueService
        List<Equipamento> lista = new ArrayList<>();
        lista.add(new Equipamento("Bola Oficial", "Esporte", 10, 20, Equipamento.Condicao.DISPONIVEL, 10));
        lista.add(new Equipamento("Colete Azul", "Acessório", 20, 5, Equipamento.Condicao.DISPONIVEL, 20));
        lista.add(new Equipamento("Raquete Tênis", "Esporte", 5, 50, Equipamento.Condicao.DISPONIVEL, 5));
        return lista;
    }

    // Classe interna simples para representar uma linha da tabela
    public static class ItemCarrinho {
        Equipamento equipamento;
        int quantidade;

        public ItemCarrinho(Equipamento equipamento, int quantidade) {
            this.equipamento = equipamento;
            this.quantidade = quantidade;
        }
        public Equipamento getEquipamento() { return equipamento; }
        public int getQuantidade() { return quantidade; }
    }
}