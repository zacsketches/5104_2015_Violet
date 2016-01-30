package org.usfirst.frc.team5104.robot;
 
import edu.wpi.first.wpilibj.IterativeRobot;
 
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
 
 
import com.kauailabs.nav6.frc.IMU;
import com.kauailabs.nav6.frc.IMUAdvanced;
import com.kauailabs.navx_mxp.AHRS;
 
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
         
    SerialPort serial_port;
    AHRS imu;                   // This class can only be used w/the navX MXP.
    boolean first_iteration;
    public static float gyro_yaw = 0.0f;
    public static float gyro_roll= 0.0f;
   
    public Robot() {
       
        try {
       
        serial_port = new SerialPort(57600,SerialPort.Port.kMXP);
       
                byte update_rate_hz = 50;
                imu = new AHRS(serial_port,update_rate_hz);
               
        } catch( Exception ex ) {
               
        }
       
        if ( imu != null ) {
            LiveWindow.addSensor("IMU", "Gyro", imu);
        }
        first_iteration = true;
    }
       
    public void robotInit() {
    	
        Console.init();
    }

	/**
     * This function is called periodically during autonomous
     */
    public void autonomousInit(){
    	
    	Console.autoInit();
    }
    
    public void autonomousPeriodic() {
        Console.auto();
    }
 
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	gyro_yaw = imu.getYaw();
    	//gyro_roll = imu.getRoll();
        Console.tele();
       
      
    }	
   
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {}
   
}