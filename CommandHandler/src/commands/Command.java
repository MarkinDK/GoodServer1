package commands;
import SetOfVehicle.SetOfVehicles;

/**
 * Класс, представляющий общий интерфейс для всех объектов,
 * осуществляющих вызов команд
 */
abstract public class Command {
    /**
     * Осуществляет действие в зависимости от типа объекта
     * @param o Строка или объект типа com.vehicle.Vehicle
     * @param set Коллекция объектов типа com.vehicle.Vehicle
     * @return Результат выполнения команды
     */
    abstract public String execute(Object o, SetOfVehicles set);

    protected abstract String isCreative();

    protected abstract String getArgumentType();
}
