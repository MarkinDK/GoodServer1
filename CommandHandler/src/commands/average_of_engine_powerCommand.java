package commands;
import SetOfVehicle.SetOfVehicles;

/**
 * Класс, объект которого выводит среднее значение поля enginePower для всех элементов коллекции
 */
public class average_of_engine_powerCommand extends Command {
    /**
     * Выводит уникальные значения поля type
     * @param o Игнорируется
     * @param set Коллекция объектов типа com.vehicle.Vehicle
     * @return Строковое представление уникальных элементов с полем type
     */
    @Override
    public String execute(Object o, SetOfVehicles set) {
        return "Среднее значение мощности двигателя "+
                "для аппаратов с двигателем: " +
                set.average_of_engine_power().toString();
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
