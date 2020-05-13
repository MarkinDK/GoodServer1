package commands;

import SetOfVehicle.SetOfVehicles;
//import com.Main;
import vehicle.Vehicle;

/**
 * Класс, объект которого осуществляет добавление нового элемента в коллекцию, если его
 * значение превышает значение наибольшего элемента в коллекции
 */
public class add_if_maxCommand extends Command {
    /**
     * Осуществляет добавление нового элемента в коллекцию, если его
     * значение превышает значение наибольшего элемента в коллекции
     *
     * @param o   Игнорируется
     * @param set Коллекция объектов типа com.vehicle.Vehicle
     * @return Сообщение об успешности выполнения команды
     */
    @Override
    @Creative
    public String execute(Object o, SetOfVehicles set) {
        Vehicle newVehicle = (Vehicle) o;
        if (set.add_if_max(newVehicle.fullyInitialize())) {
            return "Выполнено";
        }
        return "Неудача";
    }

    @Override
    protected String isCreative() {
        return "true";
    }

    @Override
    protected String getArgumentType() {
        return "Vehicle";
    }
}
