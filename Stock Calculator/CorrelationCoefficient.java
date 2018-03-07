/* CorrelationCoefficient.java   
 * @author Zachary Lem   Created: Jan. 12, 2016   Finalized Version: Jan. 16, 2016
 * The class that will calculate for the correlation coefficient and give feedback based on the calculation.
 * Implements the Calculation interface.
 */ 

public class CorrelationCoefficient implements Calculation
{
  // Instance variable
  private String data[];// To store the cost prices
  
  /* Collector Method (The Constructor Method)
   * Sets up the instance variables.
   * @param "String data[]" - The array of stock prices.
   * Pre-Condition: data[] parameter has no null values.
   * Post-Condition: store information into the instance variable.
   */ 
  public CorrelationCoefficient(String data[]){this.data = data;}
  
  /* calculate Method
   * Performs a calculation for the correlation coefficient.
   * @return the correlation coefficient.
   * Pre-Condition: data[] parameter has no null values.
   * Post-Condition: Calculates and gives the correlation coefficient.
   */ 
  public double calculate()
  {
    // Local variables
    double sumX = 0; 
    double sumY = 0;
    double sumXY = 0;
    double sumX2 = 0;
    double sumY2 = 0;
    // Update the local variables based on the cost prices 
    for (int i = 0; i < data.length; i++)
    {
      double currPrice = Double.parseDouble(data[i]);
      int dayNum = i + 1;
      sumX += (dayNum);
      sumY += currPrice;
      sumXY += (dayNum * currPrice);
      sumX2 += Math.pow(dayNum, 2);
      sumY2 += Math.pow(currPrice, 2);
    }
    // Return the correlation coefficent based on the local variables
    return ((data.length*sumXY) - (sumX*sumY)) / (Math.sqrt((data.length*sumX2) - Math.pow(sumX, 2)) * Math.sqrt((data.length*sumY2) - Math.pow(sumY, 2)));
  }// end calculate
  
  /* conclusion Method
   * Gives feedback based on the results from the calculate method.
   * @return A statement about the calculation.
   * Pre-Condition: calculate() is not greater than 1 or not smaller than -1.
   * Post-Condition: Tells about the corrlation coefficient.
   */ 
  public String conclusion()
  {
    // This String variable is used to give feedback about the calculate()
    // Check how strong the correlation is
    String statement = ". This is a ";
    if (Math.abs(calculate()) > 0 && Math.abs(calculate()) < 0.34)
      statement += "weak ";
    else if (Math.abs(calculate()) > 0.33 && Math.abs(calculate()) < 0.68)
      statement += "moderate ";
    else if (Math.abs(calculate()) > 0.67 && Math.abs(calculate()) < 1)
      statement += "strong ";
    else if (Math.abs(calculate()) == 1)
      statement += "perfect ";
    // Check if the correlation is positive or negative
    if (calculate() > 0)
      statement += "positive Correlation Coefficient.";
    else if (calculate() < 0)
      statement += "negative Correlation Coefficient.";
    else if (calculate() == 0)
      statement += "There is no visible Correlation Coefficient.";
    // Return what the correlation is along with its feedback
    return "The Correlation Coefficient is: " + Calculation.DOLLAR_FORMATTER.format(calculate()) + statement;
  }// end conclusion
}// end class