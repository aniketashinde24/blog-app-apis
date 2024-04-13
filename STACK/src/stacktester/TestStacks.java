
package stacktester;

import java.util.Scanner;

import stack.Customer;
import stack.FixedStack;
import stack.GrowableStack;
import stack.Stack;

public class TestStacks {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Stack S = null;
		int choice;
		int id;
		String name,address;
		
		do {
			System.out.println("1.CHOOSE FIXED STACK \n2.CHOOSE GROWABLE STACK \n3.PUSH IN STACK \n4.POP FROM STACK \n5.DISPLAY");
			System.out.println();
			System.out.println("Enter the choice from the Menu ");
			choice = scan.nextInt();
			switch(choice) {
				case 1:
					S = new FixedStack();
					System.out.println("FixedStack is Active Now");
					System.out.println();
					break;
				case 2:
					S = new GrowableStack();
					System.out.println("Growable Stack is Active Now ");
					System.out.println();
					
					break;
				case 3:
					if(S instanceof GrowableStack || S instanceof FixedStack) {
					System.out.println("Enter the id ");
					id = scan.nextInt();
					System.out.println("Enter the name ");
					name = scan.next();
					System.out.println("Enter the Address ");
					address = scan.next();
					
					Customer c = new Customer(id,name,address);
					S.push(c);
					System.out.println();
					}
					else {
						System.out.println("Please Select Stack Type ");
					}
					break;
				case 4:
					S.pop();
					break;
				case 5:
					S.display();
					break;
				case 0:
					System.out.println("EXITING....");
					break;
					
			}	
			
		}while(choice!=0);
		scan.close();
		
	}
}
