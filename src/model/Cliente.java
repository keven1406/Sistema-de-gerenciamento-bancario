package model;

import exception.*;
import controller.Autenticador;
import model.Conta;
import java.util.List;

public class Cliente {
    //Atributos
    private String nome;
    private String CPF;
    private String senha;

    //Construtor
    public Cliente (String nome, String CPF, String senha) throws SenhaValidacaoException, ErroValidarCPF, ErroValidacaoNome {
        Autenticador autenticador = new Autenticador();
        //Atribuindo aos atributos aos valores validados.
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
        return "Nome: " + nome + "\nCPF: " + CPF;
    }

}