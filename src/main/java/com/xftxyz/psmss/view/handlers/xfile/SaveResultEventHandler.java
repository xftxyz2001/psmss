package com.xftxyz.psmss.view.handlers.xfile;

import com.xftxyz.psmss.controller.service.ProcessManagementService;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SaveResultEventHandler implements EventHandler<ActionEvent> {
    private ProcessManagementService processManagementService;

    public SaveResultEventHandler(ProcessManagementService processManagementService) {
        this.processManagementService = processManagementService;
    }

    @Override
    public void handle(ActionEvent event) {
        processManagementService.saveResult();
    }

}
