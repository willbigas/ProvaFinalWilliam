package model.entidades;

/**
 *
 * @author Clony
 */
public class Viagem {
    
    private Integer id;
    private String destino;
    private Float kmInicial;
    private Float kmFinal;
    private Float distancia;
    private int ativo;
    private Carro carro;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Float getKmInicial() {
        return kmInicial;
    }

    public void setKmInicial(Float kmInicial) {
        this.kmInicial = kmInicial;
    }

    public Float getKmFinal() {
        return kmFinal;
    }

    public void setKmFinal(Float kmFinal) {
        this.kmFinal = kmFinal;
    }

    public Float getDistancia() {
        return distancia;
    }

    public void setDistancia(Float distancia) {
        this.distancia = distancia;
    }
    

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    @Override
    public String toString() {
        return "Viagem{" + "id=" + id + ", destino=" + destino + ", kmInicial=" + kmInicial + ", kmFinal=" + kmFinal + ", distancia=" + distancia + ", ativo=" + ativo + ", carro=" + carro + '}';
    }

    
    
    
    
    
    
    
}
