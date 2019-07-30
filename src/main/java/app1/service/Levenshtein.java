package app1.service;

import org.springframework.stereotype.Service;

@Service
public class Levenshtein implements Metrica {

	public final double distance(String s1, String s2) {
		
		if (s1 == null) {
			throw new NullPointerException("s1 no debe ser null");
		}

		if (s2 == null) {
			throw new NullPointerException("s2 no debe ser null");
		}

		if (s1.equals(s2)) {
			return 0;
		}

		if (s1.length() == 0) {
			return s2.length();
		}

		if (s2.length() == 0) {
			return s1.length();
		}

		if(s1.length() < s2.length()) {
			s2 = s2.substring(0, s1.length());
		}
		
		int[] v0 = new int[s2.length() + 1];
		int[] v1 = new int[s2.length() + 1];
		int[] vtemp;

		for (int i = 0; i < v0.length; i++) {
			v0[i] = i;
		}

		for (int i = 0; i < s1.length(); i++) {
			v1[0] = i + 1;

			int minv1 = v1[0];

			for (int j = 0; j < s2.length(); j++) {
				int cost = 1;
				if (s1.charAt(i) == s2.charAt(j)) {
					cost = 0;
				}
				v1[j + 1] = Math.min(v1[j] + 1, // Cost of insertion
						Math.min(v0[j + 1] + 1, // Cost of remove
								v0[j] + cost)); // Cost of substitution

				minv1 = Math.min(minv1, v1[j + 1]);
			}

			vtemp = v0;
			v0 = v1;
			v1 = vtemp;

		}

		return v0[s2.length()];
	}
}
