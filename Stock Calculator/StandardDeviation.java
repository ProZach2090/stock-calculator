/* StandardDeviation.java   
 * @author Zachary Lem   Created: Jan. 12, 2016   Finalized Version: Jan. 16, 2016
 * The class that will calculate for the standard deviation and give feedback based on the calculation.
 * Implements the Calculation interface.
 */ 

public class StandardDeviation implements Calculation
{
  // Instance variables
  private double sumOfDevs;
  private int totalScores;
  
  /* StandardDeviation Method (The Constructor Method)
   * Sets up the instance variables.
   * @param "double sum" - The sum of all the stock prices.
   * @param "double total" - The number of different dates that was used.
   * Pre-Condition: sum is not null, neither is total.
   * Post-Condition: store information into the instance variable.
   */ 
  public StandardDeviation(double sum, int total)
  {
    // Update the instance variables based off the parameters
    sumOfDevs = sum;
    totalScores = total;
  }// end constructor
  
  /* calculate Method
   * Performs a calculation for standard deviation.
   * @return the standard deviation.
   * Pre-Condition: totalScores is greater than 0.
   * Post-Condition: Calculates and gives the standard deviation.
   */ 
  public double calculate(){return Math.sqrt(sumOfDevs/totalScores);}
  
  /* conclusion Method
   * Gives feedback based on the results from the calculate method.
   * @return A statement about the calculation.
   * No pre-conditions for this method.
   * Post-Condition: Tells about the standard deviation.
   */ 
  public String conclusion()
  {
    // Use the calculate method in the statement to talk about the return value from calculate()
    return "The Standard Deviation is: " + Calculation.DOLLAR_FORMATTER.format(calculate()) 
      + ". This means that the average distance between the stock prices from their deviation is about " + Calculation.DOLLAR_FORMATTER.format(calculate()) + ".";
  }// end conclusion
}// end class