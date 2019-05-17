package control;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.entidades.Viagem;
import uteis.Mensagem;
import uteis.Texto;

/**
 *
 * @author william.mauro
 */
public class Validador {

    public static boolean validarTextField(JTextField textField) {
        if (textField.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean validarViagem(Viagem viagem) {
        System.out.println(viagem);
        if (viagem.getDestino().isEmpty()) {
            Mensagem.atencao(Texto.DESTINO_OBRIGATORIO);
            return false;
        } else if (viagem.getKmFinal() < viagem.getKmInicial()) {
            Mensagem.atencao(Texto.KM_MENOR_QUE_INICIAL);
            return false;
        } else if (viagem.getKmFinal() <= 0) {
            Mensagem.atencao(Texto.KM_NEGATIVO_OU_ZERADO);
            return false;
        } else if (viagem.getKmInicial() < viagem.getCarro().getKilometroAtual()) {
            Mensagem.atencao(Texto.KM_INCIAL_MENOR_KM_CARRO);
            return false;
        }
        return true;
    }

}
