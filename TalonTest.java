package org.usfirst.frc.team5104.robot;

import org.usfirst.frc.team5104.robot.partition.Core;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class TalonTest {
	
	private static final int TALON_NUM = 3;
	
	private static DriverStation ds;
	private static Joystick controller;
	
	/////////////Talon SR with PWM//////////
//	private static Talon[] talons = new Talon[TALON_NUM];
	
	///////////Talon SRX with CAN////////////
	private static CANTalon[] talons = new CANTalon[TALON_NUM];
	
	
//-----------------------------------------------------------------------------------------------------------------------------------------------------\\
	
	protected static void init(){
		
		print("Robot Initialized");
		ds = DriverStation.getInstance();
		
		controller = new Joystick(0);
		
		/////////////Talon SR with PWM//////////
//		for (int i=0;i<TALON_NUM;i++){
//			talons[i] = new Talon(i);
//		}
		///////////Talon SRX with CAN////////////
		for (int i=0;i<TALON_NUM;i++){
			talons[i] = new CANTalon(i+1);
		}
		
		Core.init();
	}//init method
	
	protected static void autoInit(){
		
	}//autoInit method
	
	protected static void auto(){
		
	}//auto method
	
	protected static void tele(){
		//TESTING ZONE OF TALONS (0->TALON_NUM-1) OR CANTALONS (1->TALON_NUM)
		float value = (float) controller.getY();
		print("Drive Value: " + value);
		for (int i=0;i<TALON_NUM;i++){
			talons[i].set(value);
		}
	}//auto method
	
	public static void print (String text){
		//Error here, why is this not printing?
		ds.reportError("[INFO]: " + text + "\n", false);
	}//print method
}//TalonTest class