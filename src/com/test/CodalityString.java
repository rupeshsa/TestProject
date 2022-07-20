package com.test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CodalityString {

	public static void main(String[] args) {
		String str = "abbbcccd";

		// Store lenght of str
		int N = str.length();
		System.out.println(minCharCountDeletionsFreq(str.toCharArray(), N));
	}

	private static int minCharCountDeletionsFreq(char[] str, int N) {
		HashMap<Character, Integer> mp = new HashMap<>();
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>((x, y) -> Integer.compare(y, x));
		int cntChar = 0;

		// Add char and occurrence to map
		for (int i = 0; i < N; i++) {
			if (mp.containsKey(str[i])) {
				mp.put(str[i], mp.get(str[i]) + 1);
			} else {
				mp.put(str[i], 1);
			}
		}

		System.out.println("mp: " + mp);

		// Add occurrence to priority queue
		for (Map.Entry<Character, Integer> it : mp.entrySet()) {
			pq.add(it.getValue());
		}
		System.out.println("pq" + pq);

		// Filter values based on sequence.
		while (!pq.isEmpty()) {
			int freq = pq.peek();
			pq.remove();

			if (pq.isEmpty()) {
				return cntChar;
			}

			if (freq == pq.peek()) {

				if (freq > 1) {
					pq.add(freq - 1);
				}
				cntChar++;
			}
		}

		return cntChar;
	}

}
