import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.la4j.Matrices;
import org.la4j.Matrix;
import org.la4j.matrix.dense.Basic2DMatrix;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class ThirdPracticalTask {
    List<Double> X;
    List<Double> Y;

    double[][] firstMatrix;
    double[][] secondMatrix;
    double[][] thirdMatrix;
    double[][] forthMatrix;


    public ThirdPracticalTask() throws Exception {
        X = Stream.generate(()-> new Random().nextDouble()).limit(100).sorted().toList();
        Y = Stream.generate(()-> new Random().nextDouble()).limit(100).sorted().toList();

        double[][] matrixVerticalX = new double[100][2]; /*Столбцы - 1, Строки - 2;*/
        double[][] matrixHorizontalX = new double[2][100];
        double[][] matrixVerticalY = new double[100][1];
        for (int i=0;i<100;i++){
            matrixVerticalX[i][0]=X.get(0);
            matrixVerticalX[i][1]=1;

            matrixHorizontalX[0][i] = X.get(i);
            matrixHorizontalX[1][i] = 1;

            matrixVerticalY[i] = new double[]{Y.get(i)};
        }


        firstMatrix = multipleMatrix(matrixHorizontalX,matrixVerticalX);
        secondMatrix = multipleMatrix(matrixHorizontalX,matrixVerticalY);
        System.out.println(1/matrixDeterminant(firstMatrix));
        thirdMatrix = multipleMatrixByValue(TMatrix(firstMatrix),1/matrixDeterminant(firstMatrix));
        forthMatrix = multipleMatrix(thirdMatrix,secondMatrix);
        System.out.println(Arrays.deepToString(forthMatrix));

        System.out.println(Arrays.deepToString(firstMatrix));
        System.out.println(Arrays.deepToString(secondMatrix));
        System.out.println(Arrays.deepToString(thirdMatrix));



    }

    public static double[][] multipleMatrix(double[][] matrix1, double[][] matrix2) throws Exception {

        if (matrix1[0].length!=matrix2.length) throw new Exception("Число столбцов первого массива не равно числу строк второго массива");
        double[][] result = new double[matrix1.length][matrix2[0].length];

        for (int i=0;i<result.length;i++){
            for (int j=0;j<result[0].length;j++){
                int matrixLine1 = i;
                int matrixLine2 = j;
                result[i][j] = IntStream.range(0,matrix2.length).mapToDouble(x->matrix1[matrixLine1][x]*matrix2[x][matrixLine2]).sum();
            }
        }
        return result;
    }

    /*только для матриц 2*2*/
    public static double matrixDeterminant(double[][] matrix){
        matrix[0][0] =Double.parseDouble(String.format("%.2f",matrix[0][0]).replace(",","."));
        matrix[0][1] = Double.parseDouble(String.format("%.2f",matrix[0][1]).replace(",","."));
        matrix[1][0] = Double.parseDouble(String.format("%.2f",matrix[1][0]).replace(",","."));
        matrix[1][1] = Double.parseDouble(String.format("%.2f",matrix[1][1]).replace(",","."));
        return matrix[0][0]*matrix[1][1]-matrix[0][1]*matrix[1][0];
    }

    public static double[][] TMatrix(double[][] matrix){
        double[][] result = new double[2][2];
        double[][] M = new double[2][2];

        M[0][0] = matrix[1][1];
        M[0][1] = -matrix[1][0];
        M[1][0] = -matrix[0][1];
        M[1][1] = matrix[0][0];

        result[0][0] = M[0][0];
        result[1][1] = M[1][1];
        result[1][0] = M[0][1];
        result[0][1] = M[1][0];

        return result;
    }

    public static double[][] multipleMatrixByValue(double[][] matrix, double value){
        System.out.println(value);
        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){
                matrix[i][j]*=value;
            }
        }
        return matrix;
    }
}
