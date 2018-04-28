import java.util.HashMap;
import java.util.Map;

public class NumberToWord {
	
	public static final String THREE_ZEROS = "000";
	public static final String FIVE_ZEROS = "00000";
	public static final String SEVEN_ZEROS = "0000000";
	public static final String NINE_ZEROS = "000000000";
	public static final String EMPTY_STRING = "";
	public static final String SPACE = " ";
	public static final String THOUSAND = "Thousand";
	public static final String LAKH = "Lakh";
	public static final String CRORE = "Crore";
	public static final String ARAB = "Arab";
	public static final String HUNDRED = "Hundred";
	
	public static final String TWENTY = "Twenty";
	public static final String THIRTY = "Thirty";
	public static final String FOURTY = "Fourty";
	public static final String FIFTY = "Fifty";
	public static final String SIXTY = "Sixty";
	public static final String SEVENTY = "Seventy";
	public static final String EIGHTY = "Eighty";
	public static final String NINTY = "Ninty";
	
	public static final String ZERO_IN_DIGIT = "0";
	public static final String ONE_IN_DIGIT = "1";
	public static final String TWO_IN_DIGIT = "2";
	public static final String THREE_IN_DIGIT = "3";
	public static final String FOUR_IN_DIGIT = "4";
	public static final String FIVE_IN_DIGIT = "5";
	public static final String SIX_IN_DIGIT = "6";
	public static final String SEVEN_IN_DIGIT = "7";
	public static final String EIGHT_IN_DIGIT = "8";
	public static final String NINE_IN_DIGIT = "9";
	
	public static final String TEN_IN_DIGIT = "10";
	public static final String ELEVEN_IN_DIGIT = "11";
	public static final String TWELVE_IN_DIGIT = "12";
	public static final String THIRTEEN_IN_DIGIT = "13";
	public static final String FOURTEEN_IN_DIGIT = "14";
	public static final String FIFTEEN_IN_DIGIT = "15";
	public static final String SIXTEEN_IN_DIGIT = "16";
	public static final String SEVENTEEN_IN_DIGIT = "17";
	public static final String EIGHTEEN_IN_DIGIT = "18";
	public static final String NINETEEN_IN_DIGIT = "19";
	
	private static Map<String, String> singleDigitMap = prepareSingleDigitMap();
	
	private NumberToWord() {}
	
	public static void main(String[] args) {
		System.out.println(getWordFromNumber(String.valueOf(99)).equals("Ninty Nine"));
		System.out.println(getWordFromNumber(String.valueOf(67)).equals("Sixty Seven"));
		System.out.println(getWordFromNumber(String.valueOf(1)).equals("One"));
		System.out.println(getWordFromNumber(String.valueOf(101)).equals("One Hundred One"));
		System.out.println(getWordFromNumber(String.valueOf(199)).equals("One Hundred Ninty Nine"));
		System.out.println(getWordFromNumber(String.valueOf(1000)).equals("One Thousand"));
		System.out.println(getWordFromNumber(String.valueOf(1999)).equals("One Thousand Nine Hundred Ninty Nine"));
		System.out.println(getWordFromNumber(String.valueOf(1200)).equals("One Thousand Two Hundred"));
		System.out.println(getWordFromNumber(String.valueOf(12000)).equals("Twelve Thousand"));
		System.out.println(getWordFromNumber(String.valueOf(12000011)).equals("One Crore Twenty Lakh Eleven"));
		System.out.println(getWordFromNumber(String.valueOf(25549)).equals("Twenty Five Thousand Five Hundred Fourty Nine"));
		System.out.println(getWordFromNumber(String.valueOf(12010211)).equals("One Crore Twenty Lakh Ten Thousand Two Hundred Eleven"));
		System.out.println(getWordFromNumber(String.valueOf(12000211)).equals("One Crore Twenty Lakh Two Hundred Eleven"));
		System.out.println(getWordFromNumber(String.valueOf(100010001)).equals("Ten Crore Ten Thousand One"));
		System.out.println(getWordFromNumber(String.valueOf(10000001)).equals("One Crore One"));
		System.out.println(getWordFromNumber(String.valueOf(120000)).equals("One Lakh Twenty Thousand"));
		System.out.println(getWordFromNumber(String.valueOf(9999999)).equals("Ninty Nine Lakh Ninty Nine Thousand Nine Hundred Ninty Nine"));
		System.out.println(getWordFromNumber(String.valueOf(10499597)).equals("One Crore Four Lakh Ninty Nine Thousand Five Hundred Ninty Seven"));
		System.out.println(getWordFromNumber(String.valueOf(144240106)).equals("Fourteen Crore Fourty Two Lakh Fourty Thousand One Hundred Six"));
		System.out.println(getWordFromNumber(String.valueOf(11144240106L)).equals("Eleven Arab Fourteen Crore Fourty Two Lakh Fourty Thousand One Hundred Six"));
	}

	public static String getWordFromNumber(String str) {
		String strOut = EMPTY_STRING;
		if(str.length() == 1) {
			strOut = getWordForOneDigit(str);
		} else if(str.length() == 2) {
			strOut = getWordForTwoDigits(str);
		} else if(str.length() == 3) {
			strOut = getWordForThreeDigits(str);
		} else if(str.length() == 4) {
			strOut = getWordForOneDigit(str.substring(0,1)) + SPACE + THOUSAND + SPACE + getWordForThreeDigits(str.substring(1));
		} else if(str.length() == 5) {
			strOut = getWordForTwoDigits(str.substring(0,2)) + SPACE + THOUSAND + SPACE + getWordForThreeDigits(str.substring(2));
		} else if(str.length() == 6) {
			strOut = getWordForOneDigit(str.substring(0,1)) + SPACE + LAKH + SPACE + getWordForFiveDigits(str.substring(1));
		} else if(str.length() == 7) {
			strOut = getWordForTwoDigits(str.substring(0,2)) + SPACE + LAKH + SPACE + getWordForFiveDigits(str.substring(2));
		} else if(str.length() == 8) {
			strOut = getWordForOneDigit(str.substring(0,1)) + SPACE + CRORE + SPACE + getWordForSevenDigits(str.substring(1));
		} else if(str.length() == 9) {
			strOut = getWordForTwoDigits(str.substring(0,2)) + SPACE + CRORE + SPACE + getWordForSevenDigits(str.substring(2));
		} else if(str.length() == 10) {
			strOut = getWordForOneDigit(str.substring(0,1)) + SPACE + ARAB + SPACE + getWordForNineDigits(str.substring(1));
		} else if(str.length() == 11) {
			strOut = getWordForTwoDigits(str.substring(0,2)) + SPACE + ARAB + SPACE + getWordForNineDigits(str.substring(2));
		}
		return strOut.trim();
	}
	
	public static String getWordForNineDigits(String strDigits) {
		String strOut = null;
		if(NINE_ZEROS.equals(strDigits)) {
			strOut = EMPTY_STRING;
		} else {
			String strTwoDigitWord = getWordForTwoDigits(strDigits.substring(0,2));
			strOut = strTwoDigitWord.equals(EMPTY_STRING) ?  getWordForSevenDigits(strDigits.substring(2)) : strTwoDigitWord + SPACE + CRORE + SPACE + getWordForSevenDigits(strDigits.substring(2));
		}
		return strOut;
	}
	
	public static String getWordForSevenDigits(String strDigits) {
		String strOut = null;
		if(SEVEN_ZEROS.equals(strDigits)) {
			strOut = EMPTY_STRING;
		} else {
			String strTwoDigitWord = getWordForTwoDigits(strDigits.substring(0,2));
			strOut = strTwoDigitWord.equals(EMPTY_STRING) ?  getWordForFiveDigits(strDigits.substring(2)) : strTwoDigitWord + SPACE + LAKH + SPACE + getWordForFiveDigits(strDigits.substring(2));
		}
		return strOut;
	}
	
	public static String getWordForFiveDigits(String strDigits) {
		String strOut = null;
		if(FIVE_ZEROS.equals(strDigits)) {
			strOut = EMPTY_STRING;
		} else {
			String strTwoDigitWord = getWordForTwoDigits(strDigits.substring(0,2));
			strOut = strTwoDigitWord.equals(EMPTY_STRING) ?  getWordForThreeDigits(strDigits.substring(2)) : strTwoDigitWord + SPACE + THOUSAND + SPACE + getWordForThreeDigits(strDigits.substring(2));	
		}
		return strOut;
	}
 	
	public static String getWordForThreeDigits(String strDigits) {
		String strOut = null;
		if(THREE_ZEROS.equals(strDigits)) {
			strOut = EMPTY_STRING;
		} else {
			String strOneDigitWord = getWordForOneDigit(strDigits.substring(0,1));
			strOut = strOneDigitWord.equals(EMPTY_STRING) ?  getWordForTwoDigits(strDigits.substring(1)) : strOneDigitWord + SPACE + HUNDRED + SPACE + getWordForTwoDigits(strDigits.substring(1));
		}
		return strOut;
	}
	
	public static String getWordForOneDigit(String strDigits) {
		return singleDigitMap.get(strDigits);
	}
	
	public static String getWordForTwoDigits(String strDigits) {
		String strOut = EMPTY_STRING;
		if(strDigits.startsWith(ZERO_IN_DIGIT)) {
			strOut = getWordForOneDigit(strDigits.substring(1));
		} else if(strDigits.startsWith(ONE_IN_DIGIT)) {
			strOut = singleDigitMap.get(strDigits);
		} else if(strDigits.startsWith(TWO_IN_DIGIT)) {
			strOut = TWENTY + SPACE + getWordForOneDigit(strDigits.substring(1));
		} else if(strDigits.startsWith(THREE_IN_DIGIT)) {
			strOut = THIRTY + SPACE + getWordForOneDigit(strDigits.substring(1));
		} else if(strDigits.startsWith(FOUR_IN_DIGIT)) {
			strOut = FOURTY + SPACE + getWordForOneDigit(strDigits.substring(1));
		} else if(strDigits.startsWith(FIVE_IN_DIGIT)) {
			strOut = FIFTY + SPACE + getWordForOneDigit(strDigits.substring(1));
		} else if(strDigits.startsWith(SIX_IN_DIGIT)) {
			strOut = SIXTY + SPACE + getWordForOneDigit(strDigits.substring(1));
		} else if(strDigits.startsWith(SEVEN_IN_DIGIT)) {
			strOut = SEVENTY + SPACE + getWordForOneDigit(strDigits.substring(1));
		} else if(strDigits.startsWith(EIGHT_IN_DIGIT)) {
			strOut = EIGHTY + SPACE + getWordForOneDigit(strDigits.substring(1));
		} else if(strDigits.startsWith(NINE_IN_DIGIT)) {
			strOut = NINTY + SPACE + getWordForOneDigit(strDigits.substring(1));
		}
		return strOut.trim();
	}
	
	private static Map<String, String> prepareSingleDigitMap() {
		singleDigitMap = new HashMap<>();
		singleDigitMap.put(ZERO_IN_DIGIT, EMPTY_STRING);
		singleDigitMap.put(ONE_IN_DIGIT, "One");
		singleDigitMap.put(TWO_IN_DIGIT, "Two");
		singleDigitMap.put(THREE_IN_DIGIT, "Three");
		singleDigitMap.put(FOUR_IN_DIGIT, "Four");
		singleDigitMap.put(FIVE_IN_DIGIT, "Five");
		singleDigitMap.put(SIX_IN_DIGIT, "Six");
		singleDigitMap.put(SEVEN_IN_DIGIT, "Seven");
		singleDigitMap.put(EIGHT_IN_DIGIT, "Eight");
		singleDigitMap.put(NINE_IN_DIGIT, "Nine");
		singleDigitMap.put(TEN_IN_DIGIT, "Ten");
		singleDigitMap.put(ELEVEN_IN_DIGIT, "Eleven");
		singleDigitMap.put(TWELVE_IN_DIGIT, "Twelve");
		singleDigitMap.put(THIRTEEN_IN_DIGIT, "Thirteen");
		singleDigitMap.put(FOURTEEN_IN_DIGIT, "Fourteen");
		singleDigitMap.put(FIFTEEN_IN_DIGIT, "Fifteen");
		singleDigitMap.put(SIXTEEN_IN_DIGIT, "Sixteen");
		singleDigitMap.put(SEVENTEEN_IN_DIGIT, "Seventeen");
		singleDigitMap.put(EIGHTEEN_IN_DIGIT, "Eighteen");
		singleDigitMap.put(NINETEEN_IN_DIGIT, "Nineteen");
		return singleDigitMap;
	}

}