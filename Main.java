import controller.Autenticador;
import controller.BancoDeDados;
import exception.*;
import model.Cliente;
import model.Conta;
import controller.BancoDeDados;
import model.Transacao;
import view.View;
import controller.Autenticador;
//PROJETO

public class Main {
    public static void main (String [] args) {
        BancoDeDados db = new BancoDeDados();
        try {
            Autenticador aut = new Autenticador();
            Cliente cliente1 = new Cliente("Ana Maria", aut.validarCPF("111.111.111-11"), "123456");
            Cliente cliente2 = new Cliente("Leoncio Tavares", aut.validarCPF("222.222.222.22"), "123456");

            db.addConta(new Conta(cliente1));
            db.addConta(new Conta (cliente2));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        int opcao = 1;
        View tela = new View();
        Autenticador autenticador = new Autenticador();

        //LOOP DO MENU DE LOGIN E LOGOFF
        while (opcao != 0) {
            try {
                //1 - ENTRAR 2 - CADASTRAR 0 - SAIR
                opcao = tela.exibirMenuLogarOuCadastrar();
                if (opcao == 1) {
                    String resposta = tela.perguntarAoCliente("QUAL SEU CPF?");
                    String cpf = autenticador.validarCPF(resposta);
                    String senha = autenticador.validarSenha(tela.perguntarAoCliente("QUAL SUA SENHA?"));
                    //Se o cliente for encontrado e a senha for autenticada, será retornado o cliente.
                    Conta conta = autenticador.autenticarUsuario(cpf, senha, db);
                    Transacao transacao = new Transacao();

                    int opcaoAutenticada = 1;
                    //LOOP DO MENU PRINCIPAL
                    while (opcaoAutenticada != 0) {
                        opcaoAutenticada = tela.exibirMenuPrincipal();
                        //Menu principal
                        switch (opcaoAutenticada) {
                            case 1:
                                //SALDO
                                opcaoAutenticada = tela.exibirSaldo(conta.getSaldo());
                                break;
                            case 2:
                                //DEPOSITAR
                                double deposito = Double.parseDouble(tela.perguntarAoCliente("QUAL VALOR DESEJA DEPOSITAR?"));
                                transacao.depositar(deposito, conta);
                                opcaoAutenticada = tela.exibirOpcaoMenuPrincipalOuSair();
                                break;
                            case 3:
                                //SACAR
                                try {
                                    double valor = Double.parseDouble(tela.perguntarAoCliente("QUAL VALOR DESEJA SACAR?"));
                                    transacao.sacar(valor, conta);
                                    opcaoAutenticada = tela.exibirOpcaoMenuPrincipalOuSair();
                                }
                                catch (Exception e) {
                                    System.out.println(e.getMessage());
                                } finally {
                                    break;
                                }

                            case 4:
                                //TRANSFERIR
                                try {
                                    String cpfDestino = autenticador.validarCPF(tela.perguntarAoCliente("DIGITE O CPF DA CONTA QUE DESEJA TRANSFERIR:"));
                                    Conta contaDestino = db.buscarConta(cpfDestino);
                                    double valorTransferido = Double.parseDouble(tela.perguntarAoCliente("DIGITE O VALOR QUE DESEJA TRANSFERIR:"));
                                    transacao.transferir(contaDestino, valorTransferido, conta);
                                    opcaoAutenticada = tela.exibirOpcaoMenuPrincipalOuSair();
                                }
                                catch (ValorIncorretoException e ) {
                                    System.out.println(e.getMessage());
                                }
                                catch (Exception e) {
                                    System.out.println(e.getMessage());
                                } finally {
                                    break;
                                }
                            case 5:
                                //EXTRATO
                                tela.inserirCabecalho();
                                for (String extratoTransacao : conta.getExtrato()) {
                                    System.out.println(extratoTransacao);
                                }
                                opcaoAutenticada = tela.exibirOpcaoMenuPrincipalOuSair();
                                break;
                            case 6:
                                //DADOS DA CONTA
                                tela.exibirDadosDaConta(conta);
                                opcaoAutenticada = tela.exibirOpcaoMenuPrincipalOuSair();
                                break;
                        }
                    }
                } else if (opcao == 2) {
                    String respostaNome = autenticador.validarNome(tela.perguntarAoCliente("INSIRA SEU NOME: "));
                    String respostaCpf = autenticador.validarCPF(tela.perguntarAoCliente("INSIRA SEU CPF: "));
                    String respostaSenha = autenticador.validarSenha(tela.perguntarAoCliente("CRIE UMA SENHA: "));

                    Cliente novoCliente = new Cliente(respostaNome, respostaCpf, respostaSenha);
                    //Criação da conta.
                    Conta conta = new Conta(novoCliente);

                    //Adicionar ao banco de dados o a chave CPF e a conta.
                    db.addConta(conta);
                    System.out.println("Cliente Adicionado com Sucesso!");

                }
            }
            catch(ValorIncorretoException e) {
                System.out.println(e.getMessage());
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Sistema encerrado!");
    }
}