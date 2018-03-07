/* Mean.java   
 * @author Zachary Lem   Created: Jan. 14, 2016   Finalized Version: Jan. 16, 2016
 * The class that will calculate for the mean and give feedback based on 
 * the calculation.
 * Implements the Calculation interface.
 */ 

public class Mean implements Calculation
{
  // Instance variables
  private double sumOfPrices;
  private double totalScores;
  
  /* Mean Method (The Constructor Method)
   * Sets up the instance variables.
   * @param "double sum" - The sum of all the stock prices.
   * @param "double total" - The number of different dates that was used.
   * Pre-Condition: sum is not null, neither is total.
   * Post-Condition: store information into the instance variable.
   */ 
  public Mean(double sum, double total)
  {
    // Update the instance variables
    sumOfPrices = sum;
    totalScores = total;
  }// end constructor
  
  /* calculate Method
   * Performs a calculation for the mean.
   * @return the mean.
   * Pre-Condition: totalScores is greater than 0.
   * Post-Condition: Calculates and gives the mean.
   */ 
  public double calculate(){return sumOfPrices/totalScores;}
  
  /* conclusion Method
   * Gives feedback based on the results from the calculate method.
   * @return A statement about the calculation.
   * No pre-conditions for this method.
   * Post-Condition: Tells about the mean.
   */ 
  public String conclusion()
  {
    return "The Mean is: " + Calculation.DOLLAR_FORMATTER.format(calculate()) 
      + ". This means that the average stock price is " 
      + Calculation.DOLLAR_FORMATTER.format(calculate()) + ".";
  }// end conclusion
}// end class