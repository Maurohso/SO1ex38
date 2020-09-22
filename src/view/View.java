package view;

import java.util.concurrent.Semaphore;

import controller.ThreadF1;

public class View {

	public static void main(String[] args) {
		int permissoes = 5;
		Semaphore controlelargada = new Semaphore(permissoes);
		
		for(int idCarro = 1; idCarro <=14; idCarro++){
			
			Thread tF1 = new ThreadF1(idCarro, controlelargada);
			tF1.start();
			
		}
		
	}

}
