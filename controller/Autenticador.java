package controller;
import model.Cliente;

import exception.ErroValidacaoNome;
import exception.ErroValidarCPF;
import exception.SenhaValidacaoException;
import exception.ClienteNaoEncontradoException;
import model.Conta;


public class Autenticador {
    public Conta autenticarUsuario (String cpf, String senha, BancoDeDados db) throws SenhaValidacaoException, ClienteNaoEncontradoException, ErroValidacaoNome, ErroValidarCPF {

        Conta conta = db.buscarConta(cpf);
        System.out.println("--------");
        if (conta.getDono().getSenha().equals(senha)) {
            return conta;
        } else {
            throw new SenhaValidacaoException("SENHA INCORRETA!");
        }
    }
    public String validarNome (String nome) throws ErroValidacaoNome {
        //O nome não pode estar vazio.
        if (nome.isEmpty()) {
            throw new ErroValidacaoNome("O nome não pode estar vazio.");
        }
        //O nome pode conter letras maíusculas, minusculas, espaços e acentos especiais em todos os caracteres do nome.
        if (nome.matches("[a-zA-ZÀ-ÿ\\s]+")) {
            return nome;
        } else {
            throw new ErroValidacaoNome("Formato do nome inválido.");
        }
    }


    public String validarCPF (String CPF) throws ErroValidarCPF {
        //Retira os pontos e traços do CPF se houver.
        String cpfFormatado = CPF.replaceAll("[^\\d]", "");
        //Verifica se a String possui 11 digitos e se são apenas numericos.
        if (!cpfFormatado.matches("\\d{11}")) {
            throw new ErroValidarCPF("CPF inválido.");
        }
        return cpfFormatado;
    }


    //Validação de senha. A senha não pode estar vazia ou ser maior que 6 digitos.
    public String validarSenha (String senha) throws SenhaValidacaoException {
        if (senha == null || senha.isEmpty() || (senha.length() > 6)) {
            throw new SenhaValidacaoException("Formato de senha inválido.");
        }
        if (!senha.matches("\\d{6}")) {
            throw new SenhaValidacaoException("A senha deve ter 6 digitos e ser composta apenas de números.");
        }
        return senha;
    }

}
