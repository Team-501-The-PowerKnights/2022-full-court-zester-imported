/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.config.CodeVersionInfo;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

/**
 * This class is used to provide a wrapper on the WPILib stuff, and a way to get
 * some personalization and configuration info injected into the dashboard and
 * log files.
 * 
 * It should not be controlled in Git, and be sure to add to the .gitignore
 */
public class Team501Robot extends Robot {

   /** Our classes' logger **/
   private static final Logger logger = RioLogger.getLogger(Team501Robot.class.getName());

   private static final String programmer = "Caleb";

   public Team501Robot() {
      logger.info("constructing");

      SmartDashboard.putString(TelemetryNames.Misc.programmer, programmer);
      logger.info("programmer={}", programmer);

      SmartDashboard.putString(TelemetryNames.Misc.codeVersion, CodeVersionInfo.version);
      logger.info("codeVersion={}", CodeVersionInfo.version);

      logger.info("constructed");
   }

}
