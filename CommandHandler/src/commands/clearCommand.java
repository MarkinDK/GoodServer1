package commands;
import SetOfVehicle.SetOfVehicles;

/**
 * Класс, объект которого очищает колекцию
 */
public class clearCommand extends Command {
    /**
     * Выводит уникальные значения поля type, оба параметра игнорируются
     * @param o Игнорируется
     * @param set Коллекция объектов типа com.vehicle.Vehicle
     * @return Строка "Выполнено"
     */
    @Override
    public String execute(Object o, SetOfVehicles set) {
        set.clear();
        return "Выполнено";
    }
    @Override
    protected String isCreative() {
        return "false";
    }

    @Override
    protected String getArgumentType() {
        return "none";
    }
}
