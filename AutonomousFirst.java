package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

@Autonomous(name = "Autonomous")
public class AutonomousFirst extends LinearOpMode {

    ColorSensor sensorColor;
    Robot robot;
    boolean merge = true;

    @Override
    public void runOpMode() throws InterruptedException {
        sensorColor = hardwareMap.get(ColorSensor.class, "culoare");
        robot = new Robot(hardwareMap, telemetry);

        waitForStart();

        float hsvValues[] = {0F, 0F, 0F};
        final float values[] = hsvValues;
        final double SCALE_FACTOR = 255;

        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        while(sensorColor.red() < 80 && sensorColor.blue() < 80 && sensorColor.green() < 80) {

            Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                    (int) (sensorColor.green() * SCALE_FACTOR),

                    (int) (sensorColor.blue() * SCALE_FACTOR),
                    hsvValues);

            robot.setMotors(1, 1, 1, 1, false);
            telemetry.addLine()
                    .addData("Red", sensorColor.red())
                    .addData("Green", sensorColor.green())
                    .addData("Blue", sensorColor.blue());
            telemetry.update();

            relativeLayout.post(new Runnable() {
                public void run() {
                    relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
                }
            });

        }

        robot.setMotors(0,0,0,0, false);

    }
}
