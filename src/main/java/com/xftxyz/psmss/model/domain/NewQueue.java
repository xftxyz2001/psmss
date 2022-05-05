package com.xftxyz.psmss.model.domain;

import com.xftxyz.psmss.model.bean.PCB;
import com.xftxyz.psmss.model.bean.ProcessStatus;

public class NewQueue extends ProcessQueueBase {

    public NewQueue(int cap) {
        super(cap);
    }

    @Override
    public boolean add(PCB pcb) {
        pcb.getStatusProperty().set(ProcessStatus.IDLE);
        return super.add(pcb);
    }

}
