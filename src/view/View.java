package view;
import exception.OpcaoInvalidaException;
import exception.ValorIncorretoException;
import exception.ValorDepositoException;
import exception.ValorNumericoContemChar;
import model.Cliente;
import model.Conta;


import java.util.Scanner;


public class View {
    //Atributos
    Scanner sc = new Scanner(System.in);

    //Metodos

    public String desenharLinha () {
        return "\n=================================\n";
    }
    public void inserirCabecalho () {
        System.out.println(desenharLinha() + "Sistema de gerenciamento Bancário" + desenharLinha());
    }
    public void inserirRodape () {
        System.out.println(desenharLinha());
    }
    public void limparConsole () {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    //MENU QUE APRESENTA A OPÇÃO VOLTAR AO MENU PRINCIPAL OU SAIR DO PROGRAMA.
    public Integer exibirOpcaoMenuPrincipalOuSair () throws OpcaoInvalidaException {
        String resposta = perguntarAoCliente("\nO QUE DESEJA FAZER AGORA?\n1 - VOLTAR AO MENU\n0 - SAIR");
        Integer resultado = testarOpcaoEntreIntervalo(0, 1, Integer.parseInt(resposta));
        limparConsole();
        return resultado; //esse resultado será inserido no teste do loop da main.
    }


    //MENU LOGAR OU CADASTARR
    public Integer exibirMenuPrincipal () throws OpcaoInvalidaException {
        inserirCabecalho();
        String resposta = perguntarAoCliente("O QUE DESEJA FAZER?\n" +
                "1 - VER SALDO\n" +
                "2 - DEPOSITAR\n" +
                "3 - SACAR\n" +
                "4 - TRANSFERIR\n" +
                "5 - EXTRATO\n" +
                "6 - DADOS DA CONTA\n" +
                "0 - SAIR\n");
        Integer opcao = testarOpcaoEntreIntervalo(0, 6, Integer.parseInt(resposta));
        inserirRodape();
        limparConsole();
        return opcao; //O valor dessa variável irá determinar o loop na main.
    }
    public Integer exibirSaldo (double saldo) throws OpcaoInvalidaException {
        inserirCabecalho();
        System.out.println("SEU SALDO ATUAL É: R$ " + saldo);
        return exibirOpcaoMenuPrincipalOuSair();
    }
    public Integer exibirDadosDaConta (Conta conta) throws OpcaoInvalidaException {
        inserirCabecalho();
        System.out.println(conta.toString());
        return exibirOpcaoMenuPrincipalOuSair();
    }

    public Integer exibirMenuLogarOuCadastrar () throws OpcaoInvalidaException {
        inserirCabecalho();
        String resposta = perguntarAoCliente("\nO QUE DESEJA FAZER?\n1 - ENTRAR\n2 - CADASTRAR\n0 - SAIR\n");

        return testarOpcaoEntreIntervalo(0, 2, Integer.parseInt(resposta));
    }

    //Será Gerado ao cliente uma pergunta e será obtido uma resposta do tipo String.
    public String perguntarAoCliente (String pergunta) {
        System.out.println(pergunta);
        System.out.print("> ");
        return sc.nextLine();
    }

    //Verifica se o valor inserido por meio do Scanner está entre o maior ou menor numero,
    //
    public Integer testarOpcaoEntreIntervalo (int menor, int maior, int valorInserido) throws OpcaoInvalidaException{
        if (valorInserido < menor || valorInserido > maior) {
            throw new OpcaoInvalidaException("Você digitou uma opção inválida!");
        } else {
            return valorInserido;
        }
    }
}