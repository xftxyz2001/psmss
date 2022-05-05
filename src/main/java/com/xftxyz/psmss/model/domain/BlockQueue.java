package com.xftxyz.psmss.model.domain;

import com.xftxyz.psmss.model.bean.PCB;
import com.xftxyz.psmss.model.bean.ProcessStatus;

public class BlockQueue extends ProcessQueueBase {

    public BlockQueue(int cap) {
        super(cap);
    }

    @Override
    public boolean add(PCB pcb) {
        pcb.getStatusProperty().set(ProcessStatus.BLOCKED);
        pcb.getHasBlockedProperty().set(true);
        return super.add(pcb);
    }
}
