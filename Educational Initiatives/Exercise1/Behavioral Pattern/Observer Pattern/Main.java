interface Observer {
    void update(float temperature);
}

class WeatherStation {
    private Observer observer;
    private float temperature;

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObserver();
    }

    private void notifyObserver() {
        if (observer != null) {
            observer.update(temperature);
        }
    }
}

class Display implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("Temperature updated: " + temperature);
    }
}

public class Main {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();
        Display display = new Display();

        station.setObserver(display);
        station.setTemperature(25);
    }
}
