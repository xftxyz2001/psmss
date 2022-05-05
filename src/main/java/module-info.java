module com.xftxyz.psmss {
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires javafx.fxml;

    // opens com.xftxyz.psmss to javafx.fxml;

    exports com.xftxyz.psmss.controller.service;
    exports com.xftxyz.psmss.model.bean;
    exports com.xftxyz.psmss.model.domain;
    exports com.xftxyz.psmss.view.ui;
    // exports com.xftxyz.psmss.model.comparator;
}
