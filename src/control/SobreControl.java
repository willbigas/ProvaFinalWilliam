/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import view.FramePrincipal;
import view.InternalSobre;

/**
 *
 * @author Clony
 */
public class SobreControl {
    
    InternalSobre internalSobre = null;

    public SobreControl() {
    }
    
    public void abrirTelaSobreAction() {
        if (internalSobre == null) { // se tiver nulo chama janela normalmente
            internalSobre = new InternalSobre();
            FramePrincipal.desktopPane.add(internalSobre);
            internalSobre.setVisible(true);
        } else {//se ele estiver criado
            if (internalSobre.isVisible()) {
                internalSobre.pack();//Redimensiona ao Quadro Original
            } else {
                FramePrincipal.desktopPane.add(internalSobre);//adiciona frame ao JDesktopPane
                internalSobre.setVisible(true);
            }
        }
    }
    
}
