/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;

import frc.robot.subsystems.ISubsystem;

/**
 * Add your docs here.
 **/
public interface IDriveSubsystem extends ISubsystem {

    /**
     * Stop the drive from any motion it may have been running under.
     */
    public void stop();

    /**
     * Set the <i>brake</i> for the <code>DriveSubsystem</code> to the value
     * provided.
     * 
     * @param brakeOn whether brake is on or off
     */
    public void setBrake(boolean brakeOn);

    /**
     * "Swap" the drive (invert the sense of the motors)
     * 
     * @param hmiSpeed
     * @param hmiTurn
     */
    public void swap();

    /**
     * 
     * @param hmiSpeed
     * @param hmiTurn
     */
    public void drive(double hmiSpeed, double hmiTurn);

    /**
     * 
     * @param start
     * @param interiorWaypoints
     * @param end
     */
    public void followPath(final Pose2d start, final List<Translation2d> interiorWaypoints, final Pose2d end);

    public void setSpeed(int canID, double speed);

    public double getEncoderClicks();

    public double getEncoderVelocity();

}
