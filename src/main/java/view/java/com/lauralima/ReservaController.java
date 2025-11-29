package com.lauralima;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;

public class ReservaController {

    @FXML
    private Spinner<?> HoraReserva;

    @FXML
    private Spinner<?> MinutosReserva;

    @FXML
    private Button OnBtnReservarLocal;

    @FXML
    private Button OnBtnVoltarPAraMenu;

    @FXML
    private ChoiceBox<?> escolherClienteReserva;

    @FXML
    private DatePicker escolherDatasReservas;

    @FXML
    private ChoiceBox<?> escolherLocaisReserva;

    @FXML
    private Spinner<?> spinnerEscolherDuracaoReserva;

    @FXML
    private Spinner<?> spinnerQuatidaddeDeConvidadosReserva;

    @FXML
    void ReservarLocal(ActionEvent event) {

    }

    @FXML
    void VoltarParaMenu(ActionEvent event) {

    }

}
