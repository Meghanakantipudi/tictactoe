package com.journaldev.first;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.Math;
import java.util.*;
/**
 * Servlet implementation class TicTacToe
 */
@WebServlet("/TicTacToe")
public class TicTacToe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//public static int[] ex = new int[9];
	List<Integer> ex = new ArrayList<>();
	List<Integer> tp = new ArrayList<>();
	public static int len = 0;
	public static String[][] arr = new String[3][3];
	public static int cnt = 1;
	public static int is_winner = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicTacToe() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getRandomWithExclusion(int min, int max) {
    	//List<Integer> tp = new ArrayList<>();
    	//tp = ex;
    	Collections.sort(ex); 
    	double rnd = Math.random(); 
    	int range = max - min + 1 - ex.size(); 
        int random = min + (int)(Math.random() * range);
        for (int i = 0; i < ex.size(); i++)  {
            if (random < ex.get(i)) {
                break;
            }
            random++;
        }
        return random;
    }
    public void print_array(){
    	for(int i = 0; i < 3; i++){
    		for(int j = 0; j < 3; j++){
    			System.out.print(arr[i][j]);
    			System.out.print(" ");
    		}
    		System.out.println();
    	}
    }
    public int[] get_indexes(int n){
    	int[] idx = new int[2];
    	if(n%3 != 0){
    		idx[0] = n/3;
    		idx[1] = (n%3) - 1;
    	}
    	else{
    		idx[0] = n/3 - 1;
    		idx[1] = 2;
    	}
    	return idx;
    }
    public int checkif_winner(String s){
    	if(arr[0][0] == s && arr[0][1] == s && arr[0][2] == s){
    		return 1;
    	}
    	if(arr[0][0] == s && arr[1][0] == s && arr[2][0] == s){
    		return 1;
    	}
    	if(arr[0][0] == s && arr[1][1] == s && arr[2][2] == s){
    		return 1;
    	}
    	if(arr[0][1] == s && arr[1][1] == s && arr[2][1] == s){
    		return 1;
    	}
    	if(arr[0][2] == s && arr[1][2] == s && arr[2][2] == s){
    		return 1;
    	}
    	if(arr[1][0] == s && arr[1][1] == s && arr[1][2] == s){
    		return 1;
    	}
    	if(arr[2][0] == s && arr[2][1] == s && arr[2][2] == s){
    		return 1;
    	}
    	if(arr[2][0] == s && arr[1][1] == s && arr[0][2] == s){
    		return 1;
    	}
    	return 0;
    }
    public int fun(int x, int y){
    	int n = x*3 + y+1;
		if(ex.size() < 8){
			if(ex.contains(n)){
				return 1;
			}
			ex.add(n);
			tp.add(n);
			len++;
		}
		else{
			return 0;
		}
		return 1;
    }
    public void append_idxs(String s){
    	if(arr[0][0] == s && arr[0][1] == s && (arr[0][2] != "X" && arr[0][2] != "O") ){
    		if(fun(0, 2) == 0){
    			return;
    		}
    	}
    	if(arr[0][0] == s && (arr[0][1] != "X" && arr[0][1] != "O") && arr[0][2] == s){
    		if(fun(0, 1) == 0){
    			return;
    		}
    	}
    	if((arr[0][0] != "X" && arr[0][0] != "O") && arr[0][1] == s && arr[0][2] == s){
    		if(fun(0, 0) == 0){
    			return;
    		}
    	}
    	if(arr[0][0] == s && arr[1][0] == s && (arr[2][0] != "X" && arr[2][0] != "O")){
    		if(fun(2, 0) == 0){
    			return;
    		}
    	}
    	if(arr[0][0] == s && arr[2][0] == s && (arr[1][0] != "X" && arr[1][0] != "O")){
    		if(fun(1, 0) == 0){
    			return;
    		}
    	}
    	if((arr[0][0] != "X" && arr[0][0] != "O") && arr[1][0] == s && arr[2][0] == s){
    		if(fun(0, 0) == 0){
    			return;
    		}
    	}
    	if((arr[0][0] != "X" && arr[0][0] != "O") && arr[1][1] == s && arr[2][2] == s){
    		if(fun(0, 0) == 0){
    			return;
    		}
    	}
    	if(arr[0][0] == s && (arr[1][1] != "X" && arr[1][1] != "O") && arr[2][2] == s){
    		if(fun(1, 1) == 0){
    			return;
    		}
    	}
    	if(arr[0][0] == s && arr[1][1] == s && (arr[2][2] != "X" && arr[2][2] != "O")){
    		if(fun(2, 2) == 0){
    			return;
    		}
    	}
    	if(arr[0][1] == s && arr[1][1] == s && (arr[2][1] != "X" && arr[2][1] != "O")){
    		if(fun(2, 1) == 0){
    			return;
    		}
    	}
    	if(arr[0][1] == s && (arr[1][1] != "X" && arr[1][1] != "O") && arr[2][1] == s){
    		if(fun(1, 1) == 0){
    			return;
    		}
    	}
    	if((arr[0][1] != "X" && arr[0][1] != "O") && arr[1][1] == s && arr[2][1] == s){
    		if(fun(0, 1) == 0){
    			return;
    		}
    	}
    	if((arr[0][2] != "X" && arr[0][2] != "O") && arr[1][2] == s && arr[2][2] == s){
    		if(fun(0, 2) == 0){
    			return;
    		}
    	}
    	if(arr[0][2] == s && (arr[1][2] != "X" && arr[1][2] != "O") && arr[2][2] == s){
    		if(fun(1, 2) == 0){
    			return;
    		}
    	}
    	if(arr[0][2] == s && arr[1][2] == s && (arr[2][2] != "X" && arr[2][2] != "O")){
    		if(fun(2, 2) == 0){
    			return;
    		}
    	}
    	if(arr[1][0] == s && arr[1][1] == s && (arr[1][2] != "X" && arr[1][2] != "O")){
    		if(fun(1, 2) == 0){
    			return;
    		}
    	}
    	if(arr[1][0] == s && (arr[1][1] != "X" && arr[1][1] != "O") && arr[1][2] == s){
    		if(fun(1, 1) == 0){
    			return;
    		}
    	}
    	if((arr[1][0] != "X" && arr[1][0] != "O") && arr[1][1] == s && arr[1][2] == s){
    		if(fun(1, 0) == 0){
    			return;
    		}
    	}
    	if(arr[2][0] == s && arr[2][1] == s && (arr[2][2] != "X" && arr[2][2] != "O")){
    		if(fun(2, 2) == 0){
    			return;
    		}
    	}
    	if((arr[2][0] != "X" && arr[2][0] != "O") && arr[2][1] == s && arr[2][2] == s){
    		if(fun(2, 0) == 0){
    			return;
    		}
    	}
    	if(arr[2][0] == s && (arr[2][1] != "X" && arr[2][1] != "O") && arr[2][2] == s){
    		if(fun(2, 1) == 0){
    			return;
    		}
    	}
    	if((arr[2][0] != "X" && arr[2][0] != "O") && arr[1][1] == s && arr[0][2] == s){
    		if(fun(2, 0) == 0){
    			return;
    		}
    	}
    	if(arr[2][0] == s && (arr[1][1] != "X" && arr[1][1] != "O") && arr[0][2] == s){
    		if(fun(1, 1) == 0){
    			return;
    		}
    	}
    	if(arr[2][0] == s && arr[1][1] == s && (arr[0][2] != "X" && arr[0][2] != "O")){
    		if(fun(0, 2) == 0){
    			return;
    		}
    	}
    }
    public void remove_indexes(int n){
    	for(int i = 0; i < tp.size(); i++){
    		System.out.println(ex);
    		System.out.println(i);
    		ex.remove(tp.get(i));
    		len--;
    	}
    	tp.clear();
    	return;
    }
    public void human_comp(){
    	Scanner input = new Scanner(System.in);
    	System.out.println("Please Enter a val in range of 1 to 9 and which are not occupied by x or o");
    	int num = input.nextInt();
    	int[] idx = new int[2];
    	idx = get_indexes(num);
    	arr[idx[0]][idx[1]] = "X";
    	ex.add(num);
    	len++;
    	if(cnt >= 3){
    		if(checkif_winner("X") == 1){
    			is_winner = 1;
    			print_array();
    			System.out.println("Congrations! You are the winner.");
    		}
    	}
    }
    public void compu_comp(){
    	int tp_idx = ex.size();
    	System.out.println(ex);
    	if(cnt >= 2){
    		append_idxs("O");
    		append_idxs("X");
    	}
    	int num = getRandomWithExclusion(1, 9);
    	if(cnt >= 2 && tp_idx != ex.size())
    		remove_indexes(tp_idx);
    	ex.add(num);
    	len++;
    	int[] idx = new int[2];
    	idx = get_indexes(num);
    	arr[idx[0]][idx[1]] = "O";
    	if(cnt >= 3){
    		if(checkif_winner("O") == 1){
    			is_winner = 1;
    			print_array();
    			System.out.println("Computer is the Winner.");
    		}
    	}
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*int[] exc = { 2, 5, 11 };
		int val = getRandomWithExclusion(1, 6, exc);
		System.out.println(val);*/
		//response.getWriter().append("Served at: " + val).append(request.getContextPath());
		ex.clear();
		tp.clear();
		len = 0;
		cnt = 1;
		is_winner = 0;
		Scanner input = new Scanner(System.in);
		System.out.println("Select Your Choice: X or O");
		String human_val = input.nextLine();
		int temp = 1;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				arr[i][j] = Integer.toString(temp);
				temp++;
			}
		}
		print_array();
		for(int i = 1; i <= 4 && is_winner == 0; i++){
			human_comp();
			if(is_winner == 1){
				break;
			}
			compu_comp();
			if(is_winner == 1){
				break;
			}
			print_array();
			cnt++;
		}
		if(is_winner == 0){
			human_comp();
		}
		if(is_winner == 0){
			print_array();
			System.out.println("It is Draw");
		}
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
