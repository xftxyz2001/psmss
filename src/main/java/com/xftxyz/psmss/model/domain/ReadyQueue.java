package com.xftxyz.psmss.model.domain;

import com.xftxyz.psmss.model.bean.PCB;
import com.xftxyz.psmss.model.bean.ProcessStatus;

public class ReadyQueue extends ProcessQueueBase {

    public ReadyQueue(int cap) {
        super(cap);
    }

    @Override
    public boolean add(PCB pcb) {
        pcb.getStatusProperty().set(ProcessStatus.READY);
        return super.add(pcb);
    }

}
