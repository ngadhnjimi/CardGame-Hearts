public class Card{
    private String suit;
    private String value;

    public Card(String suit, String value){
        this.suit = suit;
        this.value = value;
    }

    public String get_suit(){
        return suit;
    }

    public String get_value(){
        return value;
    }

    public boolean equals(Object c){
        Card obj = (Card)c;
        return (obj.get_value().equalsIgnoreCase(value) && obj.get_suit().equalsIgnoreCase(suit));
    }

    public String toString(){
        return value + " of " + suit;
    }
}
