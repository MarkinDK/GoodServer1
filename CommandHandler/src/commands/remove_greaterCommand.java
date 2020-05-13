package commands;

import SetOfVehicle.SetOfVehicles;
import vehicle.Vehicle;

/**
 * Класс, объект которого удаляет из коллекции все элементы, превышающие заданный
 */
public class remove_greaterCommand extends Command {

    @Override
    @Creative
    public String execute(Object o, SetOfVehicles set) {
        //StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        ///*if (stackTrace[2].getMethodName().equals("main")) {
        Vehicle v = (Vehicle) o;
        if (set.remove_greater(v.fullyInitialize()))
            return "Элементы удалены";
        return "Превышающих элементов не найдено";
    }/* else {
            Vehicle newVehicle = execute_scriptCommand.createVehicleFromFile();
            if (newVehicle != null)
                if (set.remove_greater(newVehicle.fullyInitialize()))
                    return "Элементы удалены";
                else
                    return "Превышающих элементов не найдено";
            return "Неудача";
        //}
    }*/

    @Override
    protected String isCreative() {
        return "true";
    }

    @Override
    protected String getArgumentType() {
        return "Vehicle";
    }
}
