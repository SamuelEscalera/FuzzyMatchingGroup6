package com.intuit.fuzzymatcher.Main;

import com.intuit.fuzzymatcher.component.MatchService;
import com.intuit.fuzzymatcher.domain.Document;
import com.intuit.fuzzymatcher.domain.Element;
import com.intuit.fuzzymatcher.domain.Match;
import com.intuit.fuzzymatcher.domain.Token;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.intuit.fuzzymatcher.domain.ElementType.*;

public class Main {

    public static void main(String[] args) {


        String[][] input = {
                {"1", "Samuel Herrera", "Sacaba", "69501869"},
                {"2", "Diego Figueroa", "Cercado", "70366703"},
                {"3", "Samuel Escalera", "Sacaba", "69501868"},
                //{"4", "Samuel Escalera", "Sacaba", "69501868"}
        };

        List<Document> documentList = Arrays.stream(input).map(contact -> {
            return new Document.Builder(contact[0])
                    .addElement(new Element.Builder<String>().setValue(contact[1]).setType(NAME).createElement())
                    .addElement(new Element.Builder<String>().setValue(contact[2]).setType(ADDRESS).createElement())
                    .addElement(new Element.Builder<String>().setValue(contact[3]).setType(PHONE).createElement())
                    .createDocument();
        }).collect(Collectors.toList());

        MatchService matchService = new MatchService();
        Map<String, List<Match<Document>>> result = matchService.applyMatchByDocId(documentList);

        result.entrySet().forEach(entry -> {
            entry.getValue().forEach(match -> {
                System.out.println("Data: " + match.getData() + " Matched With: " + match.getMatchedWith() + " Score: " + match.getScore().getResult());
            });
        });
    }
}