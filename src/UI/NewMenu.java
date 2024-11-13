package UI;

import java.util.*;

public class NewMenu {


    public interface Handler {
        public void execute();
    }

    public interface PreCondition {  
        public boolean validate();
    }


    private static Scanner is = new Scanner(System.in);


    private List<String> opcoes;
    private List<PreCondition> disponivel;
    private List<Handler> handlers;


    public NewMenu(String[] opcoes) {
        this.opcoes = Arrays.asList(opcoes);
        this.disponivel = new ArrayList<>();
        this.handlers = new ArrayList<>();
        this.opcoes.forEach(s-> {
            this.disponivel.add(()->true);
            this.handlers.add(()->System.out.println("\nATENTION: Not implemented!"));
        });
    }


    public void run() {
        int op;
        do {
            show();
            op = readOption();
            if (op>0 && !this.disponivel.get(op-1).validate()) {
                System.out.println("Invalid Option! Try again");
            } else if (op>0) {
                this.handlers.get(op-1).execute();
            }
        } while (op != 0);
    }

    public void setPreCondition(int i, PreCondition b) {
        this.disponivel.set(i-1,b);
    }

    public void setHandler(int i, Handler h) {
        this.handlers.set(i-1, h);
    }

    private void show() {
        System.out.println("\n *** Main Menu *** ");
        for (int i=0; i<this.opcoes.size(); i++) {
            System.out.print(i+1);
            System.out.print(" - ");
            System.out.println(this.disponivel.get(i).validate()?this.opcoes.get(i):"---");
        }
        System.out.println("0 - Exit");
    }

    private int readOption() {
        int op;
        //Scanner is = new Scanner(System.in);

        System.out.print("Option: ");
        try {
            String line = is.nextLine();
            op = Integer.parseInt(line);
        }
        catch (NumberFormatException e) {
            op = -1;
        }
        if (op < 0 || op > this.opcoes.size()) {
            System.out.println("Invalid Option!!!");
            op = -1;
        }
        return op;
    }
}
