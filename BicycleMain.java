public class BicycleMain
{
	public static void main(String[] args) {
		Bicycle sportsBicycle = new Bicycle();
		int numGears = sportsBicycle.gear;
		System.out.println("Number of Gears: " + numGears);
		sportsBicycle.braking();
	}
}
