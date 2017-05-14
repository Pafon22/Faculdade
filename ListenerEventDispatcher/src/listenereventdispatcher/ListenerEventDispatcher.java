/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listenereventdispatcher;

/**
 *
 * @author PauloAfonso
 */
public class ListenerEventDispatcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

// cria um novo listener que irá receber o evento quando for disparado
        Listener listener = new ListenerImpl();
// Adiciona o listener
        Dispatcher.getInstance().addListener(listener);
// Outro modo para criar um listener (Anonymous inner type)
        Dispatcher.getInstance().addListener(new Listener() {
            @Override
            public void receivedEvent(Event event) {
                System.out.println(event.getName());
            }
        });
// criando um novo evento e disparando para os listeners
        Dispatcher.getInstance().dispatchEvent(new Event("hello world"));
    }

}
