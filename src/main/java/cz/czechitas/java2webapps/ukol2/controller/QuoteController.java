package cz.czechitas.java2webapps.ukol2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Controller

public class QuoteController {

    private final Random random = new Random();

    private static List<String> readAllLines(String resource) throws IOException{
        ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
        try(InputStream inputStream=classLoader.getResourceAsStream(resource);
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))){
            return reader
                    .lines()
                    .collect(Collectors.toList());
        }
    }

    @GetMapping("/")
    public ModelAndView quoteAndImg () throws IOException {

        int randomImg = random.nextInt (8);
        int randomQuote = random.nextInt (8);

        String quote;
        List <String> quotes = readAllLines("citaty.txt");

        ModelAndView result = new ModelAndView("index");

        result.addObject("img", String.format("/images/obr-%d.jpg", randomImg));
        result.addObject("quote", quote = quotes.get (randomQuote));

        return result;
    }
}