package com.intuit.fuzzymatcher.urlElement;


import com.intuit.fuzzymatcher.domain.MatchType;

import java.util.function.Function;

import static com.intuit.fuzzymatcher.domain.MatchType.EQUALITY;
import static com.intuit.fuzzymatcher.domain.MatchType.NEAREST_NEIGHBORS;
import static com.intuit.fuzzymatcher.function.PreProcessFunction.*;
import static com.intuit.fuzzymatcher.function.TokenizerFunction.*;

/**
 * Enum to define different types of Element.
 * This is used only to categorize the data, and apply functions at different stages of match.
 * The functions, can be overridden from Element class using the appropriate setters at the time of creation.
 */
public enum ElementTypeURL {
    NAME,
    TEXT,
    ADDRESS,
    EMAIL,
    PHONE,
    NUMBER,
    DATE,
    AGE,
    URL;

    protected Function getPreProcessFunction() {
        switch (this) {
            case NAME:
                return namePreprocessing();
            case TEXT:
                return removeSpecialChars();
            case ADDRESS:
                return addressPreprocessing();
            case EMAIL:
                return removeDomain();
            case PHONE:
                return bolPhoneNormalization();
            case NUMBER:
            case AGE:
                return numberPreprocessing();
            /*case URL:
                return urlPreprocessing();*/
            default:
                return none();
        }
    }

    protected Function getTokenizerFunction() {
        switch (this) {
            case NAME:
                return wordSoundexEncodeTokenizer();
            case TEXT:
                return wordTokenizer();
            case ADDRESS:
                return wordSoundexEncodeTokenizer();
            case EMAIL:
                return triGramTokenizer();
            case PHONE:
                return octaGramTokenizer();
            /*case URL:
                return urlTokenizer();*/
            default:
                return valueTokenizer();
        }
    }

    protected MatchType getMatchType() {
        switch (this) {
            case NUMBER:
            case DATE:
            case AGE:
                return NEAREST_NEIGHBORS;
            case URL:
                return EQUALITY; // for the moment returns the default case
            default:
                return EQUALITY;
        }
    }
}