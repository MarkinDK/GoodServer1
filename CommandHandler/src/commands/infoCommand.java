package commands;
import SetOfVehicle.SetOfVehicles;

/**
 * Класс, объект которого возвращает информацию о коллекции в строковом представлении
 */
public class infoCommand extends Command {
    /**
     * Выводит информацию о коллекции в строковом представлении
     * @param o Игнорируется
     * @param set Коллекция объектов типа com.vehicle.Vehicle
     * @return Информация о коллекции в строковом представлении
     */
    @Override
    public String execute(Object o, SetOfVehicles set) {
        return set.getInfo();
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
