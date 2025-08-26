package ex01;
import java.util.*;

class SomarDoisNumeros {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		//Declaracao de variaveis
		int num1, num2, soma;
		
		//Leituras
		System.out.print("Primeiro numero inteiro: ");
		num1 = sc.nextInt();
		System.out.print("Segundo numero inteiro: ");
		num2 = sc.nextInt();
		
		//Somar
		soma = num1 + num2;
		
		//Mostrar na tela
		System.out.println("Soma = " + soma);

	}
}
