package commands;

import SetOfVehicle.SetOfVehicles;
import vehicle.FuelType;

/**
 * Класс, объект которого выводит значения поля fuelType в порядке возрастания
 */
public class print_field_ascending_fuel_typeCommand extends Command {
    /**
     * Выводит значения поля fuelType в порядке возрастания
     * @param o Строка с названием поля
     * @param set Коллекция объектов типа com.vehicle.Vehicle
     * @return строковое представление уникальных элементов с полем fuelType
     * либо сообщение о необходимости повторного ввода
     */
    @Override
    public String execute(Object o, SetOfVehicles set) {
        String arg = (String) o;
        if (arg != null)
            if (FuelType.contains(arg))
                return set.getAscendingByFuelType(FuelType.getByName(arg));
        return "Введите заново";
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
