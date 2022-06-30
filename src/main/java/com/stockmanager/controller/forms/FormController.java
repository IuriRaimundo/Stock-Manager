package com.stockmanager.controller.forms;

import com.stockmanager.view.components.MainBorderPane;
import com.stockmanager.view.forms.exceptions.UnfilledFormException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.NotNull;

import java.util.List;

abstract public class FormController {


    private List<Control> fields;

    protected final String targetScreen;
    protected final String formName;

    /**
     * @param formName Nome do formulário. É apresentado na TopBar.
     * @param targetScreen Ecrã a ser renderizado após submissão ou cancelamento do formulário.
     */
    protected FormController(@NotNull String formName, @NotNull String targetScreen) {
        this.formName = formName;
        this.targetScreen = targetScreen;

        MainBorderPane.controller.setTopBarLabelText(formName);
    }

    /**
     * Função para definir os campos a serem verificados pelo isFormFilled
     * @param fields Lista de campos
     */
    protected void setFields(@NotNull List<Control> fields) {
        this.fields = fields;
    }

    /**
     * Action handler para o botão de confirmar. Valida o preenchimento dos campos,
     * executa o submitAction e altera o ecrã para o targetScreen.
     * @param evt
     */
    @FXML
    protected void confirmHandler(ActionEvent evt) {

        try {
            if (!isFormFilled()) {
                throw new UnfilledFormException();
            }

            submitAction();

            MainBorderPane.controller.changeScreen(targetScreen);
            MainBorderPane.controller.setTopBarLabelText("Stock Manager");
        } catch (Exception e) {
            MainBorderPane.controller.showError(e);
            e.printStackTrace();
        }
    }

    /**
     * Action handler para o botão de cancelar. Troca de ecrã para o targetScreen.
     * @param evt
     */
    @FXML
    protected  void cancelHandler(ActionEvent evt) {
        MainBorderPane.controller.changeScreen(targetScreen);
        MainBorderPane.controller.setTopBarLabelText("Stock Manager");
    }


    /**
     * Método para verificar se os campos estão preenchidos. Checkboxes são ignoradas.
     * @return Verdadeiro se estiverem preenchidos, falso se não.
     */
    private boolean isFormFilled() {
        for (Control field : fields) {
            if (field instanceof TextField && ((TextField) field).getText().length() == 0) {
                return false;
            }

            if (field instanceof ComboBox && ((ComboBox) field).getValue() == null) {
                return false;
            }
        }

        return true;
    }

    /**
     * Ação a ser executada pelo formulário
     */
    protected abstract void submitAction();


}
