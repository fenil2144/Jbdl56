package defaultPackage;

interface Shape{
	void draw();
}

class Circle implements Shape{
	@Override
	public void draw() {
		System.out.println("Drawing Circle");
	}
}

class Square implements Shape{
	@Override
	public void draw() {
		System.out.println("Drawing Square");
	}
}

class ShapeFactory{
	
	public Shape getShape(String shapeType) {
		if(shapeType.equalsIgnoreCase("circle"))
			return new Circle();
		else if(shapeType.equalsIgnoreCase("square"))
			return new Square();
		return null;
	}
}

public class FactoryDesignPattern {
	
	public static void main(String[] args) {
		
		ShapeFactory shapeFactory = new ShapeFactory();
		Shape shape1 = shapeFactory.getShape("circle");
		shape1.draw();
		
		shape1 = shapeFactory.getShape("square");
		shape1.draw();
		
		
	}

}
