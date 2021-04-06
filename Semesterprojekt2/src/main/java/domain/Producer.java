package domain;

public class Producer extends Person{

    private String login;

    public Producer(String name) {
        super(name);
    }

    public Producer(String name, String role) {
        super(name, role);
    }

    public Producer(String name, String role, Production production) {
        super(name, role, production);
    }


}
