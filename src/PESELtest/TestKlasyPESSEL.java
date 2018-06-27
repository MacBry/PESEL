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
		System.out.println("Miesi¹c urodzenia to: " + pesel.getMiesiacUrodzenia());
		System.out.println("Dzieñ urodzenia to: " + pesel.getDzienUrodzenia());
		System.out.println("Pe³na data urodzenia to: " + pesel.getPelnaDataUrodzenia().getTime());
		System.out.println("Czy suma kontrolna jest poprawna: " + pesel.sumaKontrolna());
		System.out.println("P³eæ wg numeru PESEL to: " + pesel.getPlec());
		System.out.println("Czy rok urodzenia jest rokiem przestêpnym: " + pesel.CzyRokPrzestepny());
		System.out.println("Czy iloœc dni z numeru PESEL mieœci siê w iloœci dni danego miesi¹ca: " + pesel.sprawdzIloscDni());
		System.out.println("Czy iloœc miesiêcy z numeru PESEL mieœci siê w iloœci 12 miesiêcy: " + pesel.sprawdzIloscMiesiecy());
		System.out.println("Aktualnie w³aœcicie numeru PESEL ma : " + pesel.getWiek() + " lata");
		System.out.println("Czy numer PESEL przeszed³ ca³¹ walidacje: " + pesel.getValidacja());
		
		
		
	}

}
