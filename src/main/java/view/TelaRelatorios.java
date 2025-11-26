package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import service.RelatorioService;

import java.time.LocalDateTime;

public class TelaRelatorios {

    private RelatorioService service;

    public TelaRelatorios(RelatorioService service) {
        this.service = service;
    }

    public VBox getView() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);

        Label titulo = new Label("Geração de Relatórios (PDF)");
        titulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #333;");

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);

        // Botões de Relatório
        Button btnGeral = criarBotaoRelatorio("📋 Todas as Reservas", () -> 
            service.gerarPdfListaReservas("Relatorio_Geral.pdf"));

        Button btnEquip = criarBotaoRelatorio("⚽ Equipamentos Mais Usados", () -> 
            service.gerarPdfEquipamentosMaisUsados(LocalDateTime.now().minusDays(30), LocalDateTime.now().plusDays(30), "Relatorio_Equipamentos.pdf"));

        Button btnClientes = criarBotaoRelatorio("🥇 Clientes Mais Ativos", () -> 
            service.gerarPdfClientesMaisAtivos(LocalDateTime.now().minusDays(30), LocalDateTime.now().plusDays(30), "Relatorio_Clientes_Top.pdf"));

        Button btnLocais = criarBotaoRelatorio("🏟️ Locais Mais Usados", () -> 
            service.gerarPdfLocaisMaisUsados(LocalDateTime.now().minusDays(30), LocalDateTime.now().plusDays(30), "Relatorio_Locais.pdf"));

        grid.add(btnGeral, 0, 0);
        grid.add(btnEquip, 1, 0);
        grid.add(btnClientes, 0, 1);
        grid.add(btnLocais, 1, 1);

        layout.getChildren().addAll(titulo, grid);
        return layout;
    }

    private Button criarBotaoRelatorio(String texto, Runnable acao) {
        Button btn = new Button(texto);
        btn.setPrefSize(250, 60);
        btn.setStyle("-fx-font-size: 14px;");
        btn.setOnAction(e -> {
            try {
                acao.run();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Relatório gerado com sucesso na pasta do projeto!");
                alert.showAndWait();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Erro ao gerar relatório: " + ex.getMessage());
                alert.showAndWait();
            }
        });
        return btn;
    }
}