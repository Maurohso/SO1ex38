package controller;

import java.util.concurrent.Semaphore;
import java.util.Random;
import java.math.*;

/*Você foi contratado para automatizar um treino de Fórmula 1.
As regras estabelecidas pela direção da provas são simples:
“No máximo 5 carros das 7 escuderias (14 carros no total)
presentes podem entrar na pista simultaneamente, mas apenas
um carro de cada equipe. O segundo carro deve ficar à espera,
caso um companheiro de equipe já esteja na pista. Cada piloto
deve dar 3 voltas na pista. O tempo de cada volta deverá ser
exibido e a volta mais rápida de cada piloto deve ser
armazenada para, ao final, exibir o grid de largada, ordenado
do menor tempo para o maior.”*/

public class ThreadF1 extends Thread{
	private int idCarro;
	private Semaphore controlelargada;
	private double tempoVolta = 0;
	private double tempoTreino = 0;
	
	/*CONSTRUTOR*/
	public ThreadF1(int idCarro, Semaphore controlelargada){
		this.idCarro = idCarro;
		this.controlelargada = controlelargada;
		
	}
	
	/*RUN THREADS*/
	public void run(){
		Largada();
		try {
			controlelargada.acquire();
			CarroAndando();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			controlelargada.release();
			Libera();
		}
		CarroAndando();
		
	}
	
	/*LARGADA*/
	public void Largada(){
		if(idCarro <= 14){
			System.out.println("# O carro "+idCarro+" está pronto para largada.");
		}
	}
	
	/*VOLTA*/
	public void CarroAndando(){
		int distTotal = 7000;
		int distPercorrida = 0;
		int deslocamento = 250;
		int tempo = 10;
		
		for(int i=0; i<3;i++){
			while(distPercorrida < distTotal){
				distPercorrida += deslocamento;
				try {
					sleep(tempo);
				} catch (InterruptedException e){
					e.printStackTrace();
				}
				tempoVolta = tempoVolta + (double) 1 +(Math.random() * 1);
				
				System.out.println("O carro #"+idCarro+" rodou "+distPercorrida+" metros.");
				if(distPercorrida == 7000){
					tempoVolta = Math.round(tempoVolta * 100);
					System.out.println("O carro #"+idCarro+" fez a volta "+i+" em "+(tempoVolta/10)+" segundos.");
				}
			}
			distPercorrida = 0;
			tempoTreino = tempoTreino + tempoVolta;
			tempoVolta = 0;
			
		}
		System.out.println(" ==== CARRO #"+idCarro+ " TERMINOU O TREINO EM "+ (tempoTreino/60)+ "min. ====");
	}
	
	public void Libera(){
		
	}
	

}
