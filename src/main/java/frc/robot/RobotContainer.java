// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ClimbCommand;
import frc.robot.commands.DriveJoystickCommand;
import frc.robot.commands.DriveManualCommand;
import frc.robot.commands.PullCommand;
import frc.robot.commands.ResetClimbCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.helpers.ChasisControl;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.PullSubsystem;
import frc.robot.subsystems.ShootSubsystem;

public class RobotContainer {
  private final DriveTrainSubsystem m_driveTrainSubsystem = new DriveTrainSubsystem();
  private final ClimbSubsystem m_climbSubsystem = new ClimbSubsystem();
  private final ShootSubsystem m_shootSubsystem = new ShootSubsystem();
  private final PullSubsystem m_pullSubsystem = new PullSubsystem();

  private final Joystick m_stick = new Joystick(0);

  // private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public RobotContainer() {
    configureButtonBindings();

    m_driveTrainSubsystem.setDefaultCommand(new DriveJoystickCommand(m_driveTrainSubsystem, m_stick));
  }

  private void configureButtonBindings() {
    JoystickButton climbButton = new JoystickButton(m_stick, 4);
    JoystickButton climbSlowButton = new JoystickButton(m_stick, 6);
    JoystickButton resetClimbButton = new JoystickButton(m_stick, 12);
    JoystickButton shootButton = new JoystickButton(m_stick, 1);
    JoystickButton pullButton = new JoystickButton(m_stick, 3);
    JoystickButton pullReverseButton = new JoystickButton(m_stick, 5);
    JoystickButton preventJamming = new JoystickButton(m_stick, 10);
    JoystickButton pullFastButton = new JoystickButton(m_stick, 8);
    JoystickButton pullReverseFastButton = new JoystickButton(m_stick, 7);

    climbButton.whileHeld(new ClimbCommand(m_climbSubsystem, true));
    climbSlowButton.whileHeld(new ClimbCommand(m_climbSubsystem, false));
    resetClimbButton.whileHeld(new ResetClimbCommand(m_climbSubsystem));
    shootButton.whileHeld(new ShootCommand(m_shootSubsystem, false));
    pullButton.whileHeld(new PullCommand(m_pullSubsystem, -0.3));
    pullReverseButton.whileHeld(new PullCommand(m_pullSubsystem, 0.3));
    preventJamming.whileHeld(new ParallelCommandGroup(
      new PullCommand(m_pullSubsystem, 0.3),
      new ShootCommand(m_shootSubsystem, true)
    ));
    pullFastButton.whileHeld(new PullCommand(m_pullSubsystem, -0.7));
    pullReverseFastButton.whileHeld(new PullCommand(m_pullSubsystem, 0.7));


 }

  public Command getAutonomousCommand() {
    return new SequentialCommandGroup(
      new DriveManualCommand(m_driveTrainSubsystem, new ChasisControl(0.5, 0, 6)), 
      new WaitCommand(5), 
      new DriveManualCommand(m_driveTrainSubsystem, new ChasisControl(-0.5, 0, 6))
    );
  }
}
