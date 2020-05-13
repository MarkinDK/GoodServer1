package commands;

import SetOfVehicle.SetOfVehicles;
import vehicle.VehicleType;

/**
 * Выводит уникальные значения поля type
 */
public class print_unique_typeCommand extends Command {
    /**
     * Выводит уникальные значения поля type
     * @param o имя поля type
     * @param set Коллекция объектов типа com.vehicle.Vehicle
     * @return строковое представление уникальных элементов с полем type
     */
    @Override
    public String execute(Object o, SetOfVehicles set) {
        String arg = (String) o;
        if (arg != null)
            if (VehicleType.contains(arg))
                return set.getUniqueByType(VehicleType.getByName(arg));
        return "Введите заново";
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
