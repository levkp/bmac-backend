package com.bmac.warehouse.adapters.in.facade;

import com.bmac.common.facade.BatchReceiveCommand;
import com.bmac.common.facade.BatchReceiveFacade;
import org.springframework.stereotype.Component;

@Component
public class BatchReceiveFacadeAdapter implements BatchReceiveFacade {



    @Override
    public void receive(BatchReceiveCommand command) {

    }
}
