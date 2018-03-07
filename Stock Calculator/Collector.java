/* Collector.java   
 * @author Zachary Lem   Created: Jan. 10, 2016   Finalized Version: Jan. 16, 2016
 * The class that will collect data from the user's file and output additional data.
 * This class also checks if there's any sort of errors in the file.
 */ 

// Import the necessary libraries for this class
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import java.util.NoSuchElementException;

public class Collector 
{
  // Instance variables
  private String data[][]; // To collect the information form the file in a 2D array
  private ArrayList <Calculation> analysis; // To collect information from the Calculation subclasses
  private String fileName; // To store the name of the file name
  private boolean display[]; // To store the booleans of the pressed buttons' conditions
  
  /* Collector Method (The Constructor Method)
   * Sets up the instance variables.
   * @param "String fileName" - The name of the file.
   * @param "boolean display[]" - the array of booleans that correspond to the current state of the check boxes.
   * Pre-Condition: display[] has no null values.
   * Post-Condition: store information into fileName and display[] and create a new arraylist for the Calculations.
   */ 
  public Collector(String fileName, boolean display[]) throws FileNotFoundException
  {
    // Store information into the instance variables.
    this.fileName = fileName;
    this.display = display;
    // Create a new arraylist for calculation
    analysis = new ArrayList <Calculation>();
    // Execute the collectData method once completed storing the values.
    collectData();
  }// end constructor
  
  /* collectData method
   * Collects the data from the file.
   * No pre-conditions. This method will try to catch the mistake the users' make and tell them about it.
   * Post-Condition: Fill data[][] with the information from the text file or tell them that an error has occured.
   */ 
  public void collectData() throws FileNotFoundException
  {
    // Local variables
    boolean noError = true;// To tell the program whether it should continue to generate the analysis file
    int arraySize = 0;// To specify the size of the 2D array
    String storedInfo = "";// To store the seperate dates in a String variable
    
    // Create a new scanner to read the information found from the user's text file
    Scanner input = new Scanner(new FileReader("Stocks/" + fileName + ".txt"));
    // Check how many lines there are to set the size of data[][] and store it into the storedInfo variable
    while (input.hasNextLine())
    {
      storedInfo += input.nextLine() + "\n";
      arraySize++;
    }
    // Close the scanner because it will not longer be needed afterwards
    input.close();
    
    // Check to see if there is any information in the text file based on the value of arraySize
    if (arraySize == 0)// If the text file is empty
    {
      // Have the file say that the file is empty
      data = new String[1][2];
      data[0][0] = fileName + ".txt";
      data[0][1] = "is empty.";
    }
    else// If the text file has information in it
    {
      // Create the data 2D array
      data = new String[arraySize][2];
      int currRow = 0;// Index number for the 2D array
      // Use a Stringtokenizer to seperate the different dates from the storedInfo variable
      StringTokenizer tokenizer = new StringTokenizer(storedInfo, "\n");
      // While the tokeniser has more tokens
      while (tokenizer.hasMoreTokens())
      {
        // Use another tokenizer that will seperate the date and cost price into the 2D array
        StringTokenizer innerTokenizer = new StringTokenizer(tokenizer.nextToken(), "@");
        // Store the date in the first column of the 2D array
        data[currRow][0] = innerTokenizer.nextToken();
        // Try to store the cost price into the second column of the 2D array
        try {data[currRow][1] = innerTokenizer.nextToken();}
        // If the program is unable to tokenize the second part, tell the user about the error
        catch (NoSuchElementException nsee)
        {
          JOptionPane.showMessageDialog(null, "Error: Numeric data from file can not be formatted.");
          noError = false;
          break;// stop the process because an error has been detected
        }
        // Check to see if the cost price can be converted into a double variable
        try {double check = Double.parseDouble(data[currRow][1]);}
        // If the conversion can not happen, tell the user there's something wrong with their data.
        catch (NumberFormatException nfe) 
        {
          JOptionPane.showMessageDialog(null, "Error: Numeric data from file can not be formatted.");
          noError = false;
          break;// stop the process because an error has been detected.
        }
        // Move on to the next row in the 2D array
        currRow++;
      }// end while
    }// end else
    // If there was no error found in the data, carry on with the process
    if (noError)
      createAnalysis();
  }// end collectData
  
  /* organize method
   * Uses the arraylist to store the requested calculations from the display array of booleans.
   * Pre-Condition: data[][] is not empty (full of null's)
   * Post-Condition: Fill the analysis arraylist with Calculation objects.
   */ 
  public void organize()
  {
    // If the standard deviation has been selected
    if (display[0]) 
    {
      // Create its requirements
      double totalClose = 0;
      for (int i = 0; i < data.length; i++)
        totalClose += Double.parseDouble(data[i][1]);
      double mean = totalClose / data.length;
      int deviationSum = 0;
      for (int i = 0; i < data.length; i++)
        deviationSum += Math.pow((Double.parseDouble(data[i][1]) - mean), 2);
      // Add a StandardDeviation object to the arraylist
      analysis.add(new StandardDeviation(deviationSum, data.length));
    }
    // If the correlation coefficient has been selected
    if (display[1])
    {
      // Create its requirement
      String prices[] = new String[data.length];
      for (int i = 0; i < data.length; i++)
        prices[i] = data[i][1];
      // Add a CorrelationCoeffifient object to the arraylist
      analysis.add(new CorrelationCoefficient(prices));
    }
    // If the line of best fit has been selected
    if (display[2])
    {
      // Create its requirement
      String prices[] = new String[data.length];
      for (int i = 0; i < data.length; i++)
        prices[i] = data[i][1];
      // Add a LineOfBestFit object to the arraylist
      analysis.add(new LineOfBestFit(prices));
    }
    // If the mean has been selected
    if (display[3]) 
    {
      // Create its requirement
      int closeSum = 0;
      for (int i = 0; i < data.length; i++)
        closeSum += Double.parseDouble(data[i][1]);
      // Add a Mean object to the arraylist
      analysis.add(new Mean(closeSum, data.length));
    }
    // If the median has been pressed
    if (display[4])
    {
      // Create its requirement
      String prices[] = new String[data.length];
      for (int i = 0; i < data.length; i++)
        prices[i] = data[i][1];
      // Add a Median object to the arraylist
      analysis.add(new Median(prices));
    }
    // If the mode has been selected
    if (display[5])
    {
      // Create its requirement
      String prices[] = new String[data.length];
      for (int i = 0; i < data.length; i++)
        prices[i] = data[i][1];
      // Add a Mode object to the arraylist
      analysis.add(new Mode(prices));
    }
  }// end organize
  
  /* createAnalysis method
   * |Creates the analysis text file.
   * Pre-Condition: data[][] is not empty (full of null's).
   * Post-Condition: Output an analysis text file of the caluclations that the user wants.
   */ 
  public void createAnalysis() throws FileNotFoundException
  {
    // Have a PrintWriter to create a new file with the analysis
    PrintWriter dataOutput = new PrintWriter("Stock Analysis/" + fileName + " Analysis.txt");
    // Indicate where it got the information from
    dataOutput.println("Analysis based off of data in: " + fileName + ".txt");
    dataOutput.println("");
    // If the data contains the appropriate data, insert the analysis
    if (!data[0][1].equals("is empty."))
    {
      // Collect the necessary analysis
      organize();   
      // Add the statements from each Calculation object through polymorphism
      for (Calculation curr: analysis)
        dataOutput.println(curr.conclusion());
      dataOutput.println("");
    }
    // If the display variable is not selected
    if (!display[6])
    {
      // Set up a "table" for the information
      dataOutput.println("Date\t\tClose Price");
      dataOutput.println("");
      // Output the information it got from the original text file
      for (int i = 0; i < data.length; i++)
        dataOutput.println(data[i][0] + "\t" + data[i][1]);
    }
    // Else if the display variable is selected
    else
    {
      // Use a decimal formatter to round the cost values
      DecimalFormat df = new DecimalFormat("0.00");
      // Find the mean of all the prices
      double totalClose = 0;
      for (int i = 0; i < data.length; i++)
        totalClose += Double.parseDouble(data[i][1]);
      double mean = totalClose / data.length;
      // Show a legend of how the data is organized
      dataOutput.println("Date||Day # (x)||Close Price (y)||x*y||x^2||y^2||Deviation||Deviation^2");
      dataOutput.println("");
      // Display the original data with the additional statistic variables
      for (int i = 0; i < data.length; i++)
      {
        double price = Double.parseDouble(data[i][1]);
        dataOutput.println(data[i][0] + "||" // Date
                             + (i + 1) + "||" // Day #
                             + price + "||" // Close Price
                             + df.format(((i + 1) * price)) + "||" // x*y
                             + Math.pow(i + 1, 2) + "||" // x^2
                             + df.format(Math.pow(price, 2)) + "||" // y^2
                             + df.format((price - mean)) + "||" // Deviation
                             + df.format(Math.pow(price - mean, 2)) // Deviation^2
                             );
      }// end for
    }// end else
    // Indicate that the file has been created.
    JOptionPane.showMessageDialog(null, fileName + " Analysis.txt has been created.");
    // The PrintWriter object is no longer needed after this point
    dataOutput.close();
  }// end createAnalysis
}// end class