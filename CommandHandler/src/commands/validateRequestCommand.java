package commands;

import SetOfVehicle.SetOfVehicles;

public class validateRequestCommand extends Command{
    @Override
    public String execute(Object o, SetOfVehicles set) {
        String nameOfCommand = (String)o;
        try {
            Command c = (Command) Class
                    .forName("commands." + nameOfCommand + "Command")
                    .newInstance();
            return nameOfCommand+" "+c.getArgumentType() + " "+ c.isCreative();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            return "Нет такой команды";
        }
    }
    @Override
    protected String isCreative() {
        return "false";
    }

    @Override
    protected String getArgumentType() {
        return "String";
    }

}
