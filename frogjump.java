
import java.util.Collections;
import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;
public class frogjump{
	static void getStatUpdate(int position, int score, int isi){
		System.out.print("\033[H\033[2J");  
		System.out.flush();  
		System.out.println("Position\t\t: "+position);
		System.out.println("Score\t\t\t: "+score);
		System.out.println("Content of the Box\t: "+isi);
	}
	public static void main(String[] args) {
		boolean play = true;
	do{
		short max = 500;
		Scanner myObj = new Scanner(System.in);
		//Declares the array that will be the index of the box.
		Integer[] arr = new Integer[max];
		//Declares box
		int[] box = new int[max];
		
		short coin1 = 1;
		short coin2 = 2;
		short coin3 = 3;
		short monst1 = -1;
		short monst2 = -2;
		short monst3 = -3;
		//Percentage of Coin/Monster amount to place
		
		double persenCoin1=0;
		double persenCoin2=0;
		double persenCoin3=0;
		double persenMonst1=0;
		double persenMonst2=0;
		double persenMonst3=0;
		short mode = 2;
		
		//Choose mode
		System.out.print("1. Easy\n2. Intermediate\n3. Hard\nChoose mode(ex. 2) : ");
		mode = myObj.nextShort();
		
		//Coin and Monster replenishment according to the selected mode
		if(mode == 1){
			persenCoin1=25/100.0;
			persenCoin2=18/100.0;
			persenCoin3=7/100.0;
			persenMonst1=17/100.0;
			persenMonst2=8/100.0;
			persenMonst3=5/100.0;
		}else if(mode == 2){
			persenCoin1=18/100.0;
			persenCoin2=12/100.0;
			persenCoin3=5/100.0;
			persenMonst1=18/100.0;
			persenMonst2=12/100.0;
			persenMonst3=5/100.0;
		}else if(mode == 3){
			persenCoin1=17/100.0;
			persenCoin2=8/100.0;
			persenCoin3=5/100.0;
			persenMonst1=20/100.0;
			persenMonst2=15/100.0;
			persenMonst3=5/100.0;
		}else {
			System.out.println("Input correctly!");
		}
		
		//Assignment nilai pada array.
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		//Shuffle Array
		/*The goal later is to make it easier to take the value that will be used as an index in the box according to the percentage*/
		Collections.shuffle(Arrays.asList(arr));
		
		/*put monsters and coins in the box with the index that has been randomized earlier according to their respective sizes*/
		int i = 0;
		for(; i<(max*persenCoin1); i++){
			box[(arr[i])] = coin1;
			//a+=1;
		}
		for(; i<(max*(persenCoin1+persenCoin2)); i++){
			box[(arr[i])] = coin2;
			//b+=1;
		}
		for(; i<(max*(persenCoin1+persenCoin2+persenCoin3)); i++) {
			box[(arr[i])] = coin3;
			//c+=1;
		}
		for(; i<(max*(persenCoin1+persenCoin2+persenCoin3+persenMonst1)); i++) {
			box[(arr[i])] = monst1;
			//d+=1;
		}
		for(; i<(max*(persenCoin1+persenCoin2+persenCoin3+persenMonst1+persenMonst2)); i++) {
			box[(arr[i])] = monst2;
			//e+=1;
		}
		for(; i<(max*(persenCoin1+persenCoin2+persenCoin3+persenMonst1+persenMonst2+persenMonst3)); i++) {
			box[(arr[i])] = monst3;
		}
		
		short position = 0;
		box[position] = 0;
		int score = 100;
		frogjump.getStatUpdate(position, score, box[position]);
		boolean start = true;
		while(start)
		{
			short step = 0;
			
			//++++++=
			//box[position] = 0;
			try{
				System.out.print("Step : ");
				step = myObj.nextShort();
			} catch(InputMismatchException ex) {
				System.out.println("---Wrong input!---");
			} 
			myObj.nextLine(); //clear buffer line
			
			if(step > 2 || step < -2){
				System.out.println("Frogs can only jump 1 or 2 steps! Can't" + step +" Step!");
				continue;
			}else{
				position += step;
				//Stop when position is at 499 or above
				if(position >= 499){
					position=499;
					score+=(10*box[position]);
					frogjump.getStatUpdate(position, score, box[position]);
					System.out.println("The position has reached the limit! Game over!");
					System.out.println("---Final Results---");
					if(score >= 1500){
						System.out.println("Excellent!");
					}else if(score >= 500 && score < 1500){
						System.out.println("Good!");
					}else if(score < 500){
						System.out.println("Bad!");	
					}
					break;
				}
				//Putting initial constraints on box number 0
				if(position<0){
					position=0;
					frogjump.getStatUpdate(position, score, box[position]);
					System.out.println("The position is stuck at 0 and there is no going back!");
					continue;
				}else if (position>=0){
					score+=(10*box[position]);
					if(score<0){
						frogjump.getStatUpdate(position, score, box[position]);
						System.out.println("Your score is minus! Game over!");
						System.out.println("---Final results---");
						System.out.println("Bad!");	
						break;
					}
					frogjump.getStatUpdate(position, score, box[position]);
					continue;
				}
			}
		}
		//Ask the user if they want to play again
		System.out.print("Want to play again(True/False)? ");
		play = myObj.nextBoolean();
		if(play == true){
			start = true;
		}
	}while(play == true);
	//Exit
	System.out.println("Exit game...");
	}
}

