package ru.dggz;

public class CheeringManager {
    private final CheeringPhrasesStorage cheeringPhrasesStorage;

    public CheeringManager(){
        cheeringPhrasesStorage = new CheeringPhrasesStorage();
    }

    public String getRandomPhrase(){
        return cheeringPhrasesStorage.getRandomCheeringPhrase();
    }

    public String addPhrase(String phrase){
        cheeringPhrasesStorage.addCheeringPhrase(phrase);
        return "Successfully added: " + phrase;
    }
}
