//This class takes care of the graphical user interface (GUI)

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI {

    // Instance Variables
    JFrame frame = new JFrame("Weather App");
    JPanel panel = new JPanel();
    JTextField cityTextField = new JTextField();
    JTextArea weatherTextArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(weatherTextArea);
    JButton getWeatherButton = new JButton("Get Weather");
    Font font = new Font("MONOSPACED", Font.BOLD, 20);
    WeatherAPI weather;

    // Constructor
    public GUI(WeatherAPI w) {

        weather = w;
    }

    //Makes the background color pink
    public void pinkBackground(){
        cityTextField.setBackground(Color.PINK);
        weatherTextArea.setBackground(Color.PINK);
    }

    //Makes the background color green
    public void greenBackground(){
        cityTextField.setBackground(Color.GREEN);
        weatherTextArea.setBackground(Color.GREEN);
    }

    //Makes the background color cyan
    public void cyanBackground(){
        cityTextField.setBackground(Color.CYAN);
        weatherTextArea.setBackground(Color.CYAN);
    }

    // Method that creates the graphics window
    public void createWindow(){
        
        weatherTextArea.setFont(font);
        cityTextField.setFont(font);
        frame.setSize(400, 225);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new BorderLayout());
        panel.add(cityTextField, BorderLayout.NORTH);
        weatherTextArea.setEditable(false);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(getWeatherButton, BorderLayout.SOUTH);
        frame.add(panel);
        frame.setVisible(true);
    }

    // Method that makes the button clickable
    public void setUpButton() {
        getWeatherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String city = cityTextField.getText();
                if (!city.isEmpty()) {
                    try {
                        String response = weather.getAllWeatherInfo(city);
                        if (response != null) {
                            String weatherInfo = weather.parseWeatherData(response);
                            weatherTextArea.setText(weatherInfo);
                        } else {
                            weatherTextArea.setText("Could not retrieve weather data.");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    weatherTextArea.setText("Please enter a location.");
                }
            }
        });
    }

    public String getQuery() {
        return cityTextField.getText();
    }
}
