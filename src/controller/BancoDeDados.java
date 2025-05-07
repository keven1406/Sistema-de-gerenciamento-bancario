package controller;

import java.util.HashMap;
import java.util.Map;
import model.Conta;
import exception.ClienteNaoEncontradoException;

public class BancoDeDados {
    //Atributos
    Map<String, Conta> listaDeContas = new HashMap<>();

    //Metodos
    public Conta buscarConta (String cpf) throws ClienteNaoEncontradoException {
        for (Conta conta : listaDeContas.values()) {
            if (conta.getDono().getCPF().equals(cpf)) {
                return conta;
            }
        }
        throw new ClienteNaoEncontradoException("Cliente Não Encontrado");

    }
    public void addConta(Conta conta) {
        listaDeContas.put(conta.getDono().getCPF(), conta);
    }

}
