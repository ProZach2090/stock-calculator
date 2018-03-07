/* LineOfBestFit.java   
 * @author Zachary Lem   Created: Jan. 12, 2016   Finalized Version: Jan. 16, 2016
 * The class that will calculate for the line of best fit and give feedback based on the calculation.
 * Implements the Calculation interface.
 */ 

public class LineOfBestFit implements Calculation
{
  // Instance variable
  private String data[];
  
  /* LineOfBestFit Method (The Constructor Method)
   * Sets up the instance variables.
   * @param "String data[]" - The array of stock prices.
   * Pre-Condition: data[] parameter has no null values.
   * Post-Condition: store information into the instance variable.
   */ 
  public LineOfBestFit(String data[]){this.data = data;}
  
  /* calculate Method
   * Performs a calculation for the slope of the line of best fit.
   * @return the slope of the line of best fit.
   * Pre-Condition: data[] parameter has no null values.
   * Post-Condition: Calculates and gives the slope of the line of best fit.
   */ 
  public double calculate()
  {
    // Local variables
    double sumX = 0; 
    double sumY = 0;
    double sumXY = 0;
    double sumX2 = 0;
    // Update the local variables based on the cost prices 
    for (int i = 0; i < data.length; i++)
    {
      double currPrice = Double.parseDouble(data[i]);
      int dayNum = i + 1;
      sumX += (dayNum);
      sumY += currPrice;
      sumXY += (dayNum * currPrice);
      sumX2 += Math.pow(dayNum, 2);
    }
    // Return the slope of the line of best fit based on the local variables
    return ((data.length*sumXY) - (sumX*sumY)) / ((data.length*sumX2) - Math.pow(sumX, 2));
  }// end calculate
  
  /* getIntercept Method
   * Performs a calculation for the y-intercept of the line of best fit.
   * @return the y-intercept of the line of best fit.
   * Pre-Condition: data[] parameter has no null values.
   * Post-Condition: Calculates and gives the y-intercept of the line of best fit.
   */ 
  private double getIntercept()
  {
    // Local variables
    double sumX = 0; 
    double sumY = 0;
    // Update the local variables based on the stock price
    for (int i = 0; i < data.length; i++)
    {
      double currPrice = Double.parseDouble(data[i]);
      int dayNum = i + 1;
      sumX += (dayNum);
      sumY += currPrice;
    }
    // Find the average of the stock prices and the day number
    double averageX = sumX / data.length;
    double averageY = sumY / data.length;
    // Calculate the y-intercept and return that value
    return averageY - (calculate()*averageX);
  }// end getIntercept
  
  /* conclusion Method
   * Gives feedback based on the results from the calculate method.
   * @return A statement about the calculation.
   * No pre-conditions for this method.
   * Post-Condition: Tells about the line of best fit.
   */ 
  public String conclusion()
  {
    // This String variable is used to give feedback about the calculate()
    String statement = "The slope (" + Calculation.DOLLAR_FORMATTER.format(calculate()) + ") has an overall ";
    if (calculate() > 0)
      statement += "increase for the stock prices.";
    else if (calculate() < 0)
      statement += "decrease for the stock prices.";
    else if (calculate() == 0)
      statement = "flat rate for the stock prices.";
    // Return what the line of best fit is along with its feedback
    return "The Line of Best Fit is: y=" + Calculation.DOLLAR_FORMATTER.format(calculate()) + "x+(" + Calculation.DOLLAR_FORMATTER.format(getIntercept()) + "). " 
      + statement;
  }// end conclusion
}// end class