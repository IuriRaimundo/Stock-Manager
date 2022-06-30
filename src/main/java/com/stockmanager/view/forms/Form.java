package com.stockmanager.view.forms;

import javafx.event.ActionEvent;

public interface Form {

    boolean isFormFilled();

    void confirmHandler(ActionEvent event);
    void cancelHandler(ActionEvent event);

}
