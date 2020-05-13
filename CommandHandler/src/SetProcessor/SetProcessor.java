package SetProcessor;

import SetOfVehicle.SetOfVehicles;
import commands.Command;

public class SetProcessor {
    private static SetProcessor setProcessor = null;
    private SetOfVehicles setOfVehicles = null;

    SetProcessor() {
        setOfVehicles = new SetOfVehicles();
        //setOfVehicles.initialize("D:\\II\\GoodServer\\objects.xml");
        setOfVehicles.initialize("objects.xml");
    }

    public static SetProcessor instance() {
        if (setProcessor == null)
            return new SetProcessor();
        return setProcessor;
    }

    public void save(){
        setOfVehicles.saveToXML();
    }

    public String invokeCommand(String nameOfCommand, Object argument) {
        try {
            Command c = (Command) Class
                    .forName("commands." + nameOfCommand + "Command")
                    .newInstance();
            return c.execute(argument, setOfVehicles);
        } catch (IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            //e.printStackTrace();
        }
        return "Неудача при вызове команды";
    }
}
