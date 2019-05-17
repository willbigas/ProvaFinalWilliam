package control;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.entidades.Viagem;

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
            JOptionPane.showMessageDialog(null, "Campo Destino é obrigatório");
            return false;
        } else if (viagem.getKmFinal() < viagem.getKmInicial()) {
            JOptionPane.showMessageDialog(null, "O Km Final é menor que o inicial");
            return false;
        } else if (viagem.getKmFinal() <= 0) {
            JOptionPane.showMessageDialog(null, "O km Final não pode ser negativo ou zerado");
            return false;
        } else if (viagem.getKmInicial() < viagem.getCarro().getKilometroAtual()) {
            JOptionPane.showMessageDialog(null, "O km Inicial não pode ser menor do que a kilometragem atual do carro");
            return false;
        }
        return true;
    }

}
