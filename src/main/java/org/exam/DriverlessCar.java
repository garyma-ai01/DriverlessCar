package org.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.ConstructorProperties;
import java.util.Random;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverlessCar {
    private String band;

    private int x;
    private int y;
    private int neo;


    public void move(int x1, int y1){
        x=x1;
        y=y1;
        System.out.println(this);
    }


    @Override
    public String toString() {
        return band + "{" +
                "x=" + x +
                ", y=" + y +
                ", Orent=" + Constants.Orientation.E.toVisit(neo).name() +
                '}';
    }

}
