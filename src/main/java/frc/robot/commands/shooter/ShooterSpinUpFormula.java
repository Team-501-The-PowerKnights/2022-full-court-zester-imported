/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import org.slf4j.Logger;

import frc.robot.commands.PKCommandBase;
import frc.robot.sensors.vision.IVisionSensor;
import frc.robot.sensors.vision.VisionFactory;
import frc.robot.subsystems.drive.DriveFactory;
import frc.robot.subsystems.drive.IDriveSubsystem;
import frc.robot.subsystems.shooter.IShooterSubsystem;
import frc.robot.subsystems.shooter.ShooterFactory;
import riolog.RioLogger;

public class ShooterSpinUpFormula extends PKCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ShooterSpinUpFormula.class.getName());

    private IShooterSubsystem shooter;
    private IDriveSubsystem drive;

    private IVisionSensor vision;
    private double targetRpm;

    public ShooterSpinUpFormula() {
        logger.info("constructing {}", getName());

        vision = VisionFactory.getInstance();
        shooter = ShooterFactory.getInstance();
        drive = DriveFactory.getInstance();

        logger.info("constructed");
    }

    @Override
    public void execute() {
        super.execute();

        double ty = vision.getY();

        double linearVelocity = drive.getEncoderVelocity();

        double coefficient = linearVelocity < 0 ? 0.7 : 0.015;

        // targetRpm = (13.5 * (ty * ty)) - (111.3 * ty) + 3352.4;
        targetRpm = (1297 + (-15.2 * ty) + (1.25 * (ty * ty))) - (coefficient * linearVelocity);
        // targetRpm = SmartDashboard.getNumber("targetRpm", 0.0);

        shooter.setTargetRpm(targetRpm);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
