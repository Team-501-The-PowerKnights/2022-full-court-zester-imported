/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import org.slf4j.Logger;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class DriveSwap extends DriveCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(DriveSwap.class.getName());

    // FIXME - Is dependency right?
    // FIXME - Should pause a couple of cycles of execute to give time to coast out

    /**
     * Creates a new DriveJoystickControl.
     */
    public DriveSwap() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    @Override
    public void initialize() {
        super.initialize();

        drive.swap();
    }

}
