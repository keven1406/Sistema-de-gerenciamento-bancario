package model;

public class Transacao {
    // Depositar dinheiro na conta
    public void depositar(double valor, Conta conta) {
        conta.setSaldo(conta.getSaldo() + valor);
        conta.getExtrato().add("Deposito realizado no valor de R$ " + valor);
        System.out.println(conta.getDono().getNome() + " depositou R$ " + valor);
    }

    // Sacar dinheiro da conta
    public boolean sacar(double valor, Conta conta) {
        if (conta.getSaldo() >= valor) {
            conta.setSaldo(conta.getSaldo() - valor);
            conta.getExtrato().add("Saque realizado no valor de R$ " + valor);
            System.out.println(conta.getDono().getNome() + " sacou R$" + valor);
            return true;
        } else {
            System.out.println("Saldo insuficiente para saque na conta de " + conta.getDono().getNome());
            return false; }
           }

    // Transferir dinheiro da conta de origem para outra conta
    public boolean transferir(Conta destino, double valor, Conta origem) {
        if (sacar(valor, origem)) {
            destino.setSaldo(destino.getSaldo() + valor);
            origem.getExtrato().add("Transferência realizada para " + destino.getDono().getCPF() + "\nno valor de R$ " + valor);
            System.out.println("Transferência de R$" + valor + " para " + destino.getDono().getNome() + " realizada com sucesso.");
            return true;
        } else {
            System.out.println("Transferência falhou por saldo insuficiente.");
            return false;
        }
    }
}


