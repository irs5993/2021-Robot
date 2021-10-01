// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;

public class ClimbSubsystem extends MotorSubsystemBase {
  public ClimbSubsystem() {
    //FIXME: Add motor ports
    super(Constants.Ports.Climb.LEFT, Constants.Ports.Climb.RIGHT);
  }
}
