package domain;

// der skal findes et bedre navn til denne klasse. Fx medvirkende (på engelsk).
// Denne klasse indeholder alle der skal have en kreditering.

public class Actor extends Person{

    public Actor(String name) {
        super(name);
    }

    public Actor(String name, String role) {
        super(name, role);
    }

    public Actor(String name, String role, Production production) {
        super(name, role, production);
    }
}
