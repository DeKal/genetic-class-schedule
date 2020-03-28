package com.dekal.scheduling.input;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class ReaderUtilsTest {
    @Test
    void testRead_WithWrongFileResource_silentlyHandleError() {
        ReaderUtils readerUtils = new ReaderUtils();
        List<List<String>> result = readerUtils.read("not-exited-file.csv");
        Assertions.assertEquals(0, result.size());
    }
}