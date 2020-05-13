package instruction;

import java.io.Serializable;

public class Instruction<T> implements Serializable {
    private String nameOfCommand;
    private T argument;
    private static final long serialVersionUID = 6529685098267757697L;

    public Instruction() {}

    public Instruction(String nameOfCommand, T argument) {
        this.nameOfCommand = nameOfCommand;
        this.argument = argument;
    }

    Instruction(String nameOfCommand) {
        this.nameOfCommand = nameOfCommand;
    }

    public T getArgument() {
        return argument;
    }

    public String getNameOfCommand() {
        return nameOfCommand;
    }
}
