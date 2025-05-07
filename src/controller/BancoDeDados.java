package controller;

import java.util.HashMap;
import java.util.Map;
import model.Cliente;
import exception.ClienteNaoEncontradoException;

public class BancoDeDados {
    //Atributos
    Map<String, Cliente> listaDeClientes = new HashMap<>();



    //Metodos
    public Cliente buscarCliente (String cpf) throws ClienteNaoEncontradoException {
        if (listaDeClientes.containsKey(cpf)) {
            return listaDeClientes.get(cpf);
        } else {
            throw new ClienteNaoEncontradoException("Cliente Não Encontrado");
        }
    }
    public void addCliente(String cpf, Cliente cliente) {
        listaDeClientes.put(cpf, cliente);
    }

}
