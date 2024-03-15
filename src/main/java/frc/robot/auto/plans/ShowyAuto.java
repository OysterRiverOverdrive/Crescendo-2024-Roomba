package frc.robot.auto.plans;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auto.AutoCreationCmd;
import frc.robot.auto.AutoFeederCmd;
import frc.robot.auto.AutoShooterCmd;
import frc.robot.auto.AutoSleepCmd;
import frc.robot.auto.IntakeAutoCmd;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import java.util.List;

public class ShowyAuto extends ParallelCommandGroup {

  public ShowyAuto(
      DrivetrainSubsystem drivetrain,
      IntakeSubsystem intake,
      FeederSubsystem feeder,
      ShooterSubsystem shooter) {
    AutoCreationCmd autodrive = new AutoCreationCmd();

    // Auto Driving Commands

    Command showyDrive1 =
        autodrive.AutoDriveCmd(
            drivetrain, List.of(new Translation2d(.22, 0)), new Pose2d(.5, 0, new Rotation2d(0)));
    Command showyDrive2 =
        autodrive.AutoDriveCmd(
            drivetrain, List.of(new Translation2d(.85, 0)), new Pose2d(1.30, 0, new Rotation2d(0)));
    Command showyDrive3 =
        autodrive.AutoDriveCmd(
            drivetrain,
            List.of(new Translation2d(-.85, .2)),
            new Pose2d(-1.25, 0, new Rotation2d(0)));
    Command showyDrive4 =
        autodrive.AutoDriveCmd(
            drivetrain,
            List.of(new Translation2d(0, 1.72 / 2)),
            new Pose2d(.7, 1.755, new Rotation2d(0)));
    Command showyDrive5 =
        autodrive.AutoDriveCmd(
            drivetrain,
            List.of(new Translation2d(-.35, -1.72 / 2)),
            new Pose2d(-.7, -1.755, new Rotation2d(0)));
    Command showyDrive6 =
        autodrive.AutoDriveCmd(
            drivetrain,
            List.of(new Translation2d(0, -1.45 / 2)),
            new Pose2d(1.05, -1.45, new Rotation2d(0)));
    Command showyDrive7 =
        autodrive.AutoDriveCmd(
            drivetrain,
            List.of(new Translation2d(-1, 1.45 / 2)),
            new Pose2d(-1.05, 1.45, new Rotation2d(0)));
    addCommands(

        // Driving groups
        new SequentialCommandGroup(
            showyDrive1,
            new AutoSleepCmd(1),
            showyDrive2,
            new AutoSleepCmd(0),
            showyDrive3,
            new AutoSleepCmd(.5),
            showyDrive4,
            new AutoSleepCmd(.3),
            showyDrive5,
            new AutoSleepCmd(.5),
            showyDrive6,
            new AutoSleepCmd(.3),
            showyDrive7,
            new AutoSleepCmd(1000)),

        // Intake group
        new SequentialCommandGroup(new AutoSleepCmd(0), new IntakeAutoCmd(intake, 20)),

        // Feeder group
        new SequentialCommandGroup(
            new AutoSleepCmd(1.4), new AutoFeederCmd(feeder, true, .5),
            new AutoSleepCmd(2.9), new AutoFeederCmd(feeder, true, .5),
            new AutoSleepCmd(5.75), new AutoFeederCmd(feeder, true, .5),
            new AutoSleepCmd(4.75), new AutoFeederCmd(feeder, true, .5)),
        // Shooter group
        new SequentialCommandGroup(
            new AutoSleepCmd(.5), new AutoShooterCmd(shooter, 1, 1, 1.5),
            new AutoSleepCmd(2.3), new AutoShooterCmd(shooter, 1, 1, 1.5),
            new AutoSleepCmd(4), new AutoShooterCmd(shooter, 1, 1, 1.5),
            new AutoSleepCmd(4), new AutoShooterCmd(shooter, 1, 1, 1.5)));
  }
}
