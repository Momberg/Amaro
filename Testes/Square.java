import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Square {
	
	File file;
	int[][] matrix;
	int size;
	
	public int[][] readFile(String[] args) {
		if(0 < args.length) {
			file = new File(args[0]);
			this.size = sizeOfSquare(file);
			int col = 0;
			int row = 0;
			matrix = new int[size][size];
			try {
				Scanner scan = new Scanner(file);
				while(scan.hasNextLine()) {
					String line = scan.nextLine();
					Scanner item = new Scanner(line);
					col = 0;
					while(item.hasNextInt()) {
						matrix[row][col] = item.nextInt();
						col++;
					}
					row++;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return matrix;
		} else if(args.length > 1){
			System.out.println("O programa aceita ler apenas um arquivo por vez");
		} else {
			System.out.println("Digite o nome do arquivo a ser lido");
		}
		return matrix;
	}
	
	public boolean isAPerfectSquare(int[][] matrix) {
		int[] sum = new int[this.size];
		int count = 0;
		int n = 0;
		for (int[] line : matrix) {
			n = 0;
			for (int item : line) {
				n = n + item;
			}
			sum[count] = n;
			count++;
		}
		if(sum[0] != sum[1]) {
			return false;
		}
		return true;
	}
	
	private int sizeOfSquare(File file) {
		int count = 0;
		double sqr = 0;
		Scanner scan;
		try {
			scan = new Scanner(file);
			while(scan.hasNextInt()) {
				count++;
				scan.nextInt();
			}
			sqr = Math.sqrt(Double.valueOf(count));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return (int) sqr;
	}

}
