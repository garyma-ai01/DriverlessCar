package org.exam;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.exam.Constants.Orientation.E;

@Slf4j
public class BoostrapApp {

    private ParkMap part;
    private DriverlessCar car;
    private Random step = new Random();

    BoostrapApp(ParkMap part, DriverlessCar car) {
        this.part = part;
        this.car = car;
    }

    private void turnClockwise() {
        //step 0 - stay where ever
        //step 1 - turn one step clockwise
        int stay = (car.getNeo() + step.nextInt(2) )% 3;
        car.setNeo (stay);
    }

    private void checkAndMoveSingleStep() {
        int x1 = car.getX(), y1 = car.getY();
        switch (E.toVisit(car.getNeo())) {
            case E:
                y1 = y1 + 1;
                break;
            case S:
                x1 = x1 - 1;
                break;
            case W:
                y1 = y1 - 1;
                break;
            case N:
                x1 = x1 + 1;
        }
        // out of barrier
        if (x1 > part.getXaxis() || x1 < 0 || y1 < 0 || y1 > part.getYaxis()) {
            log.warn("Oops! Turn back silly cat");
            return;
        }
        car.move(x1,y1);
    }

    public void lockOn() throws InterruptedException {
        for (;;) {
            turnClockwise();
            checkAndMoveSingleStep();
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public static void main(String[] args) throws IOException {

        ParkMap park = new ParkMap(10, 10);
        DriverlessCar car = new DriverlessCar("Ferrira", 4, 5, 0);

        BoostrapApp broostStart = new BoostrapApp(park, car);
        Thread  thread =new Thread(() -> {
            try {
                broostStart.lockOn();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();
        System.in.read();
    }
}
