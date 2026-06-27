package com.vishav.bfhl;

import com.vishav.bfhl.dto.BfhlRequest;
import com.vishav.bfhl.dto.BfhlResponse;
import com.vishav.bfhl.service.BfhlServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BfhlServiceTest {

    private BfhlServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new BfhlServiceImpl();
    }

    private BfhlRequest makeRequest(String... items) {
        BfhlRequest req = new BfhlRequest();
        req.setData(Arrays.asList(items));
        return req;
    }

    @Test
    void testExampleA() {
        BfhlResponse res = service.processData(makeRequest("a", "1", "334", "4", "R", "$"));
        assertTrue(res.isSuccess());
        assertEquals("vishav_ghai_31072005", res.getUserId());
        assertEquals(List.of("1"), res.getOddNumbers());
        assertEquals(List.of("334", "4"), res.getEvenNumbers());
        assertEquals(List.of("A", "R"), res.getAlphabets());
        assertEquals(List.of("$"), res.getSpecialCharacters());
        assertEquals("339", res.getSum());
        assertEquals("Ra", res.getConcatString());
    }

    @Test
    void testExampleB() {
        BfhlResponse res = service.processData(makeRequest("2", "a", "y", "4", "&", "-", "*", "5", "92", "b"));
        assertTrue(res.isSuccess());
        assertEquals(List.of("5"), res.getOddNumbers());
        assertEquals(List.of("2", "4", "92"), res.getEvenNumbers());
        assertEquals(List.of("A", "Y", "B"), res.getAlphabets());
        assertEquals(List.of("&", "-", "*"), res.getSpecialCharacters());
        assertEquals("103", res.getSum());
        assertEquals("ByA", res.getConcatString());
    }

    @Test
    void testExampleC() {
        BfhlResponse res = service.processData(makeRequest("A", "ABCD", "DOE"));
        assertTrue(res.isSuccess());
        assertTrue(res.getOddNumbers().isEmpty());
        assertTrue(res.getEvenNumbers().isEmpty());
        assertEquals(List.of("A", "ABCD", "DOE"), res.getAlphabets());
        assertTrue(res.getSpecialCharacters().isEmpty());
        assertEquals("0", res.getSum());
        assertEquals("EoDdCbAa", res.getConcatString());
    }

    @Test
    void testEmptyInput() {
        BfhlResponse res = service.processData(makeRequest());
        assertTrue(res.isSuccess());
        assertTrue(res.getOddNumbers().isEmpty());
        assertTrue(res.getEvenNumbers().isEmpty());
        assertEquals("0", res.getSum());
        assertEquals("", res.getConcatString());
    }

    @Test
    void testOnlyNumbers() {
        BfhlResponse res = service.processData(makeRequest("2", "3", "10"));
        assertEquals(List.of("3"), res.getOddNumbers());
        assertEquals(List.of("2", "10"), res.getEvenNumbers());
        assertTrue(res.getAlphabets().isEmpty());
        assertEquals("15", res.getSum());
    }

    @Test
    void testOnlySpecialChars() {
        BfhlResponse res = service.processData(makeRequest("@", "#", "!"));
        assertEquals(List.of("@", "#", "!"), res.getSpecialCharacters());
        assertTrue(res.getAlphabets().isEmpty());
        assertEquals("0", res.getSum());
    }
}
