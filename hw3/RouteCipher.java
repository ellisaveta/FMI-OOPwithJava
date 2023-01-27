package Zad1;

public class RouteCipher {
    private int key;

    public RouteCipher(int key) {
        setKey(key);
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    private void topRight(boolean encrypt, char[][] a, StringBuilder sb, int x1, int y1, int x2, int y2) {
        int i = 0;
        int j = 0;
        // print values in the column.
        for(j = y2; j >= y1; j--) {
            if(encrypt) {
                sb.append(a[j][x2]);
            }
            else {
                a[j][x2] = sb.charAt(0);
                sb.deleteCharAt(0);
            }
        }

        // print values in the row.
        for(i = x2-1; i>=x1; i--) {
            if(encrypt) {
                sb.append(a[y1][i]);
            }
            else {
                a[y1][i] = sb.charAt(0);
                sb.deleteCharAt(0);
            }
        }

        // see if more layers need to be printed.
        if(x2-x1 > 0) {
            // if yes recursively call the function to
            // print the bottom left of the sub matrix.
            bottomLeft(encrypt, a, sb, x1, y1+1, x2-1, y2);
        }
    }

    // function to print the bottom-left peel of the matrix and
 // recursively call the print top-right on the submatrix.
    private void bottomLeft(boolean encrypt, char[][] a, StringBuilder sb, int x1, int y1, int x2, int y2) {
        int i = 0;
        int j = 0;

        // print the values in the col in reverse order.
        for(j = y1; j <= y2; j++) {
            if(encrypt) {
                sb.append(a[j][x1]);
            }
            else {
                a[j][x1] = sb.charAt(0);
                sb.deleteCharAt(0);
            }
        }

        // print the values in the row in reverse order.
        for(i = x1+1; i<=x2; i++) {
            if(encrypt) {
                sb.append(a[y2][i]);
            }
            else {
                a[y2][i] = sb.charAt(0);
                sb.deleteCharAt(0);
            }
        }

        // see if more layers need to be printed.
        if(x2-x1 > 0) {
            // if yes recursively call the function to
            // print the top right of the sub matrix.
            topRight(encrypt, a, sb, x1+1, y1, x2, y2-1);
        }
    }

    public String encrypt(String plaintext)
    {
        String resultString;
        StringBuilder sb = new StringBuilder();
        char[] tempArray = plaintext.toCharArray();
        for(int i=0; i<tempArray.length; ++i)
        {
            if((tempArray[i] >= 'A' && tempArray[i] <= 'Z') ||
                    (tempArray[i] >= 'a' && tempArray[i] <= 'z'))
            {
                sb.append(tempArray[i]);
            }
        }
        char[] plainTextChars = sb.toString().toCharArray();
        boolean positive = true;
        int columns = key;
        if(key == 0)
        {
            System.out.println("Invalid key!");
            System.exit(0);
        }
        if(key < 0)
        {
            positive = false;
            columns = -columns;
        }
        int rows = plainTextChars.length / columns;
        if(plainTextChars.length % columns != 0)
        {
            rows++;
        }
        char[][] grid = new char[rows][columns];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(index < plainTextChars.length) {
                    grid[i][j] = plainTextChars[index++];
                }
                else {
                    grid[i][j] = 'X';
                }
            }
        }
        sb.delete(0, sb.length());
        if(positive)
        {
            bottomLeft(true, grid, sb, 0, 0, columns - 1, rows - 1);
        }
        else
        {
            topRight(true, grid, sb, 0, 0, columns - 1, rows - 1);
        }
        resultString = sb.toString();
        return resultString;
    }

    public String decrypt(String ciphertext)
    {
        String resultString;
        char[] cipherTextChars = new char[ciphertext.length()];
        StringBuilder sb = new StringBuilder(ciphertext);
        boolean positive = true;
        int columns = key;
        if(key == 0)
        {
            System.out.println("Invalid key!");
            System.exit(0);
        }
        else if(key < 0)
        {
            columns = -columns;
            positive = false;
        }
        int rows = cipherTextChars.length / columns;
        char[][] grid = new char[rows][columns];

        if(positive) {
            bottomLeft(false, grid, sb, 0, 0, columns - 1, rows - 1);
        }

        else {
            topRight(false, grid, sb, 0, 0, columns - 1, rows - 1);
        }
        sb.delete(0, sb.length());
        for(int i = 0; i < rows; ++i)
        {
            for(int j = 0; j <columns; ++j)
            {
                sb.append(grid[i][j]);
            }
        }
        resultString = sb.toString();
        return resultString;
    }

    @Override
    public String toString() {
        return "Key is: " + getKey();
    }
}
