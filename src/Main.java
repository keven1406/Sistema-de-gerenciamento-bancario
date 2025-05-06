import controller.Autenticador;
import controller.BancoDeDados;
import exception.ErroAutenticacaoLogin;
import exception.ErroValidacaoNome;
import exception.ErroValidarCPF;
import exception.SenhaValidacaoException;
import model.Cliente;
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
                    opcao = tela.exibirMenuPrincipal();

                    //Menu principal
                   switch (opcao) {
                       case 1:
                            //SALDO
                            
                       case 2:
                           //DEPOSITAR
                       case 3:
                           //SACAR
                       case 4:
                           //TRANSFERIR
                       case 5:
                           //EXTRATO
                       case 6:
                           //DADOS DA CONTA
                   }

                } else if (opcao == 2) {
                    String respostaNome = autenticador.validarNome(tela.perguntarAoCliente("INSIRA SEU NOME: "));
                    String respostaCpf = autenticador.validarCPF(tela.perguntarAoCliente("INSIRA SEU CPF: "));
                    String respostaSenha = autenticador.validarSenha(tela.perguntarAoCliente("CRIE UMA SENHA: "));

                    Cliente novoCliente = new Cliente(respostaNome, respostaCpf, respostaSenha);
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