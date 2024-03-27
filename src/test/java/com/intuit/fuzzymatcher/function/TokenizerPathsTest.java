package com.intuit.fuzzymatcher.function;

import com.intuit.fuzzymatcher.domain.Element;
import org.junit.Assert;
import org.junit.Test;

import static com.intuit.fuzzymatcher.domain.ElementType.*;
import static com.intuit.fuzzymatcher.function.TokenizerPaths.*;

public class TokenizerPathsTest {
    @Test
    public void PathTokenizerSuccess() {
        String value = "/Users/juan/Documents/Proyecto/Archivo.txt";
        Element elem = new Element.Builder().setType(PATH).setValue(value).createElement();
        Assert.assertEquals(5, pathTokenizer().apply(elem).count());
    }
    
}
