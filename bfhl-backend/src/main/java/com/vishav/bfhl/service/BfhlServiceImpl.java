package com.vishav.bfhl.service;

import com.vishav.bfhl.dto.BfhlRequest;
import com.vishav.bfhl.dto.BfhlResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    private static final String USER_ID = "vishav_ghai_31072005";
    private static final String EMAIL = "vishav2244.be23@chitkara.edu.in";
    private static final String ROLL_NUMBER = "2310992244";

    @Override
    public BfhlResponse processData(BfhlRequest request) {
        List<String> data = request.getData();

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        long sum = 0;
        StringBuilder allAlphabets = new StringBuilder();

        for (String item : data) {
            if (isNumber(item)) {
                long num = Long.parseLong(item);
                sum += num;
                if (num % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } else if (isAlphabet(item)) {
                alphabets.add(item.toUpperCase());
                allAlphabets.append(item);
            } else {
                specialCharacters.add(item);
            }
        }

        String concatString = buildConcatString(allAlphabets.toString());

        BfhlResponse response = new BfhlResponse();
        response.setSuccess(true);
        response.setUserId(USER_ID);
        response.setEmail(EMAIL);
        response.setRollNumber(ROLL_NUMBER);
        response.setOddNumbers(oddNumbers);
        response.setEvenNumbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialCharacters);
        response.setSum(String.valueOf(sum));
        response.setConcatString(concatString);

        return response;
    }

    private boolean isNumber(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isAlphabet(String s) {
        return s.chars().allMatch(Character::isLetter);
    }

    /**
     * Concatenate all alphabets from input (preserving order),
     * reverse the full string, then apply alternating caps (index 0 = uppercase).
     *
     * Example A: input alphabets = "a" + "R" = "aR" → reverse = "Ra" → concat_string = "Ra"
     * Example B: input alphabets = "a"+"y"+"b" = "ayb" → reverse = "bya" → alternating = "ByA"
     * Example C: input = "A"+"ABCD"+"DOE" = "AABCDDOE" → reverse = "EODDCBAa"... 
     *            wait — let's use chars: A,A,B,C,D,D,O,E → reverse = E,O,D,D,C,B,A,A
     *            alternating caps: E(up),o(low),D(up),d(low),C(up),b(low),A(up),a(low) = "EoDdCbAa"
     */
    private String buildConcatString(String allAlphabets) {
        // Reverse the entire concatenated string
        String reversed = new StringBuilder(allAlphabets).reverse().toString();

        // Apply alternating caps: index 0 = uppercase, index 1 = lowercase, etc.
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(Character.toLowerCase(c));
            }
        }
        return result.toString();
    }
}
