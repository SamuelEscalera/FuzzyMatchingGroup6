package com.intuit.fuzzymatcher.function;

import com.intuit.fuzzymatcher.domain.Element;
import com.intuit.fuzzymatcher.domain.Token;
import com.intuit.fuzzymatcher.exception.MatchException;
import com.intuit.fuzzymatcher.util.Utils;
import org.apache.commons.codec.language.Soundex;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * A functional interface to Tokenize Elements
 */
public class TokenizerPaths {

    private static final Soundex soundex = new Soundex();

    public static Function<Element, Stream<Token>> valueTokenizer() {
        return (element -> Stream.of(new Token(element.getPreProcessedPath(), element)));
    }

    public static Function<Element<String>, Stream<Token<String>>> pathTokenizer() {
        return (element) -> Arrays.stream(element.getPreProcessedPath().split("/"))
                .map(token -> new Token<String>(token, element));
    }

    public static Function<Element<String>, Stream<Token<String>>> pathsSoundexEncodeTokenizer() {
        return (element) -> Arrays.stream(element.getPreProcessedPath().toString().split("/"))
                .map(val -> {
                    String code = val;
                    if (!Utils.isNumeric(val)) {

                        code = soundex.encode(val);
                        if (code.equals("")) {
                            code = val;
                        }
                    }
                    // System.out.println(val +"->" + code);
                    return code;
                }).map(token -> new Token<String>(token, element));
    }

    public static Function<Element<String>, Stream<Token<String>>> triGramTokenizer() {
        return (element) -> getNGramTokens(3, element);
    }

    public static Function<Element<String>, Stream<Token<String>>> decaGramTokenizer() {
        return (element) -> getNGramTokens(10, element);
    }


    public static Stream<Token<String>> getNGramTokens(int size, Element element) {
        Object elementValue = element.getPreProcessedPath();
        String elementValueStr;
        if (elementValue instanceof String) {
            elementValueStr = (String) elementValue;
        } else {
            throw new MatchException("Unsupported data type");
        }
        return Utils.getNGrams(elementValueStr, size).map(str -> new Token<String>(str, element));

    }
    
    public static Function<Element<String>, Stream<Token<String>>> chainTokenizers(Function<Element<String>, Stream<Token<String>>>... tokenizers) {
        return element -> Arrays.stream(tokenizers).flatMap(fun -> fun.apply(element));
    }
}
