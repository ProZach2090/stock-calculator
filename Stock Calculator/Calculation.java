/* Calculation.java   
 * @author Zachary Lem   Created: Jan. 8, 2016   Finalized Version: Jan. 16, 2016
 * An interface that has a calculate method and a conclusion method.
 * These methods are used to perform such calculations and give a
 * conclusion based off the answer from the calculation.
 */ 

// Import the DecimalFormat library for the DecimalFormat constant
import java.text.DecimalFormat;

public interface Calculation
{
  // Having a DecimalFormat constant to round the values to 2 decimal places
  public final DecimalFormat DOLLAR_FORMATTER = new DecimalFormat("0.00");
  
  // Abstract method that will be used to perform the calculation
  public abstract double calculate();
  
  // Abstract method that will be used to generate a conclusion from calculate()
  public abstract String conclusion();
}// end class