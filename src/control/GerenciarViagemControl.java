/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.dao.CarroDao;
import model.dao.ViagemDao;
import model.entidades.Carro;
import model.entidades.Viagem;
import model.tablemodels.TableModelViagem;
import view.FramePrincipal;
import view.InternalGerenciarViagem;

/**
 *
 * @author Clony
 */
public class GerenciarViagemControl {

    private InternalGerenciarViagem internalGerenciarViagem = null;

    CarroDao carroDao;
    ViagemDao viagemDao;
    TableModelViagem tableModelViagem;
    List<Carro> listCarros;
    Viagem viagem;

    public GerenciarViagemControl() {
        carroDao = new CarroDao();
        viagemDao = new ViagemDao();
        tableModelViagem = new TableModelViagem();
        tableModelViagem.adicionar(viagemDao.listar());
        carregarComboBox();

    }

    public void abrirTelaGerenciarViagemAction() {
        if (internalGerenciarViagem == null) { // se tiver nulo chama janela normalmente
            internalGerenciarViagem = new InternalGerenciarViagem(this);
            FramePrincipal.desktopPane.add(internalGerenciarViagem);
            internalGerenciarViagem.setVisible(true);
        } else {//se ele estiver criado
            if (internalGerenciarViagem.isVisible()) {
                internalGerenciarViagem.pack();//Redimensiona ao Quadro Original
            } else {
                FramePrincipal.desktopPane.add(internalGerenciarViagem);//adiciona frame ao JDesktopPane
                internalGerenciarViagem.setVisible(true);
            }
        }
        internalGerenciarViagem.getTbViagens().setModel(tableModelViagem);
    }

    public void carregarComboBox() {
        listCarros = carroDao.listar();
        System.out.println(listCarros);
        DefaultComboBoxModel<Carro> model = new DefaultComboBoxModel(listCarros.toArray());
        InternalGerenciarViagem.cbCarros.setModel(model);
    }

    public void cadastrarViagemAction() {
        if (!Validador.validarTextField(internalGerenciarViagem.getTfDestino())) {
            JOptionPane.showMessageDialog(null, "O Campo Destino está vazio ");
            return;
        }
        if (!Validador.validarTextField(internalGerenciarViagem.getTfKmFinal())) {
            JOptionPane.showMessageDialog(null, "O Campo KmFinal está vazio ");
            return;
        }
        if (!Validador.validarTextField(internalGerenciarViagem.getTfKmInicial())) {
            JOptionPane.showMessageDialog(null, "O Campo KmInicial está vazio ");
            return;
        }

        viagem = new Viagem();
        viagem.setDestino(internalGerenciarViagem.getTfDestino().getText());
        viagem.setKmInicial(Float.valueOf(internalGerenciarViagem.getTfKmInicial().getText()));
        viagem.setKmFinal(Float.valueOf(internalGerenciarViagem.getTfKmFinal().getText()));
        viagem.setCarro((Carro) internalGerenciarViagem.getCbCarros().getSelectedItem());

        if (!Validador.validarViagem(viagem)) {
            return;
        }

        Float distanciaPercorrida = Float.valueOf(internalGerenciarViagem.getTfKmFinal().getText()) - Float.valueOf(internalGerenciarViagem.getTfKmInicial().getText());

        Integer idInserido = viagemDao.cadastrar(viagem);
        if (idInserido != 0) {
            carroDao.atualizarKmAtual(distanciaPercorrida, viagem.getCarro().getId());
            viagem.setId(idInserido);
            tableModelViagem.addRow(viagem);
            limparCampos();
            JOptionPane.showMessageDialog(null, "Viagem adicionada com sucesso!");
        }
        viagem = null;
    }
    
    public void removerViagemAction() {
        int retorno = JOptionPane.showConfirmDialog(null, "deseja excluir o produto ");

        if (retorno == JOptionPane.NO_OPTION) {
            return;
        }

        if (retorno == JOptionPane.CANCEL_OPTION) {
            return;
        }

        if (retorno == JOptionPane.YES_OPTION) {
            viagem = tableModelViagem.getRow(internalGerenciarViagem.getTbViagens().getSelectedRow());
            boolean deletado = viagemDao.deletar(viagem.getId());
            if (deletado) {
                tableModelViagem.removeRow(internalGerenciarViagem.getTbViagens().getSelectedRow());
                internalGerenciarViagem.getTbViagens().clearSelection();
                JOptionPane.showMessageDialog(null, "Sucesso ao Deletar!");

            } else {
                JOptionPane.showMessageDialog(null, "Erro ao deletar");
            }
        }

    }
    
    public void limparCampos() {
        internalGerenciarViagem.getTfDestino().setText("");
        internalGerenciarViagem.getTfKmFinal().setText("null");
        internalGerenciarViagem.getTfKmInicial().setText("null");
        internalGerenciarViagem.getCbCarros().setSelectedIndex(0);
        internalGerenciarViagem.getTbViagens().clearSelection();
    }

}
