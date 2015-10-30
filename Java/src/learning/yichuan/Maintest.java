package learning.yichuan;

import java.util.Random;
import java.util.Scanner;

import javax.print.attribute.standard.Chromaticity;

public class Maintest {
	private static Scanner cin = new Scanner(System.in);
	public static Chrom[] popcurrent = new Chrom[4];
	public static Chrom[] popnext = new Chrom[4];

	public static void main(String[] args) {
		int num;
		int i, j, l, Max, k=0;
		Max = 0;
		System.out.println("Welcome to the Genetic Algorithm！");
		System.out.println(
				"The Algorithm is based on the function y = -x^2 + 5 to find the maximum value of the function.");
		while (true) {
			System.out.println("Please Enter the no. of iterations：");
			num = cin.nextInt();
			if (num > 1)
				break;
		}
		evpop();
		Max=popcurrent[0].fit;
		for(i=0;i<num;i++){
			System.out.println("ni="+i);
			for(j=0;j<4;j++){
				popnext[j]=popcurrent[j];
			}
		}
		pickchroms();
		crossover();
		mutation();
		for(j=0;j<4;j++){
			popcurrent[j]=popnext[j];
		}
		for(l=0;l<3;l++)
		{
			if (popcurrent[l].fit > Max) {
				Max = popcurrent[l].fit;
				k = x(popcurrent[l]);
			}
		}
		System.out.printf("当x等于 %d时，函数得到最大值为： %d ",k,Max);System.out.println("Press any key to end !");
	}

	public static void evpop() {
		int i, j, value1;
		int random;
		double sum = 0;
		for (j = 0; j < 4; j++) {
			for (i = 0; i < 6; i++) {
				random = (int) (Math.random() * Integer.MAX_VALUE);
				random = random % 2;
				popcurrent[j]=new Chrom();
				popcurrent[j].bit=new int[6];
				popcurrent[j].bit[i] = random;
			}
			value1 = x(popcurrent[j]);
			popcurrent[j].fit = y(value1);
			sum += popcurrent[j].fit;
			System.out.println("popcurrent[" + j + "]=" + popcurrent[j].bit[5] + popcurrent[j].bit[4]
					+ popcurrent[j].bit[3] + popcurrent[j].bit[2] + popcurrent[j].bit[1] + popcurrent[j].bit[0]
					+ "  value=" + value1 + "f  fitness=" + popcurrent[j].fit);
		}
		for (j = 0; j < 4; j++) {
			popcurrent[j].rfit = popcurrent[j].fit / sum;
			popcurrent[j].cfit = 0;
		}
	}

	public static int x(Chrom popcurrent) {
		int z;
		z = (popcurrent.bit[0]) + (popcurrent.bit[1] * 2) + (popcurrent.bit[2] * 4) + (popcurrent.bit[3] * 8)
				+ popcurrent.bit[4] * 16;
		if (popcurrent.bit[5] == 1)
			z = z * (-1);
		return z;
	}

	public static int y(int x) {
		int y;
		y = -1 * (x * x) + 5;
		return y;
	}

	public static void pickChroms_new() {
		int men;
		int i, j;
		double p;
		double sum = 0.0;
		for (men = 0; men < 4; men++) {
			sum += popnext[men].fit;
		}
		for (men = 0; men < 4; men++) {
			popnext[men].rfit = popnext[men].fit / sum;
		}
		popcurrent[0].cfit = popcurrent[0].rfit;
		for (men = 1; men < 4; men++) {
			popnext[men].cfit = popnext[men - 1].cfit + popnext[men].rfit;
		}
		for (i = 0; i < 4; i++) {
			p = new Random().nextDouble() % 10;
			p = p / 10;
			if (p < popnext[0].cfit) {
				popcurrent[i] = popnext[0];
			} else {
				for (j = 0; j < 4; j++) {
					if (popnext[j].cfit <= p && p < popnext[j + 1].cfit) {
						popcurrent[i] = popnext[j + 1];
					}
				}
			}
		}
		for (i = 0; i < 4; i++) {
			popnext[i] = popcurrent[i];
		}
	}

	public static void pickchroms() {
		int i, j;
		Chrom temp;
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3 - i; j++) {
				if (popnext[j + 1].fit > popnext[j].fit) {
					temp = popnext[j + 1];
					popnext[j + 1] = popnext[j];
					popnext[j] = temp;
				}
			}
		}
		for (i = 0; i < 4; i++) {
			System.out.println("Sorting:popnext[" + i + "] fitness=" + popnext[i].fit);
		}
	}

	public static double r8_uniform_ab(double a, double b, int seed) {
		{
			int i4_huge = Integer.MAX_VALUE;
			int k;
			double value;
			if (seed == 0) {
				System.out.println("R8_UNIFORM_AB - Fatal error!");
				System.out.println("Input value of SEED =0");
				System.exit(-1);
			}
			k = seed / 127773;
			seed = 16807 * (seed - k * 127773) - k * 2836;

			if (seed < 0) {
				seed = seed + i4_huge;
			}
			value = (double) (seed) * 4.656612875E-10;
			value = a + (b - a) * value;
			return value;
		}
	}

	public static void crossover() {
		int random;
		int i;
		random = (int) (Math.random() * Integer.MIN_VALUE);
		random = (random % 5) + 1;
		for (i = 0; i < random; i++) {
			popnext[2].bit[i] = popnext[0].bit[i];
			popnext[3].bit[i] = popnext[1].bit[i];
		}
		for (i = 0; i < 6; i++) {
			popnext[2].bit[i] = popnext[1].bit[i];
			popnext[3].bit[i] = popnext[0].bit[1];
		}
		for (i = 0; i < 4; i++) {
			popnext[i].fit = y(x(popnext[i]));
		}
		for (i = 0; i < 4; i++) {
			System.out.println("CrossOver popnext[" + i + "]=" + popnext[i].bit[5] + popnext[i].bit[4]
					+ popnext[i].bit[3] + popnext[i].bit[2] + popnext[i].bit[1] + popnext[i].bit[0] + "  value="
					+ x(popnext[i]) + "   fitness=" + popnext[i].fit);
		}
	}

	public static void mutation() {
		int random;
		int row, col, value;
		random = (int) (Math.random()) % 50;
		if (random == 25) {
			col = new Random().nextInt() % 6;
			row = new Random().nextInt() % 4;
			if (popnext[row].bit[col] == 0) {
				popnext[row].bit[col] = 1;
			} else if (popnext[row].bit[col] == 1) {
				popnext[row].bit[col] = 0;
			}
			popnext[row].fit = y(x(popnext[row]));
			value = x(popnext[row]);
			System.out.println("Mutation occured in popnext[" + row + "] bit[" + col + "]:=" + popnext[row].bit[5]
					+ popnext[row].bit[4] + popnext[row].bit[3] + popnext[row].bit[2] + popnext[row].bit[1]
					+ popnext[row].bit[0] + "  value=" + value + "  fitness=" + popnext[row].fit);
		}
	}
}

class Chrom {
	int bit[];
	int fit;
	double rfit;
	double cfit;

	public Chrom() {
		bit = new int[6];
	}
}