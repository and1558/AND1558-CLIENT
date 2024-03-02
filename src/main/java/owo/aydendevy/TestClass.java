package owo.aydendevy;

import owo.aydendevy.Events.EventTarget;
import owo.aydendevy.Events.impl.ClientTickEvent;

public class TestClass {

    @EventTarget
    public void testEvents(ClientTickEvent e){
        System.out.println("TEST EVENT CALLED");
    }
}
