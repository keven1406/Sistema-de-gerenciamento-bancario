package model;

import exception.*;
import controller.Autenticador;
//Para criar um novo cliente:
//  -Deverá ser inserido um nome válido. O nome não pode ser nullo, vazio ou ter numerais.
//  -Um CPF válido (11111111111 ou 111.111.111-11)
//  -Uma senha numerica de 6 digitos.


import java.util.List;

public class Cliente {
    //Atributos
    private String nome;
    private String CPF;
    private String senha;
    private Conta conta;

    //Construtor
    public Cliente (String nome, String CPF, String senha) throws SenhaValidacaoException, ErroValidarCPF, ErroValidacaoNome {
        Autenticador autenticador = new Autenticador();
        //Atribuindo aos atributos os valores validados.
        this.nome = autenticador.validarNome(nome);
        this.CPF = autenticador.validarCPF(CPF);
        this.senha = autenticador.validarSenha(senha);
    }


    //Metodos Get e Set
    public String getNome () {
        return nome;
    }
    public void setNome (String nome) {
        this.nome = nome;
    }
    public String getCPF () {
        return CPF;
    }
    public void setCPF (String CPF) {
        this.CPF = CPF;
    }
    public String getSenha () {
        return senha;
    }
    public void setSenha (String senha) {
        this.senha = senha;
    }



    //Autenticacao de Usuario. Caso aconteça algum erro será lançado a excecao ErroAutenticacao.
    public void autenticarUsuario (String CPF, String senha) throws ErroAutenticacaoLogin {
        String cpfFormatado = CPF.replaceAll("[^\\d]", "");
        if (this.CPF.equals(cpfFormatado) && this.senha.equals(senha)) {
            System.out.println("Usuario autenticado com sucesso.");
        } else {
            throw new ErroAutenticacaoLogin("Usuário ou senha incorreto.");
        }
    }
    //SOBREESCRITA DO METODO EQUALS PARA QUE OS CLIENTES POSSAM SER COMPARADOS POR MEIO DO CPF
    @Override
    public boolean equals (Object obj) {
        if (this == obj) return true; // mesmo objeto
        if (obj == null || getClass() != obj.getClass()) return false;

        Cliente outro = (Cliente) obj;
        return CPF != null && outro.CPF.equals(((Cliente) obj).CPF);
    }
    //SOBREESCRITA DO HASHCODE PARA POSSIBILITAR COMPARAÇÃO E BUSCA POR MEIO DO CPF.
    @Override
    public int hashCode () {
        if (CPF == null ) {
            return 0;
        } else {
            return CPF.hashCode();
        }
    }

    @Override
    public String toString () {
        //Falta implementar a classe conta para obter o numero da conta.
        return "TITULAR: " + nome + "\nCPF: " + CPF +"\nNUMERO DA CONTA: 11122-3";
    }

    //Adicionar model.Cliente.Conta
    public void adicionarConta (Conta conta) {
        this.conta = conta;
    }
    //Ver conta
    public Conta getConta () {
        return conta;
    }

    public static class Conta {
        //Atributos
        private double saldo;
        List<Transacao> transacoes;


        //Construtor
        public Conta (double saldo) {
            this.saldo = saldo;
        }

        public double consultarSaldo() {
            return saldo;
        }
        public void realizarDeposito (double valor) {
            this.saldo += valor;
        }
        public void sacar (double valor) {
            //Logica do saque.
        }
        public void transferirSaldo (double valor, Conta contaDestino) {
            //fazer a lógica...
            //adicionar ao historico de transacoes.
        }
        public void visualizarHistoricoTransacao () {
            System.out.println(transacoes);
        }
    }
}