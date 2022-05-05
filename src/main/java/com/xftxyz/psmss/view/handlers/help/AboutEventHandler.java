package com.xftxyz.psmss.view.handlers.help;

import com.xftxyz.psmss.view.ui.UIData;
import com.xftxyz.psmss.view.utils.DialogUtil;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AboutEventHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        DialogUtil.showInfoDialog(UIData.aboutTitle, UIData.version, UIData.aboutContent);
    }

}
