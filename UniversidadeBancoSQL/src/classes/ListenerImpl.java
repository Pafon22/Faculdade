/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;
/**
 *
 * @author PauloAfonso
 */
public class ListenerImpl implements Listener {

    @Override
    public void receivedEvent(Event evento) {
        System.out.println("Evento recebido.");
    }

}
