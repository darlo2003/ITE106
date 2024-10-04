public class Main {
    public static void main(String[] args) {
		if (args.length < 4) {
			System.out.println("Please provide at least 4 arguments.");
			return;
		}
		
        Lamp led = new Lamp();
        Lamp halogen = new Lamp();
        Lamp fluorescent = new Lamp();
        Lamp incandescent = new Lamp();
		
        // Assign names to the lamps
        led.name = args[0];
        halogen.name = args[2];
        fluorescent.name = args[0];
        incandescent.name = args[2];

        // Set lamp statuses based on arguments
        led.isOn = Boolean.parseBoolean(args[3]);
        fluorescent.isOn = Boolean.parseBoolean(args[3]);
		fluorescent.turnOn();
        System.out.println(fluorescent.name + " turned the Fluorescent lamp on.");
		led.turnOn();
		System.out.println(led.name + " turned the LED lamp on.");
        halogen.isOn = Boolean.parseBoolean(args[1]);
        incandescent.isOn = Boolean.parseBoolean(args[1]);
		incandescent.turnOff();
		System.out.println(incandescent.name + " turned the Incandescent lamp off.");
		halogen.turnOff();
		System.out.println(halogen.name + " turned the halogen off.");
    }
}
