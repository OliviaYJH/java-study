package structural.decorator;

public class Client {

	public static void main(String[] args) {
		System.out.println(new ConcreteComponent("Hello World").operation()); // Hello World
		System.out.println(new ParenthesesDecorator(new ConcreteComponent("Hello World")).operation()); // (Hello World)
		
		System.out.println(new BraceDecorator(new ConcreteComponent("Hello World")).operation()); // {Hello World}	
		System.out.println(new BraceDecorator(new ParenthesesDecorator(new ConcreteComponent("Hello World"))).operation()); // {(Hello World)}

	}

}
