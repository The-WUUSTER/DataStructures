package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Edmond Wu on 8/22/2016.
 */
public class LeetCode {

    /**
     * Method to find smallest number in the array
     * @param arr input array of integers
     * @return smallest value in array
     */
    public static int minArray(int[] arr) {
        int smallest = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < smallest) {
                smallest = arr[i];
            }
        }
        return smallest;
    }

    /**
     * Method to find biggest number in the array
     * @param arr input array of integers
     * @return biggest value in array
     */
    public static int maxArray(int[] arr) {
        int biggest = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > biggest) {
                biggest = arr[i];
            }
        }
        return biggest;
    }

    /**
     * Method to reverse a string
     * @param s input string
     * @return reversed string
     */
    public static String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    /**
     * Method to determine whether you can win the Nim game
     * The game starts with a number of stones on the table
     * 2 players take turns removing 1-3 stones from the table
     * Whoever removes the last stone wins
     * @param n number of stones
     * @return true if you can win, false otherwise
     */
    public static boolean canWinNim(int n) {
        return !(n % 4 == 0);
    }

    /**
     * Sums the digits of a number until there's only 1 number left
     * @param num input number
     * @return number returned by repeatedly summing up its digits
     */
    public static int repeatAddDigits(int num) {
        if(num == 0) {
            return 0;
        }
        else if(num % 9 == 0) {
            return 9;
        }
        else {
            return num % 9;
        }
    }

    /**
     * Given an Excel column title, return its corresponding column number
     * @param s spreadsheet column ('AA', 'BC')
     * @return number of that column
     */
    public static int columnTitleToNumber(String s) {
        //'A' = 65'
    	s = s.toUpperCase();
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
        	//start in reverse order of the string
            char c = s.charAt(s.length() - i - 1);
            int ascii = (int)c - 64;
            sum += (Math.pow(26, i) * ascii);
        }
        return sum;
    }

    /**
     * Generates a list of numbers by comparing 2 adjacent digits in the input and replaces the 2 with the larger of the 2 compared digits
     * then finds the smallest number in that list of numbers
     * @param n input
     * @return smallest number generated by replacing 2 adjacent digits with the larger of the 2 digits
     */
    public static int compareAdjacents(int n) {
        if (n < 10) {
            return n;
        }
        Set<Integer> numbers = new HashSet<>();
        //convert n to string form
        String stringForm = n + "";
        //compare each set of 2 adjacent numbers, pick larger one and form new string, then add to set
        for (int i = 0; i < stringForm.length() - 1; i++) {
            int first = Integer.parseInt(stringForm.charAt(i) + "");
            int second = Integer.parseInt(stringForm.charAt(i + 1) + "");
            int larger = Math.max(first, second);
            StringBuilder sub = new StringBuilder(stringForm);
            if (larger == first) {
                sub.deleteCharAt(i + 1);
            }
            else {
                sub.deleteCharAt(i);
            }
            numbers.add(Integer.parseInt(sub.toString()));
        }
        return Collections.min(numbers);
    }

    /**
     * Method to find the minimum amount of coins to add up to a certain value
     * @param coins array of types of coins
     * @param value value to be matched
     * @return minimum number of coins to add up to that value
     */
    public static int minCoins(int[] coins, int value) {
        //table stores the min number of coins for value i
        int table[] = new int[value + 1];
        table[0] = 0;
        //initialize as infinite
        for (int i = 1; i <= value; i++) {
            table[i] = Integer.MAX_VALUE;
        }

        //get minimum coins for values 1 to value
        for (int i = 1; i <= value; i++) {
            //iterate through coins smaller than i
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    int subAnswer = table[i - coins[j]];
                    if (subAnswer != Integer.MAX_VALUE && subAnswer + 1 < table[i]) {
                        table[i] = subAnswer + 1;
                    }
                }
            }
        }
        return table[value];
    }

    /**
     * RLE compression algorithm
     * @param source an uncompressed input string
     * @return source string in RLE-compressed form
     */
    public static String rle(String input) {
    	if (input.length() == 0) {
			return "";
		}
		StringBuilder compressed = new StringBuilder();
		int consecutive = 1;
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (i != input.length() - 1 && c == input.charAt(i + 1)) {
				consecutive++;
			}
			else {
				if (consecutive == 1) {
					compressed.append(c);
				}
				else {
					compressed.append(consecutive + "" + c);
				}
				consecutive = 1;
			}
		}
		return compressed.toString();
    }
    
    /**
     * Calculates the factorial of a number
     * @param x input number
     * @return x * (x - 1) * (x - 2)... * 2 * 1
     */
    public static long factorial(int x) {
    	if (x == 0) {
    		return 1;
    	}
    	long answer = (long)x;
    	for (int i = x - 1; i >= 1; i--) {
    		answer *= i;
    	}
    	return answer;
    }
    
    /**
     * Gets distinct combinations of set length of a String of distinct, lexicographically sorted characters
     * Set combinations don't have repeating letters
     * @param characters
     * @param combinationLength
     * @return
     */
    public static List<String> getCombinations(String characters, int combinationLength) {
    	List<String> comboList = new ArrayList<>();
    	buildCombinations(characters, combinationLength, comboList, 0, new StringBuilder());
    	return comboList;
    }
    
    /**
     * Helper, recursive method to getCombinations
     * @param characters
     * @param combinationLength
     * @param comboList
     * @param startIndex
     * @param sb
     */
    private static void buildCombinations(String characters, int combinationLength, 
    		List<String> comboList, int startIndex, StringBuilder sb) {
    	if (sb.length() == combinationLength) {
    		comboList.add(sb.toString());
    		return;
    	}
    	if (sb.length() > combinationLength) {
    		return;
    	}
    	for (int i = startIndex; i < characters.length(); i++) {
    		int sbCurrentLength = sb.length();
    		sb.append(characters.charAt(i));
    		buildCombinations(characters, combinationLength, comboList, i + 1, sb);
    		sb.setLength(sbCurrentLength);
    	}
    }
}
