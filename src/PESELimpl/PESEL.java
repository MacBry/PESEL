package PESELimpl;

import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * @author Maciej Bryja<p>
 * <p>Obiekt klasy PESEL reprezentuje pojedy�czy numer PESEL</p>
 * <p>Klasa waliduje wprowadzony numer PESEL</p>
 */
public class PESEL 
{
	/*
	 * PESEL     - Tablica kolejnych cyfr numeru PESEL
	 * validacja - Pole okre�laj�ce czy numer PESEL przeszed� poprawnie walidacje
	 * 			   wartosc false - walidacja si� niepowiod�a numer PESEL jest niepoprawny
	 * 			   wartosc true  - walidacja si� powiod�a numer PESEL jest poprawny
	 */
	private byte [] PESEL = new byte [11];
	private boolean validacja = false;
	
	/**
	 * Konstruktor Obiektu klasy PESEL 
	 * @param Pesel parametr w postacji Stringa, wczytuje znak po znaku do tablicy byte [] PESEL
	 */
	public PESEL (String Pesel)
	{
		if (Pesel.length() < 11 || Pesel.length() > 11)
		{
			this.validacja = false;
		}
		else
		{
			for (int i = 0 ; i < 11 ; i++)
			{
				this.PESEL[i] = Byte.parseByte(Pesel.substring(i , i + 1));
			}
			if (sumaKontrolna() == true && sprawdzIloscDni() == true && sprawdzIloscMiesiecy() == true)
			{
				this.validacja = true;
			}
		}	
	}
	/**
	 * Akcesor do pola klasy PESEL
	 * @return zwraca warto�� pola validacja
	 */
	public boolean getValidacja()
	{
		return validacja;
	}
	
	/**
	 * Metoda Oblicza warto�� sumy kontrolnej i sprawdza j� z cyfr� kontroln� umeru persel
	 * @return flaga czy wartosc sumy kontrolnej jest prawid�owa
	 */
	public boolean sumaKontrolna()
	{
		int suma = PESEL[0] * 1 +
				PESEL[1] * 3 +
				PESEL[2] * 7 +
				PESEL[3] * 9 +
				PESEL[4] * 1 +
				PESEL[5] * 3 +
				PESEL[6] * 7 +
				PESEL[7] * 9 +
				PESEL[8] * 1 +
				PESEL[9] * 3;
		
		suma = suma % 10;
		suma = 10 - suma;
		suma = suma % 10;
		
		if (suma != PESEL[10])
		{
			return false;
		}
		else return true;
	}
	
	/**
	 * Metoda wydobywa z numeru PESEL rok urodzenia
	 * W zale�no�ci od warto�ci PESEL[2] i PESEL[3] do wartosci dziesi�tnej i jedno�ci dodawane s� warto�ci rz�du setek i tysi�cy  
	 * @return liczba ca�kowita reprezentujaca Rok Urodzenia 
	 */
	public int getRokUrodzenia()
	{
		int rokUrodzenia = PESEL[0] * 10 + PESEL[1]; ;
		int miesiace = PESEL[2] * 10 + PESEL[3];
		
		if (miesiace > 80 && miesiace <= 92)
		{
			rokUrodzenia = rokUrodzenia + 1800;	
		}
		else if (miesiace > 0 && miesiace <= 12)
		{
			rokUrodzenia = rokUrodzenia + 1900;
		}
		else if (miesiace > 20 && miesiace <= 32)
		{
			rokUrodzenia = rokUrodzenia + 2000;
		}
		else if (miesiace > 40 && miesiace <= 52)
		{
			rokUrodzenia = rokUrodzenia + 2100;
		}
		else if (miesiace > 60 && miesiace <= 72)
		{
			rokUrodzenia = rokUrodzenia + 2200;
		}
		return rokUrodzenia;
	}
	
	/**
	 * Metoda Wydobywa z numeru PESEL miesiac urodzenia
	 * W zale�no�ci od warto�ci PESEL[2] i PESEL[3] odejmuje od obliczonych na wst�pie warto�ci miesiaca odpowiednie wartosci tak by wartosc miescila sie w 
	 * zakresie 1 do 12
	 * @return liczba calkowita reprezentujaca miesiac urodzenia
	 */
	public int getMiesiacUrodzenia ()
	{
		int miesiac = PESEL[2] * 10 + PESEL[3];
		
		if (miesiac >= 81 && miesiac <=92 )
		{
			miesiac = miesiac - 80;
		}
		if (miesiac >= 1 && miesiac <= 12)
		{
			miesiac = miesiac - 0; 
		}
		if (miesiac >= 21 && miesiac <= 32)
		{
			miesiac = miesiac - 20;
		}
		if (miesiac >= 41 && miesiac <= 52)
		{
			miesiac = miesiac - 40;
		}
		if (miesiac >= 61 && miesiac <= 52)
		{
			miesiac = miesiac - 60;
		}
		return miesiac;
	}
	
	/**
	 * Metoda wydobywa z numeru PESEL dzie� urodzenia
	 * @return Liczba ca�kowita reprezentuj�ca dzie� urodzenia 
	 */
	public int getDzienUrodzenia ()
	{
		int dzienUrodzenia = PESEL[4] * 10 + PESEL[5];
		
		return dzienUrodzenia;
	}
	
	/**
	 * Metoda na podstawie wcze�niejszych metod wydobywa pe�na date urodzenia
	 * @return Obiekt klasy GregorianCalendar reprezentujacy  date urodzenia
	 */
	public GregorianCalendar getPelnaDataUrodzenia ()
	{
		 GregorianCalendar kalendarz = new GregorianCalendar(getRokUrodzenia(), getMiesiacUrodzenia() - 1, getDzienUrodzenia());
		 
		 return kalendarz;
	}
	
	/**
	 * Metoda sprawdza p�e� w�a�ciciela numeru PESEL na podstawie przedostatniej cyfry. M�zczyzni maj� ostatnia cyfre nieparzyst�, kobiety parzyst�
	 * @return String opisuja�y p�e� w�a�ciciela numeru PESEL
	 */
	public String getPlec ()
	{
		if((PESEL[9] % 2) == 1)
		{
			return "Mezczyzna"; 
		}
		else return "Kobieta";
	}
	/**
	 * Metoda na podstawie kalendarza sprawdza czy rok urodzenia w�a�ciciela numeru PESEL by� przest�pny czy nie 
	 * @return wartosc true jesli rok by� przest�pny, false je�li nie by� przest�pny
	 */
	public boolean CzyRokPrzestepny ()
	{
		GregorianCalendar dataUrodzenia = getPelnaDataUrodzenia();
		return dataUrodzenia.isLeapYear(getRokUrodzenia());
	}
	/**
	 * Metoda sprawdza czy warto�� dnia urodzenia, wyci�gni�ta z numeru PESEL nie jest wi�ksza lub mniejsza ni� ilo�� dni dla danego miesi�ca
	 * @return true je�li warto�� dnia urodzenia miesci si� w ilo�ci dni danego miesi�ca
	 * false je�li warto�� dnia urodzenia jest mniejsza ni� 1 lub wi�ksza ni� maksymalna ilo�ci dni w miesi�cu urodzenia
	 */
	public boolean sprawdzIloscDni ()
	{
		GregorianCalendar dataUrodzenia = getPelnaDataUrodzenia();
		int dniWMiesiacu = dataUrodzenia.getActualMaximum(Calendar.DAY_OF_MONTH);
		int dniZPeselu = getDzienUrodzenia();
		
		if (dniZPeselu < 1 || dniZPeselu > dniWMiesiacu)
		{
			return false;
		}
		else return true;
	}
	/**
	 * Metoda sprawdza czy warto�� miesi�ca urodzenia odczytana z numeru PESEL miesci sie pomiezy 1 a 12
	 * @return Warto�� true je�li mie�ci sie w zakresie 1 do 12 
	 * warto�� fgale je�li wawrto�� jest inna
	 */
	public boolean sprawdzIloscMiesiecy ()
	{
		int miesiacZPeselu = getMiesiacUrodzenia();
		
		if (miesiacZPeselu < 1 || miesiacZPeselu > 12)
		{
			return false;
		}
		else return true;
	}
	
	/**
	 * Metoda Oblicza wiek w�a�ciciela numeru PESEL
	 * @return Warto�� liczbowa reprezentujaca r�nic� pomi�dzy aktualnym rokiem a rokiem urodzenia w�a�ciciela numeru PESEL
	 */
	public int getWiek()
	{
		GregorianCalendar cal = new GregorianCalendar();
		int aktualnyRok = cal.get(Calendar.YEAR);
		return aktualnyRok - getRokUrodzenia();
	}
}























