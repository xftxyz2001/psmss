package com.xftxyz.psmss.view.handlers.xedit;

import com.xftxyz.psmss.controller.service.ProcessManagementService;
import com.xftxyz.psmss.view.ui.ProcessAddStage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AddEventHandler implements EventHandler<ActionEvent> {

    private ProcessManagementService processManagementService;

    public AddEventHandler(ProcessManagementService processManagementService) {
        this.processManagementService = processManagementService;
    }

    @Override
    public void handle(ActionEvent event) {
        // PCB pcbTemp;
        // ObservableList<PCB> queue =
        // processManagementService.getAllProcess().getQueue();
        // ProcessAddStage processAddStage = new
        // ProcessAddStage(processManagementService);
        new ProcessAddStage(processManagementService);
        // int name = 0;
        // int priority = 0;
        // int arriveTime = 0;
        // int totalTime = 0;
        // boolean block = false;
        // pcbTemp = new PCB(name, priority, arriveTime, totalTime, block);
        // processManagementService.addProcess(pcbTemp);

        // addProcessStage.setTitle("arg0");
        // processAddStage.show();
    }

}
