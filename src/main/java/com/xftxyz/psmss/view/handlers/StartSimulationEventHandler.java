package com.xftxyz.psmss.view.handlers;

import com.xftxyz.psmss.controller.service.ProcessManagementService;
import com.xftxyz.psmss.view.utils.DialogUtil;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StartSimulationEventHandler implements EventHandler<ActionEvent> {

    private ProcessManagementService processManagementService;
    // private MenuItem menuItemStartSimulation;
    // private MenuItem menuItemStopSimulation;

    public StartSimulationEventHandler(ProcessManagementService processManagementService) {
        this.processManagementService = processManagementService;
        // this.menuItemStartSimulation = menuItemStartSimulation;
        // this.menuItemStopSimulation = menuItemStopSimulation;
    }

    @Override
    public void handle(ActionEvent event) {
        if (processManagementService.getTimer() != null) {
            return;
        }
        if (processManagementService.getAllProcess().size() <= 0) {
            DialogUtil.showWarningDialog("提示", null, "没有进程");
            return;
        }
        processManagementService.start();

    }

}
