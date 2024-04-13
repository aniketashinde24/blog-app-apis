package stack;

public class FixedStack implements Stack {
	
	Customer [] cust = new Customer[STACK_SIZE];
	int top = 0;
	
	public void push(Customer c) {
		if(top == STACK_SIZE) {
			System.out.println("STACK IS FULL");
		}
		else {
			cust[top] = c;
			top++;
			System.out.println("Data Pushed");
		}
	}

	@Override
	public void pop() {
		if(top == 0) {
			System.out.println("STACK IS EMPTY");
		}
		else {
			System.out.println(cust[--top]);
			System.out.println("Data Poped");
		}
	}

	@Override
	public void display() {
		for(Customer s : cust) {
			System.out.println("---------------------------------------------------------------------------------------------");
			System.out.println(s);
		}
		
	}
}
