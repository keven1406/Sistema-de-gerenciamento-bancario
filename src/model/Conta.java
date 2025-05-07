package model;

import java.util.List;
import java.util.ArrayList;

public class Conta {
	    private Cliente dono;   // O dono da conta (objeto da classe Cliente)
	    private double saldo;   // O valor de dinheiro na conta
		private List<String> extrato;

	    // Construtor: quando você cria uma conta, passa o dono
	    public Conta(Cliente dono) {
	        this.dono = dono;
	        this.saldo = 0.0; // saldo começa com 0
			extrato = new ArrayList<>();
	    }

	    // Metodo para pegar (get e set) o dono da conta - set
	    public Cliente getDono() {
			return dono;
	    }
	    public void setDono(Cliente novoDono) {
			this.dono = novoDono;
	    }

	    // Metodo para pegar (get e set) o saldo da conta
	    public double getSaldo() {
			return saldo;
	    }
	    public void setSaldo(double novoSaldo) {
			this.saldo = novoSaldo;
	    }
		public List<String> getExtrato () {
			return extrato;
		}
		@Override
		public String toString() {
			return "TITULAR: " + this.dono.getNome() + "\nCPF: " + this.getDono().getCPF();
		}
	}