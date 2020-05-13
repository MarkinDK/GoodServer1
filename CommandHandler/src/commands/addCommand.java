package commands;

import SetOfVehicle.SetOfVehicles;
//import com.Main;
import vehicle.Vehicle;

/**
 * Класс, объект которого осуществляет добавление элемента в коллекцию
 */
public class addCommand extends Command {

    /**
     * Осуществляет добавление нового элемента в коллекцию
     *
     * @param o   Игнорируется
     * @param set Коллекция объектов типа com.vehicle.Vehicle
     * @return Сообщение об успешности выполнения команды
     */
    @Override
    @Creative
    public String execute(Object o, SetOfVehicles set) {
        Vehicle newVehicle = (Vehicle) o;
        if (newVehicle != null) {
            newVehicle.fullyInitialize();
            set.add(newVehicle);
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
