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
import uteis.Mensagem;
import uteis.Texto;
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
    Integer linhaSelecionada;

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
            Mensagem.info(Texto.DESTINO_VAZIO);
            return;
        }
        if (!Validador.validarTextField(internalGerenciarViagem.getTfKmFinal())) {
            Mensagem.info(Texto.KM_FINAL_VAZIO);
            return;
        }
        if (!Validador.validarTextField(internalGerenciarViagem.getTfKmInicial())) {
            Mensagem.info(Texto.KM_INICIAL_VAZIO);
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
        viagem.setDistancia(distanciaPercorrida);

        Integer idInserido = viagemDao.cadastrar(viagem);
        if (idInserido != 0) {
            carroDao.atualizarKmAtual(distanciaPercorrida, viagem.getCarro().getId());
            viagem.setId(idInserido);
            tableModelViagem.addRow(viagem);
            limparCampos();
            Mensagem.info(Texto.SUCESSO_CADASTRAR);
        } else {
            Mensagem.info(Texto.ERRO_CADASTRAR);
        }
        viagem = null;
    }

    public void removerViagemAction() {
        int retorno = Mensagem.confirmacao(Texto.PERGUNTA_EXCLUIR);

        if (retorno == JOptionPane.NO_OPTION) {
            return;
        }

        if (retorno == JOptionPane.YES_OPTION) {
            viagem = tableModelViagem.getRow(internalGerenciarViagem.getTbViagens().getSelectedRow());
            boolean deletado = viagemDao.deletar(viagem.getId());
            if (deletado) {
                tableModelViagem.removeRow(internalGerenciarViagem.getTbViagens().getSelectedRow());
                internalGerenciarViagem.getTbViagens().clearSelection();
                Mensagem.info(Texto.SUCESSO_DELETAR);

            } else {
                Mensagem.erro(Texto.ERRO_DELETAR);
            }
        }
        viagem = null;

    }

    public void alterarViagemAction() {
        viagem.setDestino(internalGerenciarViagem.getTfDestino().getText());
        viagem.setKmInicial(Float.valueOf(internalGerenciarViagem.getTfKmInicial().getText()));
        viagem.setKmFinal(Float.valueOf(internalGerenciarViagem.getTfKmFinal().getText()));
        viagem.setCarro((Carro) internalGerenciarViagem.getCbCarros().getSelectedItem());

        if (!Validador.validarViagem(viagem)) {
            return;
        }

        Float distanciaPercorrida = Float.valueOf(internalGerenciarViagem.getTfKmFinal().getText()) - Float.valueOf(internalGerenciarViagem.getTfKmInicial().getText());
        viagem.setDistancia(distanciaPercorrida);

        boolean alterado = viagemDao.alterar(viagem);
        linhaSelecionada = internalGerenciarViagem.getTbViagens().getSelectedRow();
        if (alterado) {
            tableModelViagem.updateRow(linhaSelecionada, viagem);
            Mensagem.info(Texto.SUCESSO_EDITAR);
            limparCampos();
        } else {
            Mensagem.erro(Texto.ERRO_EDITAR);
        }
        viagem = null;
    }

    public void gravarAction() {
        if (viagem == null) {
            cadastrarViagemAction();
        } else {
            alterarViagemAction();
        }
    }

    public void carregarViagemAction() {
        viagem = tableModelViagem.getRow(internalGerenciarViagem.getTbViagens().getSelectedRow());
        internalGerenciarViagem.getTfDestino().setText(viagem.getDestino());
        internalGerenciarViagem.getTfKmFinal().setText(String.valueOf(viagem.getKmFinal()));
        internalGerenciarViagem.getTfKmInicial().setText(String.valueOf(viagem.getKmInicial()));
        internalGerenciarViagem.getCbCarros().getModel().setSelectedItem(viagem.getCarro());
    }

    public void limparCampos() {
        internalGerenciarViagem.getTfDestino().setText("");
        internalGerenciarViagem.getTfKmFinal().setText("");
        internalGerenciarViagem.getTfKmInicial().setText("");
        internalGerenciarViagem.getCbCarros().setSelectedIndex(0);
        internalGerenciarViagem.getTbViagens().clearSelection();
    }

}
