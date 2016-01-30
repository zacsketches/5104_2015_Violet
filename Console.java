package org.usfirst.frc.team5104.robot;

import org.usfirst.frc.team5104.robot.partition.Core;

import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.image.HSLImage;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;

public class Console {
		
	private static DriverStation ds;
	public static RobotDrive drive;
	
	/////////////Talon SR with PWM//////////
	
//	private static Talon driveLeftFront;
//	private static Talon driveRightFront;
//	private static Talon driveLeftBack;
//	private static Talon driveRightBack;
	
	
	///////////Talon SRX with CAN////////////
	
	private static CANTalon driveLeftFront;
	private static CANTalon driveRightFront;
	private static CANTalon driveLeftBack;
	private static CANTalon driveRightBack;
	
	
// ^^^ Uncomment whichever block for what Talon and connector you are using
//another one to change farther down VVV
	
//	public static Talon winch;
	
//	public static DigitalInput limitSwitchBOTTOM;
	
//	public static DigitalInput limitSwitchTOP;
//drive_sf = scaling factor
//change drive_sf to decimal percent of the speed you want
	public static double drive_sf = 1.0;
	public static double twist_sf = 0.5;
	
	public static double winch_sf = 1.5;
	public static double winchPower = 0;
	
	public static boolean inverse = false;
	public static boolean roboTag = false;
	
//	public static Ultrasonic ultrasonic;
//	public static double distanceInches = 0;
	
	public static Timer autoTimer;
	public static boolean resetTimer;
	
	public static float timeSec;
	
	public static int autoState = 0;
	
	private static Joystick controller;
	
//-----------------------------------------------------------------------------------------------------------------------------------------------------\\
	
	protected static void init(){
		
		print("Robot Initialized");
		ds = DriverStation.getInstance();
		
		controller = new Joystick(0);
		
		/////////////Talon SR with PWM//////////
		
//		driveLeftFront = new Talon(0);
//		driveLeftBack = new Talon(1);
//		driveRightFront = new Talon(2);
//		driveRightBack = new Talon(3);
		
		///////////Talon SRX with CAN////////////
		
		driveLeftFront = new CANTalon(1);
		driveLeftBack = new CANTalon(2);
		driveRightFront = new CANTalon(3);
		driveRightBack = new CANTalon(4);
		
//		winch = new Talon(4);
//		
//		limitSwitchBOTTOM = new DigitalInput(3);
//		
//		limitSwitchTOP = new DigitalInput(2);
//		
		
//		ultrasonic = new Ultrasonic(0, 1);
//		ultrasonic.setAutomaticMode(true);
		
//		CameraServer server = CameraServer.getInstance();
//		server.setQuality(50);
//		server.startAutomaticCapture("cam1");
		
		autoTimer = new Timer();
		resetTimer = true;
		
		drive = new RobotDrive(driveLeftFront, driveLeftBack, driveRightFront, driveRightBack);
		Core.init();
	}//init method
	
	protected static void autoInit(){
		if (resetTimer){
			autoTimer.reset();
			autoTimer.start();
			resetTimer = false;
		}
		/*
		float curTime = (float) autoTimer.get();
		curTime = (float) Math.floor(curTime);
		if (curTime < 1){
			Autonomous.auto1();
		} else if (curTime < 2){
			
		} else if (curTime < 2){
			
		} else if (curTime < 2){
			
		}
		*/
		Autonomous.auto3((float) autoTimer.get());
		timeSec = (float) autoTimer.get();

		
		
	}//autoInit method
	
	protected static void auto(){
		autoInit();//autoIndex();
		Core.auto(); //Currently does nothing
	}//auto method
	
	protected static void tele(){
		driveStyle(false,"H");
//		enableLimitSwitches();
//		enableWinch();
		
//		distanceInches = ultrasonic.getRangeInches();
//
//		if (controller.getRawButton(1)){
//			print("UltraSonic Enabled: " + ultrasonic.isEnabled());
//			print("Range Valid: " + ultrasonic.isRangeValid());
//			print("UltraSonic: " + ultrasonic.getRangeInches() + " inches away!");
//			
//		}
	
		
		Core.tele();
		
	}//auto method
	
	public static void driveStyle(Boolean gyro, String orientation){
		//With old Joystick: controller.getZ();
		//With new Joystick: controller.getRawAxis(5);
		//Gyro: true or false
		//Orientation: "H" horizontal, "V" vertical
		
		///////////////////////DRIVE SCALING FACTOR////////////////
		if (controller.getRawButton(9)){
			drive_sf = .5;
		}
		if (controller.getRawButton(10)){
			drive_sf = 1;
		}
		//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
		//////////////REVERSE DRIVING//////////////////////////////		
		if (controller.getRawButton(12)){
			inverse = false;
			print("Inverse mode: " + inverse);
		}
		if (controller.getRawButton(11)){
			inverse = true;
			print("Inverse mode: " + inverse);
		}
		//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
		
		if(gyro == true && orientation == "V"){
			drive.mecanumDrive_Cartesian(drive_sf*controller.getX(), drive_sf*controller.getY(), twist_sf*controller.getZ(), Robot.gyro_roll);}
		else if(gyro == true && orientation == "H"){
			drive.mecanumDrive_Cartesian(drive_sf*controller.getX(), drive_sf*controller.getY(), twist_sf*controller.getZ(), Robot.gyro_yaw);}
		else if(gyro == false && orientation == "V"){
			drive.mecanumDrive_Cartesian(drive_sf*controller.getX(), drive_sf*controller.getY(), twist_sf*controller.getZ(), 0);}
		else if(gyro == false && orientation == "H"){
		
		if (drive == null){
			print("DRIVE EQUALS NULL!!!!");
		}
			
		if (inverse){drive.mecanumDrive_Cartesian(/*drive_sf **/ controller.getX() * -1, drive_sf * controller.getY() * -1, twist_sf * controller.getZ(), 0);}
		else {drive.mecanumDrive_Cartesian(/*drive_sf * */controller.getX(), drive_sf * controller.getY(), twist_sf*controller.getZ(), 0);}
		
		//Button-Style Mecanum Strafing (Left and Right)
//		print(""+controller.getPOV());
//		if (controller.getRawButton(3)){
//			drive.mecanumDrive_Cartesian(drive_sf * -1 /*Left*/,0,0,0);
//		}
//		if (controller.getRawButton(4)){
//			drive.mecanumDrive_Cartesian(drive_sf * 1 /*Right*/,0,0,0);
//		} 
		}
		
		
			
	
	}
	
	public static void enableWinch(){
		// Winch power signaled by buttons on old joystick rather than z - axis on new controller
		//When attaching limit switch to PWM cable... NC2 goes to red - NO3 goes to white - COM goes to black/ground
			
		
//		if (controller.getRawButton(7)){
//			winch.set(-.25 * winch_sf);}
//		else if (controller.getRawButton(8)){
//			winch.set(.45 * winch_sf);}
//		else {winch.set(0);}
//		if (controller.getThrottle() <= 0 || controller.getRawButton(8)){   //figure out which way you want it to go 
//			winch_sf = 1.5;}
	
//		if (controller.getRawButton(2)){
//			
//			winch.set(winch_sf * controller.getThrottle() * -1);
////			if (winchThrottle < .25 || winchThrottle > -.25){
////				winchThrottle = 0;
////			}
//			
//		}
//			winch.set(winch_sf * winchThrottle);
		
		
//		12 = full down ; 10 = full up ; 9 = half down ; 11 = half up	
				
//		if (controller.getRawButton(10)){ // full up
//			winchPower = -.75;
//			winch.set(winchPower * winch_sf);}
//		
//		if (controller.getRawButton(9)){ // half up
//			winchPower = -.5;
//			winch.set(winchPower * winch_sf);}
//		
//		if (controller.getRawButton(12)){ // full down
//			winchPower = .75;
//			winch.set(winchPower * winch_sf);}
//		
//		if (controller.getRawButton(11)){ // half down
//			winchPower = .5;
//			winch.set(winchPower * winch_sf);}		
				
			}
			
	public static void enableWinchFC(){
//		double winchThrottle = controller.getThrottle() * -1;
//		
		
			
//		}
//		
//		if (controller.getRawButton(6)){
//			print("Winch throttle: " + controller.getThrottle() + "\n"
//					+ "Winch throttle (*-1): " + (controller.getThrottle() * -1));
//		}
//		
		
		//winch.set(winch_sf * controller.getZ());  //Fancy new controller
//				
//		if (controller.getRawButton(2)){
//			double winchSpeed;
//			if (controller.getThrottle() < -.3){
//				winchSpeed = -.5;
//			}
//			
//			if (controller.getThrottle() > .3){
//			winchSpeed = .8;
//			} else {winchSpeed = 0.0;};
//					winch.set(winch_sf * winchSpeed);
//					winch.set(winch_sf * controller.getThrottle());
//			} else {winch.set(winch_sf * winchPower); // (BUTTONS)}
//				
//			}
//				double winchSpeed = winch_sf *controller.getThrottle();
//				if (winchSpeed > 1){winchSpeed = 1;}
//				if (winchSpeed < -1){winchSpeed = -1;}
//				
//				winch.set(winch_sf*controller.getThrottle()); // (SLIDE)	}
			
	}
	
	public static void enableLimitSwitches(){
		


//		if (limitSwitchBOTTOM.get()){
//			print("Lowest height reached!!!");
//			winch_sf= 0;
//		}
//		if (controller.getThrottle() <= .25){   //figure out which way you want it to go 
//			winch_sf = 1;
//		}
//		
//		
//		if (limitSwitchTOP.get()){
//			print("Max height reached!!!");
//			winch_sf= 0;
//			}
//		if (controller.getThrottle() >= .75){ 
//			winch_sf = 1;
//		}
			
		


	
		
			
		}
					
		
	public static void print (String text){
		DriverStation.reportError("[INFO]: " + text + "\n", false);
	}
	public static void enableRoboTag(){
		if(controller.getRawButton(5)){
			roboTag = true;
		}
		if(controller.getRawButton(6)){
			roboTag = false;
		}
		
//		if(roboTag){
//			if(distanceInches < 40){
//				drive.mecanumDrive_Cartesian(0, drive_sf * .5, 0, 0);
//			}
//			if(distanceInches > 40){
//				drive.mecanumDrive_Cartesian(0, drive_sf * -.5, 0, 0);
//			}
//		}
	}
}
//Limit Switch Code:

