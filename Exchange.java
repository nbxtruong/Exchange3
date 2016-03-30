/**
 * The class Exchange allows to convert an amount in euro.
 * 
 * Version 3.
 */

public class Exchange {

	/*
	 * This version uses the new class Currency, which improves the 
	 * modularity, of the program and then the easiness to reuse and 
	 * its readability.
	 * 
	 * Some problems of the second version remain :
	 * - using Error() instead an exception ;
	 * - no way to add a currency ;
	 * - problem on the effenciency of the method searchCurrency(String) ;
	 * 
	 * New problem : it is always possible to modify the rate of a currency,
	 * when it would be some time impossible.
	 */

	private static final String[] CURRENCIES_NAMES = { "Franc", "Mark",
			"Dollard", "Euro" };

	private static final double[] RATES = { 6.55957, 1.95583, 1.2713, 1. };

	private static final Currency[] CURRENCIES = new Currency[CURRENCIES_NAMES.length];

	/*
	 * Static block, executed at the loading of the class.
	 */
	static {
		for (int i = 0; i < CURRENCIES.length; ++i) {
			CURRENCIES[i] = new Currency(CURRENCIES_NAMES[i], RATES[i]);
		}
	}

	private static Currency searchCurrency(String name) {
		for (int i = 0; i < CURRENCIES.length; i++) {
			if (CURRENCIES[i].name().equals(name)) {
				return CURRENCIES[i];
			}
		}
		return null;
	}

	public static Currency currency(String name) {
		Currency c = searchCurrency(name);
		if (c == null) {
			throw new Error("Currency " + name + " unknown");
		}
		return c;
	}

	public static boolean processedCurrency(String name) {
		return (searchCurrency(name) != null);
	}

	/*
	 * The array CURRENCIES is duplicated to avoid the modification of its
	 * contents.
	 */
	public static Currency[] processedCurrencies() {
		return (Currency[])CURRENCIES.clone();
	}

	public static void main(String[] args) {
		double amountToConvert = Double.valueOf(args[0]).doubleValue();
		String currencyName = args[1];

		if (processedCurrency(currencyName)) {
			System.out.println(currency(currencyName).convertInEuro(
					amountToConvert)
					+ " Euros");
		} else {
			Currency[] currencies = processedCurrencies();
			System.out.print("Currency unknown (processed currencies =");
			for (int i = 0; i < currencies.length; ++i) {
				System.out.print(" " + currencies[i].name());
			}
			System.out.println(")");
		}
	}
}
