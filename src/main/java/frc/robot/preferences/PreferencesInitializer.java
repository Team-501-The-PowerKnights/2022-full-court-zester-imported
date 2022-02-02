/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.preferences;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.Preferences;

import frc.robot.preferences.PreferenceNames.*;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class PreferencesInitializer {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(PreferencesInitializer.class.getName());

    public static void initialize() {
        logger.info("initializing");

        /*
         * Drive
         */

        if (!Preferences.containsKey(Drive.pid_P)) {
            logger.warn("{} doesn't exist; creating with default", Drive.pid_P);
            Preferences.setDouble(Drive.pid_P, 0.0);
        }

        if (!Preferences.containsKey(Drive.pid_I)) {
            logger.warn("{} doesn't exist; creating with default", Drive.pid_I);
            Preferences.setDouble(Drive.pid_I, 0.0);
        }

        if (!Preferences.containsKey(Drive.pid_D)) {
            logger.warn("{} doesn't exist; creating with default", Drive.pid_D);
            Preferences.setDouble(Drive.pid_D, 0.0);
        }

        if (!Preferences.containsKey(Drive.pid_F)) {
            logger.warn("{} doesn't exist; creating with default", Drive.pid_F);
            Preferences.setDouble(Drive.pid_F, 0.0);
        }

        /*
         * Turret
         */

        if (!Preferences.containsKey(Turret.pid_P)) {
            logger.warn("{} doesn't exist; creating with default", Turret.pid_P);
            Preferences.setDouble(Turret.pid_P, 0.0);
        }

        if (!Preferences.containsKey(Turret.pid_I)) {
            logger.warn("{} doesn't exist; creating with default", Turret.pid_I);
            Preferences.setDouble(Turret.pid_I, 0.0);
        }

        if (!Preferences.containsKey(Turret.pid_D)) {
            logger.warn("{} doesn't exist; creating with default", Turret.pid_D);
            Preferences.setDouble(Turret.pid_D, 0.0);
        }

        if (!Preferences.containsKey(Turret.pid_F)) {
            logger.warn("{} doesn't exist; creating with default", Turret.pid_F);
            Preferences.setDouble(Turret.pid_F, 0.0);
        }

        /*
         * Shooter
         */

        if (!Preferences.containsKey(Shooter.pid_P)) {
            logger.warn("{} doesn't exist; creating with default", Shooter.pid_P);
            Preferences.setDouble(Shooter.pid_P, 0.0);
        }

        if (!Preferences.containsKey(Shooter.pid_I)) {
            logger.warn("{} doesn't exist; creating with default", Shooter.pid_I);
            Preferences.setDouble(Shooter.pid_I, 0.0);
        }

        if (!Preferences.containsKey(Shooter.pid_D)) {
            logger.warn("{} doesn't exist; creating with default", Shooter.pid_D);
            Preferences.setDouble(Shooter.pid_D, 0.0);
        }

        if (!Preferences.containsKey(Shooter.pid_F)) {
            logger.warn("{} doesn't exist; creating with default", Shooter.pid_F);
            Preferences.setDouble(Shooter.pid_F, 0.0);
        }

        logger.info("preferences as initialized:\n");
        listPreferences();

        logger.info("initialized");
    }

    public static void listPreferences() {
        StringBuilder buf = new StringBuilder();
        buf.append(" preferences:");
        for (String key : Preferences.getKeys().stream().collect(Collectors.toCollection(ArrayList::new))) {
            buf.append("\n..."); // logger gobbles up leading spaces
            buf.append(key).append(" = ").append(Preferences.getDouble(key, 3171960.));
        }
        logger.info(buf.toString());
    }

}