package stack;

public class GrowableStack implements Stack {

	private int size = STACK_SIZE;
	private Customer[] cust = new Customer[size];
	private int top=0 ;
	
	
	@Override
	public void push(Customer c) {
		if(top == size ) {
			
			Customer[] temp  = new Customer[size*2];
			for(int i =0 ;i<size ;i++) {
				temp[i] = cust[i];
			
			}
			cust = temp;
			
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
			System.out.println("STACK IS EMPTY ");
		}
		else {
			System.out.println(cust[--top]);
			System.out.println("Data Poped");
		}
	}

	@Override
	public void display() {
		for(Customer c : cust) {
			System.out.println("----------------------------------------------------------------------");
			System.out.println(c);
		}
	}

}
