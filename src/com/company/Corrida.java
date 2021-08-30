package com.company;

import java.util.ArrayList;
import java.util.List;

public class Corrida {
    private double distancia;
    private double PremioEmDolares;
    private String Nome;
    private int QuantidadeDeVeiculosPermitido;
    private List<Veiculo> listaDeVeiculos;
    private SocorristaCarro socorristaCarro;
    private SocorristaMoto socorristaMoto;

    public Corrida(double distancia, double premioEmDolares, String nome, int QuantidadeDeVeiculosPermitido) {
        this.distancia = distancia;
        this.PremioEmDolares = premioEmDolares;
        this.Nome = nome;
        this.QuantidadeDeVeiculosPermitido = QuantidadeDeVeiculosPermitido;

        this.listaDeVeiculos = new ArrayList<Veiculo>();
        this.socorristaCarro = new SocorristaCarro();
        this.socorristaMoto = new SocorristaMoto();
    }

    public void adicionarCarro(double velocidade, double aceleracao, int AnguloDeVirada, String placa){
        if (QuantidadeDeVeiculosPermitido == listaDeVeiculos.size()){
            System.out.println("Não é permitido adicionar mais veículos");
        } else {
            listaDeVeiculos.add(new Carro(velocidade,aceleracao,AnguloDeVirada,placa));
        }
    }

    public void adicionarMoto(double velocidade, double aceleracao, int AnguloDeVirada, String placa){
        if (QuantidadeDeVeiculosPermitido == listaDeVeiculos.size()){
            System.out.println("Não é permitido adicionar mais veículos");
        } else {
            listaDeVeiculos.add(new Moto(velocidade,aceleracao,AnguloDeVirada,placa));
        }
    }

    // 4.
    public void removerVeiculo(String veiculo){
        listaDeVeiculos.remove(veiculo);
    }

    // 4
//    public void removerVeiculoComPlaca(String umaPlaca){
//        this.veiculos.remove(buscarVeiculo(placa));
//    }


    public Veiculo buscarVeiculo(String placa){
        return this.listaDeVeiculos.stream().filter(x -> x.getPlaca().equals(placa)).findFirst().orElse(null);
    }

    public Veiculo buscarVencedor(){
        Veiculo win = null;
        int max = 0;

        for (Veiculo v : listaDeVeiculos){
            int valor = (int) (
                    (v.getVelocidade() / 2 * v.getAceleracao()) / v.getAnguloDeVirada() * (v.getPeso() - v.getRodas() *100)
            );

            if (valor > max){
                win = v;
            }
        }
        return win;
    }

    public void socorrerCarro (String placa){
        this.socorristaCarro.socorrer((Carro) buscarVeiculo(placa));
    }

    public void socorrerMoto (String placa){
        this.socorristaMoto.socorrer((Moto) buscarVeiculo(placa));
    }

}
