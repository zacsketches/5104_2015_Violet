package org.usfirst.frc.team5104.robot;

public class Autonomous {
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////// WHEN CODING TANK DRIVE, REVERSE MOTORS ON THE RIGHT SIDE (i.e. (0.5,0.5) --> (0.5,-0.5)/////////////////////
	/////////////////// Other useful but less drastic tips//////////////////////////////////////////////////////////////////////////
	// To control the winch, use 'winch.set(s)' Where speed 's' is [-1,1]. Negative equals winch down, positive equals winch up.
	// 
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/*
	Several different ways to write time-segmented code
	
	if (THETIMER is less than TIMEVALUE){
		DO THIS
	}

	or
	
	x = low
	y = high
	after x seconds, run CODE for the next (y-x) seconds
	
	if (x < THETIMER && THETIMER < y){
		CODE
	}
	
	*/
	
	
	
	public static void auto3(float timeSec){
//		Console.enableLimitSwitches();
//		double sf = Console.winch_sf;
//	if(timeSec < 2){
//		Console.winch.set(0.3);
//	}
//		else if (timeSec < 11){
//			Console.winch.set(0);
//			Console.drive.tankDrive(0.5, -0.5);
//		}else if (timeSec == 15){
//			Console.resetTimer = true;
//		}
//			
//			if(Console.ultrasonic.getRangeInches()<=100){//192in(?)
//				Console.drive.tankDrive(-0.5, 0.5); 
//			}
//			if(Console.ultrasonic.getRangeInches()>=108){//202in(?)
//				Console.drive.tankDrive(0.5, -0.5); 
//			}
		

		
		//This may need to be verified/updated vvvvvvvvvvvv
		//At 0.5 speed, avg. distance for 1 second is 32 inches.
	}
	
	public static void auto5(){
		
	}

	public static void auto10(){
		/*//with gyro
		 * if(Robot.get_yaw <= 180){
		 * Console.drive.
		 * }
		 */
		Console.drive.tankDrive(0.75, 0.75);
		//try 0,-0.5 see which works best
		//check if positive and negative are correct we want to spin clockwise
	}

	public static void auto12(){
		
	}
	
	public static void auto15(){
		Console.drive.tankDrive(-0.5, 0.5);
	
	}

	public static void auto20(){
		
	}


}
