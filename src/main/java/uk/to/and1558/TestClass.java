package uk.to.and1558;

import uk.to.and1558.Events.EventTarget;
import uk.to.and1558.Events.impl.ClientTickEvent;

public class TestClass {

    @EventTarget
    public void testEvents(ClientTickEvent e){
        System.out.println("TEST EVENT CALLED");
    }
}
