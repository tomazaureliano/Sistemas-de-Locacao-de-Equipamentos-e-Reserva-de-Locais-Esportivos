package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import model.Cliente;
import model.Funcionario;
import model.LocalEsportivo;
import model.Reserva;
import service.ClienteService;
import service.ReservaService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class TelaReservas {

    private ReservaService reservaService;
    private ClienteService clienteService;
    private Funcionario funcionarioLogado;

    public TelaReservas(ReservaService rs, ClienteService cs, Funcionario f) {
        this.reservaService = rs;
        this.clienteService = cs;
        this.funcionarioLogado = f;
    }

    public VBox getView() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));

        Label titulo = new Label("Nova Reserva");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(15);

        // Campos
        ComboBox<Cliente> cbCliente = new ComboBox<>();
        cbCliente.setItems(FXCollections.observableArrayList(clienteService.getClientes()));
        // Mostra o nome do cliente no ComboBox
        cbCliente.setConverter(new StringConverter<Cliente>() {
            @Override public String toString(Cliente c) { return c != null ? c.getNome() : ""; }
            @Override public Cliente fromString(String string) { return null; }
        });

        ComboBox<LocalEsportivo> cbLocal = new ComboBox<>();
        // Mock de locais (Idealmente viria de um LocalService)
        cbLocal.getItems().add(new LocalEsportivo("Quadra A", "Quadra", 20, 100.0, LocalEsportivo.Condicao.DISPONIVEL, new ArrayList<>()));
        cbLocal.getItems().add(new LocalEsportivo("Campo Society", "Campo", 30, 200.0, LocalEsportivo.Condicao.DISPONIVEL, new ArrayList<>()));
        cbLocal.setConverter(new StringConverter<LocalEsportivo>() {
            @Override public String toString(LocalEsportivo l) { return l != null ? l.getNome() : ""; }
            @Override public LocalEsportivo fromString(String s) { return null; }
        });

        DatePicker dataReserva = new DatePicker(LocalDate.now());
        
        Spinner<Integer> spinnerHoraInicio = new Spinner<>(0, 23, 10);
        Spinner<Integer> spinnerDuracao = new Spinner<>(1, 5, 1);
        
        TextField txtConvidados = new TextField("10");

        Button btnReservar = new Button("Confirmar Reserva");
        btnReservar.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold;");

        // Posicionamento no Grid
        form.add(new Label("Cliente:"), 0, 0); form.add(cbCliente, 1, 0);
        form.add(new Label("Local:"), 0, 1);   form.add(cbLocal, 1, 1);
        form.add(new Label("Data:"), 0, 2);    form.add(dataReserva, 1, 2);
        form.add(new Label("Hora Início:"), 0, 3); form.add(spinnerHoraInicio, 1, 3);
        form.add(new Label("Duração (h):"), 0, 4); form.add(spinnerDuracao, 1, 4);
        form.add(new Label("Convidados:"), 0, 5); form.add(txtConvidados, 1, 5);
        form.add(btnReservar, 1, 6);

        // Ação
        btnReservar.setOnAction(e -> {
            try {
                if(cbCliente.getValue() == null || cbLocal.getValue() == null || dataReserva.getValue() == null) {
                    mostrarAlerta(Alert.AlertType.WARNING, "Preencha todos os campos!");
                    return;
                }

                LocalDateTime inicio = dataReserva.getValue().atTime(LocalTime.of(spinnerHoraInicio.getValue(), 0));
                LocalDateTime fim = inicio.plusHours(spinnerDuracao.getValue());
                int convidados = Integer.parseInt(txtConvidados.getText());

                // ID aleatório para teste
                int idReserva = (int)(Math.random() * 1000);

                Reserva nova = new Reserva(idReserva, convidados, cbCliente.getValue(), 
                                           Reserva.StatusReserva.ATIVA, cbLocal.getValue(), 
                                           inicio, fim, funcionarioLogado);
                
                reservaService.criarReserva(nova);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Reserva criada com sucesso!");

            } catch (Exception ex) {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro: " + ex.getMessage());
            }
        });

        layout.getChildren().addAll(titulo, form);
        return layout;
    }

    private void mostrarAlerta(Alert.AlertType tipo, String msg) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}