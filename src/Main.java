import controller.Autenticador;
import controller.BancoDeDados;
import exception.ErroAutenticacaoLogin;
import exception.ErroValidacaoNome;
import exception.ErroValidarCPF;
import exception.SenhaValidacaoException;
import model.Cliente;
import model.Conta;
import controller.BancoDeDados;
import view.View;
import controller.Autenticador;

public class Main {
    public static void main (String [] args) {
        BancoDeDados db = new BancoDeDados();
        try {
            db.addCliente("11111111111", new Cliente("Ana Maria", "111.111.111-11", "123456"));
            db.addCliente("11111111112", new Cliente("Leoncio Tavares", "111.111.111-12", "123456"));
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
                    Cliente cliente = autenticador.autenticarUsuario(cpf, senha, db);

                    int opcaoAutenticada = 1;
                    //LOOP DO MENU PRINCIPAL
                    while (opcaoAutenticada != 0) {
                        opcaoAutenticada = tela.exibirMenuPrincipal();
                        //Menu principal
                        switch (opcaoAutenticada) {
                            case 1:
                                //SALDO
                                //tela.exibirSaldo(cliente.getConta().exibirSaldo); COMO CONTA AINDA NÃO ESTÁ IMPREMENTADA, FAREI UM VALOR SIMOLICO.
                                opcaoAutenticada = tela.exibirSaldo(200.00);
                                break;
                            case 2:
                                //DEPOSITAR
                                //Aqui o objeto cliente irá receber o valor retornado da opcao exibirOpcaoDeposito()
                                //cliente.conta.depositar(tela.exibirPerguntaQuantoDepositar());
                                tela.perguntarAoCliente("QUAL VALOR DESEJA DEPOSITAR?");

                                System.out.println("Deposito realizado com sucesso!");
                                break;
                            case 3:
                                //SACAR
                                break;
                            case 4:
                                //TRANSFERIR
                                break;
                            case 5:
                                //EXTRATO
                                break;
                            case 6:
                                //DADOS DA CONTA
                                opcaoAutenticada = tela.exibirDadosDaConta(cliente);
                                break;
                        }
                    }
                } else if (opcao == 2) {
                    String respostaNome = autenticador.validarNome(tela.perguntarAoCliente("INSIRA SEU NOME: "));
                    String respostaCpf = autenticador.validarCPF(tela.perguntarAoCliente("INSIRA SEU CPF: "));
                    String respostaSenha = autenticador.validarSenha(tela.perguntarAoCliente("CRIE UMA SENHA: "));

                    Cliente novoCliente = new Cliente(respostaNome, respostaCpf, respostaSenha);
                    Conta conta = new Conta(novoCliente);
                    //Adicionar ao banco de dados o a chave CPF e o cliente.
                    db.addCliente(respostaCpf, novoCliente);
                    System.out.println("Cliente Adicionado com Sucesso!");

                }
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Sistema encerrado!");
    }
}