/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.slf4j.Logger;

import frc.robot.OI;
import frc.robot.commands.CommandingNames;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

public class ShooterSubsystem extends BaseShooterSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ShooterSubsystem.class.getName());

    //
    private static final int slotID = 1;

    // Left motor (master)
    private CANSparkMax leftMotor;
    // Right motor
    private CANSparkMax rightMotor;

    // Encoder
    private RelativeEncoder encoder;
    // PID
    private SparkMaxPIDController pid;

    // Value of the RPM to use for speed
    private double targetRpm;
    // Value to compare RPM actual to target
    private double tolerance;

    // Flag for whether active (i.e., spinning)
    private boolean isActive;

    /**
     * Creates a new ShooterSubsystem.
     */
    public ShooterSubsystem() {
        logger.info("constructing");

        leftMotor = new CANSparkMax(21, MotorType.kBrushless);
        leftMotor.restoreFactoryDefaults();
        rightMotor = new CANSparkMax(22, MotorType.kBrushless);
        rightMotor.restoreFactoryDefaults();
        // + spin out, - spin in

        // Slaved and inverted
        rightMotor.follow(leftMotor, true);

        encoder = leftMotor.getEncoder();

        pid = leftMotor.getPIDController();
        pid.setOutputRange(0.05, 1, slotID);

        updatePreferences();

        targetRpm = 2000; // TODO - Make the values
        isActive = false;

        SmartDashboard.putNumber(CommandingNames.Shooter.tolerance, 0.012);

        logger.info("constructed");
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putBoolean(TelemetryNames.Shooter.isActive, isActive);
        SmartDashboard.putNumber(TelemetryNames.Shooter.rpm, encoder.getVelocity());
        SmartDashboard.putNumber(TelemetryNames.Shooter.targetRpm, targetRpm);
        SmartDashboard.putBoolean(TelemetryNames.Shooter.atTarget, atTargetVelocity());
    }

    @Override
    public void validateCalibration() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updatePreferences() {
        loadPreferences();

        if (pid != null) {
            pid.setP(pid_P, slotID);
            pid.setI(pid_I, slotID);
            pid.setD(pid_D, slotID);
            pid.setFF(pid_F, slotID);
        }

        // TODO - Should this also (re)set targetRpm?
    }

    @Override
    public void disable() {
        logger.info("last value of RPM tolerance: {}", tolerance);
    }

    @Override
    public void stop() {
        // set() implemented by setReference() call
        leftMotor.set(0.0);

        isActive = false;
    }

    private String activePosition = "";

    @Override
    public String getActivePosition() {
        return activePosition;
    }

    @Override
    public void setTargetRpm(double rpm) {
        this.targetRpm = rpm; // Save off value for enabling

        if (isActive) {
            pid.setReference(targetRpm, CANSparkMax.ControlType.kVelocity, slotID);
        }
    }

    @Override
    public void shoot() {
        isActive = true;
        /* generated speed */
        pid.setReference(targetRpm, CANSparkMax.ControlType.kVelocity, slotID);
    }

    // FIXME - Was supposed to be for manual; no idleShooter scaling
    @Override
    public void setSpeed(int canID, double speed) {
        switch (canID) {
            case 21:
                leftMotor.set(idleShooter(speed));
                break;
            case 22:
                rightMotor.set(idleShooter(speed));
                break;
            case 29:
                // Assuming slaved
                leftMotor.set(idleShooter(speed));
                break;
            default:
                break;
        }
    }

    private double idleShooter(double speed) {
        // Have to be connected to the field to idle
        if (!OI.getInstance().isFieldConnected()) {
            return 0.0;
        }

        // Dashboard provides scale for shooter speed
        // double scale = Preferences.getDouble(Shooter.scale, 1.0);
        // speed *= scale;
        speed = Math.min(0.20, speed);
        return speed;
    }

    @Override
    public boolean atTargetVelocity() {
        // FIXME - Find a way to only get a change via GUI
        tolerance = SmartDashboard.getNumber(CommandingNames.Shooter.tolerance, 0.012);

        return (((Math.abs(targetRpm - encoder.getVelocity())) / targetRpm) <= tolerance);
    }

}
