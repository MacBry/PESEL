package PESELtest;

import java.util.GregorianCalendar;
import java.util.Scanner;

import PESELimpl.*;

public class TestKlasyPESSEL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Podaj numer PESEL do walidacji");
		
		Scanner sc = new Scanner(System.in);
		String peselStr = sc.nextLine();
		PESEL pesel = new PESEL(peselStr);
		
		System.out.println("Rok urodzenia to: " + pesel.getRokUrodzenia());
		System.out.println("Miesi�c urodzenia to: " + pesel.getMiesiacUrodzenia());
		System.out.println("Dzie� urodzenia to: " + pesel.getDzienUrodzenia());
		System.out.println("Pe�na data urodzenia to: " + pesel.getPelnaDataUrodzenia().getTime());
		System.out.println("Czy suma kontrolna jest poprawna: " + pesel.sumaKontrolna());
		System.out.println("P�e� wg numeru PESEL to: " + pesel.getPlec());
		System.out.println("Czy rok urodzenia jest rokiem przest�pnym: " + pesel.CzyRokPrzestepny());
		System.out.println("Czy ilo�c dni z numeru PESEL mie�ci si� w ilo�ci dni danego miesi�ca: " + pesel.sprawdzIloscDni());
		System.out.println("Czy ilo�c miesi�cy z numeru PESEL mie�ci si� w ilo�ci 12 miesi�cy: " + pesel.sprawdzIloscMiesiecy());
		System.out.println("Aktualnie w�a�cicie numeru PESEL ma : " + pesel.getWiek() + " lata");
		System.out.println("Czy numer PESEL przeszed� ca�� walidacje: " + pesel.getValidacja());
		
		
		
	}

}
