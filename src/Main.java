import SetProcessor.SetProcessor;
import connector.Connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean isProperPort = false;
        int intPort;
        while (!isProperPort) {
            try {
                System.out.println("Введите номер прослушиваемого порта");
                String port = reader.readLine();
                intPort = Integer.parseInt(port);
                isProperPort = true;
                System.out.println("Прослушиваемый порт: " + intPort);
                SetProcessor set = SetProcessor.instance();
                System.out.println("Ожидает подключения");
                Connector connector = new Connector(intPort, set);
                //connector.startConnection(set);
                while (true) {
                    System.out.println("Введите save для сохранения списка\n" +
                            "или exit для выхода");
                    String line = reader.readLine();
                    if (line.equals("save")) {
                        set.save();
                        System.out.println("Сохранено");
                    }
                    if (line.equals("exit")) {
                        set.save();
                        connector.disconnect();
                        System.exit(0);
                        break;
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Неправильный формат");
            } catch (IllegalArgumentException e) {
                System.out.println("Несуществующий порт");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
