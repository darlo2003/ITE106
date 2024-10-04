public class Lamp {
	
    public boolean isOn;
    public String name;
  // method to turn on the light
	void turnOn() {
		isOn = true;
		System.out.println("Light on? " + isOn);

  }

  // method to turnoff the light
	void turnOff() {
		isOn = false;
		System.out.println("Light on? " + isOn);
  }
}
