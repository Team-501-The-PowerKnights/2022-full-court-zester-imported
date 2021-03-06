/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors;

import frc.robot.telemetry.ITelemetryProvider;

/**
 * Add your docs here.
 */
public interface ISensor extends ITelemetryProvider {

    /**
     * Enable the sensor.
     **/
    default public void enable() {
    };

    /**
     * Disable the sensor.
     **/
    default public void disable() {
    };

    /**
     * Called to update any preferences associated with the sensor.
     **/
    default public void updatePreferences() {
    };

}
