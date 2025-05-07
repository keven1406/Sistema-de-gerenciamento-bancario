package model;

public class Conta {
	    private Cliente dono;   // O dono da conta (objeto da classe Cliente)
	    private double saldo;   // O valor de dinheiro na conta

	    // Construtor: quando você cria uma conta, passa o dono
	    public Conta(Cliente dono) {
	        this.dono = dono;
	        this.saldo = 0.0; // saldo começa com 0
	    }

	    // Método para pegar (get e set) o dono da conta - set
	    public Cliente getDono() {
	        return dono;
	    }
	    public void setDono(Cliente novoDono) {
	    	this.dono = novoDono;
	    }

	    // Método para pegar (get e set) o saldo da conta 
	    public double getSaldo() {
	        return saldo;
	    }
	    public void setSaldo(double novoSaldo) {
	        this.saldo = novoSaldo;
	    }
	}